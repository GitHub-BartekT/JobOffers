package pl.iseebugs.JobOffers.domain.offers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.iseebugs.JobOffers.domain.scheduler.SchedulerFacade;
@Configuration
class OffersConfiguration {

    @Bean
    IdGenerable idGenerable(){
        return new IdGeneratorUUID();
    };

    @Bean
    static OffersFacade offersFacade(OffersRepository offersRepository, IdGenerable idGenerable, SchedulerFacade schedulerFacade){
        return new OffersFacade(offersRepository, idGenerable, schedulerFacade);
    }
}
