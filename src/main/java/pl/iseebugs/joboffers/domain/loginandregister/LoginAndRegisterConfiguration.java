package pl.iseebugs.joboffers.domain.loginandregister;


class LoginAndRegisterConfiguration {
    static LoginAndRegisterFacade loginAndRegisterFacade(UserRepository repository){
        IdGenerable idGenerator = new IdGeneratorUUID();
        return new LoginAndRegisterFacade(repository, idGenerator);
    }
}
