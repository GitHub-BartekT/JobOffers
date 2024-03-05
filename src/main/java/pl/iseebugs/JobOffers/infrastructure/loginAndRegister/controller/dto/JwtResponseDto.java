package pl.iseebugs.JobOffers.infrastructure.loginAndRegister.controller.dto;

import lombok.Builder;

@Builder
public record JwtResponseDto(
        String username,
        String token
) {
}
