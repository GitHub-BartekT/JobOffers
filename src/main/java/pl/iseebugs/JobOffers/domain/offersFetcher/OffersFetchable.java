package pl.iseebugs.JobOffers.domain.offersFetcher;


import pl.iseebugs.JobOffers.projection.OfferWriteModel;

import java.util.List;

public interface OffersFetchable {
    List<OfferWriteModel> getOffers();
}
