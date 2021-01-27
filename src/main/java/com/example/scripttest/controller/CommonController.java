package com.example.scripttest.controller;

/**
 * @program: scripttest
 * @description: hello,world
 * @author: yhj
 * @create: 2021-01-11 15:06
 **/

import com.alibaba.fastjson.JSONObject;
import com.example.scripttest.service.Sleep;
import com.example.scripttest.unit.CallShellUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/hello")
public class CommonController {


    private static final Logger LOG = LoggerFactory.getLogger(Sleep.class);

    @Autowired
    private Sleep sleep;

    CallShellUtil callShellUtil = new CallShellUtil();
    @RequestMapping(value = "/test")
    public String demo(){

        String path = "/root/script";
        String cmd = "/root/script/test.sh &";

        return callShellUtil.callShell(path,cmd);
    }

    @RequestMapping(value = "/testSleep")
    public String sleep() {
        final String dateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();// 格式化时间
        simpleDateFormat.applyPattern(dateFormat);// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        LOG.info("begin: "+ simpleDateFormat.format(date));
        sleep.printHello();
        Date date2 = new Date();// 获取当前时间
        LOG.info("end: "+ simpleDateFormat.format(date2));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success",100);
        return jsonObject.toJSONString();
    }
}
