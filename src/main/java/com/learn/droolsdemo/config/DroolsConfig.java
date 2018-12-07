package com.learn.droolsdemo.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @Author: chengyong.wang
 * @Date: 2018/12/7 下午3:28
 * @Description:
 */
@Configuration
public class DroolsConfig {
    @Value("${drools.rule:#{rules}}")
    public   String RULES_PATH;

    private static final Logger LOGGER= LoggerFactory.getLogger(DroolsAutoConfiguration.class);

    @Bean
    public Thread mythread() {
        System.out.println(RULES_PATH);
        return new Thread();
    }





    @Bean
    public Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        System.out.println(RULES_PATH);
        return resourcePatternResolver.getResources("classpath*: /**/*.drl");
    }



    private KieServices getKieServices() {

        return KieServices.Factory.get();
    }



    @Bean
    public KModuleBeanFactoryPostProcessor kiePostProcessor() {
        return new KModuleBeanFactoryPostProcessor();
    }

}
