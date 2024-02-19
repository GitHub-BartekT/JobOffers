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
            public OfferFetch saveOffer(OfferFetch offerFetch) {
                return null;
            }

            @Override
            public List<OfferFetch> getAll() {
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
