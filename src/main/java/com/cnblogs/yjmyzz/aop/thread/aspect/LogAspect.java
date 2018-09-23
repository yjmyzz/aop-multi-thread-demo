package com.cnblogs.yjmyzz.aop.thread.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component("logAspect")
public class LogAspect {


    @Pointcut("execution(* com.cnblogs.yjmyzz..service..*(..))")
    private void logPointCut() {
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) {
        Object result = null;
        StringBuilder sb = new StringBuilder();
        long start = 0;
        try {
            //记录线程id、方法签名
            sb.append("thread:" + Thread.currentThread().getId() + ", method:" + pjp.getSignature() + ",");
            //记录参数
            if (pjp.getArgs() != null) {
                sb.append("args:");
                for (int i = 0; i < pjp.getArgs().length; i++) {
                    sb.append("[" + i + "]" + pjp.getArgs()[i] + ",");
                }
            }
            start = System.currentTimeMillis();
            result = pjp.proceed();
            //记录返回结果
            sb.append("result:" + result);
        } catch (Throwable e) {
            sb.append(",error:" + e.getMessage());
            throw e;
        } finally {
            long elapsedTime = System.currentTimeMillis() - start;
            //记录执行时间
            sb.append(",elapsedTime:" + elapsedTime + "ms");
            System.out.println(sb.toString());
            return result;
        }
    }

}
