package pl.iseebugs.JobOffers.domain.offersFetcher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface FetcherRepository extends MongoRepository<OfferFetchEntity, String> {
    OfferFetchEntity saveOffer(OfferFetchEntity offerFetchEntity);

    List<OfferFetchEntity> getAll();
}
