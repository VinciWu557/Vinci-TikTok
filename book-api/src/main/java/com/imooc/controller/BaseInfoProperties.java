package com.imooc.controller;

import com.imooc.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用于存放公用的方法
 */
public class BaseController {

    // public 使得可以被继承
    @Autowired
    public RedisOperator redis;

    // redis 相应的 Key
    public static final String MOBILE_SMSCODE = "mobile_smscode";
    public static final String REDIS_USER_TOKEN = "redis_user_token";
    public static final String REDIS_USER_INFO = "redis_user_info";

}
