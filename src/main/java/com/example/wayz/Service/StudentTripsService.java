package com.example.wayz.Service;

import com.example.wayz.Api.ApiException.ApiException;
import com.example.wayz.Model.Student;
import com.example.wayz.Model.StudentTrips;
import com.example.wayz.Model.User;
import com.example.wayz.Repository.AuthRepository;
import com.example.wayz.Repository.StudentRepository;
import com.example.wayz.Repository.StudentTripsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentTripsService {

    private final StudentTripsRepository studentTripsRepository;
    private final StudentRepository studentRepository;

    public List<StudentTrips> getAllStudentTrips() {
        return studentTripsRepository.findAll();
    }

    public void createStudentTrip(StudentTrips studentTrip, User user) {
        studentTrip.setEndTime(studentTrip.getTimestamp().plusMinutes(30));
        Student student = studentRepository.findStudentById(user.getId());

        if(student.getStudentTrips() == null) {
            student.setStudentTrips(Set.of());
            studentRepository.save(student);
        }

        studentTrip.setStudent(student);

        studentTripsRepository.save(studentTrip);
    }

    public void updateStudentTrip(Integer id, StudentTrips studentTrip) {

        StudentTrips studentTrips = studentTripsRepository.findStudentTripsById(id);
        if (studentTrips == null) {
            throw new ApiException("Student Trips with ID " + id + " not found");
        }
        studentTrips.setDay(studentTrip.getDay());
        studentTrips.setType(studentTrip.getType());
        studentTripsRepository.save(studentTrips);
    }

    public void deleteStudentTrip(Integer id) {
        StudentTrips studentTrips = studentTripsRepository.findStudentTripsById(id);
        if (studentTrips == null) {
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


    public Double getAverageTripsPerDay() {
        return studentTripsRepository.calculateAverageTripsPerDay();
    }

    public List<StudentTrips> getTripsByStudentAndType(Integer studentId, String type) {
        return studentTripsRepository.findTripsByStudentIdAndType(studentId, type);
    }

}
