package pl.iseebugs.joboffers.infrastructure.offers.controller.error;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record OfferErrorResponse(String message, HttpStatus status) {
}
