package com.junit.selenium.utils;

import org.apache.log4j.Logger;

public class Log {

    // 初始化log4j log
    private static Logger Log = Logger.getLogger(Log.class.getName());

    // 运行测试用例之前的日志输出
    public static void startTestCase(String sTestCaseName){

        Log.info("*******************************************************");
        Log.info("$$$$$$$$$          "+sTestCaseName+ "         $$$$$$$$$$");
        Log.info("*******************************************************");

    }

    // 用例执行结束后日志输出
    public static void endTestCase(String sTestCaseName){

        Log.info("XXXXXXX            "+"-E---N---D-"+"          XXXXXXXXX");
        Log.info("X");
        Log.info("X");

    }


    // 以下是不同日志级别的方法，方便需要的时候调用，一般info和error用得最多

    public static void info(String message) {

        Log.info(message);

    }



    public static void warn(String message) {

        Log.warn(message);

    }



    public static void error(String message) {

        Log.error(message);

    }



    public static void fatal(String message) {

        Log.fatal(message);

    }



    public static void debug(String message) {

        Log.debug(message);

    }

}
