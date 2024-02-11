package pl.iseebugs.JobOffers.domain.scheduler;

import pl.iseebugs.JobOffers.domain.scheduler.projection.OfferSchedulerReadModel;
import pl.iseebugs.JobOffers.domain.scheduler.projection.OfferSchedulerWriteModel;
import pl.iseebugs.JobOffers.infrastructure.security.cacheManager.projection.OfferCacheReadModel;

class SchedulerMapper {
    static OfferSchedulerReadModel toOfferReadModel(OfferScheduler offer){
        return OfferSchedulerReadModel.builder()
                .id(offer.id())
                .url(offer.url())
                .jobPosition(offer.jobPosition())
                .salaryLowerBound(offer.salaryLowerBound())
                .salaryUpperBound(offer.salaryUpperBound())
                .build();
    }

    static OfferSchedulerWriteModel toOfferWriteModel(OfferScheduler offer){
        return OfferSchedulerWriteModel.builder()
                .id(offer.id())
                .url(offer.url())
                .jobPosition(offer.jobPosition())
                .salaryLowerBound(offer.salaryLowerBound())
                .salaryUpperBound(offer.salaryUpperBound())
                .build();
    }

    static OfferSchedulerReadModel toOfferReadModelFromCache(OfferCacheReadModel offer){
        return OfferSchedulerReadModel.builder()
                .id(offer.getId())
                .url(offer.getUrl())
                .jobPosition(offer.getJobPosition())
                .salaryLowerBound(offer.getSalaryLowerBound())
                .salaryUpperBound(offer.getSalaryUpperBound())
                .build();
    }
}
