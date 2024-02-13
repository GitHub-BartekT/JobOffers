package pl.iseebugs.JobOffers.domain.loginAndRegister.projection;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterResultReadModel {
    String id;
    String username;
    boolean isCreated;
}
