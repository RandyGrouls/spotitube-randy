package nl.han.oose.entity.track;

import java.util.ArrayList;

public class Tracklist {

    private ArrayList<Track> tracks;

    public Tracklist() {
    }

    public Tracklist(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }
}
