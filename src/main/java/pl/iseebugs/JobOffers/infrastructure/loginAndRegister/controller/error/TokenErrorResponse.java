package pl.iseebugs.JobOffers.infrastructure.loginAndRegister.controller.error;

import org.springframework.http.HttpStatus;

public record TokenErrorResponse(String message,
                                 HttpStatus status) {
}
