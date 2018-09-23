package com.cnblogs.yjmyzz.aop.thread.asyn;

import com.cnblogs.yjmyzz.aop.thread.aspect.ProxyUtils;
import com.cnblogs.yjmyzz.aop.thread.service.SampleService;
import org.springframework.context.ApplicationContext;

public class RunnableB implements Runnable {

    public RunnableB() {
    }

    @Override
    public void run() {
        //注：这时改成用CGLib来创建目标的代理类实例
        SampleService sampleService = ProxyUtils.createProxyObject(SampleService.class);

//        SampleService sampleService = new SampleService();
        System.out.println("thread:" + Thread.currentThread().getId() + "," + sampleService.hello("菩提树下的杨过-2"));
    }
}
