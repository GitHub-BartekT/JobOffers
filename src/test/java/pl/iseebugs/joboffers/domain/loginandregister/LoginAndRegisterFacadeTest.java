package pl.iseebugs.joboffers.domain.loginandregister;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import pl.iseebugs.joboffers.domain.loginandregister.projection.RegisterResultReadModel;
import pl.iseebugs.joboffers.domain.loginandregister.projection.UserReadModel;
import pl.iseebugs.joboffers.domain.loginandregister.projection.UserWriteModel;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginAndRegisterFacadeTest {

    @Test
    void findByUsername_throw_BadCredentialsException_when_no_user_with_given_username() throws BadCredentialsException {
        //given
        UserRepository mockUserRepository = mock(UserRepository.class);
        when(mockUserRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        LoginAndRegisterFacade toTest = LoginAndRegisterConfiguration.loginAndRegisterFacade(mockUserRepository);
        //when
        Throwable exception = catchThrowable(() -> toTest.findByUsername("foo"));
        //then
        assertThat(exception).isInstanceOf(BadCredentialsException.class);
    }

    @Test
    void findByUsername_returns_user_with_given_username() throws BadCredentialsException {
        //given
        UserRepository repository = new InMemoryRepositoryOffersTestImpl();
        repository.save(User.builder()
                .id("123")
                .username("foo")
                .password("bar")
                .build());
        LoginAndRegisterFacade toTest = LoginAndRegisterConfiguration.loginAndRegisterFacade(repository);
        //when
        UserReadModel result = toTest.findByUsername("foo");
        //then
        assertThat(result.getUsername()).isEqualTo("foo");

    }

    @Test
    void register_return_with_false_and_no_id_when_user_with_given_username_already_exists(){
        //given
        UserRepository mockUserRepository = mock(UserRepository.class);
        when(mockUserRepository.existsByUsername(anyString())).thenReturn(true);
        LoginAndRegisterFacade toTest = LoginAndRegisterConfiguration.loginAndRegisterFacade(mockUserRepository);
        //when
        RegisterResultReadModel result = toTest.register(UserWriteModel.builder().username("foo").build());
        //then
        assertThat(result.isCreated()).isFalse();
    }

    @Test
    void register_registers_user(){
        //given
        UserRepository repository = new InMemoryRepositoryOffersTestImpl();
        LoginAndRegisterFacade toTest = LoginAndRegisterConfiguration.loginAndRegisterFacade(repository);
        //and
        UserWriteModel toRegister = UserWriteModel.builder()
                .username("foo")
                .password("bar")
                .build();
        //when
        RegisterResultReadModel result = toTest.register(toRegister);
        //then
        assertThat(result.isCreated()).isTrue();
    }
}