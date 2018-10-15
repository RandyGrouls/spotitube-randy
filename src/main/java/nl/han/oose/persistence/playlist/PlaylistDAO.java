package nl.han.oose.persistence.playlist;

import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.entity.token.UserToken;
import nl.han.oose.persistence.ConnectionFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

            System.out.println(preparedStatement);
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

}
