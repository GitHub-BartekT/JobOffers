package pl.iseebugs.joboffers.domain.scheduler;

import pl.iseebugs.joboffers.projection.OfferReadModel;

import java.util.List;

public interface SchedulerFetchListener {
    List<OfferReadModel> onScheduleFetchAllOffersAndSaveAllIfNotExists();
}
