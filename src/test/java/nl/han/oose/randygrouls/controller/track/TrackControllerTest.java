package nl.han.oose.randygrouls.controller.track;

import nl.han.oose.randygrouls.entity.track.Tracklist;
import nl.han.oose.randygrouls.service.track.TrackService;
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
public class TrackControllerTest {

    @Mock
    private TrackService trackService;

    @InjectMocks
    private TrackController sut;

    //Tests for getAllTracksNotInPlaylist
    @Test
    public void testStatusBadRequestOnEmptyUsertokenGetAllAvailableTracksForPlaylist() throws AuthenticationException {
        Response response = sut.getAllAvailableTracksForPlaylist("", "");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStatusOkAndTracklistEntityOnCorrectUsertokenGetAllAvailableTracksForPlaylist() throws AuthenticationException {
        Tracklist tracklist = new Tracklist();

        Mockito.when(trackService.getAllAvailableTracksForPlaylist(Mockito.any(), Mockito.anyInt())).thenReturn(tracklist);
        Response response = sut.getAllAvailableTracksForPlaylist("321321", "1");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracklist, response.getEntity());
    }

    @Test
    public void testStatusForbiddenOnIncorrectUsertokengetAllAvailableTracksForPlaylist() throws AuthenticationException {
        Mockito.when(trackService.getAllAvailableTracksForPlaylist(Mockito.any(), Mockito.anyInt())).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.getAllAvailableTracksForPlaylist("321321", "1");

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }
}
