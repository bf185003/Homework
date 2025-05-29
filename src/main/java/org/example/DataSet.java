package org.example;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;

public abstract class DataSet {
    // 公共参数
    protected double[] pHRange; // min, max, step
    protected double[] tempRange;
    protected double[] substrateRange;

    protected DefaultCategoryDataset  datasetpH;
    protected DefaultCategoryDataset  datasetTemp;
    protected DefaultCategoryDataset  datasetSubstrate;

    public DataSet(){
        datasetpH = new DefaultCategoryDataset();
        datasetTemp = new DefaultCategoryDataset();
        datasetSubstrate = new DefaultCategoryDataset();
        createPHActivityDataset();
        createTempActivityDataset();
    }

    // 抽象方法 - 子类必须实现
    public abstract void createPHActivityDataset();
    public abstract void createTempActivityDataset();
    public abstract void createSubstrateKineticsDataset();

    public DefaultCategoryDataset  getSeriespH(){return datasetpH;}
    public DefaultCategoryDataset  getSeriesTemp(){return datasetTemp;}
    public DefaultCategoryDataset  getSeriesSubstrate(){return datasetSubstrate;}

}
