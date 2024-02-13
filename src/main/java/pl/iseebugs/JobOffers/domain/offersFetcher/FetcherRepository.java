package pl.iseebugs.JobOffers.domain.offersFetcher;

import java.util.List;
import java.util.Optional;

interface FetcherRepository {
    OfferFetch saveOffer(OfferFetch offerFetch);

    List<OfferFetch> getAll();
}
