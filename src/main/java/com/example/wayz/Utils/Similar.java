package com.example.wayz.Utils;

import com.example.wayz.Model.Driver;
import com.example.wayz.Model.DriverTrips;
import com.example.wayz.Model.Student;
import com.example.wayz.Model.StudentTrips;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Similar {
    private List<StudentTrips> similarStudentTrips;
    private List<StudentTrips> notSimilarStudentTrips;


    private String from;
    private String to;


    private Driver driver;
}
