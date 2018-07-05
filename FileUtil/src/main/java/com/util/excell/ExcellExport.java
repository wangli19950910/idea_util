package com.util.excell;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 
 * 使用POI生成excell表格
 * @author Wang Li
 *
 */
public class ExcellExport {
	public static void main(String[] args) throws Exception {
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("运单数据");
		HSSFRow rowTitle = sheet.createRow(0);
		HSSFCellStyle styleTitle = wb.createCellStyle();
		
		//单元格式修饰
		styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = wb.createFont();
		
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("微软雅黑");
		font.setFontHeight((short)200);
		styleTitle.setFont(font);
		
		HSSFCell cell = rowTitle.createCell(0);
		cell.setCellValue("运单号");
		cell.setCellStyle(styleTitle);
		
		HSSFCell cell2 = rowTitle.createCell(1);
		cell2.setCellValue("代理人");
		cell2.setCellStyle(styleTitle);
		
		HSSFCellStyle styleCenter = wb.createCellStyle();
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFRow row1 = sheet.createRow(1);
		
		HSSFCell cell1 = row1.createCell(0);
		cell1.setCellValue("1");
		cell1.setCellStyle(styleCenter);
		
		HSSFCell cell3 = row1.createCell(1);
		cell3.setCellValue("2");;
		cell3.setCellStyle(styleCenter);
		
		//如何设置文件导出路径
		FileOutputStream fos = new FileOutputStream(new File("excel.xls"));
		wb.write(fos);
		fos.close();
		wb.close();
		System.out.println("文件导出成功");

	}
}
