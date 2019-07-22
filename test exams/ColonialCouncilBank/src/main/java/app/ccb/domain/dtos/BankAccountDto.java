package app.ccb.domain.dtos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "bank-account")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountDto {
    @XmlAttribute(name = "client")
    private String clientFullName;

    @XmlElement(name = "account-number")
    private String accountNumber;

    @XmlElement
    private double balance;

    public BankAccountDto() {
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}