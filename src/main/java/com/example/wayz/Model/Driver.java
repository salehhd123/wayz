package com.example.wayz.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "driver_table")
public class Driver {

    @Id
    @GeneratedValue
    private Integer id;

    //// TODO add pattern
    @Column(columnDefinition = "varchar(255) not null, default 'pending'")
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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;


}
