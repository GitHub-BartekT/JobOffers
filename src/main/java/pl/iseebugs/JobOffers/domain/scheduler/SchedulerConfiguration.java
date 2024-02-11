package pl.iseebugs.JobOffers.domain.scheduler;


import pl.iseebugs.JobOffers.infrastructure.security.cacheManager.CacheManagerFacade;

import java.time.Clock;

class SchedulerConfiguration {
    static SchedulerFacade toSchedulerFacade(SchedulerRepository repository,
                                             CacheManagerFacade cacheManagerFacade,
                                             Clock clock){
        return new SchedulerFacade(repository, cacheManagerFacade, clock);
    }
}
