package pl.iseebugs.JobOffers.domain.loginAndRegister.projection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserWriteModel {
    String id;
    String username;
    String password;
}
