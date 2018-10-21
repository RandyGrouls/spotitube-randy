package nl.han.oose.service.playlist;

import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.entity.token.UserToken;
import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.Tracklist;
import nl.han.oose.persistence.playlist.PlaylistDAO;
import nl.han.oose.persistence.token.TokenDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.naming.AuthenticationException;

@Default
public class PlaylistServiceImpl implements PlaylistService {

    @Inject
    private PlaylistDAO playlistDAO;

    @Inject
    private TokenDAO tokenDAO;

    @Override
    public Playlists getAllPlaylists(String token) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUsertoken(token);
        if (userToken != null && tokenDAO.isTokenValid(userToken)) {
            return playlistDAO.getAllPlaylists(userToken);
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }

    @Override
    public Tracklist getContentOfPlaylist(String token, int playlistId) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUsertoken(token);
        if (userToken != null && tokenDAO.isTokenValid(userToken)) {
            return playlistDAO.getContentOfPlaylist(playlistId);
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }

    @Override
    public Playlists deletePlaylist(String token, int playlistId) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUsertoken(token);
        if (userToken != null && tokenDAO.isTokenValid(userToken)) {
            playlistDAO.deletePlaylist(playlistId);
            return playlistDAO.getAllPlaylists(userToken);
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }

    @Override
    public Playlists renamePlaylist(String token, Playlist playlist) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUsertoken(token);
        if (userToken != null && tokenDAO.isTokenValid(userToken)) {
            playlistDAO.renamePlaylist(playlist);
            return playlistDAO.getAllPlaylists(userToken);
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }

    @Override
    public Tracklist removeTrackFromPlaylist(String token, int playlistId, int trackId) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUsertoken(token);
        if (userToken != null && tokenDAO.isTokenValid(userToken)) {
            playlistDAO.removeTrackFromPlaylist(playlistId, trackId);
            return playlistDAO.getContentOfPlaylist(playlistId);
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }

    @Override
    public Playlists addPlaylist(String token, Playlist playlist) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUsertoken(token);
        if (userToken != null && tokenDAO.isTokenValid(userToken)) {
            playlistDAO.addPlaylist(userToken, playlist);
            return playlistDAO.getAllPlaylists(userToken);
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }

    @Override
    public Tracklist addTrackToPlaylist(String token, int playlistId, Track track) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUsertoken(token);
        if (userToken != null && tokenDAO.isTokenValid(userToken)) {
            playlistDAO.addTrackToPlaylist(playlistId, track);
            return playlistDAO.getContentOfPlaylist(playlistId);
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }
}
