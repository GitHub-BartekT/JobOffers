package pl.iseebugs.JobOffers.projection;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OfferWriteModel {
    private String id;
    @NotNull(message = "{OfferWriteModel.url.not.null}")
    @NotEmpty(message = "{OfferWriteModel.url.not.empty}")
    private String url;
    @NotNull(message = "{OfferWriteModel.jobPosition.not.null}")
    @NotEmpty(message = "{OfferWriteModel.jobPosition.not.empty}")
    private String jobPosition;
    @NotNull(message = "{OfferWriteModel.companyName.not.null}")
    @NotEmpty(message = "{OfferWriteModel.companyName.not.empty}")
    private String companyName;
    private double salaryLowerBound;
    private double salaryUpperBound;
}
