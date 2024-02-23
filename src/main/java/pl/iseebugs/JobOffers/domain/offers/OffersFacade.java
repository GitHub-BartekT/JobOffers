package pl.iseebugs.JobOffers.domain.offers;

import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;
import pl.iseebugs.JobOffers.projection.OfferWriteModel;
import pl.iseebugs.JobOffers.projection.OfferReadModel;
import pl.iseebugs.JobOffers.domain.scheduler.SchedulerFacade;

import java.util.List;

@AllArgsConstructor
@Log4j2
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

        if (offersRepository.existsByUrl(offerWriteModel.getUrl())) {
            throw new IllegalArgumentException("Offer with that url already exists");
        }
        if (offerWriteModel.getId() != null && offersRepository.existsById(offerWriteModel.getId())) {
            throw new IllegalArgumentException("Offer with that Id already exists");
        }

        OfferEntity toSave = toEntityFromWriteModel(offerWriteModel);

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
