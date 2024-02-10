package pl.iseebugs.JobOffers.domain.offers;

import lombok.AllArgsConstructor;
import pl.iseebugs.JobOffers.domain.offers.projection.OffersReadModel;
import pl.iseebugs.JobOffers.domain.offers.projection.OffersWriteModel;

import java.util.List;

@AllArgsConstructor
public class OffersFacade {

    OffersRepository offersRepository;
    IdGenerable idGenerable;

    public OffersReadModel getOffer(String id){
        return null;
    }

    public List<OffersReadModel> getAll(){
        return null;
    }

    public OffersReadModel save(OffersWriteModel offersWriteModel){
        if(offersRepository.existsByUrl(offersWriteModel.getUrl())){
            throw new IllegalArgumentException("Offer with that url already exists");
        } else if (offersRepository.existsById(offersWriteModel.getId())) {
            throw new IllegalArgumentException("Offer with that Id already exists");
        }

        Offer toSave = Offer.builder()
                .id(idGenerable.createNewId())
                .url(offersWriteModel.getUrl())
                .jobPosition(offersWriteModel.getJobPosition())
                .salaryLowerBound(offersWriteModel.getSalaryLowerBound())
                .salaryUpperBound(offersWriteModel.getSalaryUpperBound())
                .build();
        OffersReadModel saved = OfferMapper.toOffersReadModel(offersRepository.save(toSave));
        return saved;
    }
}
