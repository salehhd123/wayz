package com.example.wayz.Service;

import com.example.wayz.Api.ApiException.ApiException;
import com.example.wayz.Model.StudentTrips;
import com.example.wayz.Repository.StudentTripsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentTripsService {

    private final StudentTripsRepository studentTripsRepository;

    public List<StudentTrips> getAllStudentTrips() {
        return studentTripsRepository.findAll();
    }

    public void createStudentTrip(StudentTrips studentTrip) {
         studentTripsRepository.save(studentTrip);
    }

    public void updateStudentTrip(Integer id, StudentTrips studentTrip) {

        StudentTrips studentTrips=studentTripsRepository.findStudentTripsById(id);
        if (studentTrips==null) {
            throw new ApiException("Student Trips with ID " + id + " not found");
        }
        studentTrips.setDay(studentTrip.getDay());
        studentTrips.setType(studentTrip.getType());
        studentTripsRepository.save(studentTrips);
    }

    public void deleteStudentTrip(Integer id) {
        StudentTrips studentTrips=studentTripsRepository.findStudentTripsById(id);
        if (studentTrips==null){
            throw new ApiException("Student Trips with ID " + id + " not found");
        }
        studentTripsRepository.deleteById(id);
    }

    public StudentTrips getStudentTripById(Integer id) {
        return studentTripsRepository.findStudentTripsById(id);
    }


    public List<StudentTrips> getTripsByType(String type) {
        return studentTripsRepository.findByTypeIgnoreCase(type);
    }


    public Double getAverageTripsPerDay(){
        return studentTripsRepository.calculateAverageTripsPerDay();
    }

    public List<StudentTrips> getTripsWithinDateRange(String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);

        return studentTripsRepository.findTripsWithinDateRange(start, end);
    }

    public List<StudentTrips> getTripsByStudentAndType(Integer studentId, String type) {
        return studentTripsRepository.findTripsByStudentIdAndType(studentId, type);
    }

}
