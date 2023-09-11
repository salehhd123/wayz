package com.example.wayz.Repository;

import com.example.wayz.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {

    Driver findDriverById(Integer id);

//
//    @Query("SELECT d FROM driver d ORDER BY ASC d.id LIMIT 5")
//    List<Driver> findFirstFiveDrivers();
//
//
//    @Query("SELECT d FROM driver d WHERE d.id > ?1 ORDER BY d.id LIMIT 5")
//    List<Driver> findNextFiveDrivers(Integer id);

//    @Query("select count (c) from driver c where c.Reports='approved'")
//    Integer countApproved();

}
