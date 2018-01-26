package com.grg.kpi.calculate.build;

/**
 * @Author:czq
 * @Description:
 * @Date: 10:53 2018/1/10
 * @Modified By:
 */
public class CommonKpi extends BaseKpi {

    private Double turnurrentAssets;

    private Double accouecernoverRate;

    private Double quickRatio;

    private Double cashFloatio;

    private Double interestCoverage;

    private Double inventoryTurnover;

    private Double returtalAssets;

    public Double getTurnurrentAssets() {
        return turnurrentAssets;
    }

    public void setTurnurrentAssets(Double turnurrentAssets) {
        this.turnurrentAssets = turnurrentAssets;
    }

    public Double getAccouecernoverRate() {
        return accouecernoverRate;
    }

    public void setAccouecernoverRate(Double accouecernoverRate) {
        this.accouecernoverRate = accouecernoverRate;
    }

    public Double getQuickRatio() {
        return quickRatio;
    }

    public void setQuickRatio(Double quickRatio) {
        this.quickRatio = quickRatio;
    }

    public Double getCashFloatio() {
        return cashFloatio;
    }

    public void setCashFloatio(Double cashFloatio) {
        this.cashFloatio = cashFloatio;
    }

    public Double getInterestCoverage() {
        return interestCoverage;
    }

    public void setInterestCoverage(Double interestCoverage) {
        this.interestCoverage = interestCoverage;
    }

    public Double getInventoryTurnover() {
        return inventoryTurnover;
    }

    public void setInventoryTurnover(Double inventoryTurnover) {
        this.inventoryTurnover = inventoryTurnover;
    }

    @Override
    public String toString() {
        return "CommonKpi{" +
                "turnurrentAssets=" + turnurrentAssets +
                ", accouecernoverRate=" + accouecernoverRate +
                ", quickRatio=" + quickRatio +
                ", cashFloatio=" + cashFloatio +
                ", interestCoverage=" + interestCoverage +
                ", inventoryTurnover=" + inventoryTurnover +
                "} " + super.toString();
    }

    public Double getReturtalAssets() {
        return returtalAssets;
    }

    public void setReturtalAssets(Double returtalAssets) {
        this.returtalAssets = returtalAssets;
    }
}
