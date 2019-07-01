package spring_intro.user_system.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String country;

    @OneToMany(mappedBy = "bornTown")
    private Set<User> bornUsers;

    @OneToMany(mappedBy = "currentlyLivingTown")
    private Set<User> currentlyLivingUsers;
}
