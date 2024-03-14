package pl.iseebugs.joboffers.infrastructure.loginAndRegister.controller.dto;

import lombok.Builder;

@Builder
public record JwtResponseDto(
        String username,
        String token
) {
}
