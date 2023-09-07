package com.example.wayz.DTO;


import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Integer student_id;

    @NotNull(message = "Trip Price can't be empty")
    @Column(nullable = false)
    private Integer tripPrice;

    @NotNull(message = "Days can't be empty")
    @Column(nullable = false)
    @Positive(message = "Days should be Numbers")
    private Integer days;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP) // convert from database timestamp to java LocalDateTime
    private LocalDateTime createdAt;
}
