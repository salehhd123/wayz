package com.example.wayz.Controller;


import com.example.wayz.Model.UserTrips;
import com.example.wayz.Service.UserTripsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-trips")
public class UserTripsController {

    private final UserTripsService userTripsService;


    @GetMapping("/get")
    public ResponseEntity<List<UserTrips>> findAll() {
        return ResponseEntity.ok(userTripsService.findAll());
    }


    @PostMapping("/generate")
    public ResponseEntity<String> generate() {
        userTripsService.createUserTrips();
        return ResponseEntity.ok("generated trips.");
    }
}
