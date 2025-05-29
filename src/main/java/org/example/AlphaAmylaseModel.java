package org.example;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class AlphaAmylaseModel extends DataSet{

    //淀粉酶和PH值的关系
    @Override
    public void createPHActivityDataset() {
        datasetpH.addValue(1, "Series1", "Category1");
        datasetpH.addValue(4, "Series1", "Category2");
        datasetpH.addValue(3, "Series1", "Category3");
        datasetpH.addValue(5, "Series1", "Category4");

    }

    //淀粉酶和温度的关系
    @Override
    public void createTempActivityDataset() {
        datasetTemp.addValue(2, "Series1", "Category1");
        datasetTemp.addValue(5, "Series1", "Category2");
        datasetTemp.addValue(6, "Series1", "Category3");
        datasetTemp.addValue(10, "Series1", "Category4");
    }

    //淀粉酶的底物活性
    @Override
    public void createSubstrateKineticsDataset() {
        datasetSubstrate.clear();
        for (int i = 0; i < 100; i++) {
            datasetSubstrate.addValue(
            Activity.calculateReactionRate(i,40,7,"competitive",10),
            "Series1", String.valueOf(i));
        }
    }
}
