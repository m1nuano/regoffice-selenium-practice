package com.test.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "applicants")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicantid", columnDefinition = "BIGINT")
    private Long applicantId;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @Column(name = "middlename")
    private String middleName;
    @Column(name = "passportnumber")
    private String passportNumber;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "registration_address")
    private String registrationAddress;
}
