package com.example.scripttest.unit;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * @program: taskDemo
 * @description: 调用脚本工具类
 * @author: yhj
 * @create: 2021-01-11 19:17
 **/

public class CallShellUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CallShellUtil.class);

    public String callShell(String shellPath,String cmd){

        JSONObject result = new JSONObject();
        ProcessBuilder builder = new ProcessBuilder();

        builder.directory(new File(shellPath));
        builder.command("/bin/sh","-c",cmd);

        StringBuffer stringBuffer = new StringBuffer();
        int runningStatus = 0;
        String string = null;

        try {
            Process p = builder.start();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            while ((string = stdInput.readLine()) != null) {
                LOG.info("shell log info ...." + string);
                stringBuffer.append(string);
            }
            while ((string = stdError.readLine()) != null) {
                LOG.error("shell log error...." + string);
                stringBuffer.append(string);
            }
            try {
                runningStatus = p.waitFor();
            } catch (InterruptedException e) {
                runningStatus = 1;
                LOG.error("等待shell脚本执行状态时，报错...",e);
                stringBuffer.append(e.getMessage());
            }

            closeStream(stdInput);
            closeStream(stdError);

            result.put("stat",runningStatus);
            result.put("stringBuffer",stringBuffer);


        } catch (Exception e) {
            LOG.error("执行shell脚本出错...",e);
            stringBuffer.append(e.getMessage());
            runningStatus =1;

            result.put("stat",runningStatus);
            result.put("stringBuffer",stringBuffer);
        }


        return result.toJSONString();
    }

    private void closeStream(BufferedReader reader){
        try {
            if(reader != null){
                reader.close();
            }
        } catch (Exception e) {
            reader = null;
        }
    }
}
