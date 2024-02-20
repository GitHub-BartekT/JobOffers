package pl.iseebugs.JobOffers.domain.offers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface OffersRepository extends MongoRepository<OfferEntity, String> {

    Optional<OfferEntity> getById(String id);

    boolean existsById(String id);

    boolean existsByUrl(String url);
}
