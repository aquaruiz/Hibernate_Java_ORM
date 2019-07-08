package modelmapper.exercise.controllers;

import modelmapper.exercise.domain.dtos.UserRegisterDto;
import modelmapper.exercise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class GameStoreController implements CommandLineRunner {
    private final UserService userService;
    private final Scanner scanner;

    @Autowired
    public GameStoreController(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true){
            String[] params = scanner.nextLine().split("\\|");

            switch (params[0]){
                case "RegisterUser":
                    UserRegisterDto userRegisterDto = new UserRegisterDto(params[1], params[2], params[3], params[4]);
                    String output = this.userService.registerUser(userRegisterDto);
                    System.out.println(output);
                    break;
                case "LoginUser":
                    break;
                case "Logout":
                    break;
                default:
                    break;
            }
        }
    }
}
