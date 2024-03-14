package pl.iseebugs.joboffers.domain.scheduler;


import pl.iseebugs.joboffers.projection.OfferReadModel;

class SchedulerMapper {
    static OfferReadModel toOfferReadModel(OfferSchedulerEntity offerSchedulerEntity) {
        return OfferReadModel.builder()
                .id(offerSchedulerEntity.id())
                .url(offerSchedulerEntity.url())
                .jobPosition(offerSchedulerEntity.jobPosition())
                .companyName(offerSchedulerEntity.companyName())
                .salaryLowerBound(offerSchedulerEntity.salaryLowerBound())
                .salaryUpperBound(offerSchedulerEntity.salaryUpperBound())
                .build();
    }
}
