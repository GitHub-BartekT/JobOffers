package pl.iseebugs.JobOffers.domain.offersFetcher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FetcherRepository extends MongoRepository<OfferFetchEntity, String> {
}
