package pl.iseebugs.JobOffers.domain.scheduler;

import lombok.Builder;

@Builder
record OfferScheduler (String id, String url, String jobPosition, double salaryLowerBound, double salaryUpperBound) {
}