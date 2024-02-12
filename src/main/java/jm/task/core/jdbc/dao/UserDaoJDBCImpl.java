package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("CREATE DATABASE IF NOT EXISTS preproject114;");
            statement.execute("USE preproject114;");
            statement.execute("CREATE TABLE IF NOT EXISTS users" +
                    "(" +
                    "Id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "Name VARCHAR(20) NOT NULL," +
                    "LastName VARCHAR(20) NOT NULL," +
                    "Age TINYINT CHECK(Age > 0)" +
                    ");");
        } catch (SQLException e) {
            System.err.println("Ошибка создания базы данных и таблицы: " + e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("DROP DATABASE IF EXISTS preproject114;");
        } catch (SQLException e) {
            System.err.println("Ошибка удаления базы данных: " + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("USE preproject114;");
            statement.execute(String.format("INSERT INTO Users(Name, LastName, Age) VALUES('%s', '%s', %d);", name, lastName, age));
            System.out.printf("User с именем — %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            System.err.println("Ошибка сохранения пользователя в таблице: " + e);
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("USE preproject114;");
            statement.execute("DELETE FROM users WHERE Id = " + id + ";");
        } catch (SQLException e) {
            System.err.println("Ошибка удаления пользователя из таблицы: " + e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("USE preproject114;");
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setId(rs.getLong(1));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка экспорта пользователей из таблицы в лист: " + e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("USE preproject114;");
            statement.execute("TRUNCATE TABLE users;");
        } catch (SQLException e) {
            System.err.println("Ошибка чистки таблицы: " + e);
        }
    }
}
