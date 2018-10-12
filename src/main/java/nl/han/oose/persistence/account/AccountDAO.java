package nl.han.oose.persistence.account;

import nl.han.oose.entity.account.Account;
import nl.han.oose.persistence.ConnectionFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT");
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("user");
                String password = resultSet.getString("password");
                String full_name = resultSet.getString("full_name");

                accounts.add(new Account(user, password, full_name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    public void persistAccount(Account account) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO account (user, password, full_name) VALUES(?,?,?)");
        ) {
            statement.setString(1, account.getUser());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getFullname());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
