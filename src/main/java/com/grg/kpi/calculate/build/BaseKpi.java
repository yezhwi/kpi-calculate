package com.grg.kpi.calculate.build;

/**
 * @Author:czq
 * @Description:
 * @Date: 10:53 2018/1/10
 * @Modified By:
 */
public class BaseKpi {

    private Double cyOperatingIncome;

    private Double cyOperatingProfit;

    private Double cyTotalAssets;

    private Double cyTotalEquity;

    private Double turnovotalAssets;

    private Double assetLiabiratio;

    private Double cashRecovassets;

    private Double surpluuaranteeMultiple;

    private Double returnetAssets;

    private Double costProfrgin;

    private Double netSellrate;

    private Double proporoperatingRevenue;

    public Double getCyOperatingIncome() {
        return cyOperatingIncome;
    }

    public void setCyOperatingIncome(Double cyOperatingIncome) {
        this.cyOperatingIncome = cyOperatingIncome;
    }

    public Double getCyOperatingProfit() {
        return cyOperatingProfit;
    }

    public void setCyOperatingProfit(Double cyOperatingProfit) {
        this.cyOperatingProfit = cyOperatingProfit;
    }

    public Double getCyTotalAssets() {
        return cyTotalAssets;
    }

    public void setCyTotalAssets(Double cyTotalAssets) {
        this.cyTotalAssets = cyTotalAssets;
    }

    public Double getCyTotalEquity() {
        return cyTotalEquity;
    }

    public void setCyTotalEquity(Double cyTotalEquity) {
        this.cyTotalEquity = cyTotalEquity;
    }

    public Double getTurnovotalAssets() {
        return turnovotalAssets;
    }

    public void setTurnovotalAssets(Double turnovotalAssets) {
        this.turnovotalAssets = turnovotalAssets;
    }

    public Double getAssetLiabiratio() {
        return assetLiabiratio;
    }

    public void setAssetLiabiratio(Double assetLiabiratio) {
        this.assetLiabiratio = assetLiabiratio;
    }

    public Double getCashRecovassets() {
        return cashRecovassets;
    }

    public void setCashRecovassets(Double cashRecovassets) {
        this.cashRecovassets = cashRecovassets;
    }

    public Double getSurpluuaranteeMultiple() {
        return surpluuaranteeMultiple;
    }

    public void setSurpluuaranteeMultiple(Double surpluuaranteeMultiple) {
        this.surpluuaranteeMultiple = surpluuaranteeMultiple;
    }

    public Double getReturnetAssets() {
        return returnetAssets;
    }

    public void setReturnetAssets(Double returnetAssets) {
        this.returnetAssets = returnetAssets;
    }

    public Double getCostProfrgin() {
        return costProfrgin;
    }

    public void setCostProfrgin(Double costProfrgin) {
        this.costProfrgin = costProfrgin;
    }

    public Double getNetSellrate() {
        return netSellrate;
    }

    public void setNetSellrate(Double netSellrate) {
        this.netSellrate = netSellrate;
    }

    public Double getProporoperatingRevenue() {
        return proporoperatingRevenue;
    }

    public void setProporoperatingRevenue(Double proporoperatingRevenue) {
        this.proporoperatingRevenue = proporoperatingRevenue;
    }

    @Override
    public String toString() {
        return "BaseKpi{" +
                ", cyOperatingIncome=" + cyOperatingIncome +
                ", cyOperatingProfit=" + cyOperatingProfit +
                ", cyTotalAssets=" + cyTotalAssets +
                ", cyTotalEquity=" + cyTotalEquity +
                ", turnovotalAssets=" + turnovotalAssets +
                ", assetLiabiratio=" + assetLiabiratio +
                ", cashRecovassets=" + cashRecovassets +
                ", surpluuaranteeMultiple=" + surpluuaranteeMultiple +
                ", returnetAssets=" + returnetAssets +
                ", costProfrgin=" + costProfrgin +
                ", netSellrate=" + netSellrate +
                ", proporoperatingRevenue=" + proporoperatingRevenue +
                '}';
    }
}
