package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public void createUsersTable() {
        String newTableUser = "CREATE TABLE IF NOT EXISTS userTable ("
                + "id INT NOT NULL AUTO_INCREMENT primary key,"
                + "nameUser VARCHAR(20),"
                + "lastName VARCHAR(50),"
                + "age INT)";

        try (PreparedStatement pr = new Util().getConnection().prepareStatement(newTableUser)) {
            //PreparedStatement pr = con.;
            pr.execute();
            //pr.close();
        } catch (SQLException e) {
            System.out.println("Не удалось создать таблицу в базе данных");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {

        try (PreparedStatement pr  = new Util().getConnection().prepareStatement("DROP TABLE IF EXISTS userTable")) {
            pr.execute();
        } catch (SQLException e) {
            System.out.println("Не удалось удалить таблицу в базе данных");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement pr = new Util().getConnection().prepareStatement("insert into userTable (nameUser,lastName,age) values (?,?,?)")) {
            pr.setString(1, name);
            pr.setString(2, lastName);
            pr.setByte(3, age);

            pr.executeUpdate();
            pr.close();

        } catch (SQLException e) {
            System.out.println("Не удалось добавить новую строку в таблицу данных");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {

        try (PreparedStatement pr = new Util().getConnection().prepareStatement("DELETE FROM userTable WHERE id = ?")) {
            pr.setLong(1, id);
            pr.execute();

        } catch (SQLException e) {
            System.out.println("Не удалось удалить строку по id");
            System.out.println(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        try(Statement statement= new Util().getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM userTable");
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User newUser = new User(id,name,lastName,age);
                listUsers.add(newUser);
            }
        } catch(SQLException e) {
            System.out.println("Не удалось получить все элементы из таблицы");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();}

        return listUsers;
    }

    public void cleanUsersTable() {

        try (PreparedStatement pr = new Util().getConnection().prepareStatement("TRUNCATE TABLE userTable")) {
            pr.execute();
        } catch (SQLException e) {
            System.out.println("Не удалось очистить всю таблицу");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
