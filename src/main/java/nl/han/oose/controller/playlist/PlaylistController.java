package nl.han.oose.controller.playlist;

import nl.han.oose.service.playlist.PlaylistService;

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

    @Path("/{id}/tracks")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContentOfPlaylist(@QueryParam("token") String userToken, @PathParam("id") int playlistId) {
        try {
            return Response.status(Response.Status.OK).entity(playlistService.getContentOfPlaylist(userToken, playlistId)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
