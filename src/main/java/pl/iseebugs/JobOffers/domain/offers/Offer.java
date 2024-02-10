package pl.iseebugs.JobOffers.domain.offers;

import lombok.Builder;

@Builder
record Offer(String id, String url, String jobPosition, double salaryLowerBound, double salaryUpperBound) {
}
