package pl.iseebugs.JobOffers.domain.loginAndRegister;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class IdGeneratorUUID implements pl.iseebugs.JobOffers.domain.loginAndRegister.IdGenerable {

    @Override
    public String createNewId() {
        return UUID.randomUUID().toString();
    }
}
