package pl.iseebugs.joboffers.infrastructure.loginAndRegister.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.iseebugs.joboffers.infrastructure.loginAndRegister.controller.dto.JwtResponseDto;
import pl.iseebugs.joboffers.infrastructure.loginAndRegister.controller.dto.TokenRequestDto;
import pl.iseebugs.joboffers.infrastructure.security.jwt.JwtAuthenticatorFacade;

@RestController
@RequestMapping(value = "/token")
@AllArgsConstructor
@Log4j2
public class TokenController {

    private final JwtAuthenticatorFacade jwtAuthenticatorFacade;

    @PostMapping
    public ResponseEntity<JwtResponseDto> authenticateAndGenerateToken(@Valid @RequestBody TokenRequestDto tokenRequest){
        log.info("token started");
        final JwtResponseDto jwtResponse = jwtAuthenticatorFacade.authenticateAndGenerateToken(tokenRequest);
        return ResponseEntity.ok(jwtResponse);
    }
}


