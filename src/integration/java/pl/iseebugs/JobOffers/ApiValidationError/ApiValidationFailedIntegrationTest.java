package pl.iseebugs.JobOffers.ApiValidationError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.iseebugs.JobOffers.BaseIntegrationTest;
import pl.iseebugs.JobOffers.infrastructure.apiValidation.ApiValidationErrorDto;
import pl.iseebugs.JobOffers.projection.OfferWriteModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ApiValidationFailedIntegrationTest extends BaseIntegrationTest {

    @Test
    @WithMockUser
    public void should_return_400_bad_request_and_validation_messages_when_url_is_null() throws Exception {
    //given
    OfferWriteModel toPost = OfferWriteModel.builder()
            .jobPosition("foo position")
            .companyName("foo Company").build();

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonRequest = objectMapper.writeValueAsString(toPost);

    //when
    ResultActions performPostOffer = mockMvc.perform(post("/offers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest));
    // then
        MvcResult mvcResult = performPostOffer.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto result = objectMapper.readValue(json, ApiValidationErrorDto.class);
        assertThat(result.message()).containsExactlyInAnyOrder( "Offer url must not be null", "Offer url must not be empty");
    }

    @Test
    public void should_return_400_bad_request_and_validation_message_when_url_is_empty() throws Exception {
        //given
        OfferWriteModel toPost = OfferWriteModel.builder()
                .url("")
                .jobPosition("foo position")
                .companyName("foo Company").build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(toPost);

        //when
        ResultActions performPostOffer = mockMvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));
        // then
        MvcResult mvcResult = performPostOffer.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto result = objectMapper.readValue(json, ApiValidationErrorDto.class);
        assertThat(result.message()).containsExactlyInAnyOrder( "Offer url must not be empty");
    }

    @Test
    public void should_return_400_bad_request_and_validation_messages_when_jobPosition_is_null() throws Exception {
        //given
        OfferWriteModel toPost = OfferWriteModel.builder()
                .url("foo")
                .companyName("foo Company").build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(toPost);

        //when
        ResultActions performPostOffer = mockMvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));
        // then
        MvcResult mvcResult = performPostOffer.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto result = objectMapper.readValue(json, ApiValidationErrorDto.class);
        assertThat(result.message()).containsExactlyInAnyOrder( "Job position must not be null", "Job position must not be empty");
    }

    @Test
    public void should_return_400_bad_request_and_validation_message_when_jobPosition_is_empty() throws Exception {
        //given
        OfferWriteModel toPost = OfferWriteModel.builder()
                .url("foo")
                .jobPosition("")
                .companyName("foo Company").build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(toPost);

        //when
        ResultActions performPostOffer = mockMvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));
        // then
        MvcResult mvcResult = performPostOffer.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto result = objectMapper.readValue(json, ApiValidationErrorDto.class);
        assertThat(result.message()).containsExactlyInAnyOrder( "Job position must not be empty");
    }

    @Test
    public void should_return_400_bad_request_and_validation_messages_when_companyName_is_null() throws Exception {
        //given
        OfferWriteModel toPost = OfferWriteModel.builder()
                .url("foo")
                .jobPosition("foo position").build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(toPost);

        //when
        ResultActions performPostOffer = mockMvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));
        // then
        MvcResult mvcResult = performPostOffer.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto result = objectMapper.readValue(json, ApiValidationErrorDto.class);
        assertThat(result.message()).containsExactlyInAnyOrder( "Company name must not be null", "Company name must not be empty");
    }

    @Test
    public void should_return_400_bad_request_and_validation_message_when_companyName_is_empty() throws Exception {
        //given
        OfferWriteModel toPost = OfferWriteModel.builder()
                .url("foo")
                .jobPosition("foo position")
                .companyName("").build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(toPost);

        //when
        ResultActions performPostOffer = mockMvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));
        // then
        MvcResult mvcResult = performPostOffer.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto result = objectMapper.readValue(json, ApiValidationErrorDto.class);
        assertThat(result.message()).containsExactlyInAnyOrder( "Company name must not be empty");
    }
}
