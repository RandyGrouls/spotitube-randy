package nl.han.oose.controller.login;

import nl.han.oose.entity.account.Account;
import nl.han.oose.entity.token.UserToken;
import nl.han.oose.service.login.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController sut;

    @Test
    public void testStatusOkOnSuccessfulLogin() throws LoginException {
        UserToken userToken = new UserToken("", "");
        Mockito.when(loginService.checkLogin(Mockito.any())).thenReturn(userToken);
        Account account = new Account("", "", "");
        Response loginResponse = sut.login(account);

        assertEquals(Response.Status.CREATED.getStatusCode(), loginResponse.getStatus());
        assertEquals(userToken, loginResponse.getEntity());
    }

    @Test
    public void testStatusUnauthorizedOnUnsuccessfulLogin() throws LoginException {
        Account account = new Account("", "", "");
        Mockito.when(loginService.checkLogin(Mockito.any())).thenThrow(new LoginException("Credentials incorrect"));
        Response loginResponse = sut.login(account);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), loginResponse.getStatus());
    }
}
