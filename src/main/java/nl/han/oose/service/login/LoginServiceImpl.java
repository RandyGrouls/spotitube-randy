package nl.han.oose.service.login;


import nl.han.oose.entity.account.Account;
import nl.han.oose.persistence.account.AccountDAO;
import nl.han.oose.persistence.token.TokenDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

@Default
public class LoginServiceImpl implements LoginService {

    @Inject
    private AccountDAO accountDAO;

    @Inject
    private TokenDAO tokenDAO;

    @Override
    public UserToken checkLogin(Account account) throws LoginException {
        Account userAccount = accountDAO.getAccount(account.getUser());
        if (userAccount != null && account.getPassword().equals(userAccount.getPassword())) {
            return tokenDAO.createNewTokenForUser(userAccount.getUser(), userAccount.getFullname());
        } else {
            throw new LoginException("Credentials incorrect");
        }

    }

}
