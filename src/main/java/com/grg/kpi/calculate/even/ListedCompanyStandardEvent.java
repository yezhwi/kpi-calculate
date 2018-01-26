package com.grg.kpi.calculate.even;

import org.springframework.context.ApplicationEvent;

public class ListedCompanyStandardEvent extends ApplicationEvent {
    private Integer yearId;
    private Integer monthId;

    public ListedCompanyStandardEvent(Object source) {
        super(source);
    }

    public ListedCompanyStandardEvent(Object source, Integer yearId, Integer monthId) {
        super(source);
        this.yearId = yearId;
        this.monthId = monthId;
    }

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public Integer getMonthId() {
        return monthId;
    }

    public void setMonthId(Integer monthId) {
        this.monthId = monthId;
    }
}
