package nl.han.oose.service.login;

import nl.han.oose.entity.account.Account;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.security.auth.login.LoginException;

import static junit.framework.TestCase.assertEquals;

public class LoginServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private LoginService sut;

    @Before
    public void setUp() throws Exception {
        sut = new LoginServiceImpl();
    }

    @Test
    public void testThatUserTokenIsReturnedIfCredentialsAreCorrect() throws LoginException {
        Account user = new Account("randy", "password", "Randy Grouls");
        UserToken userToken = sut.checkLogin(user);

        assertEquals("1234-1234-1234", userToken.getToken());
        assertEquals("Randy Grouls", userToken.getUser());
    }

    @Test
    public void testThaExceptionIsReturnedIfCredentialsAreIncorrect() throws LoginException {
        thrown.expect(LoginException.class);
        thrown.expectMessage("Credentials incorrect");
        Account user = new Account("randy", "wrongpassword", "Randy Grouls");
        sut.checkLogin(user);
    }
}
