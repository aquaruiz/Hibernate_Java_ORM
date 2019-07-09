package mappingobjects.demovalidations.service.impl;

import mappingobjects.demovalidations.domain.dto.GameAddDto;
import mappingobjects.demovalidations.repository.GameRepository;
import mappingobjects.demovalidations.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String addGame(GameAddDto gameAddDto) {
        StringBuilder sb = new StringBuilder();


        return sb.toString();
    }
}
