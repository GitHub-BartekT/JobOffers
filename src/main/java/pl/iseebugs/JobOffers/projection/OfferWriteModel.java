package pl.iseebugs.JobOffers.projection;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OfferWriteModel {
    private String id;
    @NotEmpty
    @NotEmpty
    private String url;
    @NotEmpty
    @NotEmpty
    private String jobPosition;
    @NotEmpty
    @NotEmpty
    private String companyName;
    private double salaryLowerBound;
    private double salaryUpperBound;
}
