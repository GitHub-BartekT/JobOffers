package pl.iseebugs.joboffers.domain.loginandregister.projection;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterResultReadModel {
    String id;
    String username;
    boolean created;
}
