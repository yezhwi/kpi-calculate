package com.grg.kpi.calculate.function;

import com.grg.kpi.calculate.build.Data;
import org.apache.commons.math3.util.FastMath;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @Author:czq
 * @Description:
 * @Date: 9:16 2018/1/10
 * @Modified By:
 */
public abstract class AbstractKpiFunction {
    /*
     * 营业收入增长率
     */
    protected KpiFunction<Data, Double> cyOperatingIncomeFunction = (x) -> x.getMtOperatingIncome() != null && this.notNullAndZero(x.getLastOperatingIncome()) ? ((x.getMtOperatingIncome() - x.getLastOperatingIncome()) / x.getLastOperatingIncome()) * 100 : null;
    /*
     * 营业利润增长率
     */
    protected KpiFunction<Data, Double> cyOperatingProfitFunction = (x) -> x.getMtOperatingProfit() != null && this.notNullAndZero(x.getLastOperatingProfit()) ? ((x.getMtOperatingProfit() - x.getLastOperatingProfit()) / x.getLastOperatingProfit()) * 100 : null;
    /*
     * 总资产增长率
     */
    protected KpiFunction<Data, Double> cyTotalAssetsFunction = (x) -> x.getMtTotalAssets() != null && this.notNullAndZero(x.getLastTotalAssets()) ? ((x.getMtTotalAssets() - x.getLastTotalAssets()) / x.getLastTotalAssets()) * 100 : null;
    /*
     * 资本保值增值率
     */
    protected KpiFunction<Data, Double> cyTotalEquityFunction = (x) -> x.getMtTotalEquity() != null && this.notNullAndZero(x.getLastTotalEquity()) ? (x.getMtTotalEquity() / x.getLastTotalEquity()) * 100 : null;
    /*
     * 流动资产周转率
     */
    protected KpiFunction<Data, Double> turnurrentAssetsFunction = (x) -> x.getMtOperatingIncome() != null && x.getMtCurrentAssets() != null && x.getLastCurrentAssets() != null && (x.getMtCurrentAssets() + x.getLastCurrentAssets()) != 0 ? (x.getMtOperatingIncome() * (12 / x.getQuarterId())) / ((x.getMtCurrentAssets() + x.getLastCurrentAssets()) / 2) : null;
    /*
     * 应收账款周转率
     */
    protected KpiFunction<Data, Double> accouecernoverRateFunction = (x) -> x.getMtOperatingIncome() != null && x.getMtAccountsReceiv() != null && x.getLastAccountsReceiv() != null && (x.getMtAccountsReceiv() + x.getLastAccountsReceiv()) != 0 ? (x.getMtOperatingIncome() * (12 / x.getQuarterId())) / ((x.getMtAccountsReceiv() + x.getLastAccountsReceiv()) / 2) : null;
    /*
     * 总资产周转率
     */
    protected KpiFunction<Data, Double> turnovotalAssetsFunction = (x) -> x.getMtOperatingIncome() != null && x.getMtTotalAssets() != null && x.getLastTotalAssets() != null && (x.getMtTotalAssets() + x.getLastTotalAssets()) != 0 ? x.getMtOperatingIncome() / ((x.getMtTotalAssets() + x.getLastTotalAssets()) / 2) * (12 / x.getQuarterId()) : null;
    /*
     * 资产负债率 (越小越好)
     */
    protected KpiFunction<Data, Double> assetLiabiratioFunction = (x) -> x.getMtTotalAmounli() != null && this.notNullAndZero(x.getMtTotalAssets()) ? (x.getMtTotalAmounli() / x.getMtTotalAssets()) * 100 : null;
    /*
     * 速动比率
     */
    protected KpiFunction<Data, Double> quickRatioFunction = (x) -> x.getMtCurrentAssets() != null && x.getMtInventory() != null && this.notNullAndZero(x.getMtCurrentLiabil()) ? ((x.getMtCurrentAssets() - x.getMtInventory()) / x.getMtCurrentLiabil()) * 100 : null;
    /*
     * 现金流动负债率
     */
    protected KpiFunction<Data, Double> cashFloatioFunction = (x) -> x.getMtNetcashFlowact() != null && this.notNullAndZero(x.getMtCurrentLiabil()) ? (x.getMtNetcashFlowact() / x.getMtCurrentLiabil()) * 100 : null;
    /*
     * 已获利息倍数 (银行，证券，保险业需要重写，不使用财务费用(MT_FINANCE_CHARGES),使用利息支出(MT_INTEREST_PAYMENTS))
     */
    protected KpiFunction<Data, Double> interestCoverageFunction = (x) -> x.getMtProfitTotal() != null && this.notNullAndZero(x.getMtFinanceCharges()) ? (x.getMtProfitTotal() + x.getMtFinanceCharges()) / x.getMtFinanceCharges() : null;
    /*
     * 资产现金回收率
     */
    protected KpiFunction<Data, Double> cashRecovassetsFunction = (x) -> x.getMtNetcashFlowact() != null && x.getMtTotalAssets() != null && x.getLastTotalAssets() != null && (x.getMtTotalAssets() + x.getLastTotalAssets()) != 0 ? (x.getMtNetcashFlowact() / ((x.getMtTotalAssets() + x.getLastTotalAssets()) / 2)) * 100 * (12 / x.getQuarterId()) : null;
    /*
     * 存货周转率
     */
    protected KpiFunction<Data, Double> inventoryTurnoverFunction = (x) -> x.getMtOperatingCost() != null && x.getMtInventory() != null && x.getLastInventory() != null && (x.getMtInventory() + x.getLastInventory()) != 0 ? x.getMtOperatingCost() / ((x.getMtInventory() + x.getLastInventory()) / 2) * (12 / x.getQuarterId()) : null;
    /*
     * 盈余现金保障倍数
     */
    protected KpiFunction<Data, Double> surpluuaranteeMultipleFunction = (x) -> x.getMtNetcashFlowact() != null && this.notNullAndZero(x.getMtNetProfit()) ? x.getMtNetcashFlowact() / x.getMtNetProfit() : null;
    /*
     * 净资产收益率
     */
    protected KpiFunction<Data, Double> returnetAssetsFunction = (x) -> x.getMtNetProfitat() != null && x.getMtErshipOwn() != null && x.getLastErshipOwn() != null && (x.getMtErshipOwn() + x.getLastErshipOwn()) != 0 ? (x.getMtNetProfitat() / ((x.getMtErshipOwn() + x.getLastErshipOwn()) / 2)) * 100 * (12 / x.getQuarterId()) : null;
    /*
     * 总资产报酬率 (银行，证券，保险业需要重写，不使用财务费用(MT_FINANCE_CHARGES),使用利息支出(MT_INTEREST_PAYMENTS))
     */
    protected KpiFunction<Data, Double> returtalAssetsFunction = (x) -> x.getMtProfitTotal() != null && x.getMtFinanceCharges() != null && x.getMtTotalAssets() != null && x.getLastTotalAssets() != null && (x.getMtTotalAssets() + x.getLastTotalAssets()) != 0 ? ((x.getMtProfitTotal() + x.getMtFinanceCharges()) / ((x.getMtTotalAssets() + x.getLastTotalAssets()) / 2)) * 100 * (12 / x.getQuarterId()) : null;
    /*
     * 成本费用利润率
     */
//    protected KpiFunction<Data, Double> costProfrginFunction = (x) -> x.getMtProfitTotal() != null && x.getMtOperatingCost() != null && x.getMtBusinessTaxadd() != null && x.getMtCostSales() != null && x.getMtManagementFees() != null && x.getMtFinanceCharges() != null && (x.getMtOperatingCost() + x.getMtBusinessTaxadd() + x.getMtCostSales() + x.getMtManagementFees() + x.getMtFinanceCharges()) != 0 ? (x.getMtProfitTotal() / ((x.getMtOperatingCost() + x.getMtBusinessTaxadd() + x.getMtCostSales() + x.getMtManagementFees() + x.getMtFinanceCharges()))) * 100 : null;
    protected KpiFunction<Data, Double> costProfrginFunction = (x) ->
    {
        if (x.getMtProfitTotal() != null && x.getMtOperatingCost() != null && x.getMtBusinessTaxadd() != null) {
            if (x.getBusinessManagementFees() != null && (x.getMtOperatingCost() + x.getMtBusinessTaxadd() + x.getBusinessManagementFees()) != 0)
                   return (x.getMtProfitTotal() / ((x.getMtOperatingCost() + x.getMtBusinessTaxadd() + x.getBusinessManagementFees()))) * 100;
            if (x.getMtCostSales() != null || x.getMtManagementFees() != null || x.getMtFinanceCharges() != null) {
                double tan = (x.getMtCostSales() == null ? 0.0D : x.getMtCostSales()) + (x.getMtManagementFees() == null ? 0.0D : x.getMtManagementFees()) + (x.getMtFinanceCharges() == null ? 0.0D : x.getMtFinanceCharges()) + x.getMtOperatingCost() + x.getMtBusinessTaxadd();
                if (tan != 0)
                    return (x.getMtProfitTotal() / tan) * 100;
            }
        }
        return null;
    };

