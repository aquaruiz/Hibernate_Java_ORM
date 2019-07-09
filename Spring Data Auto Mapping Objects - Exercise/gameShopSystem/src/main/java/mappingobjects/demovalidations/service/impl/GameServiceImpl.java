package mappingobjects.demovalidations.service.impl;

import mappingobjects.demovalidations.domain.dto.GameAddDto;
import mappingobjects.demovalidations.domain.entity.Game;
import mappingobjects.demovalidations.domain.entity.Role;
import mappingobjects.demovalidations.domain.entity.User;
import mappingobjects.demovalidations.repository.GameRepository;
import mappingobjects.demovalidations.repository.UserRepository;
import mappingobjects.demovalidations.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private String loggedInUser;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
        this.loggedInUser = "";
    }

    @Override
    public String addGame(GameAddDto gameAddDto) {
        StringBuilder sb = new StringBuilder();

        Game game = this.modelMapper.map(gameAddDto, Game.class);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Game>> violations = validator.validate(game);

        if (violations.size() > 0){
            for (ConstraintViolation<Game> violation : violations) {
                sb.append(violation.getMessage())
                  .append(System.lineSeparator());
            }

            return sb.toString();
        }

        User user = this.userRepository.findByEmail(this.loggedInUser).orElse(null);

        if (user == null){
            return  sb.append("Noone is logged in. Please log in").toString();
        }

        if (!user.getRole().equals(Role.ADMIN)){
            return sb.append(String.format("%s is NOT admin!",
                                user.getFullName()))
                     .toString();
        }

        Game gameInDb = this.gameRepository.findByTitle(gameAddDto.getTitle()).orElse(null);
        if (gameInDb != null && gameInDb.getTitle().equals(gameAddDto.getTitle())){
            return sb.append("Game with that title already exists.").toString();
        }

        Set<Game> userGames = user.getGames();
        userGames.add(game);
        user.setGames(userGames);

        this.gameRepository.saveAndFlush(game);
        this.userRepository.saveAndFlush(user);

        sb.append(String.format("Added %s", game.getTitle()));

        return sb.toString();
    }

    @Override
    public String editGame(Integer gameId, String[] gameProps) {
        StringBuilder sb = new StringBuilder();

        User user = this.userRepository.findByEmail(this.loggedInUser).orElse(null);

        if (user == null){
            return  sb.append("Noone is logged in. Please log in").toString();
        }

        if (!user.getRole().equals(Role.ADMIN)){
            return sb.append(String.format("%s is NOT admin!",
                    user.getFullName()))
                    .toString();
        }

        Game gameToEdit = this.gameRepository.findById(gameId).orElse(null);

        if (gameToEdit == null){
            return  sb.append(String.format("Game with Id %d doesn't exist.",
                    gameId)).toString();
        }

        for (String gameProp : gameProps) {
            String[] kvp = gameProp.split("=");
            String propName = kvp[0];
            System.out.println(propName);
            switch (propName){
                case "title":
                    String title = kvp[1];
                    gameToEdit.setTitle(title);
                    sb.append("Title was updated successfully.").append(System.lineSeparator());
                    break;
                case "price":
                    BigDecimal price = new BigDecimal(kvp[1]);
                    gameToEdit.setPrice(price);
                    sb.append("Price was updated successfully.").append(System.lineSeparator());
                    break;
                case "size":
                    double size = Double.parseDouble(kvp[1]);
                    System.out.println(size);
                    gameToEdit.setSize(size);
                    sb.append("Size was updated successfully.").append(System.lineSeparator());
                    break;
                case "trailer":
                    String trailer = kvp[1];
                    gameToEdit.setTrailer(trailer);
                    sb.append("Trailer was updated successfully.").append(System.lineSeparator());
                    break;
                case "imageThumbnail":
                    String imageThumbnail = kvp[1];
                    gameToEdit.setImageThumbnail(imageThumbnail);
                    sb.append("Image thumbnail was updated successfully.").append(System.lineSeparator());
                    break;
                case "description":
                    String description = kvp[1];
                    gameToEdit.setDescription(description);
                    sb.append("Description was updated successfully.").append(System.lineSeparator());
                    break;
                case "releaseDate":
                    LocalDate releaseDate = LocalDate.parse(kvp[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    gameToEdit.setReleaseDate(releaseDate);
                    sb.append("Release date was updated successfully.").append(System.lineSeparator());
                    break;
                default:
                    sb.append(propName + " field doesn't exists.").append(System.lineSeparator());
                    break;
            }
        }

        this.gameRepository.saveAndFlush(gameToEdit);
        sb.append(String.format("%s edited", gameToEdit.getTitle()));
        return sb.toString();
    }

    @Override
    @Transactional
    public String deleteGame(Integer gameId) {
        StringBuilder sb = new StringBuilder();

        User user = this.userRepository.findByEmail(this.loggedInUser).orElse(null);

        if (user == null){
            return  sb.append("Noone is logged in. Please log in").toString();
        }

        if (!user.getRole().equals(Role.ADMIN)){
            return sb.append(String.format("%s is NOT admin!",
                    user.getFullName()))
                    .toString();
        }

        Game gameToDelete = this.gameRepository.findById(gameId).orElse(null);

        if (gameToDelete == null){
            return  sb.append(String.format("Game with Id %d doesn't exist.",
                    gameId)).toString();
        }

        sb.append(String.format("Game %s was deleted successfully", gameToDelete.getTitle()));

        Set<Game> userGames = user.getGames();
        userGames.remove(gameToDelete);

        this.gameRepository.delete(gameToDelete);
        this.gameRepository.flush();

        return sb.toString();
    }

    @Override
    public String getAllGames() {
        StringBuilder sb = new StringBuilder();
        List<Game> allGames = this.gameRepository.findAll();

        allGames.forEach(g -> sb.append(String.format("%s %s",
                g.getTitle(),
                g.getPrice()))
            .append(System.lineSeparator()));

        return sb.toString();
    }

    @Override
    public String getGameByTitle(String gameTitle) {
        StringBuilder sb = new StringBuilder();
        Game game = this.gameRepository.findByTitle(gameTitle).orElse(null);
        if (game == null){
            return  sb.append(String.format("Game with Id %d doesn't exist.",
                    gameTitle)).toString();
        }

        sb.append(String.format("Title: %s", game.getTitle()))
                .append(System.lineSeparator())
                .append(String.format("Price: %s", game.getPrice()))
                .append(System.lineSeparator())
                .append(String.format("Description: %s", game.getDescription()))
                .append(System.lineSeparator())
                .append(String.format("Release date: %s", game.getReleaseDate()))
                .append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public String getGamesByLoggedInUser() {
        StringBuilder sb = new StringBuilder();

        User user = this.userRepository.findByEmail(this.loggedInUser).orElse(null);

        if (user == null){
            return  sb.append("Noone is logged in. Please log in").toString();
        }

        Set<Game> userGames = user.getGames();
        userGames.forEach(g -> sb.append(g.getTitle()).append(System.lineSeparator()));
        return sb.toString();
    }

    @Override
    public void setLoggedInUser(String email) {
        this.loggedInUser = email;
    }

    @Override
    public void setLogoutUser() {
        this.loggedInUser = "";
    }
}
