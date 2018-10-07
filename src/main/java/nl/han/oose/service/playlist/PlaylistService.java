package nl.han.oose.service.playlist;

import nl.han.oose.Playlists;
import nl.han.oose.Tracklist;

import javax.naming.AuthenticationException;

public interface PlaylistService {
    Playlists getAllPlaylists(String userToken) throws AuthenticationException;

    Tracklist getContentOfPlaylist(String userToken, int playlistId) throws AuthenticationException;
}
