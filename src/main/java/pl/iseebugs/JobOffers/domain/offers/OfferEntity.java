package pl.iseebugs.JobOffers.domain.offers;

import lombok.Builder;

@Builder
record OfferEntity(String id, String url, String jobPosition, String companyName, double salaryLowerBound, double salaryUpperBound) {
}
