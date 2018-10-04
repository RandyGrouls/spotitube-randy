package nl.han.oose;

import java.util.ArrayList;

public class Playlists {
    private ArrayList<Playlist> playlists;
    private int duration;

    public Playlists() {
        playlists.add(new Playlist(1, "Death metal", true, new ArrayList<>()));
        playlists.add(new Playlist(1, "Pop", true, new ArrayList<>()));
        this.duration = 12345;
    }
}
