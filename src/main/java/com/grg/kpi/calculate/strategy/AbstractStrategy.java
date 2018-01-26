package com.grg.kpi.calculate.strategy;

import com.grg.kpi.calculate.build.BaseKpi;
import com.grg.kpi.calculate.build.Data;
import com.grg.kpi.calculate.build.Standard;
import com.grg.kpi.calculate.build.StandardBuilder;
import com.grg.kpi.calculate.function.AbstractKpiFunction;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author:czq
 * @Description:
 * @Date: 9:29 2018/1/17
 * @Modified By:
 */
abstract class AbstractStrategy<R extends BaseKpi> extends AbstractKpiFunction {
    void buildBaseKpi(Data data, R kpi) {
        Assert.notNull(kpi, "kpi must be not null");
        kpi.setCyOperatingIncome(super.cyOperatingIncomeFunction.execute(data));
        kpi.setAssetLiabiratio(super.assetLiabiratioFunction.execute(data));
        kpi.setCashRecovassets(super.cashRecovassetsFunction.execute(data));
        kpi.setCyOperatingProfit(super.cyOperatingProfitFunction.execute(data));
        kpi.setCyTotalAssets(super.cyTotalAssetsFunction.execute(data));
        kpi.setCyTotalEquity(super.cyTotalEquityFunction.execute(data));
        kpi.setNetSellrate(super.netSellrateFunction.execute(data));
        kpi.setCostProfrgin(super.costProfrginFunction.execute(data));
        kpi.setProporoperatingRevenue(super.proporoperatingRevenueFunction.execute(data));
        kpi.setReturnetAssets(super.returnetAssetsFunction.execute(data));
        kpi.setSurpluuaranteeMultiple(super.surpluuaranteeMultipleFunction.execute(data));
        kpi.setTurnovotalAssets(super.turnovotalAssetsFunction.execute(data));
    }

    List<Standard> buildBaseKpiStandard(List<? extends BaseKpi> kpiValueList) {
        List<Standard> standardList = new ArrayList<>();
        //
        Double[] var0StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(BaseKpi::getCyOperatingIncome).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("CY_OPERATING_INCOME").data(var0StandardArray).build());
        //
        Double[] var1StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(BaseKpi::getAssetLiabiratio).filter(super.notNull).sorted(Comparator.comparingDouble(Double::doubleValue).reversed()).toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("ASSET_LIABIRATIO").data(var1StandardArray).build());
        //
        Double[] var2StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(BaseKpi::getCashRecovassets).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("CASH_RECOVASSETS").data(var2StandardArray).build());

        Double[] var3StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(BaseKpi::getCostProfrgin).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("COST_PROFRGIN").data(var3StandardArray).build());

        Double[] var4StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(BaseKpi::getCyOperatingProfit).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("CY_OPERATING_PROFIT").data(var4StandardArray).build());

        Double[] var5StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(BaseKpi::getCyTotalAssets).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("CY_TOTAL_ASSETS").data(var5StandardArray).build());

        Double[] var6StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(BaseKpi::getCyTotalEquity).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("CY_TOTAL_EQUITY").data(var6StandardArray).build());

        Double[] var7StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(BaseKpi::getNetSellrate).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("NET_SELLRATE").data(var7StandardArray).build());

        Double[] var8StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(BaseKpi::getProporoperatingRevenue).filter(super.notNull).sorted(Comparator.comparingDouble(Double::doubleValue).reversed()).toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("PROPOROPERATING_REVENUE").data(var8StandardArray).build());

        Double[] var9StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(BaseKpi::getReturnetAssets).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("RETURNET_ASSETS").data(var9StandardArray).build());

        Double[] var10StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(BaseKpi::getSurpluuaranteeMultiple).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("SURPLUUARANTEE_MULTIPLE").data(var10StandardArray).build());

        Double[] var11StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(BaseKpi::getTurnovotalAssets).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("TURNOVOTAL_ASSETS").data(var11StandardArray).build());
        return standardList;
    }
}
