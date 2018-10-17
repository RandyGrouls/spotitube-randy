package nl.han.oose.persistence.track;

import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.Tracklist;
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

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tracks_view tv\n" +
                        "        LEFT JOIN tracksInPlaylist\n" +
                        "           ON tracksInPlaylist.track_id = tv.id\n" +
                        "           AND tracksInPlaylist.playlist_id = ?\n" +
                        "WHERE tv.id NOT IN(SELECT track_id FROM tracksInPlaylist WHERE playlist_id = ?)");
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

                String publicationDate = null;

                if (!(null == (resultSet.getString("publication_date")))) {
                    SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = oldFormat.parse(resultSet.getString("publication_date"));
                    SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");

                    publicationDate = newFormat.format(date);
                }

                String description = resultSet.getString("description");
                boolean offlineAvailable = resultSet.getBoolean("offline_available");

                tracklist.getTracks().add(new Track(id, title, performer, duration, url, album, playcount, publicationDate, description, offlineAvailable));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return tracklist;
    }
}
