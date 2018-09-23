package com.cnblogs.yjmyzz.aop.thread.service;

import org.springframework.stereotype.Service;

@Service("sampleService")
public class SampleService {

    public String hello(String name) {
        return "你好," + name;
    }

}
