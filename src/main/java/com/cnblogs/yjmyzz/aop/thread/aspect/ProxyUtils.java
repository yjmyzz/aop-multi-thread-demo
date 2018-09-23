package com.cnblogs.yjmyzz.aop.thread.aspect;

import net.sf.cglib.proxy.Enhancer;

public class ProxyUtils {

    /**
     * 创建代理对象实例
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T createProxyObject(Class<T> type) {
        AopProxy factory = new AopProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(type);
        enhancer.setCallback(factory);
        //注意：被代理的类，必须有默认无参的空构造函数
        T instance = (T) enhancer.create();
        return instance;
    }
}
