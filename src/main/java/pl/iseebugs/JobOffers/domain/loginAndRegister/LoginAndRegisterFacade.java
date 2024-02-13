package pl.iseebugs.JobOffers.domain.loginAndRegister;

import lombok.AllArgsConstructor;
import pl.iseebugs.JobOffers.domain.loginAndRegister.projection.RegisterResultReadModel;
import pl.iseebugs.JobOffers.domain.loginAndRegister.projection.UserReadModel;
import pl.iseebugs.JobOffers.domain.loginAndRegister.projection.UserWriteModel;

@AllArgsConstructor
public class LoginAndRegisterFacade {

    UserRepository userRepository;
    IdGenerable idGenerator;

    UserReadModel findByUsername(String username) throws UserNotFoundException {
        return UserMapper.toUserReadModel(userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new));
    }

    RegisterResultReadModel register(UserWriteModel userWriteModel) {
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
