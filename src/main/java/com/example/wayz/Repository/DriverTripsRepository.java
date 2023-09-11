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



    @Query("SELECT dt.driver FROM driver_trips dt WHERE dt.shift = ?1 AND dt.startTime BETWEEN dt.startTime AND dt.endTime ORDER BY dt.id ASC LIMIT 5")
    List<Driver> findFirstFiveDriversInShift(String shift);


    @Query("SELECT dt.driver FROM driver_trips dt WHERE dt.shift = ?1 AND dt.startTime BETWEEN dt.startTime AND dt.endTime AND dt.id > ?1 ORDER BY dt.id ASC LIMIT 5")
    List<Driver> findNextFiveDrivers(Integer id);


    @Query("select c.driver.name as tripsCount from driver_trips c order by count(c.driver.id) DESC limit 10")
    List<String> findTheMostDriver();


    DriverTrips findDriverTripsById(Integer id);
}
