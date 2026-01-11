package org.releaf.stereotype;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // 只能用在类级别（类、接口、注解、枚举）
@Retention(RetentionPolicy.RUNTIME) // 这个注解在运行时依然存在，可以通过反射获取
@Documented // 会出现在 API 文档当中
public @interface Component {

    String value() default "";
}
