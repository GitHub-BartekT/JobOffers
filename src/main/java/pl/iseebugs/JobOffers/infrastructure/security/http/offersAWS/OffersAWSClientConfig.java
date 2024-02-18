package pl.iseebugs.JobOffers.infrastructure.security.http.offersAWS;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.iseebugs.JobOffers.domain.offersFetcher.OffersFetchable;
import pl.iseebugs.JobOffers.infrastructure.security.http.RestTemplateResponseErrorHandler;

import java.time.Duration;

@Configuration
public class OffersAWSClientConfig {

    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler(){
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateResponseErrorHandler restTemplateResponseErrorHandler){
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(1000))
                .setReadTimeout(Duration.ofMillis(1000))
                .build();
    }

    @Bean
    public OffersFetchable remoteNumberGeneratorClient(RestTemplate restTemplate,
                                                       @Value("${job-offers.offers-fetcher.http.client.config.uri}") String uri,
                                                       @Value("${job-offers.offers-fetcher.http.client.config.port}") int port){
        return new OffersFetcherRestTemplate(restTemplate, uri, port);
    }
}
