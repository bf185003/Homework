package org.example;

public class Activity {
    // 计算酶活性的方法 (简化模型)
    public static double calculateActivity(double temp, double pH) {
        double tempOpt = 50.0;  // 最适温度50°C
        double pHOpt = 6.8;     // 最适pH6.8

        // 温度影响因子(高斯分布)
        double tempFactor = Math.exp(-Math.pow((temp - tempOpt)/15.0, 2));

        // pH影响因子(高斯分布)
        double pHFactor = Math.exp(-Math.pow((pH - pHOpt)/1.5, 2));

        return tempFactor * pHFactor;  // 综合活性
    }

    public static double calculateReactionRate(
            double substrateConc,
            double temp,
            double pH,
            String inhibitorType,
            double inhibitorConc) {

        // 温度影响(Arrhenius方程)
        double Ea = 50_000; // 活化能(J/mol)
        double A = 1e12;     // 指前因子
        double kcat = A * Math.exp(-Ea/(8.314*(temp+273.15)));

        // pH影响(钟形曲线)
        double pHOpt = 6.8;
        double pHFactor = 1/(1 + Math.pow(10, pHOpt-pH) + Math.pow(10, pH-pHOpt));

        // 米氏方程基础
        double Km = 0.8; // 米氏常数
        double Vmax = kcat * 0.001 * pHFactor; // 假设酶浓度1μM

        // 抑制剂修正
        double Ki = 0.1; // 抑制常数
        if ("competitive".equals(inhibitorType)) {
            Km = Km * (1 + inhibitorConc/Ki);
        } else if ("noncompetitive".equals(inhibitorType)) {
            Vmax = Vmax / (1 + inhibitorConc/Ki);
        }

        return (Vmax * substrateConc) / (Km + substrateConc);
    }

}
