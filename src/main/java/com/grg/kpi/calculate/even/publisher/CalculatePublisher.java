package com.grg.kpi.calculate.even.publisher;

import org.springframework.context.ApplicationEventPublisherAware;

public interface CalculatePublisher extends ApplicationEventPublisherAware {
    void publishListedCompanyStandard(String string);

    void publishFinanceStandard(String string);
}
