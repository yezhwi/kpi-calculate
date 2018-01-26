package com.grg.kpi.calculate.service;

import com.grg.kpi.calculate.build.BaseKpi;
import com.grg.kpi.calculate.build.Data;
import com.grg.kpi.calculate.build.Standard;
import com.grg.kpi.calculate.strategy.CalculatingStrategy;
import org.springframework.util.Assert;

import java.util.List;

//标准计算流程
public abstract class AbstractStandardKpiService<T extends Data, R extends Standard> {
    private CalculatingStrategy strategy;

    private List<T> dataList;

    //数据，如果是上市企业对标需要从浪潮装填上市企业数据
    protected abstract List<T> getData();

    //只有上市公司才进行此操作，其他返回当前列表即可
    //计算、排序并获取40+企业（剔除营业收入为0或没有营业收入的企业）资产总额+营业收入*广百资产总额/广百营业收入
    protected abstract List<T> sortAndObtainEp();

    //计算排过序的企业列表的各个指标值，保险，证券，银行 流动资产周转率 应收账款周转率 速动比率 现金流动负债率 已获利息倍数（银行业有） 存货周转率 总资产报酬率（银行业有） 指标无需计算
    //按照4分位和10分位算法获取优秀，良好，平均，较差值
    protected abstract List<? extends BaseKpi> calculateKpi(List<T> benchMarkingList);

    //组装各个指标的标准到一个list
    protected abstract List<R> standardComposite(List<? extends BaseKpi> kpis);

    public List<R> execute(List<T> dataList, CalculatingStrategy strategy) {
        Assert.notNull(strategy);
        Assert.notNull(dataList);
        this.strategy = strategy;
        this.dataList = dataList;
        return standardComposite(calculateKpi(sortAndObtainEp()));
    }

    public List<T> executeSort(List<T> dataList) {
        Assert.notNull(dataList);
        this.dataList = dataList;
        return sortAndObtainEp();
    }

    public CalculatingStrategy getStrategy() {
        return strategy;
    }

    public List<T> getDataList() {
        return dataList;
    }
}