    /*
     * 营业利润率
     */
    protected KpiFunction<Data, Double> netSellrateFunction = (x) -> x.getMtOperatingProfit() != null && this.notNullAndZero(x.getMtOperatingIncome()) ? (x.getMtOperatingProfit() / x.getMtOperatingIncome()) * 100 : null;
    /*
     * 成本费用占收入比 (越小越好)
     */
//    protected KpiFunction<Data, Double> proporoperatingRevenueFunction = (x) -> this.notNullAndZero(x.getMtOperatingIncome()) && x.getMtOperatingCost() != null && x.getMtBusinessTaxadd() != null && x.getMtCostSales() != null && x.getMtManagementFees() != null && x.getMtFinanceCharges() != null ? ((x.getMtOperatingCost() + x.getMtBusinessTaxadd() + x.getMtCostSales() + x.getMtManagementFees() + x.getMtFinanceCharges()) / x.getMtOperatingIncome()) * 100 : null;
    protected KpiFunction<Data, Double> proporoperatingRevenueFunction = (x) -> {
        if (this.notNullAndZero(x.getMtOperatingIncome()) && x.getMtOperatingCost() != null && x.getMtBusinessTaxadd() != null) {
            if (x.getBusinessManagementFees() != null)
                return ((x.getMtOperatingCost() + x.getMtBusinessTaxadd() + x.getBusinessManagementFees()) / x.getMtOperatingIncome()) * 100;
            if (x.getMtCostSales() != null || x.getMtManagementFees() != null || x.getMtFinanceCharges() != null) {
                double tan = (x.getMtCostSales() == null ? 0.0D : x.getMtCostSales()) + (x.getMtManagementFees() == null ? 0.0D : x.getMtManagementFees()) + (x.getMtFinanceCharges() == null ? 0.0D : x.getMtFinanceCharges()) + x.getMtOperatingCost() + x.getMtBusinessTaxadd();
                if (tan != 0)
                    return (tan / x.getMtOperatingIncome()) * 100;
            }
        }
        return null;
    };

