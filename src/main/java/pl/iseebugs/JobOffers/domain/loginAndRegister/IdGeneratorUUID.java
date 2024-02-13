package pl.iseebugs.JobOffers.domain.loginAndRegister;

import pl.iseebugs.JobOffers.domain.offers.IdGenerable;

import java.util.UUID;

class IdGeneratorUUID implements IdGenerable {

    @Override
    public String createNewId() {
        return UUID.randomUUID().toString();
    }
}
