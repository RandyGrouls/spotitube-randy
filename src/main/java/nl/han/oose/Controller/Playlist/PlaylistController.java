package nl.han.oose.Controller.Playlist;

import nl.han.oose.Playlists;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String userToken) {
        if ("1234-1234-1234".equals(userToken)) {
            return Response.status(Response.Status.OK).entity(new Playlists()).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

}
