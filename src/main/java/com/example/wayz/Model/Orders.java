package com.example.wayz.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "Number of Trips can not be empty")
    @Column(nullable = false)
    private Integer numberTrips;

    @NotNull(message = "Trip Price can't be empty")
    @Column(nullable = false)
    private Integer tripPrice;


    @Pattern(message = "the status must be one of `paid`, `unpaid`. ", regexp = "(?i)\\b(paid|unpaid)\\b?")
    @Column(columnDefinition = "varchar(255) not null default 'unpaid' check (status in ('paid', 'unpaid')) ")
    private String status;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP) // convert from database timestamp to java LocalDateTime
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Student student;

}
