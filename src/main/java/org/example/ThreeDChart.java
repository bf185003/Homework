package org.example;
import org.jfree.chart3d.data.xyz.XYZSeries;
import org.jfree.chart3d.data.xyz.XYZSeriesCollection;
import org.jfree.chart3d.*;
import org.jfree.chart3d.Chart3D;

public class ThreeDChart {


    public void Draw(){

        // 示例代码
        XYZSeries series = new XYZSeries("α-淀粉酶活性");

        // 2. 生成网格数据 (温度20-70°C, pH4-9)
        for (double temp = 20; temp <= 70; temp += 2) {
            for (double pH = 4.0; pH <= 9.0; pH += 0.25) {
                double activity = Activity.calculateActivity(temp, pH);
                series.add(temp, pH, activity);
            }
        }
        XYZSeriesCollection dataset3d = new XYZSeriesCollection();
        dataset3d.add(series);

// 添加更多数据点...er
        Chart3D chart1 = Chart3DFactory.createScatterChart(
                "3D Characterization of α-Amylase Activity as a Function of Temperature and pH",  // 标题
                "",
                dataset3d,
                "Temperature (°C)",                // X轴标签
                "pH",                     // Y轴标签
                "Activity of PPO"                   // Z轴标签
        );
        Chart3DPanel chart3DPanel1 = new Chart3DPanel(chart1);

        //添加line chart 图表，放在中间
        //ChartPanel chartPanel = new ChartPanel(chart);   // 创建图表面板
        //frame.add(chart3DPanel1);                           // 将图表面板添加到窗口



    }
}
