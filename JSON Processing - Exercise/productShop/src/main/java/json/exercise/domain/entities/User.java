package json.exercise.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 3)
    private String lastName;

    private int age;

    @ManyToMany
    @JoinTable(name = "users_friends",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> friends;

    @OneToMany(mappedBy = "buyer", targetEntity = Product.class)
    private List<Product> buys;

    @OneToMany(mappedBy = "seller", targetEntity = Product.class)
    private List<Product> sells;

    public User() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public List<Product> getBuys() {
        return buys;
    }

    public void setBuys(List<Product> buys) {
        this.buys = buys;
    }

    public List<Product> getSells() {
        return sells;
    }

    public void setSells(List<Product> sells) {
        this.sells = sells;
    }

    @Override
    public String toString() {
        return (this.firstName == null ? "" : (this.firstName + " ")) + this.lastName;
    }
}