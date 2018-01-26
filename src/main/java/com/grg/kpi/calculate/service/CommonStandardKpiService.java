package com.grg.kpi.calculate.service;

import com.grg.kpi.calculate.build.BaseKpi;
import com.grg.kpi.calculate.build.Data;
import com.grg.kpi.calculate.build.KpiBuilder;
import com.grg.kpi.calculate.build.Standard;
import com.grg.kpi.calculate.strategy.KpiCalculator;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author:czq
 * @Description:
 * @Date: 16:11 2018/1/10
 * @Modified By:
 */
@Lazy
@Service
public final class CommonStandardKpiService extends AbstractStandardKpiService<Data, Standard> {

    @Override
    //包含标的企业
    protected List<Data> getData() {
        return super.getDataList();
    }

    @Override
    protected List<Data> sortAndObtainEp() {
        Map<String, List<Data>> map = this.getData().stream().filter(data -> StringUtils.hasText(data.getIndustryName())).collect(Collectors.groupingBy(Data::getIndustryName, Collectors.toList()));
        if (map != null && !map.isEmpty()) {
            Assert.isTrue(map.size() == 1, "行业类型不唯一");
            for (Map.Entry<String, List<Data>> entry : map.entrySet()) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    protected List<BaseKpi> calculateKpi(List<Data> benchMarkingList) {
        Assert.notNull(benchMarkingList, "未获取到有效对标数据，请检查是否");
        List<BaseKpi> kpiList = new ArrayList<>();
        benchMarkingList.forEach(data -> {
            BaseKpi commonKpi = KpiBuilder
                    .newBuilder()
                    .base()
                    .data(data)
                    .strategy(super.getStrategy())
                    .build();
            kpiList.add(commonKpi);
        });
        return kpiList;
    }

    @Override
    protected List<Standard> standardComposite(List<? extends BaseKpi> kpis) {
        return KpiCalculator.newBuilder().strategy(super.getStrategy()).execute(kpis);
    }
}
