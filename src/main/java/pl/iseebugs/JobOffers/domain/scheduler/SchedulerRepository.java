package pl.iseebugs.JobOffers.domain.scheduler;

import java.util.List;

interface SchedulerRepository {
    List<OfferSchedulerEntity> getAll();
}
