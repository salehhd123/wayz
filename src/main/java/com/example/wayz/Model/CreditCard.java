package com.example.wayz.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "the number field is required.")
    @Column(nullable = false)
    private String number;

    @NotEmpty(message = "the name field is required.")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "the ccv field is required.")
    @Column(nullable = false)
    private String ccv;



    @OneToOne
    @MapsId
    @JsonIgnore // prevent infinite loop
    private Student student;

}
