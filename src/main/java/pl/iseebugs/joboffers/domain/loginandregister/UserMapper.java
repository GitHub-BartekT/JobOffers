package pl.iseebugs.joboffers.domain.loginandregister;

import pl.iseebugs.joboffers.domain.loginandregister.projection.UserReadModel;

class UserMapper {
    static UserReadModel toUserReadModel(User user){
        return UserReadModel.builder()
                .id(user.id())
                .username(user.username())
                .build();
    }
}
