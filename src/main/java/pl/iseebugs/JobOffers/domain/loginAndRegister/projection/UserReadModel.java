package pl.iseebugs.JobOffers.domain.loginAndRegister.projection;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserReadModel {
    String id;
    String username;
    String password;
}
