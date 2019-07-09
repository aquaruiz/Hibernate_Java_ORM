package mappingobjects.demovalidations.domain.dto;

import mappingobjects.demovalidations.domain.entity.BaseEntity;

import java.math.BigDecimal;

public class GameAddDto extends BaseEntity {
    private String title;
    private String trailer;
    private String imageThumbnail;
    private double size;
    private BigDecimal price;
    private String description;

    public GameAddDto() {
    }

    public GameAddDto(String title, String trailer, String imageThumbnail, double size, BigDecimal price, String description) {
        this.title = title;
        this.trailer = trailer;
        this.imageThumbnail = imageThumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}