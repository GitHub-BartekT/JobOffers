package pl.iseebugs.JobOffers.domain.offersFetcher;

import java.util.List;

interface OffersFetchable {
    List<OfferFetch> getOffers();
}
