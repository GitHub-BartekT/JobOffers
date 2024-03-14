package pl.iseebugs.joboffers.domain.loginandregister;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
class IdGeneratorUUID implements pl.iseebugs.joboffers.domain.loginandregister.IdGenerable {

    @Override
    public String createNewId() {
        return UUID.randomUUID().toString();
    }
}
