package json.exercise.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.exercise.domain.dtos.ProductDto;
import json.exercise.domain.dtos.UserDto;
import json.exercise.domain.dtos.queryDtos.query3.UserProductsDto;
import json.exercise.domain.dtos.queryDtos.query3.UsersSoldProductsDto;
import json.exercise.domain.entities.User;
import json.exercise.repository.UserRepository;
import json.exercise.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final String FOLDER_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files";
    private final String FILE_PATH = FOLDER_PATH + File.separator +
            "users.json";
    private final String WRITE_PATH_QUERY4 = FOLDER_PATH + File.separator +
            "users-and-products.json";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    public long getRecordsCount() {
        return this.userRepository.count();
    }

    public void seedUsers() throws FileNotFoundException {
        UserDto[] userDtos= getFromJson(FILE_PATH);

        User[] users = mappDtoToEntity(userDtos);

        System.out.println(userDtos.length);
        System.out.println(users.length);

        if (validateEntities(users) == true){
            Arrays.stream(users)
                    .forEach(p -> p.setFriends(getRandomFriend(users)));

            this.userRepository
                    .saveAll(
                            Arrays.stream(users)
                            .collect(Collectors.toList())
                    );

            this.userRepository.flush();
            System.out.println("Users OK");
        }
    }

    private Set<User> getRandomFriend(User[] users) {
        Set<User> friends = new HashSet<>();
        Random random = new Random();

        int tries = random.nextInt((5 - 0) + 1) + 0;

        for(int i = 0; i < tries; i++){
            int num = random.nextInt(((users.length - 1) - 1) + 1) + 1;
            if (Math.sqrt(num) == (int)Math.sqrt(num)){
                friends.add(users[num]);
            }
        }

        return friends;
    }

    private UserDto[] getFromJson(String filePath) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        BufferedReader br = new BufferedReader(new FileReader(filePath));

        UserDto[] userDtos = gson.fromJson(br, UserDto[].class);
        return userDtos;
    }


    private void saveToJson(String filePath, List<? extends Object> dtos) throws IOException {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        FileWriter fw = new FileWriter(filePath);

        String jsonDto = gson.toJson(dtos);
        fw.write(jsonDto);
        fw.close();
    }

    private User[] mappDtoToEntity(UserDto[] userDtos) {
        User[] users= Arrays.stream(userDtos)
                .map(dto -> this.modelMapper.map(dto, User.class))
                .toArray(User[]::new);
        return users;
    }

    private boolean validateEntities(User[] users) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        for (User user: users) {
            Set<ConstraintViolation<User>> violations = validator.validate(user);

            if (violations.size() > 0){
                return false;
            }
        }

        return true;
    }

    @Override
    @Transactional
    public void getUsersAndSoldProducts() throws IOException {
        List<User> users = this.userRepository.findAllSoldAtLeastOneProduct();

        List<UsersSoldProductsDto> usersToSave = new ArrayList<>();

        UserProductsDto dto = new UserProductsDto();
        for (User user : users) {
            UsersSoldProductsDto newUser = this.modelMapper.map(user, UsersSoldProductsDto.class);


            List<ProductDto> userSellProducts = user.getSells().stream()
                    .map(s -> modelMapper.map(s, ProductDto.class))
                    .collect(Collectors.toList());

            newUser.getSoldProducts().increaseCountBy(userSellProducts.size());
            userSellProducts.forEach(
                    p -> {
                        newUser.getSoldProducts().addProducts(p);
                    }
            );
            dto.getUsers().add(newUser);
            dto.increaseUserCountByOne();

            dto.addUser(newUser);
        }

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        FileWriter fw = new FileWriter(WRITE_PATH_QUERY4);

        String jsonDto = gson.toJson(dto);
        fw.write(jsonDto);
        fw.close();
    }
}