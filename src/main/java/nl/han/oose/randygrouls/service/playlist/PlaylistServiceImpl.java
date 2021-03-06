package nl.han.oose.randygrouls.service.playlist;

import nl.han.oose.randygrouls.entity.playlist.Playlist;
import nl.han.oose.randygrouls.entity.playlist.Playlists;
import nl.han.oose.randygrouls.entity.token.UserToken;
import nl.han.oose.randygrouls.entity.track.Track;
import nl.han.oose.randygrouls.entity.track.Tracklist;
import nl.han.oose.randygrouls.persistence.playlist.PlaylistDAO;
import nl.han.oose.randygrouls.persistence.token.TokenDAO;
import nl.han.oose.randygrouls.persistence.track.TrackDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.naming.AuthenticationException;

@Default
public class PlaylistServiceImpl implements PlaylistService {

    @Inject
    private PlaylistDAO playlistDAO;

    @Inject
    private TokenDAO tokenDAO;

    @Inject
    private TrackDAO trackDAO;

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
            return trackDAO.getContentOfPlaylist(playlistId);
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
            return trackDAO.getContentOfPlaylist(playlistId);
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
            return trackDAO.getContentOfPlaylist(playlistId);
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }
}
