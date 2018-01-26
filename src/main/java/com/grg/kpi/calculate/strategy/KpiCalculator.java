package com.grg.kpi.calculate.strategy;

import com.grg.kpi.calculate.build.BaseKpi;
import com.grg.kpi.calculate.build.Data;
import com.grg.kpi.calculate.build.Standard;
import org.springframework.util.Assert;

import java.util.List;

public final class KpiCalculator {

    private static class LazyHolder {
        private static final StrategyStep INSTANCE = new Steps();
    }

    public static final StrategyStep newBuilder() {
        return LazyHolder.INSTANCE;
    }

    public interface StrategyStep {
        CalculatingStrategy strategy(CalculatingStrategy strategy);
    }

    private static class Steps implements CalculatingStrategy, StrategyStep {
        private CalculatingStrategy strategy;

        @Override
        public BaseKpi execute(Data data) {
            Assert.notNull(strategy, "strategy must be not null");
            return strategy.execute(data);
        }

        @Override
        public List<Standard> execute(List kpiValueList) {
            Assert.notNull(strategy, "strategy must be not null");
            return strategy.execute(kpiValueList);
        }

        @Override
        public Steps strategy(CalculatingStrategy strategy) {
            this.strategy = strategy;
            return this;
        }
    }
}
