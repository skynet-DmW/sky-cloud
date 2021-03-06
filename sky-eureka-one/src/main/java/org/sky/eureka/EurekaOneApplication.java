package org.sky.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * 启动类
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaOneApplication.class, args);
    }

}