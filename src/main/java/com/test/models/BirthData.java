package com.test.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BirthData {
    private final String birthAddress;
    private final String mother;
    private final String father;
    private final String grandma;
    private final String grandpa;
}
