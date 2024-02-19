package pl.iseebugs.JobOffers.domain.scheduler;

import pl.iseebugs.JobOffers.projection.OfferReadModel;

import java.util.List;

public interface SchedulerFetchListener {
    List<OfferReadModel> onScheduleFetchAllOffersAndSaveAllIfNotExists();
}
