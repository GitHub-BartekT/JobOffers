package pl.iseebugs.JobOffers.domain.loginAndRegister;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
record User(
        @Id
        String id, String username, String password) {
}
