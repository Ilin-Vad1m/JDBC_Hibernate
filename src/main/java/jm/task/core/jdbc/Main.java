package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();

        service.createUsersTable();
        service.saveUser("name1","lastName1", (byte) 34);
        service.saveUser("name2","lastName2",(byte) 25);
        service.saveUser("name3","lastName3",(byte) 35);
        service.saveUser("name4","lastName4",(byte) 47);
        service.removeUserById(1L);
        service.getAllUsers();
        service.cleanUsersTable();
        service.dropUsersTable();

    }
}
