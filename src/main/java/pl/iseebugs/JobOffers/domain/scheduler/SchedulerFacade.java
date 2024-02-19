package pl.iseebugs.JobOffers.domain.scheduler;

import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import pl.iseebugs.JobOffers.domain.offers.projection.OfferReadModel;
import pl.iseebugs.JobOffers.infrastructure.security.cacheManager.CacheManagerFacade;

import java.time.Clock;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
@Log4j2
public class SchedulerFacade {

    private final SchedulerRepository schedulerRepository;
    private final CacheManagerFacade cacheManagerFacade;
    final Clock clock;
    private final Timer timer = new Timer(true);
    SchedulerFetchListener fetchListener;

    SchedulerFacade(SchedulerRepository schedulerRepository, CacheManagerFacade cacheManagerFacade, Clock clock, SchedulerFetchListener fetchListener) {
        this.schedulerRepository = schedulerRepository;
        this.cacheManagerFacade = cacheManagerFacade;
        this.clock = clock;
        this.fetchListener = fetchListener;
    }

    @Scheduled(cron = "${job-offers.offers-scheduler.fetcherRunOccurrence}")
    private void startScheduler(){
      fetchListener.onScheduleFetchAllOffersAndSaveAllIfNotExists();
    }

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
