package pl.iseebugs.JobOffers.domain.offersFetcher;

import java.util.List;
import java.util.Optional;

interface FetcherRepository {
    Optional<OfferFetch> saveOffer(OfferFetch offerFetch);

    List<OfferFetch> getAll();
}
