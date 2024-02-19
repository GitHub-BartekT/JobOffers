package pl.iseebugs.JobOffers.projection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OfferWriteModel {
    private String id;
    private String url;
    private String jobPosition;
    private String companyName;
    private double salaryLowerBound;
    private double salaryUpperBound;
}
