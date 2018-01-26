/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publisher, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.grg.kpi.calculate.strategy;

import com.grg.kpi.calculate.build.BaseKpi;
import com.grg.kpi.calculate.build.Data;
import com.grg.kpi.calculate.build.Standard;
import com.grg.kpi.calculate.build.StandardBuilder;
import com.grg.kpi.calculate.function.KpiFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Lazy
@Component
public final class InsuranceStrategy extends AbstractStrategy<BaseKpi> implements CalculatingStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceStrategy.class);

    /*
     * 成本费用利润率
     */
    private KpiFunction<Data, Double> costProfrginFunction = (x) ->
    {
        if (x.getMtProfitTotal() != null && x.getBusinessExpend() != null && x.getMtBusinessTaxadd() != null) {
            if (x.getBusinessManagementFees() != null && (x.getBusinessExpend() + x.getMtBusinessTaxadd() + x.getBusinessManagementFees()) != 0)
                return (x.getMtProfitTotal() / ((x.getBusinessExpend() + x.getMtBusinessTaxadd() + x.getBusinessManagementFees()))) * 100;
            if (x.getMtCostSales() != null || x.getMtManagementFees() != null || x.getMtFinanceCharges() != null) {
                double tan = (x.getMtCostSales() == null ? 0.0D : x.getMtCostSales()) + (x.getMtManagementFees() == null ? 0.0D : x.getMtManagementFees()) + (x.getMtFinanceCharges() == null ? 0.0D : x.getMtFinanceCharges()) + x.getBusinessExpend() + x.getMtBusinessTaxadd();
                if (tan != 0)
                    return (x.getMtProfitTotal() / tan) * 100;
            }
        }
        return null;
    };

    /*
     * 成本费用占收入比 (越小越好)
     */
    private KpiFunction<Data, Double> proporoperatingRevenueFunction = (x) -> {
        if (this.notNullAndZero(x.getMtOperatingIncome()) && x.getBusinessExpend() != null && x.getMtBusinessTaxadd() != null) {
            if (x.getBusinessManagementFees() != null)
                return ((x.getBusinessExpend() + x.getMtBusinessTaxadd() + x.getBusinessManagementFees()) / x.getMtOperatingIncome()) * 100;
            if (x.getMtCostSales() != null || x.getMtManagementFees() != null || x.getMtFinanceCharges() != null) {
                double tan = (x.getMtCostSales() == null ? 0.0D : x.getMtCostSales()) + (x.getMtManagementFees() == null ? 0.0D : x.getMtManagementFees()) + (x.getMtFinanceCharges() == null ? 0.0D : x.getMtFinanceCharges()) + x.getBusinessExpend() + x.getMtBusinessTaxadd();
                if (tan != 0)
                    return (tan / x.getMtOperatingIncome()) * 100;
            }
        }
        return null;
    };

    @Override
    public BaseKpi execute(Data data) {
        BaseKpi kpi = new BaseKpi();
        super.buildBaseKpi(data, kpi);
        kpi.setCostProfrgin(this.costProfrginFunction.execute(data));
        kpi.setProporoperatingRevenue(this.proporoperatingRevenueFunction.execute(data));
        return kpi;
    }

    @Override
    public List<Standard> execute(List<? extends BaseKpi> objectValueList) {
        List<Standard> standardList = super.buildBaseKpiStandard(objectValueList);
        standardList.add(StandardBuilder.newBuilder().base("INTEREST_COVERAGE").build());

        standardList.add(StandardBuilder.newBuilder().base("RETURTAL_ASSETS").build());

        standardList.add(StandardBuilder.newBuilder().base("ACCOUECERNOVER_RATE").build());

        standardList.add(StandardBuilder.newBuilder().base("CASH_FLOATIO").build());

        standardList.add(StandardBuilder.newBuilder().base("INVENTORY_TURNOVER").build());

        standardList.add(StandardBuilder.newBuilder().base("QUICK_RATIO").build());

        standardList.add(StandardBuilder.newBuilder().base("TURNURRENT_ASSETS").build());
        return standardList;
    }
}
