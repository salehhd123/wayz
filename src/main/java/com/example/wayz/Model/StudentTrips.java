package com.example.wayz.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentTrips {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "Day can't be empty")
    @Column(columnDefinition = "int",nullable = false)
    @Positive
    private Integer day;


    @NotEmpty(message = "type can't be empty")
    @Pattern(message = "the type can only be `going`, `return`.", regexp = "(?i)\\b(going|return)\\b?")
    @Column(columnDefinition = "varchar(9) not null check (type in ('going', 'return'))")
    private String type;

    @Temporal(TemporalType.TIMESTAMP) // convert from database timestamp type to java.time.LocalDateTime
    @Column(columnDefinition = "timestamp not null") // ensure default
    private LocalDateTime timestamp;



    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Student student;
}
