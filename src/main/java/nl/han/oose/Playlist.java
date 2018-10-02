package nl.han.oose;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/playlists")
public class Playlist {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPlaylists(@QueryParam("token") String userToken) {
        System.out.println(userToken);
        return null;
    }
}
