package org.sky.web.feign;

import org.sky.web.hystrix.TestHystrixClientFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 追溯查询
 */
@FeignClient(value = "sky-server", fallbackFactory = TestHystrixClientFactory.class)
public interface TestFeignClient {

    @GetMapping(value = "test/selectServer/{id}")
    String select(@PathVariable("id") String id);

}
