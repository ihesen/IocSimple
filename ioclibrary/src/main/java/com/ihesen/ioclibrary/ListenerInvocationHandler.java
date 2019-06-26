package com.ihesen.ioclibrary;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by ihesen on 2019-05-07
 */
public class ListenerInvocationHandler implements InvocationHandler {

    // 无参数点击处理
    // 点击的阻尼事件

    private Object target; //需要拦截的对象，如；Activity / Fragment
    private HashMap<String, Method> map = new HashMap<>();

    public ListenerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取需要拦截的方法名
        String methodName = method.getName();//假如是onClick
        method = map.get(methodName);
        if (method != null) {
            //判断方法是否有参数
            if (method.getParameterTypes().length == 0) {
                return method.invoke(target);
            } else {
                return method.invoke(target, args);
            }
        }
        return null;
    }

    /**
     * 添加拦截方法
     *
     * @param methodName 本应该执行的onClick方法，进行拦截
     * @param method     去执行开发者自定义的方法
     */
    public void addMethods(String methodName, Method method) {
        map.put(methodName, method);
    }
}
