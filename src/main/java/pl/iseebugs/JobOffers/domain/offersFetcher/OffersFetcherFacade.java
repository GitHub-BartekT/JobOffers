package pl.iseebugs.JobOffers.domain.offersFetcher;

import lombok.AllArgsConstructor;
import pl.iseebugs.JobOffers.domain.scheduler.SchedulerFetchListener;

@AllArgsConstructor
public class OffersFetcherFacade implements SchedulerFetchListener {


    @Override
    public void onScheduleFetch() {
    }
}
