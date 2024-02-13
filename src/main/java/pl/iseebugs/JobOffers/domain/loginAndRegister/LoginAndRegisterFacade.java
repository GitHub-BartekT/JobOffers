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
        if (userRepository.existsById(userWriteModel.getUsername())){
            return RegisterResultReadModel.builder()
                    .id(userWriteModel.getId())
                    .isCreated(false)
                    .username(userWriteModel.getUsername()).build();
        }
        User toSave = User.builder()
                .id(idGenerator.createNewId())
                .password(userWriteModel.getPassword())
                .username(userWriteModel.getUsername())
                .build();
        UserReadModel saved = UserMapper.toUserReadModel(userRepository.save(toSave));
        return RegisterResultReadModel.builder()
                .id(saved.getId())
                .isCreated(true)
                .username(saved.getUsername()).build();
    }
}
