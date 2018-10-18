package nl.han.oose.service.playlist;

import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.entity.token.UserToken;
import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.Tracklist;
import nl.han.oose.persistence.playlist.PlaylistDAO;
import nl.han.oose.persistence.token.TokenDAO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.AuthenticationException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private PlaylistDAO playlistDAO;

    @Mock
    private TokenDAO tokenDAO;

    @InjectMocks
    private PlaylistServiceImpl sut;

    //Tests for getAllPlaylists
    @Test
    public void testThatPlaylistsAreReturnedIfUsertokenIsValidGetAllPlaylists() throws AuthenticationException {
        UserToken userToken = new UserToken("123123", "randy");
        Playlists playlists = new Playlists();

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllPlaylists(Mockito.any())).thenReturn(playlists);

        assertEquals(playlists, sut.getAllPlaylists("123"));
    }

    @Test
    public void testThatExceptionIsReturnedIfTokenIsInvalidGetAllPlaylists() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        UserToken userToken = new UserToken("123123", "randy");

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.getAllPlaylists("123");

    }

    //Tests for getContentOfPlaylist
    @Test
    public void testThatTracklistIsReturnedIfUsertokenIsValidGetContentOfPlaylist() throws AuthenticationException {
        UserToken userToken = new UserToken("123123", "randy");
        Tracklist tracklist = new Tracklist();

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getContentOfPlaylist(Mockito.anyInt())).thenReturn(tracklist);

        assertEquals(tracklist, sut.getContentOfPlaylist("123", 1));
    }

    @Test
    public void testThatExceptionIsReturnedIfTokenIsInvalidGetContentOfPlaylist() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        UserToken userToken = new UserToken("123123", "randy");

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.getContentOfPlaylist("123", 1);
    }

    //Tests for deletePlaylist
    @Test
    public void testThatPlaylistsAreReturnedIfUsertokenIsValidDeletePlaylist() throws AuthenticationException {
        UserToken userToken = new UserToken("123123", "randy");
        Playlists playlists = new Playlists();

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllPlaylists(Mockito.any())).thenReturn(playlists);

        assertEquals(playlists, sut.deletePlaylist("123", 1));
    }

    @Test
    public void testThatExceptionIsReturnedIfTokenIsInvalidDeletePlaylist() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        UserToken userToken = new UserToken("123123", "randy");

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.deletePlaylist("123", 1);
    }


    //Tests for renamePlaylist
    @Test
    public void testThatPlaylistsAreReturnedIfUsertokenIsValidRenamePlaylist() throws AuthenticationException {
        UserToken userToken = new UserToken("123123", "randy");
        Playlists playlists = new Playlists();

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllPlaylists(Mockito.any())).thenReturn(playlists);

        assertEquals(playlists, sut.renamePlaylist("123", new Playlist()));
    }

    @Test
    public void testThatExceptionIsReturnedIfTokenIsInvalidDRenamePlaylist() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        UserToken userToken = new UserToken("123123", "randy");

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.renamePlaylist("123", new Playlist());
    }

    //Tests for removeTrackFromPlaylist
    @Test
    public void testThatTracklistIsReturnedIfUsertokenIsValidRemoveTrackFromPlaylist() throws AuthenticationException {
        UserToken userToken = new UserToken("123123", "randy");
        Tracklist tracklist = new Tracklist();

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getContentOfPlaylist(Mockito.anyInt())).thenReturn(tracklist);

        assertEquals(tracklist, sut.removeTrackFromPlaylist("123", 1, 1));
    }

    @Test
    public void testThatExceptionIsReturnedIfTokenIsInvalidDRemoveTrackFromPlaylist() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        UserToken userToken = new UserToken("123123", "randy");

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.removeTrackFromPlaylist("123", 1, 1);
    }

    //Tests for addPlaylist
    @Test
    public void testThatPlaylistsAreReturnedIfUsertokenIsValidAddPlaylist() throws AuthenticationException {
        UserToken userToken = new UserToken("123123", "randy");
        Playlists playlists = new Playlists();

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllPlaylists(Mockito.any())).thenReturn(playlists);

        assertEquals(playlists, sut.addPlaylist("123", new Playlist()));
    }

    @Test
    public void testThatExceptionIsReturnedIfTokenIsInvalidAddPlaylist() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        UserToken userToken = new UserToken("123123", "randy");

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.addPlaylist("123", new Playlist());
    }

    //Tests for addTrackToPlaylist
    @Test
    public void testThatTracklistIsReturnedIfUsertokenIsValidAddTrackToPlaylist() throws AuthenticationException {
        UserToken userToken = new UserToken("123123", "randy");
        Tracklist tracklist = new Tracklist();

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getContentOfPlaylist(Mockito.anyInt())).thenReturn(tracklist);

        assertEquals(tracklist, sut.addTrackToPlaylist("123", 1, new Track()));
    }

    @Test
    public void testThatExceptionIsReturnedIfTokenIsInvalidAddTrackToPlaylist() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        UserToken userToken = new UserToken("123123", "randy");

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.addTrackToPlaylist("123", 1, new Track());
    }
}
