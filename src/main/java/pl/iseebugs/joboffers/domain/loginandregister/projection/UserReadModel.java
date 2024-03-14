package pl.iseebugs.joboffers.domain.loginandregister.projection;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserReadModel {
    String id;
    String username;
    String password;
}
