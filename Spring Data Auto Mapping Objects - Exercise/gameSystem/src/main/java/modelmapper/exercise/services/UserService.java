package modelmapper.exercise.services;

import modelmapper.exercise.domain.dtos.LoginUserDto;
import modelmapper.exercise.domain.dtos.UserRegisterDto;

public interface UserService {
    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(LoginUserDto loginUserDto);
}
