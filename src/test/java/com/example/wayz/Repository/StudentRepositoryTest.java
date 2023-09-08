package com.example.wayz.Repository;

import com.example.wayz.Model.Student;
import com.example.wayz.Model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {

    @Autowired
    AuthRepository authRepository;
    @Autowired
    StudentRepository studentRepository;

    private User user;
    private Student student;

    @BeforeEach
    void setUp() {
        user = new User(null, "0522222222", "abc1234", "USER", null, null, null);
        student = new Student(null, "saleh", "noura", "googlemaps.com/1234", 0, 0, user, null, null, null, null);
        authRepository.save(user);
        studentRepository.save(student);
    }

    @Test
    void findStudentById() {

        var studentCheck = studentRepository.findStudentById(student.getId());

        Assertions.assertThat(studentCheck).isEqualTo(student);
    }

    @Test
    void findStudentByUser() {
        var studentCheck = studentRepository.findStudentByUserId(student.getUser().getId());

        Assertions.assertThat(studentCheck).isEqualTo(student);
    }
}