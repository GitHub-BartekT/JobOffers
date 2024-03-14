package pl.iseebugs.joboffers.domain.offers;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Builder
@Document(collection = "savedOffers")
record OfferEntity(
        @Id
        String id,
        @Indexed(unique = true) String url, String jobPosition, String companyName, double salaryLowerBound, double salaryUpperBound) {
}
