package pl.iseebugs.JobOffers.domain.offersFetcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class FetcherConfiguration {

    @Bean
    static OffersFetcherFacade offersFetcherFacade(FetcherRepository repository, OffersFetchable offersFetchable){
        IdGenerable idGenerator = new IdGeneratorUUID();
        return new OffersFetcherFacade(repository, offersFetchable, idGenerator);
    }
}
