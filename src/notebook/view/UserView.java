package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;
import notebook.util.UserValidator;

import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com;
        String userId;
        while (true) {
            String command = prompt("Введите команду: ").toUpperCase();
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    User u = createUser();
                    userController.saveUser(u);
                    break;
                case READ:
                    userId = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(userId));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    System.out.println(userController.findAll());
                    break;
                case UPDATE:
                    userId = prompt("Введтите Id: ");
                    userController.updateUser(userId, createUser());
                    break;
                case DELETE:
                    userId = prompt("Dведите Id: ");
                    if (userController.delete(Long.parseLong(userId))) {
                        System.out.println("Пользователь удален");
                    } else {
                        System.out.println("Пользователь не найден");
                    }
                    break;
            }
        }
    }

    public User createUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");

        UserValidator validator = new UserValidator();
        return validator.validate(new User(firstName, lastName, phone));
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
