package pl.iseebugs.JobOffers.domain.loginAndRegister;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import pl.iseebugs.JobOffers.domain.loginAndRegister.projection.RegisterResultReadModel;
import pl.iseebugs.JobOffers.domain.loginAndRegister.projection.UserReadModel;
import pl.iseebugs.JobOffers.domain.loginAndRegister.projection.UserWriteModel;

@AllArgsConstructor
@Component
@Log4j2
public class LoginAndRegisterFacade {

    private static final String USER_NOT_FOUND = "User not found";
    UserRepository userRepository;
    IdGenerable idGenerator;

    public UserReadModel findByUsername(String username) {
        log.info("LoginAndRegisterFacade");
        return userRepository.findByUsername(username)
                .map(user -> UserReadModel.builder()
                        .username(user.username())
                        .password(user.password())
                        .id(user.id())
                        .build())
                .orElseThrow(() -> new BadCredentialsException(USER_NOT_FOUND));

       /* return UserMapper.toUserReadModel(userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException(USER_NOT_FOUND)));*/
    }

    public RegisterResultReadModel register(UserWriteModel userWriteModel) {
        if (userRepository.existsByUsername(userWriteModel.getUsername())){
            return RegisterResultReadModel.builder()
                    .id("")
                    .isCreated(false)
                    .username(userWriteModel.getUsername()).build();
        }

        User toSave = User.builder()
                .id(idGenerator.createNewId())
                .password(userWriteModel.getPassword())
                .username(userWriteModel.getUsername())
                .build();

        User saved = userRepository.save(toSave);
        return RegisterResultReadModel.builder()
                .id(saved.id())
                .isCreated(true)
                .username(saved.username()).build();
    }
}
