package spring_intro.user_system.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseId {
    @Column(length = 30, nullable = false)
//    @Min(4)
    private String username;

    @Column(nullable = false, length = 50)
//    @Size(min = 6, max = 30)
//    @Pattern(regexp = "[a-z]+")
//    @Pattern(regexp = "[A-Z]+")
//    @Pattern(regexp = "[0-9]+")
//    @Pattern(regexp = "[!@#$%^&*()_+<>?]+")
    private String password;

    @Column(nullable = false)
//    @Email(regexp = "(^[a-zA-Z0-9]+[.-_-]*[a-zA-Z0-9]+)@([a-zA-Z0-9]+[--]*[a-zA-Z0-9]+\\.[a-zA-Z0-9]+[--]*[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+[--]*[a-zA-Z0-9])*$)")
    private String email;

    @Column(name = "registered_on")
    private Date registeredOn;

    @Column(name = "last_time_logged_in")
    private Date lastTimeLoggedIn;

//    @Min(1)
//    @Max(120)
    private int age;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    private Town bornTown;

    private Town currentlyLivingTown;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Set<User> friends;

    public User() {
        this.friends = new HashSet<>();
    }

    @Override
    public String toString() {
        return "User{ " + super.getId()+
                " username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registeredOn=" + registeredOn +
                ", lastTimeLoggedIn=" + lastTimeLoggedIn +
                ", age=" + age +
                ", isDeleted=" + isDeleted +
                ", bornTown=" + bornTown +
                ", currentlyLivingTown=" + currentlyLivingTown +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", friends=" + friends +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @ManyToOne
    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    @ManyToOne
    public Town getCurrentlyLivingTown() {
        return currentlyLivingTown;
    }

    public void setCurrentlyLivingTown(Town currentlyLivingTown) {
        this.currentlyLivingTown = currentlyLivingTown;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}