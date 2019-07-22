package app.ccb.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "bankAccounts")
@Table(name = "bank_accounts")
public class BankAccount extends BaseEntity {
    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(columnDefinition = "DECIMAL(19, 2)")
    private double balance;

    @OneToOne(targetEntity = Client.class, cascade = CascadeType.ALL)
    @JoinTable(name = "bank_accounts_clients",
        joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "bank_account_id", referencedColumnName = "id"))
    private Client client;

    @OneToMany(mappedBy = "bankAccount") //??
    private List<Card> cards;

    public BankAccount() {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}