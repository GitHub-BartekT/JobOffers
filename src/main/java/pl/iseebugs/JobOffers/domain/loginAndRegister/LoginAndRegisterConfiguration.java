package pl.iseebugs.JobOffers.domain.loginAndRegister;


class LoginAndRegisterConfiguration {
    static LoginAndRegisterFacade loginAndRegisterFacade(UserRepository repository){
        IdGenerable idGenerator = new IdGeneratorUUID();
        return new LoginAndRegisterFacade(repository, idGenerator);
    }
}
