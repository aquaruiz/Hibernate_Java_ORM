package mappingobjects.demovalidations.service;

import mappingobjects.demovalidations.domain.dto.GameAddDto;

public interface GameService {
    String addGame(GameAddDto gameAddDto);
}
