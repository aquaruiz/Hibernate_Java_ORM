package mappingobjects.demovalidations.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "games")
@Table(name = "games")
public class Game extends BaseEntity {
    private String title;
    private BigDecimal price;
    private double size;
    private String trailer;
    private String imageThumbnail;
    private String description;
    private LocalDate releaseDate;

    private Set<User> users;

    public Game() {
    }

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "[A-Z][a-z]{2,99}", message = "Title did not passed validation - starts with capital and it's between 3 and 100 letters.")
    @Size(max = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(unique = true)
    @Pattern(regexp = "(https:\\/\\/www\\.youtube\\.com\\/watch\\?v=)*.*(.{11})$", message = "Not valid YouTube link.")
    // get last 11 letters only
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Column(name = "image_thumbnail", unique = true)
    @Pattern(regexp = "^(https?:\\/\\/)?.+$", message = "Not valid link")
    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    @Column(nullable = false, precision = 19, scale = 1)
    @Positive
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Column(nullable = false, precision = 19, scale = 2)
    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(length = 1000)
    @Size(min = 20, max = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "release_date", nullable = false)
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @ManyToMany(targetEntity = User.class, mappedBy = "games")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
