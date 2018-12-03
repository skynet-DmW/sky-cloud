package org.sky.web.config.rest;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: 赵明明
 * @Date: 2018/10/25 16:18
 * @Description: RestTemplate配置
 */
@SpringBootConfiguration
public class RestConfig {


    /**
     * 开启负载均衡，开启之后，通过@Autowired注入进来的只能调用eureka中的服务。
     * 如想调用外部服务，则用传统方式new一个即可 RestTemplate restTemplate = new RestTemplate()
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
