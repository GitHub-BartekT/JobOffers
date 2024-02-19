package pl.iseebugs.JobOffers.domain.offersFetcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryOffersFetcherTestImpl implements FetcherRepository{
    Map<String, OfferFetchEntity> inMemoryRepository = new HashMap<>();

    @Override
    public OfferFetchEntity saveOffer(OfferFetchEntity offerFetchEntity) {
        return inMemoryRepository.put(offerFetchEntity.id(), offerFetchEntity);
    }

    @Override
    public List<OfferFetchEntity> getAll() {
        return inMemoryRepository.values().stream().toList();
    }
}
