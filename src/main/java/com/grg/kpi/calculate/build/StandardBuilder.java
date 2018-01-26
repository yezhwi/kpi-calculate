package com.grg.kpi.calculate.build;

public class StandardBuilder {
    public StandardBuilder() {
    }

    public static BaseStep newBuilder() {
        return new StandardSteps();
    }

    public interface BaseStep {
        DataStep base(String firstLevelIndex);
    }

    public interface DataStep {
        BuildStep data(Double[] standardArr);

        Standard build();
    }

    public interface BuildStep {
        Standard build();
    }

    private static class StandardSteps implements BaseStep, DataStep, BuildStep {
        private String firstLevelIndex;
        private Double[] standardArr;

        @Override
        public Standard build() {
            Standard standard = new Standard();
            standard.setFirstLevelIndex(firstLevelIndex);
            if (standardArr != null && standardArr.length == 5) {
                standard.setPoorValue(standardArr[0]);
                standard.setLowerValue(standardArr[1]);
                standard.setAverageValue(standardArr[2]);
                standard.setGoodValue(standardArr[3]);
                standard.setExcellentValue(standardArr[4]);
            }
            return standard;
        }

        @Override
        public DataStep base(String firstLevelIndex) {
            this.firstLevelIndex = firstLevelIndex;
            return this;
        }

        @Override
        public BuildStep data(Double[] standardArr) {
            this.standardArr = standardArr;
            return this;
        }
    }
}
