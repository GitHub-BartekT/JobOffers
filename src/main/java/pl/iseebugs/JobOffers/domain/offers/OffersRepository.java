package pl.iseebugs.JobOffers.domain.offers;

import java.util.List;
import java.util.Optional;

interface OffersRepository {

    Optional<Offer> getById(String id);

    List<Offer> getAll();

    Offer save(Offer entity);

    boolean existsById(String id);

    boolean existsByUrl(String url);
}
