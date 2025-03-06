package com.test.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeathData {
    private final String dateOfDeath;
    private final String placeOfDeath;
}
