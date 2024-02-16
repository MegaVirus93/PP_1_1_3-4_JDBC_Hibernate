package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE DATABASE IF NOT EXISTS preproject114;").executeUpdate();
            session.createSQLQuery("USE preproject114;").executeUpdate();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                    "(" +
                    "Id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "Name VARCHAR(20) NOT NULL," +
                    "LastName VARCHAR(20) NOT NULL," +
                    "Age TINYINT CHECK(Age > 0)" +
                    ");").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Ошибка создания базы данных и таблицы: " + e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP DATABASE IF EXISTS preproject114;").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Ошибка удаления базы данных: " + e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.printf("User с именем — %s добавлен в базу данных\n", name);
        } catch (Exception e) {
            System.err.println("Ошибка сохранения пользователя в таблице: " + e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.byId(User.class).load(id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Ошибка удаления пользователя из таблицы: " + e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            list = session.createQuery("from User", User.class).list();
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Ошибка экспорта пользователей из таблицы в лист: " + e);
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Ошибка чистки таблицы: " + e);
        }
    }
}
