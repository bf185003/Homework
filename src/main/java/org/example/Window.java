package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Window {
    //attribute
    double substrate;              //底物浓度
    double temperature;            //温度
    double phValue;                //ph值
    int inhibitor;                 //抑制剂类型：0是没有抑制剂，1是竞争性,2是非竞争性， 3是变构调节
    double getInhibitorValue;      //抑制剂强度
    String substrateType;
    int substrateCombo;


    //window Components
    JFrame frame;
    JPanel controlPanel; // Top控制面板
    JPanel controlPanel1;//底部的竞争性radio button

    JLabel substrateLabel; //底物浓度
    JLabel pHLabel;// ph Label
    JLabel tempLabel;// 温度lable
    JSlider substrateSlider; // 底物浓度滑杆
    JSlider tempSlider;//温度调节滑杆
    JSlider pHSlider;//ph值滑杆

    JSlider inhibitorSlider;// 底部抑制剂强度滑杆
    JLabel inhibitorLabel;//抑制剂强度label

    JComboBox<String> comboBox;

    JButton updateButton;//更新图表按钮

    TwoDChart twoDChart;

    private ArrayList<DataSet> datasetList = new ArrayList<DataSet>(3); //

    public void DrawWindow(){

        frame = new JFrame("Enzyme kinetics simulation");   // 创建窗口
        DrawComponent();                        //绘制窗口组件，监听事件

        frame.setVisible(true);                 // 显示窗口

        //造数据
        createData();

        //绘制chart
        twoDChart = new TwoDChart();
        twoDChart.DrawChart(frame);

    }

    private void createData(){
        datasetList.add(new AlphaAmylaseModel());
        datasetList.add(new PepsinModel());
        datasetList.add(new AlkalinePhosphataseModel());
    }

    private void DrawComponent(){
        // 创建Swing 窗口

        frame.setSize(1200, 800);                 // 设置窗口大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // 创建控制面板（顶部的组件）
        controlPanel = new JPanel();

        //底物浓度调节组件,滑块slider
        substrateLabel = new JLabel("底物浓度 [S]:50");
        substrateSlider = new JSlider(0, 100, 50); //底物浓度0-100，初始值50
        controlPanel.add(substrateLabel);
        controlPanel.add(substrateSlider);

        //温度调节组件,滑块slider
        tempLabel = new JLabel("温度 (°C):25");
        tempSlider = new JSlider(0, 100, 25); //温度范围0-100，初始25
        controlPanel.add(tempLabel);//添加温度
        controlPanel.add(tempSlider);

        //ph值调节组件,滑块slider
        pHLabel = new JLabel("当前 pH: 7.0");
        pHSlider = new JSlider(0, 140, 70); // 范围 0-14（pH 范围），初始值 7（中性）
        pHSlider.setMajorTickSpacing(2); // 设置刻度间隔
        controlPanel.add(pHLabel);
        controlPanel.add(pHSlider);

        //添加选择酶的组件
        String[] substrate = {"AlphaAmylase", "Pepsin", "Alkaline", "All"};
        substrateType = "AlphaAmylase";
        comboBox = new JComboBox<>(substrate);
        controlPanel.add(comboBox);

        //更新图表的按钮 button
        updateButton = new JButton("update");
        controlPanel.add(updateButton); //刷新按钮

        //竞争性等等radio button
        controlPanel1 = new JPanel();

        ButtonGroup inhibitorGroup = new ButtonGroup();
        JRadioButton noInhibitorButton = new JRadioButton("无抑制剂", true);
        JRadioButton competitiveButton = new JRadioButton("竞争性抑制剂");
        JRadioButton nonCompetitiveButton = new JRadioButton("非竞争性抑制剂");
        JRadioButton allostericButton = new JRadioButton("变构调节");

        inhibitorGroup.add(noInhibitorButton);
        inhibitorGroup.add(competitiveButton);
        inhibitorGroup.add(nonCompetitiveButton);
        inhibitorGroup.add(allostericButton);

        controlPanel1.add(new JLabel("选择抑制剂类型:"));
        controlPanel1.add(noInhibitorButton);
        controlPanel1.add(competitiveButton);
        controlPanel1.add(nonCompetitiveButton);
        controlPanel1.add(allostericButton);

        // 抑制剂强度滑块slider
        inhibitorLabel = new JLabel("抑制剂强度: 0");
        inhibitorSlider = new JSlider(0, 10, 0); // 范围 0-10
        controlPanel1.add(inhibitorLabel);
        controlPanel1.add(inhibitorSlider);



        // 将上述组件添加到窗口，三个滑块放在上面，抑制剂选择放在下面
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(controlPanel1, BorderLayout.SOUTH);

        componentListen(); //监听Slider组件事件
    }

    private void componentListen(){
        // 底物浓度滑块slider事件监听
        substrateSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                substrate = substrateSlider.getValue();
                substrateLabel.setText("底物浓度 [S]:" + substrate); // 更新底物浓度标签显示

            }
        });

        //pH 滑块slider的事件监听
        pHSlider.addChangeListener(e -> {
            phValue = pHSlider.getValue() / 10.0; // 转换为 0.0-14.0
            pHLabel.setText(String.format("当前 pH: %.1f", phValue));

            if (phValue < 3 || phValue > 10) {
                JOptionPane.showMessageDialog(frame,
                        "警告：极端 pH 值（<3 或 >10）可能导致酶失活！",
                        "pH 警告",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // 温度滑块slider的事件监听
        tempSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                temperature = tempSlider.getValue();
                tempLabel.setText("温度: " + temperature + "°C"); // 更新标签显示

                // 检测高温变性
                if (temperature > 80) {
                    JOptionPane.showMessageDialog(frame,
                            "警告：温度超过80°C，酶将变性！",
                            "高温警告",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        //combo监听器
        comboBox.addActionListener(e -> {
            JComboBox<?> combo = (JComboBox<?>) e.getSource();
            this.substrateType  = (String) combo.getSelectedItem();
            this.substrateCombo = combo.getSelectedIndex();
            // 根据选择更新UI或数据，可以combo选择变更，也可以用按钮变更
            twoDChart.updatechart(datasetList.get(substrateCombo).getSeriespH(),datasetList.get(substrateCombo).getSeriesTemp());
            //updateChartsForEnzyme();
        });

        //update按钮按下的监听
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateChartsForEnzyme();  // 调用你定义的函数
            }
        });
    }

    //刷新下面的3个chart，这个在按钮按下时调用，以及初始化的时候调用
    private void updateChartsForEnzyme(){
        datasetList.get(substrateCombo).createSubstrateKineticsDataset();
        twoDChart.updateBottomChart(datasetList.get(substrateCombo).getSeriesSubstrate());
    }
}
