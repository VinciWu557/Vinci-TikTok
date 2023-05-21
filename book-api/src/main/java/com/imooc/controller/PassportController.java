package com.imooc.controller;

import com.imooc.grace.result.GraceJSONResult;
import com.imooc.utils.IPUtil;
import com.imooc.utils.SMSUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Api(tags = "PassportController 通行证接口模块")
@RequestMapping("/passport")
@RestController
public class PassportInfoProperties extends BaseInfoProperties {

    @Autowired
    private SMSUtils smsUtils;

    @PostMapping("/getSMSCode")
    public GraceJSONResult getSMSCode(@RequestParam String mobile,
                                      HttpServletRequest request) throws Exception {
        // 判空
        if(StringUtils.isBlank(mobile)){
            return GraceJSONResult.ok();
        }

        // 获取用户IP
        String userIp = IPUtil.getRequestIp(request);
        // 根据用户IP进行限制, 限制60s内只能获得一次验证码
        redis.setnx60s(MOBILE_SMSCODE + ":" + userIp, userIp);

        // 验证码
        String code = (int)((Math.random() * 9 + 1) * 100000) + "";
        // 发送验证码
        smsUtils.sendSMS(mobile, code);
        log.info(code);

        // 把验证码存入 Redis 中用于后续验证 (30 min 有效)
        redis.set(MOBILE_SMSCODE + ":" + mobile, code, 30 * 60);

        return GraceJSONResult.ok();
    }
}
