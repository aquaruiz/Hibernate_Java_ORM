package spring_intro.user_system.services;

import spring_intro.user_system.entities.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    List<String> getAllUsernamesAndEmailsByEmailProvider(String emailProvider);

    void save(User user);

    int getDbUsersCount();

    Integer removeInactiveUsersSine(Date thresholdDate);

    void deleteInactiveUsers();
}
