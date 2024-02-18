package pl.iseebugs.JobOffers.infrastructure.security.http.offersAWS;

import lombok.Builder;
import lombok.Setter;
import org.springframework.web.client.RestTemplate;

@Builder
record OffersAWSDto(String title, String company, String salary, String offerUrl) {
}
