package com.example.wayz.Controller;

import com.example.wayz.Service.StudentTripsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student-trips")
public class StudentTripsController {

    private final StudentTripsService studentTripsService;

}
