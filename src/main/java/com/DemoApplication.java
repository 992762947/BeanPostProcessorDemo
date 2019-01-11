package com;

import com.demo.AnnotationBeanPostProcessor;
import com.demo.People;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by RongJie on 2019/1/10 0010.
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(DemoApplication.class, args);
        People people = (People) ac.getBean("people");
        people.say();
    }

    @Bean
    public BeanPostProcessor AnnotationBeanPostProcessor(ConfigurableEnvironment environment){
        return new AnnotationBeanPostProcessor(environment);
    }
}