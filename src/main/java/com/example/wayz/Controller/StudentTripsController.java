package com.example.wayz.Controller;

import com.example.wayz.Model.StudentTrips;
import com.example.wayz.Service.StudentTripsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student-trips")
public class StudentTripsController {

    private final StudentTripsService studentTripsService;

    @GetMapping("/get-all")
    public ResponseEntity getAllStudentTrips() {
        return ResponseEntity.status(200).body(studentTripsService.getAllStudentTrips());
    }

    @PostMapping("/add")
    public ResponseEntity addStudentTrip(@RequestBody @Valid StudentTrips studentTrip) {
        studentTripsService.createStudentTrip(studentTrip);
        return ResponseEntity.status(200).body("Student Trips added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudentTrip(
            @PathVariable Integer id, @RequestBody @Valid StudentTrips studentTrip) {
             studentTripsService.updateStudentTrip(id, studentTrip);
            return ResponseEntity.status(200).body("Student Trips updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudentTrip(@PathVariable Integer id) {
        studentTripsService.deleteStudentTrip(id);
        return ResponseEntity.status(200).body("Deleted Successfully");
    }

    @GetMapping("/getStudentTripById/{id}")
    public ResponseEntity getStudentTripById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(studentTripsService.getStudentTripById(id));
    }



    @GetMapping("/findByTypeIgnoreCase/{type}")
    public ResponseEntity getStudentTripsByTypeIgnoreCase(@PathVariable String type) {
        return ResponseEntity.status(200).body(studentTripsService.getTripsByType(type));
    }


    @GetMapping("/getAverageTripsPerDay")
    public ResponseEntity getAverageTripsPerDay() {
        return ResponseEntity.status(200).body(studentTripsService.getAverageTripsPerDay());
    }


    @GetMapping("/by-date-range")
    public ResponseEntity getTripsWithinDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate)
    {
        return ResponseEntity.status(200).body(studentTripsService.getTripsWithinDateRange(startDate, endDate));
    }

    @GetMapping("/TripsByStudentIdAndType/{studentId}/{type}")
    public ResponseEntity getTripsByStudentIdAndType(@PathVariable Integer studentId,@PathVariable String type ) {
        return ResponseEntity.status(200).body(studentTripsService.getTripsByStudentAndType(studentId,type));
    }

}
