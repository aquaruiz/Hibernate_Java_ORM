package app.ccb.domain.entities;

import javax.persistence.*;

@Entity(name = "cards")
@Table(name = "cards")
public class Card extends BaseEntity {
    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "card_status", nullable = false)
    private String cardStatus;

    @ManyToOne(targetEntity = BankAccount.class)
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    public Card() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}