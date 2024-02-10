package pl.iseebugs.JobOffers.domain.offers;

class OffersConfiguration {
    static OffersFacade offersFacade(OffersRepository offersRepository,IdGenerable idGenerable){
        return new OffersFacade(offersRepository, idGenerable);
    }
}
