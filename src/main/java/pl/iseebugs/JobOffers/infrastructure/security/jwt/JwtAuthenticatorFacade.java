package pl.iseebugs.JobOffers.infrastructure.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import pl.iseebugs.JobOffers.infrastructure.loginAndRegister.controller.dto.JwtResponseDto;
import pl.iseebugs.JobOffers.infrastructure.loginAndRegister.controller.dto.TokenRequestDto;

import java.time.*;

@AllArgsConstructor
@Component
@Log4j2
public class JwtAuthenticatorFacade {

    private final AuthenticationManager authenticationManager;
    private final Clock clock;

    public JwtResponseDto authenticateAndGenerateToken(TokenRequestDto loginRequest){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        User user = (User) authenticate.getPrincipal();
        String token = createToken(user);
        String username = user.getUsername();
        return JwtResponseDto.builder()
                .token(token)
                .username(username)
                .build();
    }

    private String createToken(User user) {
        String secretKey = "tratata";
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant now = LocalDateTime.now(clock).toInstant(ZoneOffset.UTC);
        Instant expiresAt = now.plus(Duration.ofDays(30));
        String issuer = "Job offers Service";
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withIssuer(issuer)
                .sign(algorithm);
    }
}
