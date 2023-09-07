package com.example.wayz.Repository;

import com.example.wayz.Model.DriverTrips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverTripsRepository extends JpaRepository<DriverTrips, Integer> {

    // TODO: need testing findAllByDriverId
    List<DriverTrips> findAllByDriverId(Integer driverId);

}
