package pl.iseebugs.joboffers.domain.offers;

import pl.iseebugs.joboffers.projection.OfferReadModel;

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
