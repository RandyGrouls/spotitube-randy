package nl.han.oose.service.playlist;

import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.entity.token.UserToken;
import nl.han.oose.entity.track.Tracklist;
import nl.han.oose.persistence.playlist.PlaylistDAO;
import nl.han.oose.persistence.token.TokenDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.naming.AuthenticationException;

@Default
public class PlaylistServiceImpl implements PlaylistService {

    private static Playlists playlists;

    @Inject
    private PlaylistDAO playlistDAO;

    @Inject
    private TokenDAO tokenDAO;

    @Override
    public Playlists getAllPlaylists(String token) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUsertoken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            return playlistDAO.getAllPlaylistsForUser(userToken);
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }

    @Override
    public Tracklist getContentOfPlaylist(String token, int playlistId) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUsertoken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            return playlistDAO.getContentOfPlaylist(playlistId);
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }

    @Override
    public Playlists deletePlaylist(String token, int playlistId) throws AuthenticationException {
        if ("1234-1234-1234".equals(token)) {
            playlists.getPlaylists().removeIf(playlist -> playlist.getId() == playlistId);
            return playlists;
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }

    @Override
    public Playlists renamePlaylist(String token, Playlist playlist) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUsertoken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            return playlistDAO.renamePlaylist(userToken, playlist);
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }
}
