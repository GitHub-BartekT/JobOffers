package pl.iseebugs.JobOffers.infrastructure.cacheManager;

import org.springframework.stereotype.Component;
import pl.iseebugs.JobOffers.projection.OfferReadModel;
import java.util.List;
@Component
public class CacheManagerFacade {
    public List<OfferReadModel> getAll(){return null;}

    public boolean updateCache(){
        return false;
    }

    public boolean isEmpty(){
        return true;
    }
}
