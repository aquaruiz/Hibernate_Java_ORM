package softuni.account.accounts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import softuni.account.accounts.models.User;
import softuni.account.accounts.repositories.UserRepository;

@Service
@Primary
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        checkIfUserExists(user);
        this.userRepository.save(user);
    }

    private void checkIfUserExists(User user) {
        System.out.println(this.userRepository
                .findByUsername(user.getUsername()));
//        this.userRepository
//                .findByUsername(user.getUsername())
//                .orElseThrow(() -> new IllegalArgumentException("Username already exists!"));
    }
}
