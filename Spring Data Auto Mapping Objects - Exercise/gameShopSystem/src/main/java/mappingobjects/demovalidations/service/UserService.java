package mappingobjects.demovalidations.service;

import mappingobjects.demovalidations.domain.dto.UserLoginDto;
import mappingobjects.demovalidations.domain.dto.UserRegisterDto;

public interface UserService {
    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);

    String logoutUser();
}
