package pl.iseebugs.joboffers.domain.offersfetcher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FetcherRepository extends MongoRepository<OfferFetchEntity, String> {
}
