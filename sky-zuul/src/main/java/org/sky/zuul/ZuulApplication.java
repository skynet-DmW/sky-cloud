package org.sky.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 启动类
 */
@SpringBootApplication
@EnableEurekaClient // 表示eureka客户端
@EnableZuulProxy // 开启Zuul的注解
public class ZuulApplication {

   public static void main(String[] args) {
      SpringApplication.run(ZuulApplication.class, args);
   }
}