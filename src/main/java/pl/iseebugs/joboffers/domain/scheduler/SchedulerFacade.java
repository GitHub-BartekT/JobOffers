package pl.iseebugs.joboffers.domain.scheduler;

import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import pl.iseebugs.joboffers.projection.OfferReadModel;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;
import java.util.List;

@Log4j2
public class SchedulerFacade {

    private final SchedulerRepository schedulerRepository;
    final Clock clock;
    SchedulerFetchListener schedulerFetchListener;
    private final SimpleDateFormat dateFormat;

    SchedulerFacade(SchedulerRepository schedulerRepository, Clock clock, SchedulerFetchListener schedulerFetchListener) {
        this.schedulerRepository = schedulerRepository;
        this.clock = clock;
        this.schedulerFetchListener = schedulerFetchListener;
        dateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    @Scheduled(fixedDelayString = "${job-offers.offers-scheduler.fetcherRunOccurrence}")
    public List<OfferReadModel> startScheduler(){
        log.info("Started scheduler {}", dateFormat.format(new Date()));
        return schedulerFetchListener.onScheduleFetchAllOffersAndSaveAllIfNotExists();
    }

    @Cacheable("jobOffers")
    public List<OfferReadModel> getAll(){
        return schedulerRepository.findAll().stream()
                .map(SchedulerMapper::toOfferReadModel)
                .toList();
    }
}
