package nl.han.oose.persistence.track;

import nl.han.oose.entity.track.Song;
import nl.han.oose.entity.track.Tracklist;
import nl.han.oose.entity.track.Video;
import nl.han.oose.persistence.ConnectionFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrackDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    public Tracklist getAllAvailableTracksForPlaylist(int playlistId) {
        Tracklist tracklist = new Tracklist();
        Tracklist songs = getAllAvailableSongsForPlaylist(playlistId);
        Tracklist videos = getAllAvailableVideosForPlaylist(playlistId);

        tracklist.getTracks().addAll(songs.getTracks());
        tracklist.getTracks().addAll(videos.getTracks());

        return tracklist;
    }

    public Tracklist getAllAvailableSongsForPlaylist(int playlistId) {
        Tracklist tracklist = new Tracklist();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT sv.*, tip.offline_available FROM songs_view sv\n" +
                        "        LEFT JOIN tracksInPlaylist tip\n" +
                        "           ON tip.track_id = sv.id\n" +
                        "           AND tip.playlist_id = ?\n" +
                        "WHERE sv.id NOT IN(SELECT track_id FROM tracksInPlaylist WHERE playlist_id = ?)");
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String performer = resultSet.getString("performer");
                int duration = resultSet.getInt("duration");
                String url = resultSet.getString("url");
                int playcount = resultSet.getInt("playcount");
                String album = resultSet.getString("album");
                boolean offlineAvailable = resultSet.getBoolean("offline_available");


                tracklist.getTracks().add(new Song(id, title, performer, duration, url, playcount, offlineAvailable, album));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tracklist;
    }

    public Tracklist getAllAvailableVideosForPlaylist(int playlistId) {
        Tracklist tracklist = new Tracklist();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT vv.*, tip.offline_available FROM videos_view vv\n" +
                        "        LEFT JOIN tracksInPlaylist tip\n" +
                        "           ON tip.track_id = vv.id\n" +
                        "           AND tip.playlist_id = ?\n" +
                        "WHERE vv.id NOT IN(SELECT track_id FROM tracksInPlaylist WHERE playlist_id = ?)");
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String performer = resultSet.getString("performer");
                int duration = resultSet.getInt("duration");
                String url = resultSet.getString("url");
                int playcount = resultSet.getInt("playcount");

                String publicationDate = null;

                if (!(null == (resultSet.getString("publication_date")))) {
                    SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = oldFormat.parse(resultSet.getString("publication_date"));
                    SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");

                    publicationDate = newFormat.format(date);
                }

                String description = resultSet.getString("description");
                boolean offlineAvailable = resultSet.getBoolean("offline_available");


                tracklist.getTracks().add(new Video(id, title, performer, duration, url, playcount, offlineAvailable, publicationDate, description));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return tracklist;
    }
}
