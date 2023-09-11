package com.example.wayz.Repository;

import com.example.wayz.Model.Driver;
import com.example.wayz.Model.DriverTrips;
import com.example.wayz.Model.Student;
import com.example.wayz.Model.TopDrivers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DriverTripsRepository extends JpaRepository<DriverTrips, Integer> {

    // TODO: need testing findAllByDriverId
    List<DriverTrips> findAllByDriverId(Integer driverId);



//    AND (dt.driver.car.currentPassengers <= dt.driver.car.seats OR dt.driver.car.currentPassengers = 0)

    @Query("SELECT dt FROM driver_trips dt WHERE dt.driver.availability != 'busy' AND (dt.driver.car.currentPassengers <= dt.driver.car.seats OR dt.driver.car.currentPassengers = 0) AND dt.shift = :shift AND dt.startTime > :startTime and not (dt.endTime < :endTime) ORDER BY dt.id ASC LIMIT 10")
    List<DriverTrips> findDriversByShiftAndTimeWithLimit(String shift, LocalDateTime startTime, LocalDateTime endTime);


    @Query("SELECT dt FROM driver_trips dt WHERE dt.driver.availability != 'busy' AND dt.university = ?4 AND dt.driver.car.currentPassengers <= dt.driver.car.seats AND dt.shift = ?1 AND dt.startTime <= ?2 AND dt.endTime >= ?3 ORDER BY dt.id ASC LIMIT 1")
    List<DriverTrips> findOneDriverByShiftAndTimeWithUniversity(String shift, LocalDateTime startTime, LocalDateTime endTime, String university);


//    @Query("SELECT dt.driver FROM driver_trips dt WHERE dt.shift = ?1 AND ?2 BETWEEN ?2 AND ?3 AND dt.id > ?1 ORDER BY dt.id ASC LIMIT 5")
//    List<Driver> findNextFiveDrivers(Integer id, LocalDateTime startTime, LocalDateTime endTime);
//

    @Query("select c.driver.name as tripsCount from driver_trips c order by count(c.driver.id) DESC limit 10")
    List<String> findTheMostDriver();


    DriverTrips findDriverTripsById(Integer id);
}
