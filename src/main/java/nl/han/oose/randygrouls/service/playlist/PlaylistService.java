package nl.han.oose.randygrouls.service.playlist;

import nl.han.oose.randygrouls.entity.playlist.Playlist;
import nl.han.oose.randygrouls.entity.playlist.Playlists;
import nl.han.oose.randygrouls.entity.track.Track;
import nl.han.oose.randygrouls.entity.track.Tracklist;

import javax.naming.AuthenticationException;

public interface PlaylistService {
    Playlists getAllPlaylists(String userToken) throws AuthenticationException;

    Tracklist getContentOfPlaylist(String userToken, int playlistId) throws AuthenticationException;

    Playlists deletePlaylist(String userToken, int playlistId) throws AuthenticationException;

    Playlists renamePlaylist(String token, Playlist playlist) throws AuthenticationException;

    Tracklist removeTrackFromPlaylist(String token, int playlistId, int trackId) throws AuthenticationException;

    Playlists addPlaylist(String token, Playlist playlist) throws AuthenticationException;

    Tracklist addTrackToPlaylist(String token, int playlistId, Track track) throws AuthenticationException;
}
