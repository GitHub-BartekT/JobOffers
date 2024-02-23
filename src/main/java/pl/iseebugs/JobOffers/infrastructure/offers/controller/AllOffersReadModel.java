package pl.iseebugs.JobOffers.infrastructure.offers.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import pl.iseebugs.JobOffers.projection.OfferReadModel;

import java.util.List;

@Builder
public record AllOffersReadModel (List<OfferReadModel> offerReadModels){
}
