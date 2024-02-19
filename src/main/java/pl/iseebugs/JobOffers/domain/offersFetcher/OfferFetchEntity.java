package pl.iseebugs.JobOffers.domain.offersFetcher;

import lombok.Builder;

@Builder
record OfferFetchEntity(String id, String url, String jobPosition, String companyName, double salaryLowerBound, double salaryUpperBound) {
}
