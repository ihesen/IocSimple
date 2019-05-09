package com.ihesen.ioclibrary.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ihesen on 2019-05-07
 */
@Target(ElementType.TYPE)//该注解，作用在什么之上（如：类，方法，属性）
@Retention(RetentionPolicy.RUNTIME)//在jvm运行时通过反射来获取注解的值
public @interface ContentView {
    //int类型资源
    int value();
}
