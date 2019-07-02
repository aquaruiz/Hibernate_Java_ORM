package spring_intro.user_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import spring_intro.user_system.entities.User;
import spring_intro.user_system.services.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Controller
public class UserController implements CommandLineRunner {
    private final UserService userService;
    private final Scanner scanner;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void run(String... args) throws Exception {
        if (this.userService.getDbUsersCount() < 1){
            populateWithUsers(14);
        }

        getUsersByEmailProvider();
        removeInactiveUsers();
    }

    private void removeInactiveUsers() throws ParseException {
        System.out.print("Do you wanna remove inactive users? (Y / N)");
        String answer = this.scanner.nextLine();

        switch (answer.trim().toLowerCase()){
            case "y":
                System.out.print("Please enter threshold date in format d/MM/yyyy: ");
                String dateString = scanner.nextLine();
//                String dateString = "20/12/2011";
                SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
                Date thresholdDate = formatter.parse(dateString);
                Integer deletedCount = this.userService.removeInactiveUsersSine(thresholdDate);

                if (deletedCount > 0){
                    System.out.println(
                            String.format("%d user%s have been deleted",
                                    deletedCount,
                                    deletedCount > 1 ? "s" : ""));


                    System.out.print("Do you REALLLY wanna delete THESE inactive users? (Y / N)");

                    answer = this.scanner.nextLine();
                    switch (answer.trim().toLowerCase()){
                        case "y":
                            this.userService.deleteInactiveUsers();
                            System.out.println("Shame on you. BYE");
                            break;
                        default:
                            System.out.println("BYE");
                            break;
                    }
                } else {
                    System.out.println("No users have been deleted");
                }
                break;
            default:
                System.out.println("BYE");
                break;
        }
    }

    private void getUsersByEmailProvider() {
        System.out.print("Enter email provider to search users by it: ");
        String emailProvider = this.scanner.nextLine();

        if (emailProvider.isEmpty()){
            System.out.println("Provider name cannot be empty string. Try again.");
            this.getUsersByEmailProvider();
        }

        List<String> usernameAndEmails = this.userService
                .getAllUsernamesAndEmailsByEmailProvider(emailProvider);
        if (usernameAndEmails.size() < 1) {
            System.out.println("None found.");
        } else {
            usernameAndEmails.forEach(System.out::println);

        }
    }

    private void populateWithUsers(long count) {
        for (int i = 1; i <= count; i++) {
            User user = new User();
            user.setUsername("username" + i);
            user.setPassword("pasSword%" + i);
            user.setEmail("mymail" + i + "x@gmail.bg");
            user.setAge(i % 120 + 1);
            user.setFirstName("First" + i);
            user.setLastName("Last" + i);
            user.setRegisteredOn(new Date());
            user.setLastTimeLoggedIn(new Date());
            user.setDeleted(false);
            this.userService.save(user);
        }
    }
}