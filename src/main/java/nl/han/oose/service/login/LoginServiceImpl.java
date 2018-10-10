package nl.han.oose.service.login;


import nl.han.oose.entity.account.Account;
import nl.han.oose.persistence.AccountDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

@Default
public class LoginServiceImpl implements LoginService {

    @Inject
    private AccountDAO accountDAO;

    @Override
    public UserToken checkLogin(Account account) throws LoginException {
        if ("randy".equals(account.getUser()) && "password".equals(account.getPassword())) {
            return new UserToken("1234-1234-1234", "Randy Grouls");
        } else {
            throw new LoginException("Credentials incorrect");
        }

    }

}
