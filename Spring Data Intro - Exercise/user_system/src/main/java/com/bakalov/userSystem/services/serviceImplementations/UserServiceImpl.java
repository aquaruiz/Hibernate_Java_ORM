package com.bakalov.userSystem.services.serviceImplementations;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.bakalov.userSystem.enteties.User;
import com.bakalov.userSystem.repositories.UserRepository;
import com.bakalov.userSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> getUserByEmailProvider(String emailProvider) {

        return this.userRepository
                .getAllByEmailLike(emailProvider)
                .stream()
                .map(user -> String.format("%s %s",
                        user.getUsername(),
                        user.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getInactiveUserByDate(LocalDateTime dateTime) {
        return this.userRepository.findAllByLastLoggedTimeBefore(dateTime);
    }

    @Override
    public void deleteInactiveUsers(LocalDateTime dateTime) {

        printInactiveUsers(dateTime);

        this.userRepository.findAllByLastLoggedTimeBefore(dateTime)
                .forEach(user -> user.setDeleted(true));

        this.userRepository.deleteUserByDeletedTrue();
    }

    private void printInactiveUsers(LocalDateTime dateTime) {
        List<User> inactiveUsers = this.getInactiveUserByDate(dateTime);

        int count = inactiveUsers.size();

        if (count > 1) {
            System.out.printf("%d users have been deleted", count).println();
        } else if (count == 1) {
            System.out.println("1 users have been deleted");

        } else {
            System.out.println("No users have been deleted");
        }
    }

}
