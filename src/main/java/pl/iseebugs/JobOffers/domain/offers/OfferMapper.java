package pl.iseebugs.JobOffers.domain.offers;

import pl.iseebugs.JobOffers.projection.OfferReadModel;

class OfferMapper {
    static OfferReadModel toOfferReadModel(OfferEntity offerEntity){
        return OfferReadModel.builder()
                .id(offerEntity.id())
                .url(offerEntity.url())
                .jobPosition(offerEntity.jobPosition())
                .companyName(offerEntity.companyName())
                .salaryLowerBound(offerEntity.salaryLowerBound())
                .salaryUpperBound(offerEntity.salaryUpperBound())
                .build();
    }
}
