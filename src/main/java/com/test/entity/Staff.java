package com.test.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staffid", columnDefinition = "BIGINT")
    private Long staffId;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @Column(name = "middlename")
    private String middleName;
    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;
    @Column(name = "passportnumber")
    private String passportNumber;
    @Column(name = "phonenumber")
    private String phoneNumber;
}
