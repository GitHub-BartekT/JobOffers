package pl.iseebugs.JobOffers.domain.offersFetcher;

class FetcherConfiguration {
    static OffersFetcherFacade offersFetcherFacade(FetcherRepository repository, OffersFetchable offersFetchable){
        IdGenerable idGenerator = new IdGeneratorUUID();
        return new OffersFetcherFacade(repository, offersFetchable, idGenerator);
    }
}
