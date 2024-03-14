package pl.iseebugs.joboffers.infrastructure.http.offersAWS;

import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "job-offers.offers-fetcher.http.client.config")
@Builder
public record OfferAWSFetcherClientProperties(String uri, int port, int readTimeout, int connectionTimeout) {
}
