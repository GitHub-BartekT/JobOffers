package pl.iseebugs.JobOffers.domain.loginAndRegister;

import lombok.Builder;

@Builder
record User(String id, String username, String password) {
}