    protected boolean notNullAndZero(Double value) {
        return value != null && value != 0;
    }

    protected Predicate<Double> notNull = Objects::nonNull;

    /**
     * 分位
     *
     * @param arr
     * @return
     */
    private TantileFunction<Double[], Double, Double, Double> tanFunction = (arr, tan, tanValue) -> {
        Double[] tempArr = Arrays.copyOf(arr, arr.length);
        double var0 = (tempArr.length - 1) / tan;
        double var1 = 1.0D + var0 * tanValue;
        int varx = (int) FastMath.floor(var1) - 1;
        int vary = (int) FastMath.ceil(var1) - 1;
        return tempArr[varx] + (tempArr[vary] - tempArr[varx]) * (var1 - (int) FastMath.floor(var1));
    };
    /**
     * 求分位数
     *
     * @param arr
     * @return 存放标准的数组
     */
    protected KpiFunction<Double[], Double[]> quartilesFunction = (arr) -> {
        if (arr.length > 0) {
            Double[] tempArr = Arrays.copyOf(arr, arr.length);
            Double[] quartiles = new Double[5];
            quartiles[0] = tempArr[0];
            quartiles[1] = this.tanFunction.execute(tempArr, 4.0D, 1.0D);
            quartiles[2] = this.tanFunction.execute(tempArr, 4.0D, 2.0D);
            quartiles[3] = this.tanFunction.execute(tempArr, 4.0D, 3.0D);
            quartiles[4] = this.tanFunction.execute(tempArr, 10.0D, 9.0D);
            return quartiles;
        }
        return arr;
    };
}
