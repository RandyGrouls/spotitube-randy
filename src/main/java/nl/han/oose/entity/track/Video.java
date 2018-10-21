package nl.han.oose.entity.track;

public class Video extends Track {

    private String publicationDate;
    private String description;

    public Video() {

    }

    public Video(int id, String title, String performer, int duration, String url, int playcount, boolean offlineAvailable, String publicationDate, String description) {
        super(id, title, performer, duration, url, playcount, offlineAvailable);
        this.publicationDate = publicationDate;
        this.description = description;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
