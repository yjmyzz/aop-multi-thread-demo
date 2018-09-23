package com.cnblogs.yjmyzz.aop.thread.asyn;

import com.cnblogs.yjmyzz.aop.thread.service.SampleService;
import org.springframework.context.ApplicationContext;

public class RunnableA implements Runnable {

    private ApplicationContext context;

    public RunnableA(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run() {
        SampleService sampleService = context.getBean(SampleService.class);
        System.out.println("thread:" + Thread.currentThread().getId() + "," + sampleService.hello("菩提树下的杨过-2"));
    }
}
