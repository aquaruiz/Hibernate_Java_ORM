package com.bakalov.userSystem.services;


import com.bakalov.userSystem.enteties.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    List<String> getUserByEmailProvider(String emailProvider);

    List<User> getInactiveUserByDate(LocalDateTime dateTime);

    void deleteInactiveUsers(LocalDateTime dateTime);
}
