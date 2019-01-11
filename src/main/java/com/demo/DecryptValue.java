package com.demo;

import java.lang.annotation.*;

/**
 * @DecryptValue 注解主要作用是可以自定义属性注入的处理逻辑，类似于{@Value},但两者的注入时机不相同。
 * Created by RongJie.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Documented
public @interface DecryptValue {

    String value();

}
