package pl.iseebugs.JobOffers.domain.scheduler;

import lombok.AllArgsConstructor;
import pl.iseebugs.JobOffers.domain.offers.projection.OfferReadModel;
import pl.iseebugs.JobOffers.infrastructure.security.cacheManager.CacheManagerFacade;

import java.time.Clock;
import java.util.List;

@AllArgsConstructor
public class SchedulerFacade {

    SchedulerRepository schedulerRepository;
    CacheManagerFacade cacheManagerFacade;
    Clock clock;

    public List<OfferReadModel> getAll(){
        if(TimeValidator.wasCalledWithinLastHour(clock)){
            return getAllFromCache();
        } else {
            return getAllFromDB();
        }
    }

    private List<OfferReadModel> getAllFromCache(){
        return cacheManagerFacade.getAll();
    }

    private List<OfferReadModel> getAllFromDB(){
        cacheManagerFacade.updateCache();
        return schedulerRepository.getAll().stream()
                .map(SchedulerMapper::toOfferReadModel)
                .toList();
    }
}
