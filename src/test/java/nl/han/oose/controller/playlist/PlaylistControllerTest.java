package nl.han.oose.controller.playlist;

import nl.han.oose.Playlist;
import nl.han.oose.Playlists;
import nl.han.oose.service.playlist.PlaylistService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.AuthenticationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {

    @Mock
    private PlaylistService playlistService;

    @InjectMocks
    private PlaylistController sut;

    @Test
    public void testStatusOkAndPlaylistsEntityOnCorrectUsertoken() throws AuthenticationException {
        Playlists playlists = new Playlists();
        playlists.getPlaylists().add(new Playlist(1, "Death metal", true, new ArrayList<>()));
        playlists.getPlaylists().add(new Playlist(2, "Pop", false, new ArrayList<>()));
        playlists.setLength(1234);

        Mockito.when(playlistService.getAllPlaylists(Mockito.any())).thenReturn(playlists);
        Response response = sut.getAllPlaylists("");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlists, response.getEntity());
    }

    @Test
    public void testStatusUnauthorizedOnIncorrectUsertoken() throws AuthenticationException {
        Mockito.when(playlistService.getAllPlaylists("")).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.getAllPlaylists("");

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

}
