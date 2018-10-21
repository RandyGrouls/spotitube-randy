package nl.han.oose.entity.track;

public class Song extends Track {

    private String album;

    public Song() {
    }

    public Song(int id, String title, String performer, int duration, String url, int playcount, boolean offlineAvailable, String album) {
        super(id, title, performer, duration, url, playcount, offlineAvailable);
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
