package pl.iseebugs.JobOffers.infrastructure.offers.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.iseebugs.JobOffers.domain.offers.OfferNotFoundException;
import pl.iseebugs.JobOffers.domain.offers.OffersFacade;
import pl.iseebugs.JobOffers.infrastructure.http.offersAWS.OfferAWSFetcherClientProperties;
import pl.iseebugs.JobOffers.projection.OfferReadModel;
import pl.iseebugs.JobOffers.projection.OfferWriteModel;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/offers")
@AllArgsConstructor
@Log4j2
public class OfferController {

    OfferAWSFetcherClientProperties properties;
    private final OffersFacade offersFacade;

    @GetMapping
    ResponseEntity< AllOffersReadModel> readAllOffers(){
        log.info("OfferController. Request GET /offers");
        List<OfferReadModel> offers = offersFacade.getAll();
        AllOffersReadModel response = AllOffersReadModel.builder()
                .offerReadModels(offers).build();
        log.info("Taken {} offers", offers.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping({"/{id}"})
    ResponseEntity<OfferReadModel> readOfferById(@PathVariable String id) throws OfferNotFoundException {
        log.info("Offer Controller. Request GET /offers/{}", id);
        OfferReadModel response = offersFacade.getOffer(id);
        log.info("Taken offer with id: {}", response.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<OfferReadModel> createOffer(@RequestBody @Valid OfferWriteModel toWrite)
            throws OfferNotFoundException {
        log.info("Offer Controller. Request POST /offers");
        OfferReadModel response = offersFacade.save(toWrite);
        log.info("Saved offer with id: {}", response.getId());
        return ResponseEntity.created(URI.create (getUrlForService() + response.getId())).build();
    }

    private String getUrlForService() {
        String uri = properties.uri();
        int port = properties.port();
        String service = "/offers/";
        return uri + ":" + port + service;
    }
}
