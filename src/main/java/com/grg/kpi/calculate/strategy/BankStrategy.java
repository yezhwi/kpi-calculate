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

import com.grg.kpi.calculate.build.*;
import com.grg.kpi.calculate.function.KpiFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Lazy
@Component
public final class BankStrategy extends AbstractStrategy<BankKpi> implements CalculatingStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankStrategy.class);

    private KpiFunction<Data, Double> interestCoverageFunction = (x) -> x.getMtProfitTotal() != null && super.notNullAndZero(x.getMtInterestPayments()) ? (x.getMtProfitTotal() + x.getMtInterestPayments()) / x.getMtInterestPayments() : null;

    private KpiFunction<Data, Double> returtalAssetsFunction = (x) -> x.getMtProfitTotal() != null && x.getMtInterestPayments() != null && x.getMtTotalAssets() != null && x.getLastTotalAssets() != null && (x.getMtTotalAssets() + x.getLastTotalAssets()) != 0 ? ((x.getMtProfitTotal() + x.getMtInterestPayments()) / ((x.getMtTotalAssets() + x.getLastTotalAssets()) / 2)) * 100 : null;

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
    public BankKpi execute(Data data) {
        BankKpi bankKpi = new BankKpi();
        super.buildBaseKpi(data, bankKpi);
        bankKpi.setCostProfrgin(this.costProfrginFunction.execute(data));
        bankKpi.setProporoperatingRevenue(this.proporoperatingRevenueFunction.execute(data));
        bankKpi.setInterestCoverage(this.interestCoverageFunction.execute(data));
        bankKpi.setReturtalAssets(this.returtalAssetsFunction.execute(data));
        return bankKpi;
    }

    @Override
    public List<Standard> execute(List<? extends BaseKpi> objectValueList) {
        List<Standard> standardList = super.buildBaseKpiStandard(objectValueList);
        List<BankKpi> kpiValueList = (List<BankKpi>) objectValueList;
        Double[] var12StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(BankKpi::getInterestCoverage).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("INTEREST_COVERAGE").data(var12StandardArray).build());

        Double[] var13StandardArray = super.quartilesFunction.execute(kpiValueList.stream().map(BankKpi::getReturtalAssets).filter(super.notNull).sorted().toArray(Double[]::new));
        standardList.add(StandardBuilder.newBuilder().base("RETURTAL_ASSETS").data(var13StandardArray).build());

        standardList.add(StandardBuilder.newBuilder().base("ACCOUECERNOVER_RATE").build());

        standardList.add(StandardBuilder.newBuilder().base("CASH_FLOATIO").build());

        standardList.add(StandardBuilder.newBuilder().base("INVENTORY_TURNOVER").build());

        standardList.add(StandardBuilder.newBuilder().base("QUICK_RATIO").build());

        standardList.add(StandardBuilder.newBuilder().base("TURNURRENT_ASSETS").build());

        return standardList;
    }
}
