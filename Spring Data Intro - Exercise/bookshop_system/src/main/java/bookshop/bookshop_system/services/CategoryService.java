package bookshop.bookshop_system.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

public interface CategoryService {
    void seedCategory() throws IOException;
}
