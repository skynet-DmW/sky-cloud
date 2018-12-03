package org.sky.web.hystrix;

import feign.hystrix.FallbackFactory;
import org.sky.web.feign.TestFeignClient;
import org.springframework.stereotype.Component;


/**
 * Hystrix降级处理
 */
@Component
public class TestHystrixClientFactory implements FallbackFactory<TestFeignClient> {


    @Override
    public TestFeignClient create(Throwable throwable) {
        TestFeignClient testFeignClient = (String id) -> ("Hystrix降级处理");
        return testFeignClient;
    }

}