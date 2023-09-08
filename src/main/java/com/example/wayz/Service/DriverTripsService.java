package com.example.wayz.Service;

import com.example.wayz.Api.ApiException.ApiException;
import com.example.wayz.DTO.DriverDTO;
import com.example.wayz.DTO.DriverTripDTO;
import com.example.wayz.Model.DriverTrips;
import com.example.wayz.Model.User;
import com.example.wayz.Repository.AuthRepository;
import com.example.wayz.Repository.DriverRepository;
import com.example.wayz.Repository.DriverTripsRepository;
import com.example.wayz.Utils.TimeRange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
    public List<DriverTrips> findAllByDriverId(User user) {
        if(!user.getRole().equalsIgnoreCase("driver")) {
            throw new ApiException("invalid role.");
        }

        return driverTripsRepository.findAllByDriverId(user.getId());
    }

    public HashMap<String, DriverTrips> addDriverTrip(User user, DriverTripDTO driverTripDTO) {
        if(!user.getRole().equalsIgnoreCase("driver")) {
            throw new ApiException("invalid role.");
        }

        DriverTrips driverTrips = new DriverTrips();

        driverTrips.setUniversity(driverTripDTO.getUniversity());

        TimeRange[][] timeRanges = generateRanges();

        int index = driverTripDTO.getTripIndex();

        TimeRange timeRange = new TimeRange();

        if(driverTripDTO.getShift().equalsIgnoreCase("morning")) {
            timeRange = timeRanges[0][index];
        }

        if(driverTripDTO.getShift().equalsIgnoreCase("evening")) {
            timeRange = timeRanges[1][index];
        }

        driverTrips.setStartTime(timeRange.getStart());
        driverTrips.setEndTime(timeRange.getEnd());

        // Assign driver to a trip
        driverTrips.setDriver(driverRepository.findDriverById(user.getId()));

        driverTripsRepository.save(driverTrips);



        HashMap<String, DriverTrips> driverTripsHashMap = new HashMap<>();
        driverTripsHashMap.put("driverTrip", driverTrips);

        return driverTripsHashMap;
    }


    public HashMap<String, Object> deleteDriverTrip(User user, Integer driverTripId) {
        if(!user.getRole().equalsIgnoreCase("driver")) {
            throw new ApiException("invalid role.");
        }

        DriverTrips driverTrips = driverTripsRepository.findDriverTripsById(driverTripId);

        if(driverTrips == null) {
            throw new ApiException("driver trip not found.");
        }

        if(!Objects.equals(driverTrips.getDriver().getId(), user.getId())) {
            throw new ApiException("you don't own this trip.");
        }

        driverTripsRepository.deleteById(driverTripId);


        HashMap<String, Object> driverTripsHashMap = new HashMap<>();
        driverTripsHashMap.put("message", "driver trip have been deleted.");
        driverTripsHashMap.put("driverTrip", driverTrips);

        return driverTripsHashMap;
    }


    // index 0 -> morning ranges.
    // index 1 -> evening ranges.
    public TimeRange[][] generateRanges() {
        // morning

        TimeRange[] morningTimeRanges = new TimeRange[3];

        morningTimeRanges[0] = new TimeRange("06:00 - 10:29", LocalDateTime.now().withHour(6).withMinute(0), LocalDateTime.now().withHour(10).withMinute(29));
        morningTimeRanges[1] = new TimeRange("10:30 - 12:29", LocalDateTime.now().withHour(10).withMinute(30), LocalDateTime.now().withHour(12).withMinute(29));
        morningTimeRanges[2] = new TimeRange("12:30 - 14:29", LocalDateTime.now().withHour(12).withMinute(30), LocalDateTime.now().withHour(14).withMinute(29));

        // evening
        TimeRange[] eveningTimeRanges = new TimeRange[3];

        eveningTimeRanges[0] = new TimeRange("14:30 - 16:29", LocalDateTime.now().withHour(14).withMinute(30), LocalDateTime.now().withHour(16).withMinute(29));
        eveningTimeRanges[1] = new TimeRange("16:30 - 18:29", LocalDateTime.now().withHour(16).withMinute(30), LocalDateTime.now().withHour(18).withMinute(29));
        eveningTimeRanges[2] = new TimeRange("18:30 - 20:30", LocalDateTime.now().withHour(18).withMinute(0), LocalDateTime.now().withHour(20).withMinute(30));

        TimeRange[][] allShifts = new TimeRange[][]{morningTimeRanges, eveningTimeRanges};


        return allShifts;


        //------------------------------------//


        // currently the agreed ranges are:

        // Morning:
        // 06:00 - 10:30
        // 10:00 - 12:30
        // 12:30 - 14:30

        // Evening:
        // 14:00 - 16:30
        // 16:00 - 18:30
        // 18:00 - 20:30
    }
}
