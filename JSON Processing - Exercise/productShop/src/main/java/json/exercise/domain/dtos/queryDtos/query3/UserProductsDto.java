package json.exercise.domain.dtos.queryDtos.query3;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class UserProductsDto {
    @Expose
    private Integer usersCount;
    @Expose
    private List<UsersSoldProductsDto> users;

    public UserProductsDto() {
        this.users = new ArrayList<>();
        this.usersCount = 0;
    }

    public UserProductsDto(Integer usersCount, List<UsersSoldProductsDto> users) {
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