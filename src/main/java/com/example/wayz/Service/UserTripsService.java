package com.example.wayz.Service;

import com.example.wayz.Api.ApiException.ApiException;
import com.example.wayz.Model.Driver;
import com.example.wayz.Model.StudentTrips;
import com.example.wayz.Model.User;
import com.example.wayz.Model.UserTrips;
import com.example.wayz.Repository.DriverRepository;
import com.example.wayz.Repository.DriverTripsRepository;
import com.example.wayz.Repository.StudentTripsRepository;
import com.example.wayz.Repository.UserTripsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserTripsService {

    private final UserTripsRepository userTripsRepository;
    private final StudentTripsRepository studentTripsRepository;
    private final DriverTripsRepository driverTripsRepository;
    private final DriverRepository driverRepository;



    public List<UserTrips> findAll() {
        return userTripsRepository.findAll();
    }


    public void createUserTrips() {

        List<StudentTrips> studentTrips = studentTripsRepository.findAllStudentTripsBetweenTimestampAndEndTime("going");
        List<Driver> drivers = driverTripsRepository.findFirstFiveDriversInShift("morning");


        startProcess(drivers, studentTrips);





//        List<StudentTrips> studentTrips = studentTripsRepository.findAll();
//        List<Driver> drivers = driverRepository.findAll();
//
//
//        // O(n^2)
//
//        for (Driver driver : drivers) {
//            int counter = driver.getCar().getSeats();
//
//            UserTrips userTrips = new UserTrips();
//            userTrips.setDriver(driver);
//
//            for (StudentTrips studentTrips1 : studentTrips) {
//                if(counter == 0) {
//                    break;
//                }
//
//                userTrips.getStudentTrips().add(studentTrips1);
//
//                counter--;
//            }
//
//            userTripsRepository.save(userTrips);
//        }


    }


    public void startProcess(List<Driver> drivers, List<StudentTrips> studentTrips) {
        for (Driver driver: drivers) {

            if(studentTrips.isEmpty()) {
                break;
            }

            int counter = driver.getCar().getSeats();

            UserTrips userTrips = new UserTrips();
            userTrips.setStatus("in-progress");
            userTrips.setDriver(driver);

            for (StudentTrips studentTrips1 : studentTrips) {


                if(userTrips.getStudentTrips() == null) {
                    userTrips.setStudentTrips(Set.of());
                }

                userTripsRepository.save(userTrips);

                if(counter == 0) {
                    break;
                }


//                userTrips.getStudentTrips().add(studentTrips1);

                studentTrips1.setUserTrips(userTrips);
                studentTripsRepository.save(studentTrips1);


                counter--;
            }

        }
    }




    public String switchStatusToComplete(User user, Integer userTripId) {

        UserTrips userTrips = userTripsRepository.findUserTripsById(userTripId);

        if(userTrips == null) {
            throw new ApiException("user trip not found.");
        }

        if(!Objects.equals(userTrips.getDriver().getId(), user.getId())) {
            throw new ApiException("you can not edit this trip.");
        }

        userTrips.setStatus("completed");

        userTripsRepository.save(userTrips);

        return "user trip with id: " + userTripId + " have been completed.";
    }


}
