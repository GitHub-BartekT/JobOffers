package pl.iseebugs.joboffers.domain.scheduler;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "savedOffers")
record OfferSchedulerEntity(
        @Id
        String id,
        String url,
        String jobPosition,
        String companyName,
        double salaryLowerBound,
        double salaryUpperBound) {
}