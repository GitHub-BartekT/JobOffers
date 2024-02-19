package pl.iseebugs.JobOffers.infrastructure.security.http.offersAWS;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.iseebugs.JobOffers.domain.offers.projection.OfferWriteModel;
import pl.iseebugs.JobOffers.domain.offersFetcher.OffersFetchable;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OffersFetcherRestTemplate implements OffersFetchable {

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;

    @Override
    public List<OfferWriteModel> getOffers() {
        String url = getUrlForService("/offers");
        HttpHeaders headers = new HttpHeaders();
        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<OffersAWSDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        List<OffersAWSDto> offers = response.getBody();
        List<OfferWriteModel> result = offers.stream().map(offersAWSDto -> OfferWriteModel.builder()
                .url(offersAWSDto.offerUrl())
                .jobPosition(offersAWSDto.title())
                .companyName(offersAWSDto.company())
                .build()).toList();
        return result;
    }

    private String getUrlForService(String service) {
        return uri + ":" + port + service;
    }
}
