package com.example.wayz.Service;

import com.example.wayz.Repository.StudentTripsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentTripsService {

    private final StudentTripsRepository studentTripsRepository;
}
