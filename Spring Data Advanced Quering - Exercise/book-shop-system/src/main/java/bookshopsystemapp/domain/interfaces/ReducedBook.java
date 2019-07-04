package bookshopsystemapp.domain.interfaces;

import bookshopsystemapp.domain.entities.AgeRestriction;
import bookshopsystemapp.domain.entities.EditionType;

import java.math.BigDecimal;

public class ReducedBook {
    private String title;
    private EditionType editionType;
    private BigDecimal price;
    private AgeRestriction ageRestriction;

    public ReducedBook() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }
}