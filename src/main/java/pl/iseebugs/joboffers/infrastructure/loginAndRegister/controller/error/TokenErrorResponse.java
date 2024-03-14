package pl.iseebugs.joboffers.infrastructure.loginAndRegister.controller.error;

import org.springframework.http.HttpStatus;

public record TokenErrorResponse(String message,
                                 HttpStatus status) {
}
