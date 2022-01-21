package com.junit.selenium.config;

public class Constants {

    // 这里定义为public static的类型，方便其他任何类进行访问和调用
    public static final String URL = "https://www.baidu.com";
    public static final String MACHINE = "mac_m1";
//    public static final String MACHINE = "windows";

    public static final String Path_TestData = "./src/main/java/com/junit/selenium/data/data.xlsx";


    // data.xlsx中一些单元格的索引值
    public static final int Col_TestCaseID = 0;
    public static final int Col_TestScenarioID =1;
    public static final int Col_Variable = 3;
    public static final int Col_PageObject =4;
    public static final int Col_ActionKeyword =5;
    public static final int Col_RunMode = 2;

    // 第一个是测试用例结果标记列的索引，第二个是测试步骤里面的结果列索引
    public static final int Col_Result = 3;
    public static final int Col_TestStepResult = 6;

    // 结果状态标记
    public static final String KEYWORD_FAIL = "FAIL";
    public static final String KEYWORD_PASS = "PASS";

    // Data.excel中sheet的名称
    public static final String Sheet_TestSteps = "TestSteps";
    public static final String Sheet_TestCases = "TestCases";


    // OR(对象仓库)文件路径
    public static final String OR_Path ="./src/main/java/com/junit/selenium/config/OR.txt";

//    // 测试登录用到的用户数据
//    public static final String UserName = "MaoHaonan";
//    public static final String Password = "shellingford1234";
//
//
//    //测试的项目
//    public static final String Project = "5th_test";

}
