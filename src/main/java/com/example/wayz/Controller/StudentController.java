package com.example.wayz.Controller;

import com.example.wayz.Api.ApiResponse.ApiResponse;
import com.example.wayz.DTO.StudentDTO;
import com.example.wayz.Model.User;
import com.example.wayz.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get-all")
    public ResponseEntity getAllStudent() {
        return ResponseEntity.status(200).body(studentService.getAllStudent());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@AuthenticationPrincipal User user,@RequestBody @Valid StudentDTO studentDTO){
        studentService.updateStudent(user.getId(), studentDTO);
        return ResponseEntity.status(200).body("Student updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body("Student deleted successfully");
    }



}
