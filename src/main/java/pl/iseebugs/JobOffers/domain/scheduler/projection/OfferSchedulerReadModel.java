package pl.iseebugs.JobOffers.domain.scheduler.projection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class OfferSchedulerReadModel {
    private String id;
    private String url;
    private String jobPosition;
    private double salaryLowerBound;
    private double salaryUpperBound;
}
