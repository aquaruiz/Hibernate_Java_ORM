package com.bakalov.userSystem.repositories;

import com.bakalov.userSystem.enteties.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album,Integer> {
}
