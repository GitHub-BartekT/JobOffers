package pl.iseebugs.JobOffers.feature;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.iseebugs.JobOffers.BaseIntegrationTest;
import pl.iseebugs.JobOffers.SampleJobOfferResponse;
import pl.iseebugs.JobOffers.domain.offersFetcher.OffersFetcherFacade;
import pl.iseebugs.JobOffers.infrastructure.offers.controller.AllOffersReadModel;
import pl.iseebugs.JobOffers.projection.OfferReadModel;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
public class FirstUsageByUserWithPostingAndGettingOffersIntegrationTest extends BaseIntegrationTest implements SampleJobOfferResponse {

    @Autowired
    OffersFetcherFacade offersFetcherFacade;

    @Test
    void should_user_register_post_get_offers() throws Exception {
//   Step 1: There are no offers on in external HTTP server(http://ec2-3-120-147-150.eu-central-1.compute.amazonaws.com:5057/offers).
    // given
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithZeroOffersJson())));


//   Step 2: Scheduler ran 1st time and made GET to external server and system add 0 offers to database.
    //given && when
        List<OfferReadModel> offers = offersFetcherFacade.onScheduleFetchAllOffersAndSaveAllIfNotExists();
    //then
        assertThat(offers.isEmpty()).isTrue();


//   Step 3: User tried to get JWT token by requesting POST /token with username-someUser, password=somePassword and system returned UNAUTHORIZED(401)
//   Step 4: User made GET /offers with no jwt token and system returned UNAUTHORIZED(401)
//   Step 5: user made POST /register with username=someUser, password=somePassword and system registered user with status OK(200)
//   Step 6: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned OK(200) and jwttoken=AAAA.BBBB.CCC
//   Step 7: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 0 offers
    //given + when
        ResultActions offersAPINoOffers = mockMvc.perform(get("/offers")
                        .contentType(MediaType.APPLICATION_JSON)
        );
    //then
        MvcResult mvcResult = offersAPINoOffers.andExpect(status().isOk()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        AllOffersReadModel offersFromBackend = objectMapper.readValue(json, AllOffersReadModel.class);
        assertAll(
                () -> assertThat(offersFromBackend.offerReadModels().size()).isEqualTo(0),
                () -> assertThat(offersFromBackend.offerReadModels()).isNotNull()
                );


//   Step 8: there are 2 new offers in external HTTP server
//   Step 9: scheduler ran 2nd time and made GET to external server and system added 2 new offers with ids: 1000 and 2000 to database
//   Step 10: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 2 offers with ids: 1000 and 2000
//   Step 11: user made GET /offers/9999 and system returned NOT_FOUND(404) with message “Offer with id 9999 not found”
//   Step 12: user made GET /offers/1000 and system returned OK(200) with offer
//   Step 13: there are 2 new offers in external HTTP server
//   Step 14: scheduler ran 3rd time and made GET to external server and system added 2 new offers with ids: 3000 and 4000 to database
//   Step 15: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 4 offers with ids: 1000,2000, 3000 and 4000

    }
}