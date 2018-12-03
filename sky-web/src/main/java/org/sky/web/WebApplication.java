package org.sky.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 */
@SpringBootApplication
@EnableEurekaClient // 表示eureka客户端
@EnableFeignClients // 启动feign
@EnableCircuitBreaker // 开启hystrix断路器模式
@EnableHystrixDashboard // 启用Hystrix Dashboard功能
@ComponentScan(basePackages = {"org.sky"})
public class WebApplication {

   public static void main(String[] args) {
      SpringApplication.run(WebApplication.class, args);
   }

}