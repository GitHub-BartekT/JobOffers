package pl.iseebugs.JobOffers.domain.offers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class InMemoryRepositoryOffersTestImpl implements OffersRepository{
    Map<String, Offer> inMemoryRepository = new HashMap<>();

    @Override
    public Optional<Offer> getById(String id) {
        return Optional.ofNullable(inMemoryRepository.get(id));
    }

    @Override
    public List<Offer> getAll() {
        return inMemoryRepository.values().stream().toList();
    }

    @Override
    public Offer save(Offer entity) {
        inMemoryRepository.put(entity.id(), entity);
        return inMemoryRepository.get(entity.id());
    }

    @Override
    public boolean existsById(String id) {
        return inMemoryRepository.containsKey(id);
    }

    @Override
    public boolean existsByUrl(String url) {
        return inMemoryRepository.values().stream().anyMatch(offer -> offer.url().equals(url));
    }
}
