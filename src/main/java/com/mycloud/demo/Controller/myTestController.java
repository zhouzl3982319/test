package com.mycloud.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//本实例演示
@RestController
public class myTestController {
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping("/test")
    public String test(@RequestParam(value = "name", defaultValue = "范保林") String name) {

        return "hi"+name+", 我是插件服务2";
    }

    @RequestMapping("/send")
    public String send() {

        return restTemplate.getForObject("http://ys-test/test?param=" +"1", String.class);
    }

}
