package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.Session;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    Transaction transaction;
    public UserDaoHibernateImpl() {
    }

    private final SessionFactory sessionFactory = Util.getUtil().getSessionFactory();

    @Override
    public void createUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Transaction transaction = session.getTransaction();

            String createTableQuery = "CREATE TABLE IF NOT EXISTS User (" +
                                      "id INT PRIMARY KEY AUTO_INCREMENT, " +
                                      "name VARCHAR(50), " +
                                      "lastName VARCHAR(50), " +
                                      "age TINYINT" +
                                      ")";

            session.createSQLQuery(createTableQuery).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Transaction transaction = session.getTransaction();

            String dropTableQuery = "DROP TABLE IF EXISTS User";

            session.createSQLQuery(dropTableQuery).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Transaction transaction = session.getTransaction();

            session.save(new User(name, lastName, age));
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Transaction transaction = session.getTransaction();

            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Transaction transaction = session.getTransaction();

            String getAllUsersQuery = "FROM User";
            Query<User> query = session.createQuery(getAllUsersQuery, User.class);
            userList = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                transaction.rollback();
            }
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Transaction transaction = session.getTransaction();

            String deleteAllUsersQuery = "DELETE FROM User";
            Query<?> query = session.createQuery(deleteAllUsersQuery);
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                transaction.rollback();
            }
        }
    }
}
