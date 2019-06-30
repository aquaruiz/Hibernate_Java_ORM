package softuni.account.accounts.services;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney (BigDecimal money, Long id);
    void tranferMoney (BigDecimal money, Long id);
}
