package com.example.wayz.Repository;

import com.example.wayz.Model.Driver;
import com.example.wayz.Model.DriverTrips;
import com.example.wayz.Model.UserTrips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTripsRepository extends JpaRepository<UserTrips, Integer> {



    @Query("SELECT ut.driver FROM user_trips ut WHERE ut.status = 'completed'")
    List<Driver> findAllFreeDrivers();



    UserTrips findUserTripsById(Integer userTripId);
}
