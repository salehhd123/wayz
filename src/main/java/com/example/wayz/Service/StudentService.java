package com.example.wayz.Service;


import com.example.wayz.Api.ApiException.ApiException;
import com.example.wayz.DTO.StudentDTO;
import com.example.wayz.Model.CreditCard;
import com.example.wayz.Model.Student;
import com.example.wayz.Model.User;
import com.example.wayz.Repository.AuthRepository;
import com.example.wayz.Repository.CreditCardRepository;
import com.example.wayz.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CreditCardRepository creditCardRepository;


    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    public void updateStudent(Integer id, StudentDTO studentDTO){
        Student student = studentRepository.findStudentById(id);
        studentDTO.setUsername(student.getUser().getUsername());
        studentDTO.setPassword(student.getUser().getPassword());
        studentDTO.setName(student.getName());
        studentDTO.setUniversity(student.getUniversity());
        studentDTO.setHomeGoogleMapUrl(student.getHomeGoogleMapUrl());
        studentRepository.save(student);
    }


    public void deleteStudent(Integer id){
        Student student = studentRepository.findStudentById(id);
        if (student==null){
            throw new ApiException("Student with ID " + id + " not found");
        }
        studentRepository.delete(student);
    }

}
