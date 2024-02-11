package pl.iseebugs.JobOffers.infrastructure.security.cacheManager.projection;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OfferCacheReadModel {
    private String id;
    private String url;
    private String jobPosition;
    private double salaryLowerBound;
    private double salaryUpperBound;
}
