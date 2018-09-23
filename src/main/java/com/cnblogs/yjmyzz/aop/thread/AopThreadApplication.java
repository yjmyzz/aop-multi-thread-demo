package com.cnblogs.yjmyzz.aop.thread;

import com.cnblogs.yjmyzz.aop.thread.aspect.ProxyUtils;
import com.cnblogs.yjmyzz.aop.thread.asyn.RunnableA;
import com.cnblogs.yjmyzz.aop.thread.asyn.RunnableB;
import com.cnblogs.yjmyzz.aop.thread.service.SampleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.cnblogs.yjmyzz"})
public class AopThreadApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(AopThreadApplication.class, args);
//        SampleService sampleService = context.getBean(SampleService.class);

        System.out.println("main thread:" + Thread.currentThread().getId());
        System.out.println();

//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.submit(ProxyUtils.createProxyObject(RunnableB.class));

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new RunnableB());

    }
}
