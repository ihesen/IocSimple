package com.ihesen.ioclibrary.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ihesen on 2019-05-07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ClickBase(listenerSetter = "setOnClickListener", listenerType = View.OnClickListener.class, callBackListener = "onClick")
public @interface OnClick {

    int[] value();

}
