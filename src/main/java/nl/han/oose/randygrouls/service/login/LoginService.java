package nl.han.oose.randygrouls.service.login;

import nl.han.oose.randygrouls.entity.account.Account;
import nl.han.oose.randygrouls.entity.token.UserToken;

import javax.security.auth.login.LoginException;

public interface LoginService {
    UserToken checkLogin(Account account) throws LoginException;
}
