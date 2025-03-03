package com.test.models;

import lombok.Getter;

@Getter
public class MarriageData {
    private final String dateOfRegistration;
    private final String newFamily;
    private final String lastNameOfSpouse;
    private final String firstNameOfSpouse;
    private final String middleNameOfSpouse;
    private final String dateOfBirthOfSpouse;
    private final String passportNumberOfSpouse;

    public MarriageData(String dateOfRegistration, String newFamily, String lastNameOfSpouse,
                        String firstNameOfSpouse, String middleNameOfSpouse,
                        String dateOfBirthOfSpouse, String passportNumberOfSpouse) {
        this.dateOfRegistration = dateOfRegistration;
        this.newFamily = newFamily;
        this.lastNameOfSpouse = lastNameOfSpouse;
        this.firstNameOfSpouse = firstNameOfSpouse;
        this.middleNameOfSpouse = middleNameOfSpouse;
        this.dateOfBirthOfSpouse = dateOfBirthOfSpouse;
        this.passportNumberOfSpouse = passportNumberOfSpouse;
    }
}
