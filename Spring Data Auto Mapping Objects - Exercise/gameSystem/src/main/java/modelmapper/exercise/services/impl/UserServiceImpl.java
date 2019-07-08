package modelmapper.exercise.services.impl;

import modelmapper.exercise.domain.dtos.LoginUserDto;
import modelmapper.exercise.domain.dtos.UserRegisterDto;
import modelmapper.exercise.domain.entities.Role;
import modelmapper.exercise.domain.entities.User;
import modelmapper.exercise.repositories.UserRepository;
import modelmapper.exercise.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.stereotype.Service;


import javax.xml.validation.Validator;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        StringBuilder sb = new StringBuilder();

        User user = this.modelMapper.map(userRegisterDto, User.class);

        User inDb = this.userRepository.findByEmail(user.getEmail()).orElse(null);

        if (inDb != null){
            return sb.append("User with that email is already registered")
                    .append(System.lineSeparator())
                    .toString();
        }



//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//        Set<ConstraintViolation<User>> violations = validator.validate(user);
//
//        if (this.userRepository.count() == 0){
//            user.setRole(Role.ADMIN);
//        } else {
//            user.setRole(Role.USER);
//        }
//
//        if (violations.size() > 0){
//            for (ConstraintViolation<User> violation : violations) {
//                sb.append(violation.getMessage()).append(System.lineSeparator());
//            }
//        } else {
//            sb.append(String.format("%s was registered.",
//                    user.getFullName()))
//                .append(System.lineSeparator());
//            this.userRepository.saveAndFlush(user);
//        }

        return sb.toString();
    }

    @Override
    public String loginUser(LoginUserDto loginUserDto) {
        return null;
    }
}