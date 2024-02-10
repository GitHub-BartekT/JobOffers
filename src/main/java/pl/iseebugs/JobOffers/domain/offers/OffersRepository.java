package pl.iseebugs.JobOffers.domain.offers;

import java.util.List;
import java.util.Optional;

interface OffersRepository {

    Optional<Offer> getById(String id);

    List<Offer> getAll();

    Offer sava(Offer entity);
}
