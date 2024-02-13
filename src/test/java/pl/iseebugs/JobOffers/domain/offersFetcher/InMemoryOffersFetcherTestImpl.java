package pl.iseebugs.JobOffers.domain.offersFetcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryOffersFetcherTestImpl implements FetcherRepository{
    Map<String, OfferFetch> inMemoryRepository = new HashMap<>();

    @Override
    public OfferFetch saveOffer(OfferFetch offerFetch) {
        return inMemoryRepository.put(offerFetch.id(), offerFetch);
    }

    @Override
    public List<OfferFetch> getAll() {
        return inMemoryRepository.values().stream().toList();
    }
}
