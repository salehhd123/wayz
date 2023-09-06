package com.example.wayz.Repository;

import com.example.wayz.Model.StudentTrips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTripsRepository extends JpaRepository<StudentTrips,Integer> {
}
