package pl.iseebugs.joboffers.projection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OfferReadModel implements Serializable {
    private String id;
    private String url;
    private String jobPosition;
    private String companyName;
    private double salaryLowerBound;
    private double salaryUpperBound;

}
