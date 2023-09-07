package com.example.wayz.Service;

import com.example.wayz.Api.ApiException.ApiException;
import com.example.wayz.DTO.DriverDTO;
import com.example.wayz.DTO.DriverTripDTO;
import com.example.wayz.Model.DriverTrips;
import com.example.wayz.Repository.AuthRepository;
import com.example.wayz.Repository.DriverRepository;
import com.example.wayz.Repository.DriverTripsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverTripsService {

    private final DriverTripsRepository driverTripsRepository;
    private final DriverRepository driverRepository;


    // for users with ADMIN
    public List<DriverTrips> findAll(String role) {
        if(!role.equalsIgnoreCase("admin")) {
            throw new ApiException("invalid role.");
        }

        return driverTripsRepository.findAll();
    }

    // for users with DRIVER role
    public List<DriverTrips> findAllByDriverId(Integer id, String role) {
        if(!role.equalsIgnoreCase("driver")) {
            throw new ApiException("invalid role.");
        }

        return driverTripsRepository.findAllByDriverId(id);
    }

    public String addDriverTrip(Integer id, String role, DriverTripDTO driverTripDTO) {
        if(!role.equalsIgnoreCase("driver")) {
            throw new ApiException("invalid role.");
        }

        DriverTrips driverTrips = new DriverTrips();

//        driverTrips.setDay(driverTrips.getDay());
//        driverTrips.setType(driverTrips.getType());



        // Assign driver to a trip
        driverTrips.setDriver(driverRepository.findDriverById(id));


        return "driver trip have been added.";
    }
}
