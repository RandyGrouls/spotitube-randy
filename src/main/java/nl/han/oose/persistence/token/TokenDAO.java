package nl.han.oose.persistence.token;

import nl.han.oose.persistence.ConnectionFactory;
import nl.han.oose.service.login.UserToken;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class TokenDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    public UserToken createNewTokenForUser(String user, String fullname) {
        UserToken userToken = null;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO token (token, account_user, expiry_date) VALUES (?,?,?);")
        ) {
            String token = UUID.randomUUID().toString();
            LocalDateTime expiryDate = LocalDateTime.now();

            preparedStatement.setString(1, token);
            preparedStatement.setString(2, user);
            preparedStatement.setString(3, expiryDate.toString());

            userToken = new UserToken(token, fullname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userToken;
    }

}
