package nl.han.oose.randygrouls.service.track;

import nl.han.oose.randygrouls.entity.track.Tracklist;

import javax.naming.AuthenticationException;

public interface TrackService {
    Tracklist getAllAvailableTracksForPlaylist(String userToken, int playlistId) throws AuthenticationException;
}
