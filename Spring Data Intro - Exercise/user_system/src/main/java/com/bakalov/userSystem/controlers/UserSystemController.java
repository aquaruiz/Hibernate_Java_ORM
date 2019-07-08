package com.bakalov.userSystem.controlers;

import com.bakalov.userSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class UserSystemController implements CommandLineRunner {

    private final BufferedReader reader;

    private final UserService userService;

    @Autowired
    public UserSystemController(BufferedReader reader, UserService userService) {
        this.reader = reader;
        this.userService = userService;

    }

    @Override
    public void run(String... args) throws Exception {
//        this.getUsersByEmailProvider(); // <- Get Users by Email Provider
//        this.removeInactiveUsers(); // <- Remove Inactive Users
    }

    private void getUsersByEmailProvider() throws IOException {
        String emailProvider = reader.readLine();

        List<String> users = this.userService.getUserByEmailProvider(emailProvider);

        if (users.isEmpty()) {
            System.out.printf("No users found with email domain %s", emailProvider);
        } else {
            this.userService.getUserByEmailProvider(emailProvider)
                    .forEach(System.out::println);
        }
    }


    //Not tested
    private void removeInactiveUsers() throws IOException {
        LocalDateTime localDateTime = this.formatLocalDateTime();

        this.userService.deleteInactiveUsers(localDateTime);
    }

    private LocalDateTime formatLocalDateTime() throws IOException {
        System.out.println("Enter date and time in this format \"yyyy-MM-dd HH:mm\"");
        String date = reader.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return LocalDateTime.parse(date, formatter);
    }
}
