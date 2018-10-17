package nl.han.oose.service.track;

import nl.han.oose.entity.token.UserToken;
import nl.han.oose.entity.track.Tracklist;
import nl.han.oose.persistence.token.TokenDAO;
import nl.han.oose.persistence.track.TrackDAO;

import javax.inject.Inject;
import javax.naming.AuthenticationException;

public class TrackServiceImpl implements TrackService {

    @Inject
    private TokenDAO tokenDAO;

    @Inject
    private TrackDAO trackDAO;

    @Override
    public Tracklist getAllAvailableTracksForPlaylist(String token, int playlistId) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUsertoken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            return trackDAO.getAllAvailableTracksForPlaylist(playlistId);
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }
}
