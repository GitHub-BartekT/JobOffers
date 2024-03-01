package pl.iseebugs.JobOffers.http.offer;

import org.springframework.web.client.RestTemplate;
import pl.iseebugs.JobOffers.domain.offersFetcher.OffersFetchable;
import pl.iseebugs.JobOffers.infrastructure.http.offersAWS.OffersAWSClientConfig;

import static pl.iseebugs.JobOffers.BaseIntegrationTest.WIRE_MOCK_HOST;

public class OffersHttpClientTestConfig extends OffersAWSClientConfig {
    public OffersFetchable remoteOfferAWSTestClient(int port, int connectionTimeout, int readTimeout) {
        final RestTemplate restTemplate = restTemplate(connectionTimeout, readTimeout, restTemplateResponseErrorHandler());
        return remoteNumberGeneratorClient(WIRE_MOCK_HOST, port, restTemplate);
    }
}
