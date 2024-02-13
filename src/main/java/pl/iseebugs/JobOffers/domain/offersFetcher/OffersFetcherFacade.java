package pl.iseebugs.JobOffers.domain.offersFetcher;

import lombok.AllArgsConstructor;
import pl.iseebugs.JobOffers.domain.scheduler.SchedulerFetchListener;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class OffersFetcherFacade implements SchedulerFetchListener {

    private final FetcherRepository fetcherRepository;
    private final OffersFetchable offersFetchable;
    private final IdGenerable idGenerable;

    @Override
    public void onScheduleFetchAllOffersAndSaveAllIfNotExists() {
        List<OfferFetch> currentOffers = fetcherRepository.getAll();
        List<OfferFetch> newOffers = offersFetchable.getOffers();
        List<OfferFetch> addedOffers = new ArrayList<>();

        for (OfferFetch offer: newOffers) {
            if (currentOffers.stream()
                    .noneMatch(offerFetch -> offerFetch.url().equals(offer.url()))){
                addedOffers.add(OfferFetch.builder()
                        .id(idGenerable.createNewId())
                        .companyName(offer.companyName())
                        .jobPosition(offer.jobPosition())
                        .url(offer.url())
                        .salaryLowerBound(offer.salaryLowerBound())
                        .salaryUpperBound(offer.salaryUpperBound()).build());
            }
        }

        for (OfferFetch offer : addedOffers) {
            fetcherRepository.saveOffer(offer);
        }
    }
}
