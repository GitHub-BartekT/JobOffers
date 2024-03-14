package pl.iseebugs.joboffers.domain.offers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface OffersRepository extends MongoRepository<OfferEntity, String> {

    Optional<OfferEntity> getById(String id);

    boolean existsByUrl(String url);

    boolean existsById(String id);
}
