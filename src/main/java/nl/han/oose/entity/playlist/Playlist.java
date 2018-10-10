package nl.han.oose.entity.playlist;

import nl.han.oose.entity.track.Track;

import java.util.ArrayList;

public class Playlist {
    private int id;
    private String name;
    private Boolean owner;
    private ArrayList<Track> tracks;


    public Playlist() {

    }

    public Playlist(int id, String name, Boolean owner, ArrayList<Track> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }
}
