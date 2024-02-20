package pl.iseebugs.JobOffers.domain.offers;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

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

    @Override
    public <S extends OfferEntity> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends OfferEntity> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends OfferEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends OfferEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends OfferEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends OfferEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends OfferEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends OfferEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends OfferEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends OfferEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<OfferEntity> findById(String string) {
        return Optional.empty();
    }

    @Override
    public List<OfferEntity> findAll() {
        return null;
    }

    @Override
    public List<OfferEntity> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String string) {

    }

    @Override
    public void delete(OfferEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends OfferEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<OfferEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<OfferEntity> findAll(Pageable pageable) {
        return null;
    }
}
