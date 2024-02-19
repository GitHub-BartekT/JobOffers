package pl.iseebugs.JobOffers.domain.offersFetcher;

import java.util.List;

interface FetcherRepository {
    OfferFetchEntity saveOffer(OfferFetchEntity offerFetchEntity);

    List<OfferFetchEntity> getAll();
}
