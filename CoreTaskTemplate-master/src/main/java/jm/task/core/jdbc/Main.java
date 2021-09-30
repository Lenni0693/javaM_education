package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
         //добавляем юзеров в таблицу
        userService.saveUser("Иван", "Иванов",(byte)28);
        System.out.println("User с именем – Иван добавлен в базу данных");
        userService.saveUser("Степан", "Петров",(byte)40);
        System.out.println("User с именем – Степан добавлен в базу данных");
        userService.saveUser("Олег", "Степанов",(byte)38);
        System.out.println("User с именем – Олег добавлен в базу данных");
        userService.saveUser("Дмитрий", "Стеклов",(byte)18);
        System.out.println("User с именем – Дмитрий добавлен в базу данных");

        List<User> userList = userService.getAllUsers();
        for (User user:userList) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
