package com.bakalov.userSystem.repositories;

import com.bakalov.userSystem.enteties.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town,Integer> {
}
