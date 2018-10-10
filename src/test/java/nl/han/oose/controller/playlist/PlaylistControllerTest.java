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
    public void testStatusUnauthorizedOnGetAllPlaylistsOnIncorrectUsertoken() throws AuthenticationException {
        Mockito.when(playlistService.getAllPlaylists("")).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.getAllPlaylists("");

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStatusOkAndTracklistEntityOnCorrectUsertoken() throws AuthenticationException {
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(1, "Song for someone", "The Frames", 350, "The Cost", 50, "01-05-2007", "Title song from the Album The Cost", false));
        tracks.add(new Track(2, "The cost", "The Frames", 423, "The Cost", 50, "10-01-2005", "Title song from the Album The Cost", true));
        Tracklist tracklist = new Tracklist(tracks);

        Mockito.when(playlistService.getContentOfPlaylist(Mockito.anyString(), Mockito.anyInt())).thenReturn(tracklist);
        Response response = sut.getContentOfPlaylist("", 1);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracklist, response.getEntity());
    }

    @Test
    public void testStatusUnauthorizedOnGetContentOfPlaylistOnIncorrectUsertoken() throws AuthenticationException {
        Mockito.when(playlistService.getContentOfPlaylist(Mockito.anyString(), Mockito.anyInt())).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.getContentOfPlaylist("", 1);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

}
