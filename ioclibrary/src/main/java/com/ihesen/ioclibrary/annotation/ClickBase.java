package com.ihesen.ioclibrary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ihesen on 2019-05-07
 * OnClick 注解的父类
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickBase {

    //1 setxxxListener
    String listenerSetter();

    //2 new View.OnxxxListener监听对象
    Class<?> listenerType();

    //3 回调、最终执行的方法
    String callBackListener();

}