package pl.iseebugs.JobOffers.infrastructure.security.http.offersAWS;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    OfferAWSFetcherClientProperties properties;

    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler(){
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateResponseErrorHandler restTemplateResponseErrorHandler){
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(properties.connectionTimeout()))
                .setReadTimeout(Duration.ofMillis(properties.readTimeout()))
                .build();
    }

    @Bean
    public OffersFetchable remoteNumberGeneratorClient(RestTemplate restTemplate){
        return new OffersFetcherRestTemplate(restTemplate, properties.uri(), properties.port());
    }
}
