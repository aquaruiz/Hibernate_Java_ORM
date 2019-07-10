package json.exercise.service;

import java.io.FileNotFoundException;

public interface UserService {
    long getRecordsCount();

    void seedUsers() throws FileNotFoundException;

}
