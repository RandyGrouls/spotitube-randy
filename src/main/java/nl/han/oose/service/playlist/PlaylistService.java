package nl.han.oose.service.playlist;

import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.entity.track.Tracklist;

import javax.naming.AuthenticationException;

public interface PlaylistService {
    Playlists getAllPlaylists(String userToken) throws AuthenticationException;

    Tracklist getContentOfPlaylist(String userToken, int playlistId) throws AuthenticationException;

    Playlists deletePlaylist(String userToken, int playlistId) throws AuthenticationException;
}
