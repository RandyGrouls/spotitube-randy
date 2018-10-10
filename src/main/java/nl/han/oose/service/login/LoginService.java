package nl.han.oose.service.login;

import nl.han.oose.entity.account.Account;

import javax.security.auth.login.LoginException;

public interface LoginService {
    UserToken checkLogin(Account account) throws LoginException;
}
