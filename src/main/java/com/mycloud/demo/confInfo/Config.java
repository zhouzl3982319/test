package com.mycloud.demo.confInfo;

import com.mycloud.demo.interceptor.MyRibbonInterceptor;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;

//生成restTemplate boot版本不一样 早期版本有自动生成 LoadBalanced负载均衡
@Configuration
public class Config {
    @Autowired
    MyRibbonInterceptor myRibbonInterceptor;
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        builder.setConnectTimeout(Duration.ofMillis(100));
        RestTemplate restTemplate=builder.build();
        restTemplate.setInterceptors(Collections.singletonList(myRibbonInterceptor));
        return restTemplate;
    }
    @Bean
    public IRule ribbonRule(){
        //随机负载
        return new RandomRule();
    }
}
