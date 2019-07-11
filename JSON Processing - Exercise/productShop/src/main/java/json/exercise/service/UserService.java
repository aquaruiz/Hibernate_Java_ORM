package json.exercise.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface UserService {
    long getRecordsCount();

    void seedUsers() throws FileNotFoundException;

    void getUsersAndSoldProducts() throws IOException;
}
