/*
package by.iba.dao;

import by.iba.model.User;
import by.iba.util.ConnectorDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final static String SQL_CHECK_LOGIN = "SELECT login FROM users WHERE login = ?";
    private final static String SQL_INSERT_USER = "INSERT INTO users(login ,passw) VALUES (? , ?)";
    private final static String SQL_GET_USER = "select login,passw from users where login=? and passw=?";
    private Connection connection;

    public UserDao() {
        try {
            connection = ConnectorDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isValidUser(final String login, final byte[] password) {

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_GET_USER);

            ps.setString(1, login);
            ps.setBytes(2, password);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;

    }


    public boolean insertUser(User user) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_CHECK_LOGIN); preparedStatement.setString(1, user.getLogin()); ResultSet result = preparedStatement.executeQuery();
            if ( result.next()){ preparedStatement.close(); return false;
            }
            else {
                preparedStatement = connection.prepareStatement(SQL_INSERT_USER);

                preparedStatement.setString(1, user.getLogin()); preparedStatement.setBytes(2, user.getPassw());

                preparedStatement.executeUpdate(); preparedStatement.close();

            }
        } catch (SQLException e) { e.printStackTrace();
        }
        return true;


    }
}
*/
