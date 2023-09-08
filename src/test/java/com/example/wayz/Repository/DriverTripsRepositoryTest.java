package com.example.wayz.Repository;

import com.example.wayz.Model.Car;
import com.example.wayz.Model.Driver;
import com.example.wayz.Model.DriverTrips;
import com.example.wayz.Model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DriverTripsRepositoryTest {

    @Autowired
    DriverTripsRepository driverTripsRepository;
    @Autowired
    DriverRepository driverRepository;

    DriverTrips driverTrips;


    User user;

    Driver driver;

    DriverTrips driverTrips1;
    DriverTrips driverTrips2;




    @BeforeEach
    void setUp() {
        user = new User(null,"0501111111","12345","DRIVER",
                null,null,null);

         driver=new Driver(null,"bakr","pending",null,null,null,null,5,user,null,null,null);
        driverRepository.save(driver);

        driverTrips1=new DriverTrips(null,"umm Al-qura", LocalDateTime.now(),LocalDateTime.now().plusHours(1),driver);
        driverTrips2=new DriverTrips(null,"umm Al-qura", LocalDateTime.now(),LocalDateTime.now().plusHours(2),driver);
    }

    @Test
    void findAllByDriverId() {
        driverTripsRepository.save(driverTrips1);


        List<DriverTrips> driverTrips=driverTripsRepository.findAllByDriverId(driver.getId());
        Assertions.assertThat(driverTrips.get(0).getDriver().getId()).isEqualTo(driver.getId());
    }
}