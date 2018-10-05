package nl.han.oose.service.playlist;

import nl.han.oose.Playlists;
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
        sut = new PlaylistService();
    }

    @Test
    public void testThatPlaylistsAreReturnedIfUsertokenIsCorrect() throws AuthenticationException {
        Playlists playlists = sut.getAllPlaylists("1234-1234-1234");

        assertEquals(Playlists.class, playlists.getClass());
        assertEquals(2, playlists.getPlaylists().size());
        assertEquals(1234, playlists.getLength());
    }

    @Test
    public void testThatExceptionIsReturnedIfUsertokenIsIncorrect() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");
        sut.getAllPlaylists("1234");
    }
}
