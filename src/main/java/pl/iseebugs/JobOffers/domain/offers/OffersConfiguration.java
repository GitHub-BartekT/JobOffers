package pl.iseebugs.JobOffers.domain.offers;

import pl.iseebugs.JobOffers.domain.scheduler.SchedulerFacade;

class OffersConfiguration {
    static OffersFacade offersFacade(OffersRepository offersRepository, IdGenerable idGenerable, SchedulerFacade schedulerFacade){
        return new OffersFacade(offersRepository, idGenerable, schedulerFacade);
    }
}
