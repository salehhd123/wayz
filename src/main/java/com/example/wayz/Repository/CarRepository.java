package com.example.wayz.Repository;

import com.example.wayz.Model.Car;
import com.example.wayz.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Car findCarById(Integer id);

    Car findCarByDriverId(Integer id);

}
