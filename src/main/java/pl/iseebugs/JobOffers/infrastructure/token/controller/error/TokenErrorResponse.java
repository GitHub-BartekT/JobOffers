package pl.iseebugs.JobOffers.infrastructure.token.controller.error;

import org.springframework.http.HttpStatus;

public record TokenErrorResponse(String message,
                                 HttpStatus status) {
}
