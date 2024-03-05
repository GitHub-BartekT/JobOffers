package pl.iseebugs.JobOffers.infrastructure.loginAndRegister.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.iseebugs.JobOffers.domain.loginAndRegister.LoginAndRegisterFacade;
import pl.iseebugs.JobOffers.domain.loginAndRegister.projection.RegisterResultReadModel;
import pl.iseebugs.JobOffers.domain.loginAndRegister.projection.UserWriteModel;

@RestController
@AllArgsConstructor
public class RegisterController {

    private final LoginAndRegisterFacade loginAndRegisterFacade;
    private final PasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/register")
    public ResponseEntity<RegisterResultReadModel> register(@RequestBody UserWriteModel userWriteModel){
        String encodedPassword = bCryptPasswordEncoder.encode(userWriteModel.getPassword());
        RegisterResultReadModel registerResult = loginAndRegisterFacade.register(UserWriteModel.builder()
                .username(userWriteModel.getUsername())
                .password(encodedPassword)
                .build());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResult);
    }
}
