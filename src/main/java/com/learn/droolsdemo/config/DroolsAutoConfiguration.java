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
 * @Date: 2018/9/30 上午10:06
 * @Description: drools config
 */
@Configuration
public class DroolsAutoConfiguration {

    public String RULES_PATH;

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsAutoConfiguration.class);

    @Bean
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(getRULES_PATH(RULES_PATH) + file.getFilename(), "UTF-8"));
        }
        return kieFileSystem;
    }

    @Bean
    public Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + getRULES_PATH(RULES_PATH) + "/**/*.drl");
    }

    @Bean
    public String getRULES_PATH(@Value("${drools.rule:#{rules}}") String RULES_PATH) {
        return RULES_PATH;
    }

    @Bean
    public KieContainer kieContainer() throws IOException {
        KieServices kieServices = getKieServices();
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem());
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            LOGGER.error("fireRuleError", results.getMessages());
        }
        kieBuilder.buildAll();
        return kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
    }

    private KieServices getKieServices() {

        return KieServices.Factory.get();
    }

    @Bean
    public KieBase kieBase() throws IOException {
        return kieContainer().getKieBase();
    }

    @Bean
    public KieSession kieSession() throws IOException {
        return kieContainer().newKieSession();
    }

    @Bean
    public KModuleBeanFactoryPostProcessor kiePostProcessor() {
        return new KModuleBeanFactoryPostProcessor();
    }

}