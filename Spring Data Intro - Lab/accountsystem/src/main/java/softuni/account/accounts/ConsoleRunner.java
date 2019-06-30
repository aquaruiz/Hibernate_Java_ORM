package softuni.account.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import softuni.account.accounts.models.Account;
import softuni.account.accounts.models.User;
import softuni.account.accounts.services.AccountServiceImpl;
import softuni.account.accounts.services.UserServiceImpl;

import java.math.BigDecimal;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserServiceImpl userService;
    private AccountServiceImpl accountService;

    @Autowired
    public ConsoleRunner(UserServiceImpl userService, AccountServiceImpl accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User example = new User();
        example.setUsername("example");
        example.setAge(18);

        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(25000));

        example.getAccounts().add(account);
        userService.registerUser(example);

        accountService.withdrawMoney(BigDecimal.valueOf(20000), account.getId());
        accountService.tranferMoney(BigDecimal.valueOf(20000), 1L);
    }
}