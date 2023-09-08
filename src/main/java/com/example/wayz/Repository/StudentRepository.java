package com.example.wayz.Repository;

import com.example.wayz.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findStudentById(Integer id);

    @Query("select c from student c where c.user.id=?1")
    Student findStudentByUserId(Integer id);

}
