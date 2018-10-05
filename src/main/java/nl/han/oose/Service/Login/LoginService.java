package nl.han.oose.Service.Login;


import nl.han.oose.Account;
import nl.han.oose.UserToken;

import javax.security.auth.login.LoginException;

public class LoginService {

    public UserToken checkLogin(Account account) throws LoginException {
        if ("randy".equals(account.getUser()) && "password".equals(account.getPassword())) {
            return new UserToken("1234-1234-1234", "Randy Grouls");
        } else {
            throw new LoginException("Credentials incorrect");
        }

    }

}
