package com.example.wayz.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //// TODO add pattern
    @Column(columnDefinition = "varchar(255) not null default 'pending'")
    private String status;

    @Column(columnDefinition = "varchar(255) unique not null")
    private String driverLicenceImgPath;

    @Column(columnDefinition = "varchar(255) unique not null")
    private String carRegistrationImgPath;

    @Column(columnDefinition = "varchar(255) unique not null")
    private String driverImgPath;

    @Column(columnDefinition = "varchar(255) unique not null")
    private String govIdImgPath;


    @OneToOne
    @MapsId
    @JsonIgnore // prevent infinite loop
    private User user;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "driver")
    @PrimaryKeyJoinColumn
    private Car car;

}
