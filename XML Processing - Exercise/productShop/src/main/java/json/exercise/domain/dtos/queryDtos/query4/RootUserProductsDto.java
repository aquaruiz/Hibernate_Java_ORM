package json.exercise.domain.dtos.queryDtos.query4;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootUserProductsDto {
    @XmlAttribute(name = "count")
    private Integer usersCount;

    @XmlElement(name = "user")
    private List<UsersSoldProductsDto> users;

    public RootUserProductsDto() {
        this.users = new ArrayList<>();
        this.usersCount = 0;
    }

    public RootUserProductsDto(Integer usersCount, List<UsersSoldProductsDto> users) {
        this.usersCount = usersCount;
        this.users = users;
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public List<UsersSoldProductsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UsersSoldProductsDto> users) {
        this.users = users;
    }

    public void addUser(UsersSoldProductsDto user) {
        if (this.users.contains(user)){
            return;
        }

        this.users.add(user);
    }

    public void increaseUserCountByOne(){
        this.setUsersCount(this.getUsersCount() + 1);
    }
}