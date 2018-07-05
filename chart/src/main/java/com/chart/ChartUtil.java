package com.chart;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 使用jfreeChart制作柱状台的util工具类
 */
public class ChartUtil {

    public static CategoryDataset createDataSet(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(500,"java图书","J2SE类");
        dataset.addValue(100,"java图书","J2ME类");
        dataset.addValue(900,"java图书","J2EE类");
        dataset.addValue(300,"java图书","J2RW类");
        return dataset;
    }
    public static JFreeChart createChart(CategoryDataset categoryDataset){
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));
        standardChartTheme.setRegularFont(new Font("宋体",Font.PLAIN,15));
        standardChartTheme.setLargeFont(new Font("宋体",Font.PLAIN,15));

        ChartFactory.setChartTheme(standardChartTheme);

        JFreeChart chart = ChartFactory.createBarChart3D(
                "java图书销量统计",   //标题
                "java图书",//副标题
                "销量(本)",
                categoryDataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false

        );
        return chart;
    }

    /**
     * 转存为文件--图片文件格式
     * @param jFreeChart
     * @param fileName
     * @param weight
     * @param height
     */
    public static void saveAsFile(JFreeChart jFreeChart,String fileName,int weight,int height){
        FileOutputStream out = null;
        try{
            File outFile = new File(fileName);

            if(!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(fileName);

            ChartUtilities.writeChartAsJPEG(out,jFreeChart,weight,height);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }


    public static void main(String[] args) {
        /**
         * 转换成图片文件格式
         */
        CategoryDataset categoryDataset = createDataSet();
        JFreeChart jFreeChart = createChart(categoryDataset);
        saveAsFile(jFreeChart,"chart/img/chart.jpg",600,400);

        /**
         * java的awt文件信息
         */
        ChartFrame cf = new ChartFrame("test",jFreeChart);
        cf.pack();
        cf.setVisible(true);
}
}
