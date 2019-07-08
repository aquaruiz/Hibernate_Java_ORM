package mappingobjects.demovalidations.controller;

import mappingobjects.demovalidations.domain.dto.UserLoginDto;
import mappingobjects.demovalidations.domain.dto.UserRegisterDto;
import mappingobjects.demovalidations.service.UserService;
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

            switch (params[0].toLowerCase()){
                case "registeruser":
                    UserRegisterDto userRegisterDto = new UserRegisterDto(params[1], params[2], params[3], params[4]);
                    String output = this.userService.registerUser(userRegisterDto);
                    System.out.println(output);
                    break;
                case "loginuser":
                    UserLoginDto userLoginDto = new UserLoginDto(params[1], params[2]);
                    output = this.userService.loginUser(userLoginDto);
                    System.out.println(output);
                    break;
                case "logoutuser":
                    output = this.userService.logoutUser();
                    System.out.println(output);
                    break;
                case "logout":
                    output = this.userService.logoutUser();
                    System.out.println(output);
                    break;
                default:
                    break;
            }
        }
    }
}