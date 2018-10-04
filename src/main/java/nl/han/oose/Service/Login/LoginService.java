package nl.han.oose.Service.Login;


import nl.han.oose.Account;
import nl.han.oose.UserToken;

import javax.security.auth.login.LoginException;

public class LoginService {

    public UserToken checkLogin(Account user) throws LoginException {
        if ("randy".equals(user.getUser()) && "password".equals(user.getPassword())) {
            return new UserToken("1234-1234-1234", "Randy Grouls");
        } else {
            throw new LoginException("Credentials incorrect");
        }

    }

}
