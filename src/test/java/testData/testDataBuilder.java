package testData;

import models.request.*;
import net.datafaker.Faker;

import java.time.LocalDate;

public class testDataBuilder {

    private final Faker faker = new Faker();
    public static String username;
    public static String email;
    public static String password;

    public registerUser registerUserPayload() {

        username = "tester_" + faker.number().digits(6);
        email = username + "@gmail.com";
        password = "Qa@" + faker.number().digits(8);

        registerUser user = new registerUser();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

    public loginUser loginUserPayload() {

        loginUser user = new loginUser();
        user.setIdentifier(username);
        user.setPassword(password);
        return user;
    }

    public logoutUser logoutUserPayload(String userId) {
        logoutUser payload = new logoutUser();
        payload.setUserid(userId);
        return payload;
    }

    public createTodoCategory createTodoCategoryPayload() {

        createTodoCategory payload = new createTodoCategory();

        payload.setCategoryName(faker.book().genre());

        return payload;
    }

    public createTodo createTodoPayload(String categoryId){
        createTodo todo = new createTodo();

        todo.setTitle(faker.book().title());

        todo.setDescription(faker.lorem().sentence());

        todo.setDeadline(
                LocalDate.now()
                        .plusDays(5)
                        .toString()
        );

        todo.setTodoCategoryId(categoryId);

        return todo;
    }

    public toggleTodo toggleTodoPayload(String todoId) {

        toggleTodo payload = new toggleTodo();

        payload.setTodoId(todoId);

        return payload;
    }
}