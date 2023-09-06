package com.example.wayz.Repository;

import com.example.wayz.Model.StudentTrips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StudentTripsRepository extends JpaRepository<StudentTrips,Integer> {


    StudentTrips findStudentTripsById(Integer id);

    @Query("select st from StudentTrips st where lower(st.type) = lower(:type)")
    List<StudentTrips> findByTypeIgnoreCase(String type);


    @Query("select count(st) / (DATEDIFF(max(st.timestamp), min(st.timestamp)) + 1) from StudentTrips st")
    Double calculateAverageTripsPerDay();

    // query to find student trips within a date range
    @Query("select st from StudentTrips st where st.timestamp between :startDate and :endDate")
    List<StudentTrips> findTripsWithinDateRange(LocalDateTime startDate, LocalDateTime endDate);


    @Query("select st from StudentTrips st where st.student.id = :studentId and lower(st.type) = lower(:type) ")
    List<StudentTrips> findTripsByStudentIdAndType(Integer studentId, String type);

}
