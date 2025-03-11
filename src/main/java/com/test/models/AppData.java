package com.test.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AppData {
    private final String name;
    private final String phone;
    private final String passport;
    private final String address;
    private final String date;
    private final String gender;
    private final String mode;

    private final BirthData birthData;
    private final DeathData deathData;
    private final MarriageData marriageData;
}