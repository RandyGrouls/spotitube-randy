package nl.han.oose.service.track;

import nl.han.oose.entity.token.UserToken;
import nl.han.oose.entity.track.Tracklist;
import nl.han.oose.persistence.token.TokenDAO;
import nl.han.oose.persistence.track.TrackDAO;
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
public class TrackServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private TrackDAO trackDAO;

    @Mock
    private TokenDAO tokenDAO;

    @InjectMocks
    private TrackServiceImpl sut;

    //Tests for getAllPlaylists
    @Test
    public void testThatTracklistIsReturnedIfUsertokenIsValidGetAllAvailableTracksForPlaylist() throws AuthenticationException {
        UserToken userToken = new UserToken("123123", "randy");
        Tracklist tracklist = new Tracklist();

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(trackDAO.getAllAvailableTracksForPlaylist(Mockito.anyInt())).thenReturn(tracklist);

        assertEquals(tracklist, sut.getAllAvailableTracksForPlaylist("123", 1));
    }

    @Test
    public void testThatExceptionIsReturnedIfTokenIsInvalidGetAllAvailableTracksForPlaylist() throws AuthenticationException {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        UserToken userToken = new UserToken("123123", "randy");

        Mockito.when(tokenDAO.getUsertoken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.getAllAvailableTracksForPlaylist("123", 1);

    }
}
