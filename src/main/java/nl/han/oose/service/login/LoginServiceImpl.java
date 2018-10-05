package nl.han.oose.service.login;


import nl.han.oose.Account;
import nl.han.oose.UserToken;

import javax.enterprise.inject.Default;
import javax.security.auth.login.LoginException;

@Default
public class LoginServiceImpl implements LoginService {

    @Override
    public UserToken checkLogin(Account account) throws LoginException {
        if ("randy".equals(account.getUser()) && "password".equals(account.getPassword())) {
            return new UserToken("1234-1234-1234", "Randy Grouls");
        } else {
            throw new LoginException("Credentials incorrect");
        }

    }

}
