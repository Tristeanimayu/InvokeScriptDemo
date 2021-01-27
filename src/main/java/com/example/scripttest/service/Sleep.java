package com.example.scripttest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: scripttest
 * @description: 后台执行测试
 * @author: yhj
 * @create: 2021-01-16 18:56
 **/

@Component
public class Sleep {

    private static final Logger LOG = LoggerFactory.getLogger(Sleep.class);
    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    @Async
    public void printHello(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();// 格式化时间
        simpleDateFormat.applyPattern(dateFormat);// a为am/pm的标记
        Date date = new Date();// 获取当前时间

        int i = 0;
        while (i<5){
            i++;
            LOG.info("hello: " + simpleDateFormat.format(date));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                LOG.error("error",e);
            }
        }

    }
}
