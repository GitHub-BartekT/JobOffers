package pl.iseebugs.JobOffers.infrastructure.token.controller;

import lombok.Builder;

@Builder
public record JwtResponseDto(
        String username,
        String token
) {
}
