package pl.iseebugs.JobOffers.infrastructure.security.cacheManager;

import org.springframework.stereotype.Component;
import pl.iseebugs.JobOffers.domain.offers.projection.OfferReadModel;
import java.util.List;
@Component
public class CacheManagerFacade {
    public List<OfferReadModel> getAll(){return null;}

    public boolean updateCache(){
        return false;
    }
}
