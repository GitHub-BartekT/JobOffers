package pl.iseebugs.JobOffers.domain.loginAndRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class InMemoryRepositoryOffersTestImpl implements UserRepository{
    Map<String, User> inMemoryRepository = new HashMap<>();

    @Override
    public Optional<User> findByUsername(String username) {
        return inMemoryRepository.values()
                .stream()
                .filter(user -> user.username().equals(username))
                .findFirst();
    }

    @Override
    public User save(User user) {
        inMemoryRepository.put(user.id(), user);
        return inMemoryRepository.get(user.id());
    }

    @Override
    public boolean existsByUsername(String username) {
        return inMemoryRepository.values().stream().anyMatch(user -> user.username().equals(username));
    }
}
