package org.sky.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 */
@SpringBootApplication
@EnableEurekaClient // 表示eureka客户端
@ComponentScan(basePackages = {"org.sky"})
public class ServerApplication {

   public static void main(String[] args) {
      SpringApplication.run(ServerApplication.class, args);
   }

}