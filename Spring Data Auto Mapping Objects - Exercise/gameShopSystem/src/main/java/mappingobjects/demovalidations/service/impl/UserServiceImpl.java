package mappingobjects.demovalidations.service.impl;

import mappingobjects.demovalidations.domain.dto.UserLoginDto;
import mappingobjects.demovalidations.domain.dto.UserRegisterDto;
import mappingobjects.demovalidations.domain.entity.Role;
import mappingobjects.demovalidations.domain.entity.User;
import mappingobjects.demovalidations.repository.UserRepository;
import mappingobjects.demovalidations.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private String loggedInUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
        this.loggedInUser = "";
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

        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())){
            return sb.append("Passwords didn't match.")
                    .append(System.lineSeparator())
                    .toString();
        }

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (this.userRepository.count() == 0){
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }

        if (violations.size() > 0){
            for (ConstraintViolation<User> violation : violations) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }
        } else {
            sb.append(String.format("%s was registered.",
                    user.getFullName()))
                .append(System.lineSeparator());
            this.userRepository.saveAndFlush(user);
        }

        return sb.toString();
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {
        StringBuilder sb = new StringBuilder();

        if (!this.loggedInUser.isEmpty()){
            return sb.append("A user is already logged in").toString();
        }

        User user = this.userRepository.findByEmail(userLoginDto.getEmail()).orElse(null);

        if (user == null || !user.getPassword().equals(userLoginDto.getPassword())){
            return sb.append("Incorect username / password").toString();
        } else {
            sb.append(String.format(
                    "Successfully logged in Ivan",
                    user.getFullName()
            ));
        }

        return sb.toString();
    }

    @Override
    public String logoutUser() {
        StringBuilder sb = new StringBuilder();
        if (this.loggedInUser.isEmpty()){
            sb.append("Cannot log out. No user was logged in.");
        } else {
            User user = this.userRepository.findByEmail(this.loggedInUser).orElse(null);
            sb.append(String.format("User %s successfully logged out",
                    user.getFullName()));
            this.loggedInUser = "";
        }
        return sb.toString();
    }
}