package pl.iseebugs.JobOffers.domain.scheduler;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
record OfferSchedulerEntity(
        @Id
        String id, String url, String jobPosition, String companyName, double salaryLowerBound, double salaryUpperBound) {
}