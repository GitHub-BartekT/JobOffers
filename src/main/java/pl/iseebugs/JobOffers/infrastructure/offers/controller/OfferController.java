package pl.iseebugs.JobOffers.infrastructure.offers.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.iseebugs.JobOffers.domain.offers.OfferNotFoundException;
import pl.iseebugs.JobOffers.domain.offers.OffersFacade;
import pl.iseebugs.JobOffers.projection.OfferReadModel;

import java.util.List;

@RestController
@RequestMapping("/offers")
@AllArgsConstructor
@Log4j2
public class OfferController {

    private final OffersFacade offersFacade;

    @GetMapping
    ResponseEntity< AllOffersReadModel> readAllOffers(){
        log.info("Controller. Request /offers");
        List<OfferReadModel> offers = offersFacade.getAll();
        AllOffersReadModel response = AllOffersReadModel.builder()
                .offerReadModels(offers).build();
        log.info("Taken {} offers", offers.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping({"/{id}"})
    ResponseEntity<OfferReadModel> readOfferById(@PathVariable String id) throws OfferNotFoundException {
        log.info("Controller. Request /offers/{}", id);
        OfferReadModel response = offersFacade.getOffer(id);
        log.info("Taken offer with id: {}", response.getId());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(OfferNotFoundException.class)
    ResponseEntity<String> handlerOfferNotFoundException(OfferNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
