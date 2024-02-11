package pl.iseebugs.JobOffers.domain.offers;

import pl.iseebugs.JobOffers.domain.offers.projection.OfferReadModel;
import pl.iseebugs.JobOffers.domain.offers.projection.OfferWriteModel;

class OfferMapper {
    static OfferReadModel toOfferReadModel(Offer offer){
        return OfferReadModel.builder()
                .id(offer.id())
                .url(offer.url())
                .jobPosition(offer.jobPosition())
                .salaryLowerBound(offer.salaryLowerBound())
                .salaryUpperBound(offer.salaryUpperBound())
                .build();
    }

    static OfferWriteModel toOfferWriteModel(Offer offer){
        return OfferWriteModel.builder()
                .id(offer.id())
                .url(offer.url())
                .jobPosition(offer.jobPosition())
                .salaryLowerBound(offer.salaryLowerBound())
                .salaryUpperBound(offer.salaryUpperBound())
                .build();
    }
}
