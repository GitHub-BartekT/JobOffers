package pl.iseebugs.joboffers.infrastructure.http.offersAWS;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.iseebugs.joboffers.domain.offersfetcher.OffersFetchable;
import pl.iseebugs.joboffers.infrastructure.http.RestTemplateResponseErrorHandler;

import java.time.Duration;

@Configuration
public class OffersAWSClientConfig {

    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler(){
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(
            @Value("${job-offers.offers-fetcher.http.client.config.connectionTimeout:1000}") int connectionTimeout,
            @Value("${job-offers.offers-fetcher.http.client.config.readTimeout:1000}") int readTimeout,
            RestTemplateResponseErrorHandler restTemplateResponseErrorHandler){
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                    .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    public OffersFetchable remoteNumberGeneratorClient(
            @Value("${job-offers.offers-fetcher.http.client.config.uri}") String uri,
            @Value("${job-offers.offers-fetcher.http.client.config.port}") int port,
            RestTemplate restTemplate){
        return new OffersFetcherRestTemplate(restTemplate, uri, port);
    }
}
