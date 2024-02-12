package pl.iseebugs.JobOffers.domain.scheduler;

import pl.iseebugs.JobOffers.domain.offers.projection.OfferReadModel;

class SchedulerMapper {
    static OfferReadModel toOfferReadModel(OfferScheduler offerScheduler) {
        return OfferReadModel.builder()
                .id(offerScheduler.id())
                .url(offerScheduler.url())
                .jobPosition(offerScheduler.jobPosition())
                .salaryLowerBound(offerScheduler.salaryLowerBound())
                .salaryUpperBound(offerScheduler.salaryUpperBound())
                .build();
    }
}
