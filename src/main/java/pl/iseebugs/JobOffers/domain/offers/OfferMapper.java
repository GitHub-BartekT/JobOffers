package pl.iseebugs.JobOffers.domain.offers;

import pl.iseebugs.JobOffers.domain.offers.projection.OffersReadModel;
import pl.iseebugs.JobOffers.domain.offers.projection.OffersWriteModel;

class OfferMapper {
    static OffersReadModel toOffersReadModel(Offer offer){
        return OffersReadModel.builder()
                .id(offer.id())
                .url(offer.url())
                .jobPosition(offer.jobPosition())
                .salaryLowerBound(offer.salaryLowerBound())
                .salaryUpperBound(offer.salaryUpperBound())
                .build();
    }

    static OffersWriteModel toOffersWriteModel(Offer offer){
        return OffersWriteModel.builder()
                .id(offer.id())
                .url(offer.url())
                .jobPosition(offer.jobPosition())
                .salaryLowerBound(offer.salaryLowerBound())
                .salaryUpperBound(offer.salaryUpperBound())
                .build();
    }
}
