package com.grg.kpi.calculate.build;

public class Standard {
    private String firstLevelIndex;

    private Double excellentValue;

    private Double goodValue;

    private Double averageValue;

    private Double lowerValue;

    private Double poorValue;

    public String getFirstLevelIndex() {
        return firstLevelIndex;
    }

    public void setFirstLevelIndex(String firstLevelIndex) {
        this.firstLevelIndex = firstLevelIndex;
    }

    public Double getExcellentValue() {
        return excellentValue;
    }

    public void setExcellentValue(Double excellentValue) {
        this.excellentValue = excellentValue;
    }

    public Double getGoodValue() {
        return goodValue;
    }

    public void setGoodValue(Double goodValue) {
        this.goodValue = goodValue;
    }

    public Double getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(Double averageValue) {
        this.averageValue = averageValue;
    }

    public Double getLowerValue() {
        return lowerValue;
    }

    public void setLowerValue(Double lowerValue) {
        this.lowerValue = lowerValue;
    }

    public Double getPoorValue() {
        return poorValue;
    }

    public void setPoorValue(Double poorValue) {
        this.poorValue = poorValue;
    }

    @Override
    public String toString() {
        return "标准值{" +
                "标识：'" + firstLevelIndex + '\'' +
                ", 优秀：" + excellentValue +
                ", 良好：" + goodValue +
                ", 平均：" + averageValue +
                ", 较低：" + lowerValue +
                ", 较差：" + poorValue +
                '}';
    }
}
