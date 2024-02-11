package pl.iseebugs.JobOffers.domain.scheduler;

import lombok.AllArgsConstructor;
import pl.iseebugs.JobOffers.domain.offers.projection.OfferReadModel;
import pl.iseebugs.JobOffers.domain.scheduler.projection.OfferSchedulerReadModel;
import pl.iseebugs.JobOffers.infrastructure.security.cacheManager.CacheManagerFacade;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SchedulerFacade {

    SchedulerRepository schedulerRepository;
    CacheManagerFacade cacheManagerFacade;
    Clock clock;

    public List<OfferSchedulerReadModel> getAll(){
        if(TimeValidator.wasCalledWithinLastHour()){
            return getAllFromCache();
        } else{
            return getAllFromDB();
        }
    }

    List<OfferSchedulerReadModel> getAllFromCache(){
        return cacheManagerFacade.getAll().stream()
                .map(SchedulerMapper::toOfferReadModelFromCache)
                .toList();
    }

    List<OfferSchedulerReadModel> getAllFromDB(){
        return schedulerRepository.getAll().stream()
                .map(SchedulerMapper::toOfferReadModel)
                .toList();
    }
}
