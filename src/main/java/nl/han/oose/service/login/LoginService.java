package nl.han.oose.service.login;

import nl.han.oose.Account;
import nl.han.oose.UserToken;

import javax.security.auth.login.LoginException;

public interface LoginService {
    UserToken checkLogin(Account account) throws LoginException;
}
