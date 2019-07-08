package com.bakalov.userSystem.repositories;

import com.bakalov.userSystem.enteties.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture,Integer> {
}
