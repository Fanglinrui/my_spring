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

