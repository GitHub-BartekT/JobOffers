package pl.iseebugs.JobOffers.domain.loginAndRegister;

import pl.iseebugs.JobOffers.domain.loginAndRegister.projection.UserReadModel;
import pl.iseebugs.JobOffers.domain.loginAndRegister.projection.UserWriteModel;

class UserMapper {
    static UserReadModel toUserReadModel(User user){
        return UserReadModel.builder()
                .id(user.id())
                .username(user.username())
                .build();
    }
}
