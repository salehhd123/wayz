package com.example.wayz.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String media;

    @NotEmpty(message = "the description field is required.")
    @Column(nullable = false)
    private String description;

    @Column(columnDefinition = "varchar(255) not null default 'pending'")
    private String status="pending";


    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Driver driver;


}
