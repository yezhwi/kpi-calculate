package com.grg.kpi.calculate.even.listener;

import com.grg.kpi.calculate.even.BankStandardEvent;
import org.springframework.context.ApplicationListener;

public abstract class BankStandardListener implements ApplicationListener<BankStandardEvent> {
    protected abstract void execute(BankStandardEvent bankStandardEvent);

    @Override
    public void onApplicationEvent(BankStandardEvent bankStandardEvent) {
        execute(bankStandardEvent);
    }
}
