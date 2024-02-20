package pl.iseebugs.JobOffers.domain.scheduler;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.iseebugs.JobOffers.infrastructure.cacheManager.CacheManagerFacade;

import java.time.Clock;
import java.util.List;

@Configuration
class SchedulerConfiguration {

    @Bean
    SchedulerRepository schedulerRepository(){
        return new SchedulerRepository() {
            @Override
            public List<OfferSchedulerEntity> getAll() {
                return null;
            }
        };
    }

    @Bean
    static SchedulerFacade toSchedulerFacade(SchedulerRepository repository,
                                             CacheManagerFacade cacheManagerFacade,
                                             SchedulerFetchListener fetchListener
                                             ){
        Clock clock = Clock.systemDefaultZone();
        return new SchedulerFacade(repository, cacheManagerFacade, clock, fetchListener);
    }

    static SchedulerFacade toSchedulerFacadeToTest(SchedulerRepository repository,
                                             CacheManagerFacade cacheManagerFacade,
                                             Clock clock,
                                             SchedulerFetchListener fetchListener
    ){
        return new SchedulerFacade(repository, cacheManagerFacade, clock, fetchListener);
    }
}
