package pl.iseebugs.JobOffers.domain.offersFetcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class FetcherConfiguration {

    @Bean
    FetcherRepository fetcherRepository(){
        return new FetcherRepository() {
            @Override
            public OfferFetchEntity saveOffer(OfferFetchEntity offerFetchEntity) {
                return null;
            }

            @Override
            public List<OfferFetchEntity> getAll() {
                return null;
            }
        };
    }

    @Bean
    static OffersFetcherFacade offersFetcherFacade(FetcherRepository repository, OffersFetchable offersFetchable){
        IdGenerable idGenerator = new IdGeneratorUUID();
        return new OffersFetcherFacade(repository, offersFetchable, idGenerator);
    }
}
