package pl.iseebugs.JobOffers.domain.offersFetcher;

import lombok.AllArgsConstructor;
import pl.iseebugs.JobOffers.domain.scheduler.SchedulerFetchListener;
import pl.iseebugs.JobOffers.projection.OfferWriteModel;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class OffersFetcherFacade implements SchedulerFetchListener {

    private final FetcherRepository fetcherRepository;
    private final OffersFetchable offersFetchable;
    private final IdGenerable idGenerable;

    @Override
    public void onScheduleFetchAllOffersAndSaveAllIfNotExists() {
        List<OfferFetchEntity> currentOffers = fetcherRepository.getAll();
        List<OfferWriteModel> newOffers = offersFetchable.getOffers();
        List<OfferFetchEntity> addedOffers = new ArrayList<>();

        for (OfferWriteModel offer: newOffers) {
            if (currentOffers.stream()
                    .noneMatch(offerFetch -> offerFetch.url().equals(offer.getUrl()))){
                addedOffers.add(OfferFetchEntity.builder()
                        .id(idGenerable.createNewId())
                        .companyName(offer.getCompanyName())
                        .jobPosition(offer.getJobPosition())
                        .url(offer.getUrl())
                        .salaryLowerBound(offer.getSalaryLowerBound())
                        .salaryUpperBound(offer.getSalaryUpperBound())
                        .build());
            }
        }

        for (OfferFetchEntity offer : addedOffers) {
            fetcherRepository.saveOffer(offer);
        }
    }
}
