package pl.iseebugs.JobOffers.infrastructure.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth.jwt")
public record JwtConfigurationProperties (
    String secret,
    long expirationDays,
    String issuer
    ){
}
