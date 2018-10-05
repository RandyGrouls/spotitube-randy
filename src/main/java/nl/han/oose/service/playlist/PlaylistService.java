package nl.han.oose.service.playlist;

import nl.han.oose.Playlist;
import nl.han.oose.Playlists;
import nl.han.oose.Track;
import nl.han.oose.Tracklist;

import javax.naming.AuthenticationException;
import java.util.ArrayList;


public class PlaylistService {
    Playlists playlists = new Playlists();

    public Playlists getAllPlaylists(String userToken) throws AuthenticationException {
        if ("1234-1234-1234".equals(userToken)) {
            playlists.getPlaylists().add(new Playlist(1, "Death metal", true, new ArrayList<>()));
            playlists.getPlaylists().add(new Playlist(2, "Pop", false, new ArrayList<>()));
            playlists.setLength(1234);
            return playlists;
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }

    public Tracklist getContentOfPlaylist(String userToken, int playlistId) throws AuthenticationException {
        if ("1234-1234-1234".equals(userToken)) {
            ArrayList<Track> tracks = new ArrayList<>();
            tracks.add(new Track(1, "Song for someone", "The Frames", 350, "The Cost", 50, "01-05-2007", "Title song from the Album The Cost", false));
            tracks.add(new Track(2, "The cost", "The Frames", 423, "The Cost", 50, "10-01-2005", "Title song from the Album The Cost", true));
            return new Tracklist(tracks);
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }
}
