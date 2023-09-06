package com.example.wayz.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Trip Price can't be empty")
    @Column(nullable = false)
    private Integer tripPrice;

    @NotNull(message = "Days can't be empty")
    @Column(nullable = false)
    @Positive(message = "Days should be Numbers")
    private Integer days;

    @CreationTimestamp
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Student student;

}
