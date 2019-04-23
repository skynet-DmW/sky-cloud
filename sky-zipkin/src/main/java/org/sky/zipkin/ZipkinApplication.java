package org.sky.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.internal.EnableZipkinServer;

/**
 * @Date: 2019/2/22 15:34
 * @Description: 启动类
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer // 启动zipkin
public class ZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }

}
