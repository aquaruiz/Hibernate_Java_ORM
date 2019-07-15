package xml.service.impl;

import xml.config.*;
import xml.domain.dtos.UserDto;
import xml.domain.dtos.UsersDto;
import xml.domain.dtos.queryDtos.query4.ProductDto;
import xml.domain.dtos.queryDtos.query4.RootUserProductsDto;
import xml.domain.dtos.queryDtos.query4.UsersSoldProductsDto;
import xml.domain.entities.User;
import xml.repository.UserRepository;
import xml.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long getRecordsCount() {
        return this.userRepository.count();
    }

    public void seedUsers() throws FileNotFoundException, JAXBException {
        UsersDto usersDto = ImportService.getFromXml(InputConstants.FILE_USERS, UsersDto.class);

        UserDto[] userDtos = usersDto.getUsers();
        List<User> users = ImportService.mappDtoToEntity(userDtos, User.class);
        int usersTotalCount = users.size();
        
        if (ImportService.areValidEntities(users)){

            users.stream().forEach(p -> {
                    Set<User> friends = new HashSet<>();
                    for (int i = 0; i < RandomService.getRandomInt(1, 5); i++) {
                        friends.add(users.get(RandomService.getRandomInt(0, usersTotalCount - 1)));
                    }
                    p.setFriends(friends);
                }
            );

            this.userRepository.saveAll(users);
            this.userRepository.flush();
        }
    }

    @Override
    @Transactional
    public void getUsersAndSoldProducts() throws IOException, JAXBException {
        List<User> users = this.userRepository.findAllSoldAtLeastOneProduct();

        RootUserProductsDto dtoToXml = new RootUserProductsDto();
        List<UsersSoldProductsDto> usersToSave = new LinkedList<>();

        for (User user : users) {
            UsersSoldProductsDto newUser = ExportService.mappOneEntityToDto(user, UsersSoldProductsDto.class);

            List<ProductDto> userSellProducts = user.getSells().stream()
                    .map(s -> ExportService.mappOneEntityToDto(s, ProductDto.class))
                    .collect(Collectors.toList());

            newUser.getSells().increaseCountBy(userSellProducts.size());
            userSellProducts.forEach(
                    p -> {
                        newUser.getSells().addProducts(p);
                    }
            );
            dtoToXml.addUser(newUser);
            dtoToXml.increaseUserCountByOne();
        }

        ExportService.saveToXml(OutputConstants.WRITE_PATH_QUERY4, dtoToXml);
    }
}