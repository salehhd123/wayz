package com.example.wayz.Repository;

import com.example.wayz.Model.UserTrips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTripsRepository extends JpaRepository<UserTrips, Integer> {



}
