package nl.han.oose.persistence.playlist;

import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.entity.token.UserToken;
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
import java.util.ArrayList;
import java.util.Date;

public class PlaylistDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    public Playlists getAllPlaylistsForUser(UserToken userToken) {
        Playlists playlists = new Playlists();
        int playlistLength = 0;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlist WHERE account_user = ?");
        ) {
            preparedStatement.setString(1, userToken.getUser());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Boolean owner = resultSet.getBoolean("owner");


                playlists.getPlaylists().add(new Playlist(id, name, owner, new ArrayList<>()));
                playlistLength += getLengthOfPlaylist(id);
            }
            playlists.setLength(playlistLength);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playlists;
    }

    public int getLengthOfPlaylist(int playlistId) {
        int playlistLength = 0;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(duration) AS playlist_length FROM track WHERE playlist_id = ?")
        ) {

            preparedStatement.setInt(1, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                playlistLength += resultSet.getInt("playlist_length");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistLength;
    }

    public Tracklist getContentOfPlaylist(int playlistId) {
        Tracklist tracklist = new Tracklist();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM track WHERE playlist_id = ?");
        ) {
            preparedStatement.setInt(1, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String performer = resultSet.getString("performer");
                int duration = resultSet.getInt("duration");
                String album = resultSet.getString("album");
                int playcount = resultSet.getInt("playcount");

                SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = oldFormat.parse(resultSet.getString("publication_date"));
                SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");

                String publicationDate = newFormat.format(date);
                String description = resultSet.getString("description");
                boolean offlineAvailable = resultSet.getBoolean("offline_available");

                tracklist.getTracks().add(new Track(id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return tracklist;
    }

    public Playlists renamePlaylist(UserToken userToken, Playlist playlist) {
        Playlists playlists = null;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE playlist SET name = ? WHERE id = ?");
        ) {
            preparedStatement.setString(1, playlist.getName());
            preparedStatement.setInt(2, playlist.getId());

            preparedStatement.execute();

            System.out.println(preparedStatement);

            playlists = getAllPlaylistsForUser(userToken);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlists;
    }

}
