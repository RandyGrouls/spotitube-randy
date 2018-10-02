package nl.han.oose;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        if ("randy".equals(loginRequest.getUser()) && "password".equals(loginRequest.getPassword())) {
            return Response.status(Response.Status.ACCEPTED).entity(new UserToken("1234-1234-1234", "Randy Grouls")).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
