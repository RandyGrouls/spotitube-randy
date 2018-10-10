package nl.han.oose.entity.playlist;

import java.util.ArrayList;

public class Playlists {
    private ArrayList<Playlist> playlists = new ArrayList<>();
    private int length;


    public Playlists() {
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
