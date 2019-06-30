package softuni.account.accounts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import softuni.account.accounts.models.Account;
import softuni.account.accounts.repositories.AccountRepository;

import java.math.BigDecimal;

@Service
@Primary
public class AccountServiceImpl implements  AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        validateMoneyAmount(money);

        Account account = getAccount(id);
        account.setBalance(account.getBalance().subtract(money));
        this.accountRepository.save(account);
    }

    private void validateMoneyAmount(BigDecimal money) {
        if (!money.abs().equals(money)) {
            throw new IllegalArgumentException("No negative money!");
        }
    }

    private Account getAccount(Long id) {
        Account account = this.accountRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        return account;
    }

    @Override
    public void tranferMoney(BigDecimal money, Long id) {
        validateMoneyAmount(money);

        Account account = getAccount(id);
        account.setBalance(account.getBalance().add(money));
        this.accountRepository.save(account);

    }
}
