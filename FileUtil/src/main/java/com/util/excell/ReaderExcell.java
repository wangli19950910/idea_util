package com.util.excell;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 解析excell表格信息
 */
public class ReaderExcell {

    private static Logger logger = LoggerFactory.getLogger(ReaderExcell.class);

    private Workbook wb;  //创建工作簿变量信息
    private Sheet sheet;  //创建sheet表
    private Row row;      //创建列

    /**
     * 针对不同后缀 .xls  .xlsx 文件生成不同的工作簿
     */
    public ReaderExcell(String filename){
        if(filename==null){
            return;
        }
        String ext = filename.substring(filename.lastIndexOf("."));

        try {
            InputStream is = new FileInputStream(filename);
            if(".xls".equals(ext)){
                wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(ext)){
                wb = new XSSFWorkbook(is);
            }else{
                wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 表头内容处理
     */
    public String[] readExcelTitlt() throws  Exception{
        if(wb == null){
            throw  new Exception("WorkBook对象空");
        }
        //获取第一行数据信息
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        //计算标题总数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("colNum : "+colNum);//计算总列数
        String[] title = new String[colNum];
        for(int i=0;i<colNum;i++){
            title[i] = row.getCell(i).getStringCellValue();//getCellFormula()出现格式异常信息
        }
        return title;
    }

    /**
     * 解析excell数据内容信息
     * @param
     * @throws Exception
     */
    public Map<Integer,Map<Integer,Object>> readEcelContent() throws  Exception{
        if(wb == null){
            throw  new Exception("Workbook对象为空");
        }
        Map<Integer,Map<Integer,Object>> content = new HashMap<Integer, Map<Integer, Object>>();

        sheet = wb.getSheetAt(0);
        //文件的总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        //文件总列数
        int colNum = row.getPhysicalNumberOfCells();
        for(int i=1;i<rowNum;i++){
            row = sheet.getRow(i);
            int j = 0;
            Map<Integer,Object> cellValue = new HashMap<Integer, Object>();
            while(j<colNum){
                Object obj = getCellFormValue(row.getCell(j));
                cellValue.put(j,obj);
                j++;
            }
            content.put(i,cellValue);

        }
        return content;
    }

    public Object getCellFormValue(Cell cell){
        Object cellValue = "";
        if(cell!=null){
            switch (cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                case Cell.CELL_TYPE_FORMULA:
                    if(DateUtil.isCellDateFormatted(cell)){
                        Date date = cell.getDateCellValue();
                        cellValue = date;
                    }else{
                        //对于保留小数为和不保留小数位数据处理
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue  =cell.getBooleanCellValue();
                    break;
                default:
                    cellValue = "";

            }

        }else{
            cellValue = "";
        }
        return  cellValue;
    }



    public static void main(String[] args) throws  Exception{
        String filename = "C:/Users/lenovo/Desktop/test.xlsx";
        ReaderExcell readerExcell = new ReaderExcell(filename);
        String[] title = readerExcell.readExcelTitlt();
        System.out.println(Arrays.toString(title));
        Map<Integer,Map<Integer,Object>> map = readerExcell.readEcelContent();
        System.out.println(map);
    }
}
