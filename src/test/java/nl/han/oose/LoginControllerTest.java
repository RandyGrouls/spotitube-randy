package nl.han.oose;

import org.junit.Before;
import org.junit.Test;

public class LoginControllerTest {

    private LoginController loginController;

    @Before
    public void setUp() throws Exception {
        loginController = new LoginController();
    }

    @Test
    public void testThatLoginReturnsUserTokenIfUsernameAndPasswordMatch() {
        loginController.login(new LoginRequest("randy", "password"));
    }

}
