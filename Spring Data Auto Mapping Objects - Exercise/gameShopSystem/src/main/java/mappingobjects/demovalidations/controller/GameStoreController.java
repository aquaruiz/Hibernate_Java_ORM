package mappingobjects.demovalidations.controller;

import mappingobjects.demovalidations.domain.dto.GameAddDto;
import mappingobjects.demovalidations.domain.dto.UserLoginDto;
import mappingobjects.demovalidations.domain.dto.UserRegisterDto;
import mappingobjects.demovalidations.service.GameService;
import mappingobjects.demovalidations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;

@Controller
public class GameStoreController implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;
    private final Scanner scanner;

    @Autowired
    public GameStoreController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.print("What do you wanna do? ");
        while (true){
            String[] params = scanner.nextLine().trim().split("\\|");

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
                    this.gameService.setLoggedInUser(userLoginDto.getEmail());
                    break;
                case "logoutuser":
                case "logout":
                    output = this.userService.logoutUser();
                    System.out.println(output);
                    this.gameService.setLogoutUser();
                    break;
                case "addgame":
                    GameAddDto gameAddDto = new GameAddDto(
                            params[1], new BigDecimal(params[2]), Double.parseDouble(params[3]),
                            params[4], params[5], params[6],
                            LocalDate.parse(params[7], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    output = this.gameService.addGame(gameAddDto);
                    System.out.println(output);
                    break;
                case "editgame":
                    Integer gameId = Integer.parseInt(params[1]);
                    String[] gameProps = Arrays.copyOfRange(params, 2, params.length);
                    break;
                case "deletegame":
                    gameId = Integer.parseInt(params[1]);
                    output = this.gameService.deleteGame(gameId);
                    System.out.println(output);
                    break;
                case "allgames":
                    output = this.gameService.getAllGames();
                    System.out.print(output);
                    break;
                case "detailsgame":
                case "detailgame":
                    String gameTitle = params[1];
                    output = this.gameService.getGameByTitle(gameTitle);
                    System.out.print(output);
                    break;
                case "ownedgames":
                    output = this.gameService.getGamesByLoggedInUser();
                    System.out.print(output);
                    break;
                case "exit":
                    System.out.println("Bye!");
                    exit(0);
                default:
                    break;
            }

            System.out.print("What do you wanna do next? ");
        }
    }
}
