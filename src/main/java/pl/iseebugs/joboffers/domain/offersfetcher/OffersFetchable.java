package pl.iseebugs.joboffers.domain.offersfetcher;


import pl.iseebugs.joboffers.projection.OfferWriteModel;

import java.util.List;

public interface OffersFetchable {
    List<OfferWriteModel> getOffers();
}
