package pl.iseebugs.JobOffers.infrastructure.http.offersAWS;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import pl.iseebugs.JobOffers.projection.OfferWriteModel;
import pl.iseebugs.JobOffers.domain.offersFetcher.OffersFetchable;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Log4j2
public class OffersFetcherRestTemplate implements OffersFetchable {

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;

    @Override
    public List<OfferWriteModel> getOffers() {
        log.info("Starting fetching offers using http client");

        HttpHeaders headers = new HttpHeaders();
        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);

        try{
        String url = getUrlForService("/offers");
        log.info("url: {}", url);
        ResponseEntity<List<OffersAWSDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        List<OffersAWSDto> offers = response.getBody();

        if (offers.isEmpty()) {
            log.info("Response body was null returning empty list");
            return Collections.emptyList();
        }

        List<OfferWriteModel> result = offers.stream().map(offersAWSDto -> OfferWriteModel.builder()
                .url(offersAWSDto.offerUrl())
                .jobPosition(offersAWSDto.title())
                .companyName(offersAWSDto.company())
                .build()).toList();
        log.info("Success response body returned with {} offers", result.size());
        return result;
        } catch (ResourceAccessException e){
            log.error("Error while fetching offers using http client:" + e.getMessage());
            return Collections.emptyList();
        }
    }

    private String getUrlForService(String service) {
        return uri + ":" + port + service;
    }
}
