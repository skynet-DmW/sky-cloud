package org.sky.server.test.controller;


import org.sky.common.constant.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestServerController {


    @GetMapping("selectServer/{id}")
    public String selectServer(@PathVariable("id") String id){
        return Constants.SUCCESS + ":" + id;
    }
}
