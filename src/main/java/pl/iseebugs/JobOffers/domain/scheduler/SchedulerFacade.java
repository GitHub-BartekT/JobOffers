package pl.iseebugs.JobOffers.domain.scheduler;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import pl.iseebugs.JobOffers.infrastructure.cacheManager.CacheManagerFacade;
import pl.iseebugs.JobOffers.projection.OfferReadModel;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;
import java.util.List;

@Log4j2
public class SchedulerFacade {

    private final SchedulerRepository schedulerRepository;
    private final CacheManagerFacade cacheManagerFacade;
    final Clock clock;
    SchedulerFetchListener schedulerFetchListener;
    private final SimpleDateFormat dateFormat;

    SchedulerFacade(SchedulerRepository schedulerRepository, CacheManagerFacade cacheManagerFacade, Clock clock, SchedulerFetchListener schedulerFetchListener) {
        this.schedulerRepository = schedulerRepository;
        this.cacheManagerFacade = cacheManagerFacade;
        this.clock = clock;
        this.schedulerFetchListener = schedulerFetchListener;
        dateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    @Scheduled(fixedDelayString = "${job-offers.offers-scheduler.fetcherRunOccurrence}")
    public List<OfferReadModel> startScheduler(){
        log.info("Started scheduler {}", dateFormat.format(new Date()));
        return schedulerFetchListener.onScheduleFetchAllOffersAndSaveAllIfNotExists();
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
