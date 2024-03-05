package pl.iseebugs.JobOffers.infrastructure.token.controller.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class TokenControllerErrorHandler {

    private static final String BAD_CREDENTIALS = "Bad Credentials";

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public TokenErrorResponse handleBadCredentials() {
        log.info("Throws bad Credentials");
        return new TokenErrorResponse(BAD_CREDENTIALS, HttpStatus.UNAUTHORIZED);
    }
}
