package com.example.wayz.Controller;

import com.example.wayz.DTO.DriverTripDTO;
import com.example.wayz.Model.DriverTrips;
import com.example.wayz.Model.TopDrivers;
import com.example.wayz.Model.User;
import com.example.wayz.Service.DriverTripsService;
import com.example.wayz.Utils.TimeRange;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/v1/driver-trips")
@RequiredArgsConstructor
public class DriverTripsController {

    private final DriverTripsService driverTripsService;

    @GetMapping("/get")
    public ResponseEntity<List<DriverTrips>> findAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(driverTripsService.findAll(user.getRole()));
    }

    @GetMapping("/my-trips")
    public ResponseEntity<List<DriverTrips>> findMyTrips(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(driverTripsService.findAllByDriverId(user));
    }

    @GetMapping("/generate-ranges")
    public ResponseEntity<HashMap<String, Object>> showDate() {
        TimeRange[][] ranges = driverTripsService.generateRanges();
        HashMap<String, Object> output = new HashMap<>();

        output.put("morning", ranges[0]);
        output.put("evening", ranges[1]);

        return ResponseEntity.ok(output);
    }

    @PostMapping("/create")
    public ResponseEntity<HashMap<String, DriverTrips>> createDriverTrip(@AuthenticationPrincipal User user, @RequestBody @Valid DriverTripDTO driverTripDTO) {
        return ResponseEntity.ok(driverTripsService.addDriverTrip(user, driverTripDTO));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteDriverTrip(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        return ResponseEntity.ok(driverTripsService.deleteDriverTrip(user, id));
    }


    @GetMapping("/top-three")
    public ResponseEntity topThreeDriver() {
        return ResponseEntity.ok(driverTripsService.getTopDrivers());
    }

}
