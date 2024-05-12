package com.airline.common_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class CommonMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonMsApplication.class, args);
    }

}
