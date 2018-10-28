package nl.han.oose.randygrouls.persistence.account;

import nl.han.oose.randygrouls.entity.account.Account;
import nl.han.oose.randygrouls.persistence.ConnectionFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    public Account getAccount(String username) {
        Account account = null;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement getAccountStatement = connection.prepareStatement("SELECT * FROM account WHERE user = ?");
        ) {
            getAccountStatement.setString(1, username);
            ResultSet accountResult = getAccountStatement.executeQuery();
            while (accountResult.next()) {
                String user = accountResult.getString("user");
                String password = accountResult.getString("password");
                String full_name = accountResult.getString("full_name");
                account = new Account(user, password, full_name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }
}
