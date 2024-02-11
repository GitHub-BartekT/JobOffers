package pl.iseebugs.JobOffers.domain.offers;

import lombok.AllArgsConstructor;
import pl.iseebugs.JobOffers.domain.offers.projection.OfferReadModel;
import pl.iseebugs.JobOffers.domain.offers.projection.OfferWriteModel;
import pl.iseebugs.JobOffers.domain.scheduler.SchedulerFacade;

import java.util.List;

@AllArgsConstructor
public class OffersFacade {

    OffersRepository offersRepository;
    IdGenerable idGenerable;
    SchedulerFacade schedulerFacade;

    public OfferReadModel getOffer(String id) throws OfferNotFoundException {
        OfferReadModel toRead = offersRepository.getById(id)
                                .map(OfferMapper::toOfferReadModel)
                                .orElseThrow(OfferNotFoundException::new);
        return toRead;
    }

    public List<OfferReadModel> getAll(){
        return schedulerFacade.getAll();
    }

    public OfferReadModel save(OfferWriteModel offerWriteModel){
        if(offersRepository.existsByUrl(offerWriteModel.getUrl())){
            throw new IllegalArgumentException("Offer with that url already exists");
        } else if (offersRepository.existsById(offerWriteModel.getId())) {
            throw new IllegalArgumentException("Offer with that Id already exists");
        }

        Offer toSave = Offer.builder()
                .id(idGenerable.createNewId())
                .url(offerWriteModel.getUrl())
                .jobPosition(offerWriteModel.getJobPosition())
                .salaryLowerBound(offerWriteModel.getSalaryLowerBound())
                .salaryUpperBound(offerWriteModel.getSalaryUpperBound())
                .build();
        OfferReadModel saved = OfferMapper.toOfferReadModel(offersRepository.save(toSave));
        return saved;
    }
}
