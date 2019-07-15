package json.exercise.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersDto implements Serializable {
    @XmlElement(name = "user")
    private UserDto[] users;

    public UsersDto() {
    }

    public UserDto[] getUsers() {
        return users;
    }

    public void setUsers(UserDto[] users) {
        this.users = users;
    }
}