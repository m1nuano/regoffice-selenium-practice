package com.test.models;

import lombok.Getter;

@Getter
public class BirthData {
    private final String birthAddress;
    private final String mother;
    private final String father;
    private final String grandma;
    private final String grandpa;

    public BirthData(String birthAddress, String mother, String father, String grandma, String grandpa) {
        this.birthAddress = birthAddress;
        this.mother = mother;
        this.father = father;
        this.grandma = grandma;
        this.grandpa = grandpa;
    }
}
