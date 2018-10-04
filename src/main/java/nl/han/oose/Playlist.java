package nl.han.oose;

import java.util.ArrayList;

public class Playlist {
    private int id;
    private String name;
    private Boolean owner;
    private ArrayList<Track> tracks;


    public Playlist(int id, String name, Boolean owner, ArrayList<Track> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }
}
