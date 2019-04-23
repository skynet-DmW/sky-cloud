package org.sky.web.test.controller;

import org.sky.common.pojo.Result;
import org.sky.web.feign.TestFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2018/12/3 10:31
 * @Description: 测试
 */
@RequestMapping("test")
@RestController
public class TestWebController {


    @Autowired
    private TestFeignClient testFeignClient;


    @GetMapping("selectWeb/{id}")
    public ResponseEntity<Object> selectWeb(@PathVariable("id") String id) {
        String s = this.testFeignClient.select(id);
        return ResponseEntity.ok(Result.data(s));
    }


}
