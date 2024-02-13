package pl.iseebugs.JobOffers.domain.scheduler;


import pl.iseebugs.JobOffers.infrastructure.security.cacheManager.CacheManagerFacade;

import java.time.Clock;

class SchedulerConfiguration {
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
