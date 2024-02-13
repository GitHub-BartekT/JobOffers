package pl.iseebugs.JobOffers.domain.loginAndRegister;

import java.util.Optional;

interface UserRepository {

    Optional<User> findByUsername(String id);

    User save(User user);

    boolean existsByUsername(String id);


}
