package pl.iseebugs.JobOffers.domain.offers;

import java.util.List;
import java.util.Optional;

interface OffersRepository {

    Optional<OfferEntity> getById(String id);

    List<OfferEntity> getAll();

    OfferEntity save(OfferEntity entity);

    boolean existsById(String id);

    boolean existsByUrl(String url);
}
