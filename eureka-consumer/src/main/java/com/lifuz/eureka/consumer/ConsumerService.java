package com.lifuz.eureka.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2017/12/1 15:40
 */

@Service
public class ConsumerService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    public String consumer() {

        String url = "http://EUREKA-CLIENT/hello";
        ResponseEntity<String> entity = restTemplate.getForEntity(url , String.class);
        logger.info("status = " + entity.getStatusCode());
        return entity.getBody();
    }

    public String error() {
        return "error";
    }

}
