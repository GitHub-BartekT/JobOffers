package pl.iseebugs.joboffers.domain.offersfetcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FetcherConfiguration {

    @Bean
    static OffersFetcherFacade offersFetcherFacade(FetcherRepository repository, OffersFetchable offersFetchable){
        IdGenerable idGenerator = new IdGeneratorUUID();
        return new OffersFetcherFacade(repository, offersFetchable, idGenerator);
    }
}
