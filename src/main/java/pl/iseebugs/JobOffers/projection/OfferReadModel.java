package pl.iseebugs.JobOffers.projection;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OfferReadModel {
    private String id;
    private String url;
    private String jobPosition;
    private String companyName;
    private double salaryLowerBound;
    private double salaryUpperBound;
}
