package pl.iseebugs.JobOffers.domain.offersFetcher;

import java.util.UUID;

class IdGeneratorUUID implements IdGenerable {

    @Override
    public String createNewId() {
        return UUID.randomUUID().toString();
    }
}
