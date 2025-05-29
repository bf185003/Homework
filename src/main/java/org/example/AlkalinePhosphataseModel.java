package org.example;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;

public class AlkalinePhosphataseModel extends DataSet{

    //酶和PH值的关系
    @Override
    public void createPHActivityDataset() {
        datasetpH.addValue(9, "Series1", "Category1");
        datasetpH.addValue(5, "Series1", "Category2");
        datasetpH.addValue(4, "Series1", "Category3");
        datasetpH.addValue(5, "Series1", "Category4");
    }

    //淀粉酶和温度的关系
    @Override
    public void createTempActivityDataset() {
        datasetTemp.addValue(12, "Series1", "Category1");
        datasetTemp.addValue(8, "Series1", "Category2");
        datasetTemp.addValue(7, "Series1", "Category3");
        datasetTemp.addValue(10, "Series1", "Category4");
    }

    @Override
    public void createSubstrateKineticsDataset() {
        for (int i = 0; i < 100; i++) {
            datasetSubstrate.addValue((int)(Math.random() * 100), "Series1", String.valueOf(i));
        }
    }
}
