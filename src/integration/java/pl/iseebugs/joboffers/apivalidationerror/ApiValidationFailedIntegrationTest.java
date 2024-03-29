package pl.iseebugs.joboffers.apivalidationerror;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.iseebugs.joboffers.BaseIntegrationTest;
import pl.iseebugs.joboffers.infrastructure.apivalidation.ApiValidationErrorDto;
import pl.iseebugs.joboffers.infrastructure.loginAndRegister.controller.dto.JwtResponseDto;
import pl.iseebugs.joboffers.projection.OfferWriteModel;

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
        String jwtToken = loginAndRegister();
        OfferWriteModel toPost = OfferWriteModel.builder()
                .url("")
                .jobPosition("foo position")
                .companyName("foo Company").build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(toPost);

        //when
        ResultActions performPostOffer = mockMvc.perform(post("/offers")
                .header("Authorization", "Bearer " + jwtToken)
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
        String jwtToken = loginAndRegister();
        OfferWriteModel toPost = OfferWriteModel.builder()
                .url("foo")
                .companyName("foo Company").build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(toPost);

        //when
        ResultActions performPostOffer = mockMvc.perform(post("/offers")
                .header("Authorization", "Bearer " + jwtToken)
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
        String jwtToken = loginAndRegister();
        OfferWriteModel toPost = OfferWriteModel.builder()
                .url("foo")
                .jobPosition("")
                .companyName("foo Company").build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(toPost);

        //when
        ResultActions performPostOffer = mockMvc.perform(post("/offers")
                .header("Authorization", "Bearer " + jwtToken)
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
        String jwtToken = loginAndRegister();
        OfferWriteModel toPost = OfferWriteModel.builder()
                .url("foo")
                .jobPosition("foo position").build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(toPost);

        //when
        ResultActions performPostOffer = mockMvc.perform(post("/offers")
                .header("Authorization", "Bearer " + jwtToken)
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
        String jwtToken = loginAndRegister();
        OfferWriteModel toPost = OfferWriteModel.builder()
                .url("foo")
                .jobPosition("foo position")
                .companyName("").build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(toPost);

        //when
        ResultActions performPostOffer = mockMvc.perform(post("/offers")
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));
        // then
        MvcResult mvcResult = performPostOffer.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto result = objectMapper.readValue(json, ApiValidationErrorDto.class);
        assertThat(result.message()).containsExactlyInAnyOrder( "Company name must not be empty");
    }

    String loginAndRegister() throws Exception {
        // step 1: someUser was registered with somePassword
        // given & when
        ResultActions registerAction = mockMvc.perform(post("/register")
                .content("""
                        {
                        "username": "someUser",
                        "password": "somePassword"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
        registerAction.andExpect(status().isCreated());

        // step 2: login
        // given && when
        ResultActions successLoginRequest = mockMvc.perform(post("/token")
                .content("""
                        {
                        "username": "someUser",
                        "password": "somePassword"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
        MvcResult mvcTokenResult = successLoginRequest.andExpect(status().isOk()).andReturn();
        String jsonToken = mvcTokenResult.getResponse().getContentAsString();
        JwtResponseDto jwtResponse = objectMapper.readValue(jsonToken, JwtResponseDto.class);
        return jwtResponse.token();
    }
}
