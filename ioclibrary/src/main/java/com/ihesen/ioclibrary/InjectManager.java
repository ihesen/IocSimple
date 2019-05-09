package com.ihesen.ioclibrary;

import android.app.Activity;
import android.view.View;

import com.ihesen.ioclibrary.annotation.ContentView;
import com.ihesen.ioclibrary.annotation.ClickBase;
import com.ihesen.ioclibrary.annotation.InjectView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ihesen on 2019-05-07
 */
public class InjectManager {

    public static void inject(Activity activity) {
        //布局的注入
        injectLayout(activity);
        //控件的注入
        injectViews(activity);
        //事件的注入
        injectEvents(activity);
    }

    private static void injectEvents(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        //获取类中所有的方法
        Method[] methods = aClass.getDeclaredMethods();
        //循环方法
        for (Method method : methods) {
            //annotations 因为方法上面可能添加多个注解
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation != null) {
                    //获取OnClick上面的注解类型
                    Class<? extends Annotation> annotationType = annotation.annotationType();
                    if (annotationType != null) {
                        //获取EventBase封装注解
                        ClickBase clickBase = annotationType.getAnnotation(ClickBase.class);
                        if (clickBase != null) {
                            String listenerSetter = clickBase.listenerSetter();
                            Class<?> listenerType = clickBase.listenerType();
                            //本应该执行的回调方法"Onclick",进行拦截
                            String callBackListener = clickBase.callBackListener();
                            try {
                                //通过annotationType获取OnClick注解的value方法
                                Method valueMethod = annotationType.getDeclaredMethod("value");
                                //执行value方法，获取注解的值（数组）
                                int[] viewIds = (int[]) valueMethod.invoke(annotation);

//                                方法1：这样写会写死了
//                                View.OnClickListener listener = new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//
//                                    }
//                                }

                                //AOP 切面
                                ListenerInvocationHandler handler = new ListenerInvocationHandler(activity);
                                handler.addMethods(callBackListener, method);
                                //代理
                                Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, handler);

                                for (int viewId : viewIds) {
                                    //获取当前activity的view控件
                                    View view = activity.findViewById(viewId);
                                    //获取制定的方法，比如（setXXXListener）
                                    Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                                    //执行方法
                                    setter.invoke(view, listener);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    private static void injectViews(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        //获取所有属性
        Field[] declaredFields = aClass.getDeclaredFields();
        //循环获取属性注解
        for (Field field : declaredFields) {
            InjectView annotation = field.getAnnotation(InjectView.class);
            //获取findViewById()方法
            if (annotation != null) {
                int viewId = annotation.value();
                //第一种方式
//                View viewById = activity.findViewById(viewId);

                //第二种方法
                try {
                    Method method = aClass.getMethod("findViewById", int.class);
                    Object view = method.invoke(activity, viewId);
                    field.setAccessible(true);
                    //给属性字段赋值
                    field.set(activity, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectLayout(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        //获取类之上的注解
        ContentView contentView = aClass.getAnnotation(ContentView.class);
        if (contentView != null) {
            //获取注解的值
            int layoutId = contentView.value();
//            第一种方式
//            activity.setContentView(layoutId);

            //第二种 setContentView()
            try {
                //TODO  getMethod获取当前类包括父类中的方法
                Method method = aClass.getMethod("setContentView", int.class);
                method.invoke(activity, layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
