package nl.han.oose.persistence.playlist;

import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.entity.token.UserToken;
import nl.han.oose.entity.track.Song;
import nl.han.oose.entity.track.Track;
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
import java.util.ArrayList;
import java.util.Date;

public class PlaylistDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    public Playlists getAllPlaylists(UserToken userToken) {
        Playlists playlists = new Playlists();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlist");
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String accountUser = resultSet.getString("account_user");
                Boolean owner = true;
                if (!accountUser.equals(userToken.getUser())) {
                    owner = false;
                }

                playlists.getPlaylists().add(new Playlist(id, name, owner, new ArrayList<>()));
            }
            playlists.setLength(getLengthOfAllPlaylists());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playlists;
    }

    public int getLengthOfAllPlaylists() {
        int playlistsLength = 0;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(track.duration) AS length_all_playlists FROM track LEFT JOIN tracksInPlaylist ON track.id = tracksInPlaylist.track_id");
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                playlistsLength = resultSet.getInt("length_all_playlists");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistsLength;
    }

    public Tracklist getContentOfPlaylist(int playlistId) {
        Tracklist tracklist = new Tracklist();
        Tracklist songs = getSongsInPlaylist(playlistId);
        Tracklist videos = getVideosInPlaylist(playlistId);

        tracklist.getTracks().addAll(songs.getTracks());
        tracklist.getTracks().addAll(videos.getTracks());

        return tracklist;
    }

    public Tracklist getSongsInPlaylist(int playlistId) {
        Tracklist tracklist = new Tracklist();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT sv.*, tip.offline_available FROM songs_view sv\n" +
                        "        LEFT JOIN tracksInPlaylist tip\n" +
                        "           ON tip.track_id = sv.id\n" +
                        "           AND tip.playlist_id = ?\n" +
                        "WHERE sv.id IN(SELECT track_id FROM tracksInPlaylist WHERE playlist_id = ?)");
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

    public Tracklist getVideosInPlaylist(int playlistId) {
        Tracklist tracklist = new Tracklist();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT vv.*, tip.offline_available FROM videos_view vv\n" +
                        "        LEFT JOIN tracksInPlaylist tip\n" +
                        "           ON tip.track_id = vv.id\n" +
                        "           AND tip.playlist_id = ?\n" +
                        "WHERE vv.id IN(SELECT track_id FROM tracksInPlaylist WHERE playlist_id = ?)");
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

    public void renamePlaylist(Playlist playlist) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE playlist SET name = ? WHERE id = ?");
        ) {
            preparedStatement.setString(1, playlist.getName());
            preparedStatement.setInt(2, playlist.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePlaylist(int playlistId) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM playlist WHERE id = ?");
        ) {
            preparedStatement.setInt(1, playlistId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeTrackFromPlaylist(int playlistId, int trackId) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tracksInPlaylist WHERE playlist_id = ? AND track_id = ?");
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addPlaylist(UserToken userToken, Playlist playlist) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO playlist (account_user, name) VALUES(?,?)");
        ) {
            preparedStatement.setString(1, userToken.getUser());
            preparedStatement.setString(2, playlist.getName());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTrackToPlaylist(int playlistId, Track track) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tracksInPlaylist (playlist_id, track_id, offline_available) VALUES(?,?,?)");
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, track.getId());
            preparedStatement.setBoolean(3, track.isOfflineAvailable());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
