package pl.iseebugs.joboffers.domain.scheduler;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
class SchedulerConfiguration {

    @Bean
    static SchedulerFacade toSchedulerFacade(SchedulerRepository repository,
                                             SchedulerFetchListener fetchListener
                                             ){
        Clock clock = Clock.systemDefaultZone();
        return new SchedulerFacade(repository, clock, fetchListener);
    }

    static SchedulerFacade toSchedulerFacadeToTest(SchedulerRepository repository,
                                             Clock clock,
                                             SchedulerFetchListener fetchListener
    ){
        return new SchedulerFacade(repository, clock, fetchListener);
    }
}
