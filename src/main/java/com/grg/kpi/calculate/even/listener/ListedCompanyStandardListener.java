package com.grg.kpi.calculate.even.listener;

import com.grg.kpi.calculate.even.ListedCompanyStandardEvent;
import org.springframework.context.ApplicationListener;

public abstract class ListedCompanyStandardListener implements ApplicationListener<ListedCompanyStandardEvent> {
    protected abstract void execute(ListedCompanyStandardEvent listedCompanyStandardEvent);

    @Override
    public void onApplicationEvent(ListedCompanyStandardEvent listedCompanyStandardEvent) {
        execute(listedCompanyStandardEvent);
    }
}
