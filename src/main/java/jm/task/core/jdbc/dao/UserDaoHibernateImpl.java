package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;



import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (final Session session = Util.getSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User " +
                                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                                        "name VARCHAR(50) not NULL," +
                                        "lastName VARCHAR (50), " +
                                        "age INTEGER not NULL, " +
                                        "PRIMARY KEY (id))").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
            try (final Session session = Util.getSession()) {
                session.beginTransaction();
                session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
                session.getTransaction().commit();
            }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (final Session session = Util.getSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (final Session session = Util.getSession()) {
            session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Пользователя с таким ID не существует");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSession()) {
            Criteria criteria = session.createCriteria(User.class);
            List <User> users = criteria.list();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (final Session session = Util.getSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE user").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
