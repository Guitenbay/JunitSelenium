package com.junit.selenium.script;

import com.junit.selenium.config.ActionKeywords;
import com.junit.selenium.config.Constants;
import com.junit.selenium.utils.ExcelUtils;
import com.junit.selenium.utils.Log;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class DriverScript {

    private static ActionKeywords actionKeywords;
    private static String sActionKeyword;//从Excel中读到的关键字
    private static String[] sArgs; //输入方法的参数
    public static Properties OR; //页面元素的xpath

    private static Method methods[];

    private static int iTestStep;
    public static boolean bResult;


    private DriverScript() throws NoSuchMethodException, SecurityException, ClassNotFoundException{
        actionKeywords = new ActionKeywords();
        methods = actionKeywords.getClass().getMethods();
    }

    public static void main(String[] args) throws Exception{
        try {
            DOMConfigurator.configure("log4j.xml");

            Log.startTestCase("Login_01");

            Log.info("加载和读取Excel数据文件");
            ExcelUtils.setExcelFile(Constants.Path_TestData);

            FileInputStream fs = new FileInputStream(Constants.OR_Path);// 创建一个文件输入流对象
            OR = new Properties(System.getProperties()); // 创建一个Properties对象

            OR.load(fs);// 加载全部对象仓库文件

            DriverScript startEngine = new DriverScript();
            Log.info("开始执行测试用例。");
            startEngine.execute_Testcase();
            Log.info("测试用例执行结束。");

        }catch(Exception e) {
            Log.info("测试有问题，无法结束---"+e.getMessage());
            ActionKeywords.closeBrowser();
        }
    }

    private void execute_Testcase() throws Exception{
        //获取测试用例数量
        int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);

        //外层for循环，有多少个测试用例就执行多少次循环
        for(int iTestcase=1;iTestcase<=iTotalTestCases;iTestcase++){
            bResult = true;
            //从Test Case表获取测试ID
            String sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);

            //获取当前测试用例的Run Mode的值
            String sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode, Constants.Sheet_TestCases);

            // Run Mode的值控制用例是否被执行
            if (sRunMode.equals("Yes")){

                // 只有当Run Mode的单元格数据是Yes，下面代码才会被执行
                iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
                int iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
                bResult=true;

                //下面这个for循环的次数就等于测试用例的步骤数
                for (; iTestStep <= iTestLastStep; iTestStep++){

                    sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
                    //从Excel中读取的页面元素名称

                    String sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
                    //从Excel中读取输入的值
                    String sVariable = ExcelUtils.getCellData(iTestStep, Constants.Col_Variable, Constants.Sheet_TestSteps);

                    if (!sVariable.equals("")) {
                        sArgs = new String[2];
                        sArgs[1] = sVariable;
                    } else {
                        sArgs = new String[1];
                    }
                    sArgs[0] = sPageObject;

                    execute_Actions();

                    if(!bResult){

                        //If 'false' then store the test case result as Fail
                        ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);

                        //End the test case in the logs
                        Log.endTestCase(sTestCaseID);
                        //By this break statement, execution flow will not execute any more test step of the failed test case
                        break;
                    }
                }

                //This will only execute after the last step of the test case, if value is not 'false' at any step
                if(bResult){
                    //Storing the result as Pass in the excel sheet
                    ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
                    Log.endTestCase(sTestCaseID);
                }
            }
        }
    }

    private static void execute_Actions() throws Exception {

        // 循环遍历每一个关键字驱动方法（在 actionkeywords.java 中）
        // 下面method.length表示方法个数，method变量表示任何一个关键字方法，例如openBrowser()
        for (Method method : methods) {
            // 开始对比代码中关键字方法名称和excel中关键字这列值是否匹配
            if (method.getName().equals(sActionKeyword)) {

                // 一但匹配到相关关键字方法，就会调用对应的关键字静态方法
                // methods[i].invoke(actionKeywords);
                method.invoke(actionKeywords, sArgs); //如果元素对象是空的，则excel中必须有个空格字符，否则java会编译不通过。

                // 一旦任何关键字被执行，利用break语句去跳出for循环。
                // This code block will execute after every test step

                if (bResult) {

                    // If the executed test step value is true, Pass the test step in Excel sheet
                    ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
                    break;

                } else {
                    // If the executed test step value is false, Fail the test step in Excel sheet
                    ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);

                    // In case of false, the test execution will not reach to last step of closing browser
                    // So it make sense to close the browser before moving on to next test case
                    ActionKeywords.closeBrowser();
                    break;
                }
            }
        }
    }

}
