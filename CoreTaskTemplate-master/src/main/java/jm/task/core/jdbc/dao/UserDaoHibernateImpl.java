package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = new Util().getSessionFactory();

    @Override
    public void createUsersTable() {
        Session session = null;
        String newTableUser = "CREATE TABLE IF NOT EXISTS usertable ("
                + "id BIGINT NOT NULL AUTO_INCREMENT primary key,"
                + "nameUser varchar(255),"
                + "lastName varchar(255),"
                + "age TINYINT)";
        try {
            session= sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(newTableUser).executeUpdate();
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.openSession();;
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS usertable").executeUpdate();

        }catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction =session.beginTransaction();

            session.save(new User(name,lastName,age));
            transaction.commit();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            if (session!=null){
                session.close();
            }
        }


    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(User.class,id));
            transaction.commit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            listUsers = session.createCriteria(User.class).list();
            transaction.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        return listUsers;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            final List<User> instances = session.createCriteria(User.class).list();

            for (User o : instances) {
                session.delete(o);
            }
            transaction.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
