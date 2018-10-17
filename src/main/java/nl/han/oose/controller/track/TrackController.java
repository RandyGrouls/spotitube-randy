package nl.han.oose.controller.track;

import nl.han.oose.controller.ErrorCheck;
import nl.han.oose.service.track.TrackService;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController extends ErrorCheck {

    @Inject
    private TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAvailableTracksForPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistId) {
        if (paramIsEmpty(token) || paramIsEmpty(playlistId)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.status(Response.Status.OK).entity(trackService.getAllAvailableTracksForPlaylist(token, playlistId)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }


}
