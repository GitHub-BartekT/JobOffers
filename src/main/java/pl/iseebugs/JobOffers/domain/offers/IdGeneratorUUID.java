package pl.iseebugs.JobOffers.domain.offers;

import java.util.UUID;

class IdGeneratorUUID implements IdGenerable {

    @Override
    public String createNewId() {
        return UUID.randomUUID().toString();
    }
}
