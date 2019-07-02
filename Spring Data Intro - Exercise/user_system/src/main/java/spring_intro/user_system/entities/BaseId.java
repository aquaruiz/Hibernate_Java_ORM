package spring_intro.user_system.entities;

import javax.persistence.*;

@MappedSuperclass
public class BaseId {
    private Integer id;

    public BaseId(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}