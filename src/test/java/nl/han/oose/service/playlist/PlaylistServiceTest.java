package nl.han.oose.service.playlist;

import nl.han.oose.Playlists;
import nl.han.oose.Tracklist;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.naming.AuthenticationException;

import static org.junit.Assert.assertEquals;

public class PlaylistServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private PlaylistService sut;

    @Before
    public void setUp() throws Exception {
        sut = new PlaylistServiceImpl();
    }

    @Test
    public void testThatPlaylistsAreReturnedIfUsertokenIsCorrect() throws AuthenticationException {
        Playlists playlists = sut.getAllPlaylists("1234-1234-1234");

        assertEquals(2, playlists.getPlaylists().size());
        assertEquals("Death metal", playlists.getPlaylists().get(0).getName());
        assertEquals("Pop", playlists.getPlaylists().get(1).getName());
        assertEquals(1234, playlists.getLength());
    }

    @Test
    public void testThatExceptionIsReturnedOnGetAllPlaylistsIfUsertokenIsIncorrect() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");
        sut.getAllPlaylists("1234");
    }

    @Test
    public void testThatTracksAreReturnedIfUsertokenIsCorrect() throws AuthenticationException {
        Tracklist tracklist = sut.getContentOfPlaylist("1234-1234-1234", 1);

        assertEquals(1, tracklist.getTracks().get(0).getId());
        assertEquals("Song for someone", tracklist.getTracks().get(0).getTitle());
        assertEquals(2, tracklist.getTracks().get(1).getId());
        assertEquals("The cost", tracklist.getTracks().get(1).getTitle());
    }

    @Test
    public void testThatExceptionIsReturnedOnGetContentOfPlaylistIfUsertokenIsIncorrect() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");
        sut.getContentOfPlaylist("1234", 1);
    }
}
