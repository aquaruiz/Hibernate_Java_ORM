package spring_intro.user_system.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_intro.user_system.entities.User;
import spring_intro.user_system.repositories.UserRepository;
import spring_intro.user_system.services.UserService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllUsernamesAndEmailsByEmailProvider(String emailProvider) {
        List<User> users = this.userRepository.findAllByEmailIsEndingWith(emailProvider);

        List<String> usernamesAndEmails = users.stream()
                .map(u -> String.format("%s %s",
                        u.getUsername(),
                        u.getEmail()))
                .collect(Collectors.toList());
        return usernamesAndEmails;
    }

    public void save(User user) {
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public int getDbUsersCount() {
        return (int) this.userRepository.count();
    }

    @Override
    @Transactional
    public Integer removeInactiveUsersSine(Date thresholdDate) {
        List<User> inactiveUsers = this.userRepository.findAllByLastTimeLoggedInBefore(thresholdDate);
        inactiveUsers.forEach(user -> user.setDeleted(true));
        this.userRepository.saveAll(inactiveUsers);
        return inactiveUsers.size();
    }

    @Override
    @Transactional
    public void deleteInactiveUsers() {
        List<User> deleteUsers = this.userRepository.findAllByDeletedIs(true);
        this.userRepository.deleteAll(deleteUsers);
        this.userRepository.flush();
    }
}