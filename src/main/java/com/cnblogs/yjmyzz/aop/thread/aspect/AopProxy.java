package com.cnblogs.yjmyzz.aop.thread.aspect;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;

public class AopProxy implements MethodInterceptor {

    private final static int MAX_LEVEL = 3;
    private final static String DOT = ".";

    public static String getMethodName(Method method) {
        if (method == null) {
            return null;
        }
        String[] arr = method.toString().split(" ");
        String methodName = arr[2].split("\\(")[0] + "()";
        String[] arr2 = methodName.split("\\.");
        if (arr2.length > MAX_LEVEL) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr2.length; i++) {
                if (i <= MAX_LEVEL) {
                    sb.append(arr2[i].substring(0, 1) + DOT);
                } else {
                    sb.append(arr2[i] + DOT);
                }
            }
            String temp = sb.toString();
            if (temp.endsWith(DOT)) {
                temp = temp.substring(0, temp.length() - 1);
            }
            return temp;
        }
        return methodName;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        StringBuilder sb = new StringBuilder();
        Object result = null;
        long start = System.currentTimeMillis();
        boolean hasError = false;
        try {
            sb.append("thread[" + Thread.currentThread().getId() + "] " + getMethodName(method) + " =>args:");
            if (ArrayUtils.isNotEmpty(objects)) {
                for (int i = 0; i < objects.length; i++) {
                    sb.append("[" + i + "]" + objects[i].toString() + ",");
                }
            } else {
                sb.append("null,");
            }
            result = methodProxy.invokeSuper(o, objects);
            sb.append(" result:" + result);
        } catch (Exception e) {
            sb.append(", error:" + e.getMessage());
            hasError = true;
        } finally {
            long execTime = System.currentTimeMillis() - start;
            sb.append(", execTime:" + execTime + " ms");
        }
        System.out.println(sb.toString());
        return result;
    }
}