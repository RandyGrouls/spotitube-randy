package nl.han.oose.persistence.token;

import nl.han.oose.entity.token.UserToken;
import nl.han.oose.persistence.ConnectionFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class TokenDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    public UserToken createNewTokenForUser(String user, String fullname) {
        UserToken userToken;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO token (token, account_user, expiry_date) VALUES (?,?,?)");
        ) {
            String token = UUID.randomUUID().toString();
            LocalDateTime expiryDate = LocalDateTime.now().plusHours(24);

            preparedStatement.setString(1, token);
            preparedStatement.setString(2, user);
            preparedStatement.setString(3, expiryDate.toString());
            preparedStatement.execute();

            userToken = new UserToken(token, fullname);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userToken;
    }

    public boolean isTokenValid(UserToken userToken) {
        boolean isValid = false;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM token WHERE token = ? AND account_user = ?");
        ) {
            preparedStatement.setString(1, userToken.getToken());
            preparedStatement.setString(2, userToken.getUser());
            ResultSet resultSet = preparedStatement.executeQuery();
            LocalDateTime currentDateTime = LocalDateTime.now();
            while (resultSet.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime expiryDate = LocalDateTime.parse(resultSet.getString("expiry_date"), formatter);
                if (expiryDate.isAfter(currentDateTime)) {
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isValid;
    }

    public UserToken getUsertoken(String token) {
        UserToken userToken = null;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement getTokenStatement = connection.prepareStatement("SELECT * FROM token WHERE token = ?")
        ) {
            getTokenStatement.setString(1, token);
            ResultSet resultSet = getTokenStatement.executeQuery();
            while (resultSet.next()) {
                String tokenString = resultSet.getString("token");
                String user = resultSet.getString("account_user");
                userToken = new UserToken(tokenString, user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userToken;
    }

    public void deleteAllExpiredTokensForUser(String user) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM token WHERE expiry_date < NOW() AND account_user = ?");
        ) {
            preparedStatement.setString(1, user);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
