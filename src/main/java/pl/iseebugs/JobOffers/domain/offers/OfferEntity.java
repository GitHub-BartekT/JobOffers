package pl.iseebugs.JobOffers.domain.offers;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
record OfferEntity(
        @Id
        String id,
        String url, String jobPosition, String companyName, double salaryLowerBound, double salaryUpperBound) {
}
