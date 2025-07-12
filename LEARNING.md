# 基础篇：IOC  

## 最简单的bean容器  

定义一个 Map，然后一个 register（也就是put）和一个getBean（也就是get）



## BeanDefinition和BeanDefinitionRegistry

我参考原文档中的这张图，从上往下依次构建：

![bean-definition-and-bean-definition-registry](./LEARNING.assets/bean-definition-and-bean-definition-registry.png)  

- 相比最开始的一个map，这里改为两个新Map分别用来存储 BeanDefinition 和 Bean 的单例
- 将 Bean 相关信息存储进 BeanDefinitions 中后，当需要这个实例时，会使用 `getSingleton()` 方法，进入第二个Map中查找是否有，没有就通过 `beanClass.newInstance()` 方法由反射创建，而后加入Map并返回

## Bean 实例化策略 InstantiationStrategy

上一节的实例方法 `beanClass.newInstance()` 只适用于无参构造，所以要优化，抽象出实例化策略接口 `InstantiationStartegy`  

两种 `newInstance()`

| 特性             | `Class.newInstance()`                                     | `Constructor.newInstance()`                       |
| ---------------- | --------------------------------------------------------- | ------------------------------------------------- |
| 💡 来源           | `Class` 类的方法                                          | `Constructor` 类的方法                            |
| 🎯 调用的构造方法 | 只能调用 **无参构造器**                                   | 可以调用 **任意参数构造器**                       |
| ⚠️ 异常类型       | 抛出 `InstantiationException` 和 `IllegalAccessException` | 抛出 `InvocationTargetException`                  |
| 🛠 灵活性         | 较低，只适用于无参构造函数                                | 高，可选具体构造器并传参                          |
| 🔒 可见性要求     | 构造器必须是 public                                       | 可通过 `setAccessible(true)` 访问非 public 构造器 |

![instantiation-strategy](./LEARNING.assets/instantiation-strategy.png)

- SimpleInstantiationStrategy，使用bean的构造函数来实例化
- CglibSubclassingInstantiationStrategy，使用CGLIB动态生成子类
  - 当 BeanDefinition中包含 `lookup-method` 或 `replace-method` 时，Spring 无法直接通过构造器或工厂方法实例化对象，而是需要通过 **CGLIB 动态生成子类**，覆盖指定方法以实现注入逻辑。
  - 目前 BeanDefinition 中只有 Class 信息，所以这个实例化方法暂时用不到


新建了以上的三个文件，并修改了 AbstractAutowireCapableBeanFactory，在其中加入了策略的默认值以及调用。  



## 为 Bean 填充属性  

> 分支 04

- POM中添加了hutool  
- BeanDefinition 中添加了 propertyValues 这个属性  
- 定义了 propertyValue 包含 name 和 value  
- 定义了 propertyValues 为数组类型的pv
- 在 AbstractAutowireCapableBeanFactory中bean后添加了applyPropertyValue  

具体填充属性的方法就是用反射，hutool中有BeanUtil这个东西

具体变化可以通过 diff <branch1> <branch2> 来对比前后，为了方便查找，每次都会有序号

## 为 Bean 注入 Bean

> 分支05  

增加BeanReference类，包装一个bean对另一个bean的引用。实例化beanA后填充属性时，若PropertyValue#value为BeanReference，引用beanB，则先去实例化beanB。
由于不想增加代码的复杂度提高理解难度，暂时不支持循环依赖，后面会在高级篇中解决该问题。

## 资源和资源加载器  

> 分支06  

这一节应该是为了下一节的xml做准备  

- 资源  
  - ClassPathResource:

    - ```java
      InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.path);
      ```
  
  - FileSystemResource：
  
    - ```java
    Path path = new File(this.filePath).toPath();
    return Files.newInputStream(path);
  
  - UrlResource
  
    - ```java
      return urlConnection.getInputStream();
      ```
  
- 资源加载器

  - ResourceLoader接口则是资源查找定位策略的抽象，
  - DefaultResourceLoader是其默认实现类

## 在 xml 文件中定义 bean

> 分支07  

要实现“在 xml 中定义 bean”，需要这几个东西：

- 资源加载器 —— 上一节实现的  
- 抽象出读取bean定义信息的接口（BeanDefinitionReader），同时承担读取信息后的注册功能。以及对应的抽象实现类（AbstractBeanDefinitionReader）以及抽象实现类的实现类（XmlBeanDefinitionReader）
  - **当前**从xml读取的是String类型，所以属性也就是String或引用其他bean，后续会有类型转换器  
- 调整BeanFactory结构，相比于此前的内容新增了如下框内的东西，

![image-20250712102638734](./LEARNING.assets/image-20250712102638734.png)

其中：

1. `BeanFactory`

   - 提供最简单的 Bean 获取方法，如 `getBean(String name)`

   - 延迟初始化 bean，适合轻量级场景

   - 实际开发中较少直接使用，通常由 `ApplicationContext` 封装


2. `ListableBeanFactory`

   - 允许根据类型、注解等条件获取多个 bean，例如 `getBeansOfType(Class<T>)`

   - 提供更强大的 bean 枚举能力


3. `HierarchicalBeanFactory`

   - 增加 `getParentBeanFactory()` 方法

   - 可向父工厂查找 bean，提高容器复用性，适用于嵌套容器场景


4. `AutowireCapableBeanFactory`

   - 提供自动注入功能，例如 `autowireBean(Object existingBean)`

   - 适用于将外部对象注入 Spring 容器管理的组件


5. `ConfigurableBeanFactory`

   - 支持配置 Bean 后处理器（如 `BeanPostProcessor`）、作用域注册、自定义属性编辑器等

   - 可以设置容器特性，如 `setBeanClassLoader(ClassLoader cl)`


6. `ConfigurableListableBeanFactory`

   - 是最强大的 BeanFactory 扩展接口

   - 除了包含前面所有功能，还支持：
     - 修改 BeanDefinition
     - 再处理 BeanFactoryPostProcessor
     - 对 BeanDefinition 做高级解析和合并


目前，大部分都只是个架子
