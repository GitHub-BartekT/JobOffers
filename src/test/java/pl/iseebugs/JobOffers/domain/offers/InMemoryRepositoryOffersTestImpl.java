package pl.iseebugs.JobOffers.domain.offers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class InMemoryRepositoryOffersTestImpl implements OffersRepository{
    Map<String, OfferEntity> inMemoryRepository = new HashMap<>();

    @Override
    public Optional<OfferEntity> getById(String id) {
        return Optional.ofNullable(inMemoryRepository.get(id));
    }

    @Override
    public List<OfferEntity> getAll() {
        return inMemoryRepository.values().stream().toList();
    }

    @Override
    public OfferEntity save(OfferEntity entity) {
        inMemoryRepository.put(entity.id(), entity);
        return inMemoryRepository.get(entity.id());
    }

    @Override
    public boolean existsById(String id) {
        return inMemoryRepository.containsKey(id);
    }

    @Override
    public boolean existsByUrl(String url) {
        return inMemoryRepository.values().stream().anyMatch(offerEntity -> offerEntity.url().equals(url));
    }
}
