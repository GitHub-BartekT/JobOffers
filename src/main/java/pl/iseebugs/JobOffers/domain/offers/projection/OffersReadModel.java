package pl.iseebugs.JobOffers.domain.offers.projection;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OffersReadModel {
    private String id;
    private String url;
    private String jobPosition;
    private double salaryLowerBound;
    private double salaryUpperBound;
}
