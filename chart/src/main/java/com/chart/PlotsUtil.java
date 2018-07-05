package com.chart;

import org.jfree.chart.*;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * jfreechart生成折线图
 */
public class PlotsUtil {

    /**
     * 基础信息设置
     * @return
     */
    public static CategoryDataset createDataset(){
        String[] rowKeys = {"A平台"};
        String[] colKeys =  {"0:00", "1:00", "2:00", "7:00", "8:00", "9:00",
                "10:00", "11:00", "12:00", "13:00", "16:00", "20:00", "21:00",
                "23:00"};
        double[][] data = {{4, 3, 1, 1, 1, 1, 2, 2, 2, 1, 8, 2, 1, 1},};
        return DatasetUtilities.createCategoryDataset(rowKeys,colKeys,data);
    }

    /**
     * 该方法还存在中文乱码问题
     * @param categoryDataset
     * @return
     */
    public static JFreeChart createChart(CategoryDataset categoryDataset) {
        //设置字体解决中文乱码问题
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,18));
        standardChartTheme.setRegularFont(new Font("宋体",Font.PLAIN,12));
        standardChartTheme.setLargeFont(new Font("宋体",Font.PLAIN,12));

        ChartFactory.setChartTheme(standardChartTheme);

        JFreeChart jFreeChart = ChartFactory.createLineChart("不同类别按小时计算拆线图", // 标题
                "年分", // categoryAxisLabel （category轴，横轴，X轴标签）
                "数量", // valueAxisLabel（value轴，纵轴，Y轴的标签）
                categoryDataset, // dataset
                PlotOrientation.VERTICAL, true, // legend
                false, // tooltips
                false); // URLs
        CategoryPlot plot = (CategoryPlot)jFreeChart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.5f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("暂无数据显示");
        plot.setBackgroundPaint(Color.WHITE);
        // 设置网格竖线颜色
        plot.setDomainGridlinePaint(Color.pink);
        // 设置网格横线颜色
        plot.setRangeGridlinePaint(Color.pink);
        plot.setNoDataMessage("暂无数据显示！");
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(true); // series 点（即数据点）可见
        renderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
        renderer.setUseSeriesOffset(true); // 设置偏移量
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        return jFreeChart;


    }

    /**
     * 将散点图转换成文件
     */
    public static void saveAsFile(JFreeChart chart,String outputPath,int weight,int height){
        FileOutputStream out = null;
        try{
            File outFile = new File(outputPath);

            if(!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outputPath);

            ChartUtilities.writeChartAsJPEG(out,chart,weight,height);

            out.flush();

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
        CategoryDataset dataset = createDataset();
        JFreeChart jFreeChart = createChart(dataset);
        saveAsFile(jFreeChart,"chart/img/line.jpg",800,600);
        ChartFrame cf = new ChartFrame("test",jFreeChart);
        cf.pack();
        cf.setVisible(true);
}
}
