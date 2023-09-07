package com.example.wayz.Service;


import com.example.wayz.Repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;



}
