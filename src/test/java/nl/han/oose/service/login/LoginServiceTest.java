package nl.han.oose.service.login;

import nl.han.oose.entity.account.Account;
import nl.han.oose.entity.token.UserToken;
import nl.han.oose.persistence.account.AccountDAO;
import nl.han.oose.persistence.token.TokenDAO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private AccountDAO accountDAO;

    @Mock
    private TokenDAO tokenDAO;

    @InjectMocks
    private LoginServiceImpl sut;

    @Test
    public void testThatUserTokenIsReturnedIfCredentialsAreCorrect() throws LoginException {
        Account account = new Account("randy", "randypassword", "Randy Grouls");
        UserToken userToken = new UserToken("123123", "randy");
        Mockito.when(accountDAO.getAccount(Mockito.any())).thenReturn(account);
        Mockito.when(tokenDAO.createNewTokenForUser(Mockito.any(), Mockito.any())).thenReturn(userToken);
        sut.checkLogin(account);

        assertEquals("123123", userToken.getToken());
        assertEquals("randy", userToken.getUser());
    }

    @Test
    public void testThaExceptionIsReturnedIfCredentialsAreIncorrect() throws LoginException {
        thrown.expect(LoginException.class);
        thrown.expectMessage("Credentials incorrect");

        Account account = new Account("randy", "randypassword", "Randy Grouls");
        Account account1 = new Account("randy", "randywrongpassword", "Randy Grouls");
        Mockito.when(accountDAO.getAccount(Mockito.any())).thenReturn(account);
        sut.checkLogin(account1);
    }
}
