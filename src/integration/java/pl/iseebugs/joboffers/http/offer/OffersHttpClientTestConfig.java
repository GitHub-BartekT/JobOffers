package pl.iseebugs.joboffers.http.offer;

import org.springframework.web.client.RestTemplate;
import pl.iseebugs.joboffers.domain.offersfetcher.OffersFetchable;
import pl.iseebugs.joboffers.infrastructure.http.offersAWS.OffersAWSClientConfig;

import static pl.iseebugs.joboffers.BaseIntegrationTest.WIRE_MOCK_HOST;

public class OffersHttpClientTestConfig extends OffersAWSClientConfig {
    public OffersFetchable remoteOfferAWSTestClient(int port, int connectionTimeout, int readTimeout) {
        final RestTemplate restTemplate = restTemplate(connectionTimeout, readTimeout, restTemplateResponseErrorHandler());
        return remoteNumberGeneratorClient(WIRE_MOCK_HOST, port, restTemplate);
    }
}
