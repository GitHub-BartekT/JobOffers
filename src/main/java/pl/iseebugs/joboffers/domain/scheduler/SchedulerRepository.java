package pl.iseebugs.joboffers.domain.scheduler;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SchedulerRepository extends MongoRepository<OfferSchedulerEntity, String> {
}
