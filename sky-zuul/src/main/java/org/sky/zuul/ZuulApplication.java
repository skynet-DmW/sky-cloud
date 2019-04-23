package org.sky.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 启动类
 */
@SpringCloudApplication
@EnableHystrixDashboard // 启用Hystrix Dashboard功能
@EnableZuulProxy // 开启Zuul的注解
public class ZuulApplication {

   public static void main(String[] args) {
      SpringApplication.run(ZuulApplication.class, args);
   }
}