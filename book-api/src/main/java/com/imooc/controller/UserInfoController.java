package com.imooc.controller;

import com.imooc.grace.result.GraceJSONResult;
import com.imooc.model.Stu;
import com.imooc.utils.SMSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "Hello 测试的接口")
@RestController
public class HelloController {

    @ApiOperation(value = "hello - 这是一个 Hello 的测试路由")
    @GetMapping("/hello")
    public Object hello(){
        return GraceJSONResult.ok("Hello SpringBoot!");
    }

    @Autowired
    private SMSUtils smsUtils;

    @GetMapping("/sms")
    public Object sms() throws Exception {

        String code = "123456";
        smsUtils.sendSMS("15122083673", code);


        return GraceJSONResult.ok();
    }



}
