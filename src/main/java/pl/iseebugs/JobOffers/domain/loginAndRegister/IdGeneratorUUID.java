package pl.iseebugs.JobOffers.domain.loginAndRegister;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
class IdGeneratorUUID implements pl.iseebugs.JobOffers.domain.loginAndRegister.IdGenerable {

    @Override
    public String createNewId() {
        return UUID.randomUUID().toString();
    }
}
