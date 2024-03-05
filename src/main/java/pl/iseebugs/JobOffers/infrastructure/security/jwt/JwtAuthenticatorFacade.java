package pl.iseebugs.JobOffers.infrastructure.security.jwt;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.iseebugs.JobOffers.infrastructure.token.controller.JwtResponseDto;
import pl.iseebugs.JobOffers.infrastructure.token.controller.TokenRequestDto;

@AllArgsConstructor
@Component
@Log4j2
public class JwtAuthenticatorFacade {

    private final AuthenticationManager authenticationManager;

    public JwtResponseDto authenticateAndGenerateToken(TokenRequestDto loginRequest){
        log.info("2. start authenticator");
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        log.info("3. end authenticator");

        return JwtResponseDto.builder().build();
    }
}
