package nl.han.oose.randygrouls.controller.playlist;

import nl.han.oose.randygrouls.entity.playlist.Playlist;
import nl.han.oose.randygrouls.entity.track.Track;
import nl.han.oose.randygrouls.service.playlist.PlaylistService;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.apache.cxf.common.util.StringUtils.isEmpty;

@Path("/playlists")
public class PlaylistController {

    @Inject
    private PlaylistService playlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        if (isEmpty(token)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.status(Response.Status.OK).entity(playlistService.getAllPlaylists(token)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path("/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContentOfPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId) {
        if (isEmpty(token)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.status(Response.Status.OK).entity(playlistService.getContentOfPlaylist(token, playlistId)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId) {
        if (isEmpty(token)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.status(Response.Status.OK).entity(playlistService.deletePlaylist(token, playlistId)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response renamePlaylist(@QueryParam("token") String token, Playlist playlist) {
        if (isEmpty(token)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.status(Response.Status.OK).entity(playlistService.renamePlaylist(token, playlist)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, Playlist playlist) {
        if (isEmpty(token)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.status(Response.Status.CREATED).entity(playlistService.addPlaylist(token, playlist)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path("/{playlistId}/tracks/{trackId}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {
        if (isEmpty(token)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.status(Response.Status.OK).entity(playlistService.removeTrackFromPlaylist(token, playlistId, trackId)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Path("/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, Track track) {
        if (isEmpty(token)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.status(Response.Status.CREATED).entity(playlistService.addTrackToPlaylist(token, playlistId, track)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
