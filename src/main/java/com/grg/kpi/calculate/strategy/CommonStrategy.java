package com.grg.kpi.calculate.strategy;

import com.grg.kpi.calculate.build.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Lazy
@Component
public final class CommonStrategy extends AbstractStrategy<CommonKpi> implements CalculatingStrategy {

    private static final Logger logger = LoggerFactory.getLogger(CommonStrategy.class);

    @Override
    public CommonKpi execute(Data data) {
        CommonKpi commonKpi = new CommonKpi();
        super.buildBaseKpi(data, commonKpi);
        commonKpi.setAccouecernoverRate(super.accouecernoverRateFunction.execute(data));
        commonKpi.setCashFloatio(super.cashFloatioFunction.execute(data));
        commonKpi.setInterestCoverage(super.interestCoverageFunction.execute(data));
        commonKpi.setInventoryTurnover(super.inventoryTurnoverFunction.execute(data));
        commonKpi.setQuickRatio(super.quickRatioFunction.execute(data));
        commonKpi.setReturtalAssets(super.returtalAssetsFunction.execute(data));
        commonKpi.setTurnurrentAssets(super.turnurrentAssetsFunction.execute(data));
        return commonKpi;
    }

    @Override
    public List<Standard> execute(List<? extends BaseKpi> objectValueList) {
        List<CommonKpi> kpiValueList = (List<CommonKpi>) objectValueList;
        List<Standard> standardList = super.buildBaseKpiStandard(objectValueList);
        Double[] var12StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(CommonKpi::getInterestCoverage).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("INTEREST_COVERAGE").data(var12StandardArray).build());

        Double[] var13StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(CommonKpi::getReturtalAssets).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("RETURTAL_ASSETS").data(var13StandardArray).build());

        Double[] var14StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(CommonKpi::getAccouecernoverRate).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("ACCOUECERNOVER_RATE").data(var14StandardArray).build());

        Double[] var15StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(CommonKpi::getCashFloatio).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("CASH_FLOATIO").data(var15StandardArray).build());

        Double[] var16StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(CommonKpi::getInventoryTurnover).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("INVENTORY_TURNOVER").data(var16StandardArray).build());

        Double[] var17StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(CommonKpi::getQuickRatio).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("QUICK_RATIO").data(var17StandardArray).build());

        Double[] var18StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(CommonKpi::getTurnurrentAssets).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("TURNURRENT_ASSETS").data(var18StandardArray).build());
        return standardList;
    }
}
