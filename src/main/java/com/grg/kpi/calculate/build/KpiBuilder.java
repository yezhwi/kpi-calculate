package com.grg.kpi.calculate.build;

import com.grg.kpi.calculate.strategy.CalculatingStrategy;
import com.grg.kpi.calculate.strategy.KpiCalculator;

public class KpiBuilder {
    public KpiBuilder() {
    }

    public static BaseStep newBuilder() {
        return new KpiSteps();
    }

    public interface BaseStep {
        DataStep base();
    }

    public interface DataStep {
        StrategyStep data(Data data);
    }

    public interface StrategyStep {
        BuildStep strategy(CalculatingStrategy strategy);
    }

    public interface BuildStep {
        BaseKpi build();
    }

    private static class KpiSteps implements DataStep, StrategyStep, BaseStep, BuildStep {

        private Data data;

        private CalculatingStrategy strategy;

        @Override
        public BaseKpi build() {
            BaseKpi baseKpi = KpiCalculator.newBuilder().strategy(strategy).execute(data);
            return baseKpi;
        }

        @Override
        public DataStep base() {
            return this;
        }

        @Override
        public BuildStep strategy(CalculatingStrategy strategy) {
            this.strategy = strategy;
            return this;
        }

        @Override
        public StrategyStep data(Data data) {
            this.data = data;
            return this;
        }
    }
}
