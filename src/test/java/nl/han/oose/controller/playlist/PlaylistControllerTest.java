package nl.han.oose.controller.playlist;

import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.Tracklist;
import nl.han.oose.service.playlist.PlaylistService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.AuthenticationException;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {

    @Mock
    private PlaylistService playlistService;

    @InjectMocks
    private PlaylistController sut;

    //Tests for getAllPlaylists
    @Test
    public void testStatusBadRequestOnEmptyUsertokenGetallPlaylists() throws AuthenticationException {
        Response response = sut.getAllPlaylists("");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStatusOkAndPlaylistsEntityOnCorrectUsertokenGetallPlaylists() throws AuthenticationException {
        Playlists playlists = new Playlists();

        Mockito.when(playlistService.getAllPlaylists(Mockito.any())).thenReturn(playlists);
        Response response = sut.getAllPlaylists("321321");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlists, response.getEntity());
    }

    @Test
    public void testStatusForbiddenOnIncorrectUsertokenGetallPlaylists() throws AuthenticationException {
        Mockito.when(playlistService.getAllPlaylists(Mockito.any())).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.getAllPlaylists("321321");

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }


    //Tests for getContentOfPlaylist
    @Test
    public void testStatusBadRequestOnEmptyUsertokenGetContentOfPlaylist() throws AuthenticationException {
        Response response = sut.getContentOfPlaylist("", 1);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStatusOkAndTracklistEntityOnCorrectUsertokenGetContentOfPlaylist() throws AuthenticationException {
        Tracklist tracklist = new Tracklist();

        Mockito.when(playlistService.getContentOfPlaylist(Mockito.any(), Mockito.anyInt())).thenReturn(tracklist);
        Response response = sut.getContentOfPlaylist("321321", 1);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracklist, response.getEntity());
    }

    @Test
    public void testStatusForbiddenOnIncorrectUsertokenGetContentOfPlaylist() throws AuthenticationException {
        Mockito.when(playlistService.getContentOfPlaylist(Mockito.any(), Mockito.anyInt())).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.getContentOfPlaylist("321321", 1);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    //Tests for deletePlaylist
    @Test
    public void testStatusBadRequestOnEmptyUsertokenDeletePlaylist() throws AuthenticationException {
        Response response = sut.deletePlaylist("", 1);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStatusOkAndPlaylistsEntityOnCorrectUsertokenDeletePlaylist() throws AuthenticationException {
        Playlists playlists = new Playlists();

        Mockito.when(playlistService.deletePlaylist(Mockito.any(), Mockito.anyInt())).thenReturn(playlists);
        Response response = sut.deletePlaylist("321321", 1);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlists, response.getEntity());
    }

    @Test
    public void testStatusForbiddenOnIncorrectUsertokenDeletePlaylist() throws AuthenticationException {
        Mockito.when(playlistService.deletePlaylist(Mockito.any(), Mockito.anyInt())).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.deletePlaylist("321321", 1);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    //Tests for renamePlaylist
    @Test
    public void testStatusBadRequestOnEmptyUsertokenRenamePlaylist() throws AuthenticationException {

        Response response = sut.renamePlaylist("", new Playlist());
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStatusOkAndPlaylistsEntityOnCorrectUsertokenRenamePlaylist() throws AuthenticationException {
        Playlists playlists = new Playlists();

        Mockito.when(playlistService.renamePlaylist(Mockito.any(), Mockito.any(Playlist.class))).thenReturn(playlists);
        Response response = sut.renamePlaylist("321321", new Playlist());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlists, response.getEntity());
    }

    @Test
    public void testStatusForbiddenOnIncorrectUsertokenRenamePlaylist() throws AuthenticationException {
        Mockito.when(playlistService.renamePlaylist(Mockito.any(), Mockito.any(Playlist.class))).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.renamePlaylist("321321", new Playlist());

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    //Tests for addPlaylist
    @Test
    public void testStatusBadRequestOnEmptyUsertokenAddPlaylist() throws AuthenticationException {
        Response response = sut.addPlaylist("", new Playlist());
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStatusOkAndPlaylistsEntityOnCorrectUsertokenAddPlaylist() throws AuthenticationException {
        Playlists playlists = new Playlists();

        Mockito.when(playlistService.addPlaylist(Mockito.any(), Mockito.any(Playlist.class))).thenReturn(playlists);
        Response response = sut.addPlaylist("321321", new Playlist());
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals(playlists, response.getEntity());
    }

    @Test
    public void testStatusForbiddenOnIncorrectUsertokenAddPlaylist() throws AuthenticationException {
        Mockito.when(playlistService.addPlaylist(Mockito.any(), Mockito.any(Playlist.class))).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.addPlaylist("321321", new Playlist());

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    //Tests for removeTrackFromPlaylist
    @Test
    public void testStatusBadRequestOnEmptyUsertokenRemoveTrackFromPlaylist() throws AuthenticationException {
        Response response = sut.removeTrackFromPlaylist("", 1, 1);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStatusOkAndTracklistEntityOnCorrectUsertokenRemoveTrackFromPlaylist() throws AuthenticationException {
        Tracklist tracklist = new Tracklist();

        Mockito.when(playlistService.removeTrackFromPlaylist(Mockito.any(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(tracklist);
        Response response = sut.removeTrackFromPlaylist("321321", 1, 1);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracklist, response.getEntity());
    }

    @Test
    public void testStatusForbiddenOnIncorrectUsertokenRemoveTrackFromPlaylist() throws AuthenticationException {
        Mockito.when(playlistService.removeTrackFromPlaylist(Mockito.any(), Mockito.anyInt(), Mockito.anyInt())).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.removeTrackFromPlaylist("321321", 1, 1);

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    //Tests for addTrackToPlaylist
    @Test
    public void testStatusBadRequestOnEmptyUsertokenAddTrackToPlaylist() {
        Response response = sut.addTrackToPlaylist("", 1, new Track());
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStatusOkAndTracklistEntityOnCorrectUsertokenAddTrackToPlaylist() throws AuthenticationException {
        Tracklist tracklist = new Tracklist();

        Mockito.when(playlistService.addTrackToPlaylist(Mockito.any(), Mockito.anyInt(), Mockito.any(Track.class))).thenReturn(tracklist);
        Response response = sut.addTrackToPlaylist("321321", 1, new Track());
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals(tracklist, response.getEntity());
    }

    @Test
    public void testStatusForbiddenOnIncorrectUsertokenRemoveAddTrackToPlaylist() throws AuthenticationException {
        Mockito.when(playlistService.addTrackToPlaylist(Mockito.any(), Mockito.anyInt(), Mockito.any(Track.class))).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.addTrackToPlaylist("321321", 1, new Track());

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }
}
