package pl.iseebugs.JobOffers.feature;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.iseebugs.JobOffers.AdjustableClock;
import pl.iseebugs.JobOffers.BaseIntegrationTest;
import pl.iseebugs.JobOffers.SampleJobOfferResponse;
import pl.iseebugs.JobOffers.domain.offersFetcher.OffersFetcherFacade;
import pl.iseebugs.JobOffers.infrastructure.offers.controller.AllOffersReadModel;
import pl.iseebugs.JobOffers.projection.OfferReadModel;
import pl.iseebugs.JobOffers.projection.OfferWriteModel;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Log4j2
public class FirstUsageByUserWithPostingAndGettingOffersIntegrationTest extends BaseIntegrationTest implements SampleJobOfferResponse {

    static AdjustableClock clock = new AdjustableClock(LocalDateTime.of(2024, 2, 6, 10, 0, 0).toInstant(UTC), ZoneId.systemDefault());

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
        // given && then
        ResultActions failedLoginRequest = mockMvc.perform(post("/token")
            .content("""
                {
                "username": "someUser",
                "password": "somePassword"
                }
                """.trim())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
        failedLoginRequest
            .andExpect(status().isUnauthorized())
            .andExpect(content().json("""
                {
                "message": "Bad Credentials",
                "status": "UNAUTHORIZED"
                }
                """.trim()));


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

        assertThat(offersFromBackend.offerReadModels()).isEmpty();


        //   Step 8: there are 2 new offers in external HTTP server
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithTwoOffersJson())));


        //   Step 9: scheduler ran 2nd time and made GET to external server and system added 2 new offers with ids: 1000 and 2000 to database
        //given && when
        List<OfferReadModel> offersTwoNewOffers = offersFetcherFacade.onScheduleFetchAllOffersAndSaveAllIfNotExists();
        //then
        assertThat(offersTwoNewOffers).hasSize(2);


        //   Step 10: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 2 offers with ids: 1000 and 2000
        //given + when
        clock.advanceInTimeBy(Duration.ofHours(2));
        ResultActions offersAPITwoOffers = mockMvc.perform(get("/offers")
                .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        MvcResult resultTwoOffers = offersAPITwoOffers.andExpect(status().isOk()).andReturn();
        String resultJsonTwoOffers = resultTwoOffers.getResponse().getContentAsString();
        AllOffersReadModel offersFromBackendTwo = objectMapper.readValue(resultJsonTwoOffers, AllOffersReadModel.class);
        assertAll(
                () -> assertThat(offersFromBackendTwo.offerReadModels()).hasSize(2),
                () -> assertThat(offersFromBackendTwo.offerReadModels().stream()
                        .map(OfferReadModel::getJobPosition).toList())
                        .containsExactlyInAnyOrder("Junior Java Developer", "Junior DevOps")
        );


        //   Step 11: user made GET /offers/9999 and system returned NOT_FOUND(404) with message “Offer with id 9999 not found”
        // when
        ResultActions performGetResultsWithNotExistingId = mockMvc.perform(get("/offers/9999")
                .contentType(MediaType.APPLICATION_JSON));
        // then
        performGetResultsWithNotExistingId.andExpect(status().isNotFound())
                .andExpect(content().json("""
                {
                "message": "Offer with id 9999 not found",
                "status": "NOT_FOUND"
                }
                """.trim()));


        //   Step 12: user made GET /offers/1000 and system returned OK(200) with offer
        // when
        String offerId = offersTwoNewOffers.stream()
                .filter(offer -> offer.getCompanyName().equals("OCC"))
                .findFirst().get().getId();
        ResultActions performGetResultsWithExistingId = mockMvc.perform(get("/offers/"+ offerId)
                .contentType(MediaType.APPLICATION_JSON));
        // then
        performGetResultsWithExistingId.andExpect(status().isOk())
                .andExpect(content().json("""
                {"url":"https://OCK.io",
                "jobPosition":"Junior DevOps",
                "companyName":"OCC",
                "salaryLowerBound":0.0,
                "salaryUpperBound":0.0}
                """.trim()));


        //   Step 13: there are 2 new offers in external HTTP server
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithFourOffersJson())));



        //   Step 14: scheduler ran 3rd time and made GET to external server and system added 2 new offers with ids: 3000 and 4000 to database
        //given && when
        List<OfferReadModel> offersFourExternalOffers = offersFetcherFacade.onScheduleFetchAllOffersAndSaveAllIfNotExists();
        //then
        assertThat(offersFourExternalOffers).hasSize(2);


        //   Step 15: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 4 offers with ids: 1000,2000, 3000 and 4000
        //given + when
        clock.advanceInTimeBy(Duration.ofHours(2));
        ResultActions offersAPIFourOffers = mockMvc.perform(get("/offers")
                .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        MvcResult resultFourOffers = offersAPIFourOffers.andExpect(status().isOk()).andReturn();
        String resultJsonFourOffers = resultFourOffers.getResponse().getContentAsString();
        AllOffersReadModel offersFromBackendFour = objectMapper.readValue(resultJsonFourOffers, AllOffersReadModel.class);
        assertAll(
                () -> assertThat(offersFromBackendFour.offerReadModels()).hasSize(4),
                () -> assertThat(offersFromBackendFour.offerReadModels().stream()
                        .map(OfferReadModel::getJobPosition).toList())
                        .containsExactlyInAnyOrder("Junior Java Developer", "Junior DevOps", "Mid Fullstack", "Full, Senior, AI, Blockchain Master")
        );


        // Step 16: user made POST /offers with header "Authorization: Bearer AAAA.BBBB.CCC" and offer as body and system returned CREATED(201) with saved offer
        //given
        OfferWriteModel toPost = OfferWriteModel.builder()
                .url("foo url")
                .jobPosition("foo position")
                .companyName("foo Company").build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(toPost);

        //when
        ResultActions performPostOffer = mockMvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
        // then
                .andExpect(status().isCreated());


        // Step 17: user made GET /offers with header: "Authorization: Bearer AAAA.BBBB.CCC" and system returned OK(200) with 1 offer
        //given + when
        clock.advanceInTimeBy(Duration.ofHours(2));
        ResultActions offersAPIOneOffers = mockMvc.perform(get("/offers")
                .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        MvcResult result = offersAPIOneOffers.andExpect(status().isOk()).andReturn();
        String resultJson = result.getResponse().getContentAsString();
        offersFromBackend = objectMapper.readValue(resultJson, AllOffersReadModel.class);

        assertThat(offersFromBackend.offerReadModels()).hasSize(5);
    }
}
