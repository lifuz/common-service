package com.lifuz.eureka.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2017/11/29 18:09
 */

@RestController
public class ConsumerController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String consumer() {

        ServiceInstance instance = loadBalancerClient.choose("eureka-client");

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/hello";

        logger.info("url = {}" , url);

        return restTemplate.getForObject(url, String.class);

    }


}
