package com.bakalov.userSystem.repositories;

import com.bakalov.userSystem.enteties.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Integer> {
}
