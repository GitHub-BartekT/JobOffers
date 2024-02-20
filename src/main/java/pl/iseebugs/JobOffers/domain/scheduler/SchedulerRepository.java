package pl.iseebugs.JobOffers.domain.scheduler;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SchedulerRepository extends MongoRepository<OfferSchedulerEntity, String> {
}
