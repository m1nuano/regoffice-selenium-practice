package com.test.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendUserRequest {
    private String mode;
    private String personalLastName;
    private String personalFirstName;
    private String personalMiddleName;
    private String personalPhoneNumber;
    private String personalNumberOfPassport;
    private String personalAddress;
    private String citizenLastName;
    private String citizenFirstName;
    private String citizenMiddleName;
    private String citizenBirthDate;
    private String citizenNumberOfPassport;
    private String citizenGender;
    private String citizenAddress;
    @JsonProperty("birth_place")
    private String birthPlace;
    @JsonProperty("birth_mother")
    private String birthMother;
    @JsonProperty("birth_father")
    private String birthFather;
    @JsonProperty("birth_grandpa")
    private String birthGrandpa;
    @JsonProperty("birth_grandma")
    private String birthGrandma;
    private String dateOfMarriage;
    private String newLastName;
    private String anotherPersonLastName;
    private String anotherPersonFirstName;
    private String anotherPersonMiddleName;
    @JsonProperty("birth_of_anotoherPerson")
    private String birthOfAnotherPerson;
    private String anotherPersonPassport;
    @JsonProperty("death_dateOfDeath")
    private String deathDateOfDeath;
    @JsonProperty("death_placeOfDeath")
    private String deathPlaceOfDeath;
}