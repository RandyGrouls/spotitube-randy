package nl.han.oose.service.track;

import nl.han.oose.entity.track.Tracklist;

import javax.naming.AuthenticationException;

public interface TrackService {
    Tracklist getAllAvailableTracksForPlaylist(String userToken, int playlistId) throws AuthenticationException;
}
