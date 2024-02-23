package pl.iseebugs.JobOffers.domain.offers;

import lombok.AllArgsConstructor;

import pl.iseebugs.JobOffers.projection.OfferWriteModel;
import pl.iseebugs.JobOffers.projection.OfferReadModel;
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
                                .orElseThrow(() -> new OfferNotFoundException(id));
        return toRead;
    }

    public List<OfferReadModel> getAll(){
        return schedulerFacade.getAll();
    }

    public OfferReadModel save(OfferWriteModel offerWriteModel){
        OfferEntity toSave = toEntityFromWriteModel(offerWriteModel);

        if(offersRepository.existsByUrl(toSave.url())){
            throw new IllegalArgumentException("Offer with that url already exists");
        } else if (offersRepository.existsById(toSave.id())) {
            throw new IllegalArgumentException("Offer with that Id already exists");
        }

        OfferReadModel saved = OfferMapper.toOfferReadModel(offersRepository.save(toSave));
        return saved;
    }

    private OfferEntity toEntityFromWriteModel (OfferWriteModel offerWriteModel){
        OfferEntity toSave = OfferEntity.builder()
                .id(idGenerable.createNewId())
                .url(offerWriteModel.getUrl())
                .jobPosition(offerWriteModel.getJobPosition())
                .companyName(offerWriteModel.getCompanyName())
                .salaryLowerBound(offerWriteModel.getSalaryLowerBound())
                .salaryUpperBound(offerWriteModel.getSalaryUpperBound())
                .build();
        return toSave;
    }
}
