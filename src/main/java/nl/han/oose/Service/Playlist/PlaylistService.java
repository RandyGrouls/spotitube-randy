package nl.han.oose.Service.Playlist;

import nl.han.oose.Playlist;
import nl.han.oose.Playlists;

import javax.naming.AuthenticationException;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;

public class PlaylistService {

    public Playlists getAllPlaylists(@QueryParam("token") String userToken) throws AuthenticationException {
        if ("1234-1234-1234".equals(userToken)) {
            Playlists playlists = new Playlists();
            playlists.getPlaylists().add(new Playlist(1, "Death metal", true, new ArrayList<>()));
            playlists.getPlaylists().add(new Playlist(2, "Pop", false, new ArrayList<>()));
            playlists.setLength(1234);
            return playlists;
        } else {
            throw new AuthenticationException("Usertoken not correct");
        }
    }
}
