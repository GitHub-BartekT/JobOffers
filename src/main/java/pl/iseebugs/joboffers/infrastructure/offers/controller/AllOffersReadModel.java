package pl.iseebugs.joboffers.infrastructure.offers.controller;

import lombok.Builder;
import pl.iseebugs.joboffers.projection.OfferReadModel;

import java.util.List;

@Builder
public record AllOffersReadModel (List<OfferReadModel> offerReadModels){
}
