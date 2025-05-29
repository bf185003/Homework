package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

public class TwoDChart {
    JFreeChart chart1;
    JFreeChart chart2;
    JFreeChart chart;

    ChartPanel panel1;
    ChartPanel panel2;
    ChartPanel chartPanel;

    JPanel topPanel;
    JPanel bottomPanel;
    DefaultCategoryDataset dataset1;
    DefaultCategoryDataset dataset2;
    DefaultCategoryDataset dataset;

    public void DrawChart(JFrame frame){

        // Step 1: 测试数据集，显示line chart 专用
        dataset1 = new DefaultCategoryDataset();
        dataset2 = new DefaultCategoryDataset();
        dataset = new DefaultCategoryDataset();
        dataset.addValue(1, "Series1", "Category1");
        dataset.addValue(4, "Series1", "Category2");
        dataset.addValue(3, "Series1", "Category3");
        dataset.addValue(5, "Series1", "Category4");

        dataset.addValue(3, "Series2", "Category1");
        dataset.addValue(6, "Series2", "Category2");
        dataset.addValue(8, "Series2", "Category3");
        dataset.addValue(10, "Series2", "Category4");

        // Step 2: create line chart
        //先做上面两个linchart
        chart1 = ChartFactory.createLineChart("The Impact of Temperature on Enzyme Activity",
                "Temperature",              // X 轴标签
                "Intensity of Reaction",                 // Y 轴标签
                dataset1,                 // 数据集
                PlotOrientation.VERTICAL,// 图表方向
                false,                    // 是否显示图例
                true,                    // 是否生成工具提示
                false                    // 是否生成 URL 链接
                );
        chart2 = ChartFactory.createLineChart("The Impact of pH on Enzyme Activity",
                "pH",              // X 轴标签
                "Intensity of Reaction",                 // Y 轴标签
                dataset2,                 // 数据集
                PlotOrientation.VERTICAL,// 图表方向
                false,                    // 是否显示图例
                true,                    // 是否生成工具提示
                false                    // 是否生成 URL 链接
        );


        chart = ChartFactory.createLineChart(
                " enzyme dynamic 2D Line Chart Display",          // 图表标题
                "concentration of substrate(S)",              // X 轴标签
                "Intensity of Reaction",                 // Y 轴标签
                dataset,                 // 数据集
                PlotOrientation.VERTICAL,// 图表方向
                false,                    // 是否显示图例
                true,                    // 是否生成工具提示
                false                    // 是否生成 URL 链接
        );

        //添加line chart 图表，放在中间
        panel1 = new ChartPanel(chart1);
        panel2 = new ChartPanel(chart2);
        chartPanel = new ChartPanel(chart);   // 创建2d图表面板

        // 3. 设置面板首选大小（重要！）
        panel1.setPreferredSize(new Dimension(450, 300));
        panel2.setPreferredSize(new Dimension(450, 300));
        chartPanel.setPreferredSize(new Dimension(900, 400));

        // 4. 创建上部面板（放置chart1和chart2）
        topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(36, 10, 10, 10));
        topPanel.add(panel1);
        topPanel.add(panel2);

        // 5. 创建下部面板（放置chart3）
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(chartPanel);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.CENTER);

        frame.revalidate();      // 重新计算布局
        frame.repaint();         // 重绘窗口
    }

    public void updatechart(DefaultCategoryDataset setpH, DefaultCategoryDataset setTemp){
        CategoryPlot plot1 = chart1.getCategoryPlot();
        plot1.setDataset(setTemp);

        CategoryPlot plot2 = chart2.getCategoryPlot();
        plot2.setDataset(setpH);

//        dataset.addValue(3, "Series2", "Category5");
//        dataset.addValue(6, "Series2", "Category6");
//        dataset.addValue(8, "Series2", "Category7");
//        dataset.addValue(10, "Series2", "Category8");
    }

    public void updateBottomChart(DefaultCategoryDataset setSubstrate){
        CategoryPlot plot3 = chart.getCategoryPlot();
        plot3.setDataset(setSubstrate);

    }
}
