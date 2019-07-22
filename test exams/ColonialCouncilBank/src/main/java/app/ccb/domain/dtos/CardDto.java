package app.ccb.domain.dtos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardDto {
    @XmlAttribute
    private String status;

    @XmlAttribute(name = "account-number")
    private String Accountnumber;

    @XmlElement(name = "card-number")
    private String cardNumber;

    public CardDto() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountnumber() {
        return Accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        Accountnumber = accountnumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}