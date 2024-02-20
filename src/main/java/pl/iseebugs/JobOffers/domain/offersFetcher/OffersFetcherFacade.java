package pl.iseebugs.JobOffers.domain.offersFetcher;

import lombok.AllArgsConstructor;
import pl.iseebugs.JobOffers.domain.scheduler.SchedulerFetchListener;
import pl.iseebugs.JobOffers.projection.OfferReadModel;
import pl.iseebugs.JobOffers.projection.OfferWriteModel;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class OffersFetcherFacade implements SchedulerFetchListener {

    private final FetcherRepository fetcherRepository;
    private final OffersFetchable offersFetchable;
    private final IdGenerable idGenerable;

    @Override
    public List<OfferReadModel> onScheduleFetchAllOffersAndSaveAllIfNotExists() {
        List<OfferFetchEntity> currentOffers = fetcherRepository.findAll();
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
        List<OfferFetchEntity> savedOffers = new ArrayList<>();
        for (OfferFetchEntity offer : addedOffers) {
            savedOffers.add(fetcherRepository.save(offer));
        }
        return savedOffers.stream().map(offerFetchEntity -> OfferReadModel.builder()
                .url(offerFetchEntity.url())
                .id(offerFetchEntity.id())
                .jobPosition(offerFetchEntity.jobPosition())
                .companyName(offerFetchEntity.companyName())
                .salaryLowerBound(offerFetchEntity.salaryLowerBound())
                .salaryUpperBound(offerFetchEntity.salaryUpperBound())
                .build()).toList();
    }
}
