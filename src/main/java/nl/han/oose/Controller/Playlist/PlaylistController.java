package nl.han.oose.Controller.Playlist;

import nl.han.oose.Service.Playlist.PlaylistService;

import javax.naming.AuthenticationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
    private PlaylistService playlistService = new PlaylistService();

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String userToken) {
        try {
            return Response.status(Response.Status.OK).entity(playlistService.getAllPlaylists(userToken)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
