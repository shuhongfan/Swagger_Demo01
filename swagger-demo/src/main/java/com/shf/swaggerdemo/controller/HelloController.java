package com.shf.swaggerdemo.controller;

import com.shf.swaggerdemo.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiOperation("Hello控制类")
@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello(){
        return "Hello";
    }

//    只要我们的接口中，返回值中存在实体类，他就会被扫描到swagger中
    @PostMapping(value = "/user")
    public User user(){
        return new User();
    }

    @ApiOperation("Hello控制类")
    @GetMapping("/hello2")
    public String hello(@ApiParam("用户名") String username){
        return "hello"+username;
    }

    @ApiOperation("post测试")
    @PostMapping("/postt")
    public String hello(@ApiParam("用户名") User user){
        return "hello"+ user;
    }
}
