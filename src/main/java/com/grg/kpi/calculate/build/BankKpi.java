package com.grg.kpi.calculate.build;

/**
 * @Author:czq
 * @Description:
 * @Date: 10:53 2018/1/10
 * @Modified By:
 */
public class BankKpi extends BaseKpi {

    private Double interestCoverage;

    private Double returtalAssets;

    public Double getInterestCoverage() {
        return interestCoverage;
    }

    public void setInterestCoverage(Double interestCoverage) {
        this.interestCoverage = interestCoverage;
    }

    public Double getReturtalAssets() {
        return returtalAssets;
    }

    public void setReturtalAssets(Double returtalAssets) {
        this.returtalAssets = returtalAssets;
    }
}
