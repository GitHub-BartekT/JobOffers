package pl.iseebugs.JobOffers.infrastructure.offers.controller.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.iseebugs.JobOffers.domain.offers.OfferNotFoundException;

@ControllerAdvice
@Log4j2
public class OffersExceptionHandler {

    @ExceptionHandler(OfferNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public OfferErrorResponse handlerOfferNotFoundException(OfferNotFoundException e){
        OfferErrorResponse response = OfferErrorResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
        return response;
    }
}
