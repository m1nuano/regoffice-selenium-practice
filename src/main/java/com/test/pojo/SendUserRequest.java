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
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("personalLastName")
    private String personalLastName;
    @JsonProperty("personalFirstName")
    private String personalFirstName;
    @JsonProperty("personalMiddleName")
    private String personalMiddleName;
    @JsonProperty("personalPhoneNumber")
    private String personalPhoneNumber;
    @JsonProperty("personalNumberOfPassport")
    private String personalNumberOfPassport;
    @JsonProperty("personalAddress")
    private String personalAddress;
    @JsonProperty("citizenLastName")
    private String citizenLastName;
    @JsonProperty("citizenFirstName")
    private String citizenFirstName;
    @JsonProperty("citizenMiddleName")
    private String citizenMiddleName;
    @JsonProperty("citizenBirthDate")
    private String citizenBirthDate;
    @JsonProperty("citizenNumberOfPassport")
    private String citizenNumberOfPassport;
    @JsonProperty("citizenGender")
    private String citizenGender;
    @JsonProperty("citizenAddress")
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
    @JsonProperty("dateOfMarriage")
    private String dateOfMarriage;
    @JsonProperty("newLastName")
    private String newLastName;
    @JsonProperty("anotherPersonLastName")
    private String anotherPersonLastName;
    @JsonProperty("anotherPersonFirstName")
    private String anotherPersonFirstName;
    @JsonProperty("anotherPersonMiddleName")
    private String anotherPersonMiddleName;
    @JsonProperty("birth_of_anotoherPerson")
    private String birthOfAnotherPerson;
    @JsonProperty("anotherPersonPassport")
    private String anotherPersonPassport;
    @JsonProperty("death_dateOfDeath")
    private String deathDateOfDeath;
    @JsonProperty("death_placeOfDeath")
    private String deathPlaceOfDeath;
}