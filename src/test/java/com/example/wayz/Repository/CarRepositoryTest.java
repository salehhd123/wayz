package com.example.wayz.Repository;

import com.example.wayz.Model.Car;
import com.example.wayz.Model.Driver;
import com.example.wayz.Model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    Car car;

    Car car1;

    User user1;

    @BeforeEach
    void setUp() {
        user1 = new User(null,"0501111111","Abcdefghi_#12345","DRIVER",
                null,null,null);

        Driver driver=new Driver(null,"bakr","pending",5,user1,null,null,null,null);
        car1 = new Car(null, 2017, 5, "abe 123", "Sedan", driver);
    }

    @Test
    void findCarByDriverId() {
        carRepository.save(car1);

        car=carRepository.findCarByDriverId(car1.getDriver().getId());
        Assertions.assertThat(car).isEqualTo(car1);
    }
}