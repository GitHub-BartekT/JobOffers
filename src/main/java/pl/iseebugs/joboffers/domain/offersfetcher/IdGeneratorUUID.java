package pl.iseebugs.joboffers.domain.offersfetcher;

import java.util.UUID;

class IdGeneratorUUID implements IdGenerable {

    @Override
    public String createNewId() {
        return UUID.randomUUID().toString();
    }
}
