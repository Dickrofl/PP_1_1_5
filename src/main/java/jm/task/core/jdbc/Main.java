package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        printAndSaveUser(new User("Анна", "Каренина", (byte) 18));
        printAndSaveUser(new User("Михаил", "Кочевний", (byte) 34));
        printAndSaveUser(new User("София", "Овечкина", (byte) 29));
        printAndSaveUser(new User("Виктор", "Шевченко", (byte) 14));

        List<User> users = userService.getAllUsers();

        for (User user : users) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

    public static void printAndSaveUser(User user) {
        UserService userService = new UserServiceImpl();
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
    }
}