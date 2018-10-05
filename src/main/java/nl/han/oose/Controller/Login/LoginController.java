package nl.han.oose.Controller.Login;

import nl.han.oose.Account;
import nl.han.oose.Service.Login.LoginService;

import javax.security.auth.login.LoginException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    private LoginService loginService = new LoginService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Account account) {
        try {
            return Response.status(Response.Status.OK).entity(loginService.checkLogin(account)).build();
        } catch (LoginException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
