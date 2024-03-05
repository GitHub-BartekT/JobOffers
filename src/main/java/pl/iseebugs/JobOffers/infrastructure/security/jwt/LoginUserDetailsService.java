package pl.iseebugs.JobOffers.infrastructure.security.jwt;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import pl.iseebugs.JobOffers.domain.loginAndRegister.LoginAndRegisterFacade;
import pl.iseebugs.JobOffers.domain.loginAndRegister.projection.UserReadModel;

import java.util.Collections;

@AllArgsConstructor
@Log4j2
public class LoginUserDetailsService implements UserDetailsService {

    private final LoginAndRegisterFacade loginAndRegisterFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException{
        log.info("LoginUserDetailsService");
            UserReadModel userReadModel = loginAndRegisterFacade.findByUsername(username);
        return getUser(userReadModel);
    }

    private User getUser(UserReadModel user){
        return new User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList());
    }
}
