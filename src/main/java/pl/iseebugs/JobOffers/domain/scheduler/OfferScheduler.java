package pl.iseebugs.JobOffers.domain.scheduler;

import lombok.Builder;

@Builder
record OfferScheduler (String id, String url, String jobPosition, String companyName, double salaryLowerBound, double salaryUpperBound) {
}