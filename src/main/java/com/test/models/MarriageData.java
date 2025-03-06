package com.test.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MarriageData {
    private final String dateOfRegistration;
    private final String newLastName;
    private final String lastNameOfSpouse;
    private final String firstNameOfSpouse;
    private final String middleNameOfSpouse;
    private final String dateOfBirthOfSpouse;
    private final String passportNumberOfSpouse;
}
