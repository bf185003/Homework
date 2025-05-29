package org.example;

public class PepsinModel extends DataSet{
    @Override
    public void createPHActivityDataset() {
        datasetpH.addValue(19, "Series1", "Category1");
        datasetpH.addValue(21, "Series1", "Category2");
        datasetpH.addValue(3, "Series1", "Category3");
        datasetpH.addValue(34, "Series1", "Category4");

    }

    //淀粉酶和温度的关系
    @Override
    public void createTempActivityDataset() {
        datasetTemp.addValue(12, "Series1", "Category1");
        datasetTemp.addValue(44, "Series1", "Category2");
        datasetTemp.addValue(34, "Series1", "Category3");
        datasetTemp.addValue(117, "Series1", "Category4");
    }

    @Override
    public void createSubstrateKineticsDataset() {
        for (int i = 0; i < 100; i++) {
            datasetSubstrate.addValue((int)(Math.random() * 100), "Series1", String.valueOf(i));
        }

    }
}
