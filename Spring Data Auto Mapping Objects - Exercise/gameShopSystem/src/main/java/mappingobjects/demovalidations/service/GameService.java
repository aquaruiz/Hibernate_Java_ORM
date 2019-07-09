package mappingobjects.demovalidations.service;

import mappingobjects.demovalidations.domain.dto.GameAddDto;

public interface GameService {
    String addGame(GameAddDto gameAddDto);

    void setLoggedInUser(String email);

    void setLogoutUser();

    String editGame(Integer gameId, String[] gameProps);

    String deleteGame(Integer gameId);

    String getAllGames();

    String getGameByTitle(String gameTitle);

    String getGamesByLoggedInUser();
}
