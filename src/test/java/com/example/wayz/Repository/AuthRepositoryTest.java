package com.example.wayz.Repository;

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
class AuthRepositoryTest {

    @Autowired
    AuthRepository authRepository;

    private User user, userAdmin;

    @BeforeEach
    void setUp() {
        userAdmin = new User(null, "05333333333", "abc1234", "ADMIN", null, null, null);
        user = new User(null, "0522222222", "abc1234", "USER", null, null, null);
        authRepository.save(user);
        authRepository.save(userAdmin);
    }

    @Test
    void findUserByUsername() {
        var userCheck = authRepository.findUserByUsername(user.getUsername());

        Assertions.assertThat(userCheck).isEqualTo(user);

    }

    @Test
    void findUserById() {

        var userCheck = authRepository.findUserById(user.getId());

        System.out.println("***" + user.getId());
        Assertions.assertThat(userCheck).isEqualTo(user);
    }

    @Test
    void findAdmin() {

        var userCheck = authRepository.findAdmin(userAdmin.getId());

        System.out.println("***" + user.getId());
        Assertions.assertThat(userCheck.getRole()).isEqualTo("ADMIN");
    }

}
//class AuthRepositoryTest {
//
//    @Test
//    void findUserByUsername() {
//    }
//
//    @Test
//    void findUserById() {
//    }
//
//    @Test
//    void findAdmin() {
//    }
//}