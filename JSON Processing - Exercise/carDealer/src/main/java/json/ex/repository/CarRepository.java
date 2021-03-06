package json.ex.repository;

import json.ex.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> getAllByMake(String make);

    List<Car> getAllByMakeOrderByModelAscTravelledDistanceDesc(String make);
}
