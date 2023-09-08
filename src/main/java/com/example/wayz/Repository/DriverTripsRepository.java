package com.example.wayz.Repository;

import com.example.wayz.Model.Driver;
import com.example.wayz.Model.DriverTrips;
import com.example.wayz.Model.Student;
import com.example.wayz.Model.TopDrivers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverTripsRepository extends JpaRepository<DriverTrips, Integer> {

    // TODO: need testing findAllByDriverId
    List<DriverTrips> findAllByDriverId(Integer driverId);


    @Query("select c.driver, count (c) as tripsCount from driver_trips c order by count(c.driver.id) DESC limit 10")
    List<TopDrivers> findTheMostDriver();


    DriverTrips findDriverTripsById(Integer id);
}
