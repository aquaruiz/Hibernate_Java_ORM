package com.bakalov.userSystem.enteties;

import com.bakalov.userSystem.anotations.email.Email;
import com.bakalov.userSystem.anotations.password.Password;
import com.bakalov.userSystem.utilities.Constants;
import com.bakalov.userSystem.utilities.LocalDateAttributeConverter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends IdEntity {

    private String username;
    private String password;
    private String email;
    private LocalDateTime registeredOn;
    private LocalDateTime lastLoggedTime;
    private Byte age;
    private Boolean isDeleted;
    private Town bornTown;
    private Town currentlyLiving;
    private String firstName;
    private String lastName;
    private Set<User> friends;
    private Set<Album> albums;
    private byte[] profilePicture;

    public User() {
        this.friends = new HashSet<>();
        this.albums = new HashSet<>();
    }


    @Column(name = "username", nullable = false, length = 30)
    @Length(min = 4, max = 30, message = Constants.USERNAME_INCORRECT_LENGTH)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false, unique = true)
    @Password(
            maxLength = 50,
            containsDigit = true,
            containLowerCase = true,
            containUpperCase = true,
            containSpecialSymbols = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false, unique = true)
    @Pattern(
            regexp = "^[a-zA-Z0-9]+[-_.]*[a-zA-Z0-9]+@[a-zA-Z0-9]+[-_.]*[a-zA-Z0-9](\\.[a-zA-Z0-9]+[-_.]*[a-zA-Z0-9]+)+$",
            message = "Invalid email address")
    @Email()
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "registered_on")
    @Convert(converter = LocalDateAttributeConverter.class)
    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    @Column(name = "last_time_logged_in")
    @Convert(converter = LocalDateAttributeConverter.class)
    public LocalDateTime getLastLoggedTime() {
        return lastLoggedTime;
    }

    public void setLastLoggedTime(LocalDateTime lastLoggedTime) {
        this.lastLoggedTime = lastLoggedTime;
    }

    @Column(nullable = false)
    @Min(value = 1, message = Constants.AGE_TOO_LOW)
    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Column(name = "is_deleted")
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @ManyToOne
    @JoinColumn(name = "born_town_id")
    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    @ManyToOne
    @JoinColumn(name = "currently_living_town_id")
    public Town getCurrentlyLiving() {
        return currentlyLiving;
    }

    public void setCurrentlyLiving(Town currentlyLiving) {
        this.currentlyLiving = currentlyLiving;
    }

    @Column(name = "first_name", length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name",length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ManyToMany
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @OneToMany(mappedBy = "owner")
    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
