package com.junit.selenium.utils;

import com.junit.selenium.config.Constants;
import com.junit.selenium.script.DriverScript;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private static XSSFSheet  ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    //设置Excel文件路径，方便读取到文件
    public static void setExcelFile(String Path) throws IOException {
        try {
            FileInputStream ExcelFile = new FileInputStream(Path);
            ExcelWBook = new XSSFWorkbook(ExcelFile);

        }catch(Exception e) {
            Log.error("Class Utils | Method setExcelFile | Exception desc : "+e.getMessage());
            DriverScript.bResult = false;
        }
        //ExcelWSheet = ExcelWBook.getSheet(SheetName);
    }

    //读取Excel文件单元格数据
    public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception{
        try{
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            Row = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum);
            return Cell.getStringCellValue();
        } catch (NullPointerException ne) {
            Log.error("Class Utils | Method getCellData | Exception desc : null pointer");
            return "";
        } catch (Exception e){
            Log.error("Class Utils | Method getCellData | Exception desc : "+e.getMessage());
            DriverScript.bResult = false;
            return "";
        }
    }

    //得到一共多少行数据
    public static int getRowCount(String SheetName){
        int number = 0;
        try {
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            number = ExcelWSheet.getLastRowNum()+1;
        } catch(Exception e) {
            Log.error("Class Utils | Method getRowCount | Exception desc : " + e.getMessage());
            DriverScript.bResult = false;
        }

        return number;
    }

    //得到测试用例的行号
    public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{
        int i = 0;
        try {
            int rowCount = ExcelUtils.getRowCount(SheetName);
            for (; i<rowCount; i++){
                if  (ExcelUtils.getCellData(i,colNum,SheetName).equalsIgnoreCase(sTestCaseName)){
                    break;
                }
            }
        }catch(Exception e) {
            Log.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
            DriverScript.bResult = false;
        }
        return i;
    }


    //计算一个测试用例有多少个步骤
    public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{
        try {
            for(int i=iTestCaseStart;i<=ExcelUtils.getRowCount(SheetName);i++){
                if(!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName))){
                    int number = i;
                    return number;
                }
            }
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int number=ExcelWSheet.getLastRowNum()+1;
            return number;

        }catch(Exception e) {
            Log.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
            DriverScript.bResult = false;
            return 0;
        }
    }

    public static void setCellData(String Result,  int RowNum, int ColNum, String SheetName) throws Exception    {
        try{
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            Row  = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum);

            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }

            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData);
            ExcelWBook.write(fileOut);

            //fileOut.flush();
            fileOut.close();
            ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestData));

        } catch(Exception e) {
            DriverScript.bResult = false;
        }
    }

}
