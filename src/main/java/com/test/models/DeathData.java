package com.test.models;

import lombok.Getter;

@Getter
public class DeathData {
    private final String dateOfDeath;
    private final String placeOfDeath;

    public DeathData(String dateOfDeath, String placeOfDeath) {
        this.dateOfDeath = dateOfDeath;
        this.placeOfDeath = placeOfDeath;
    }
}
