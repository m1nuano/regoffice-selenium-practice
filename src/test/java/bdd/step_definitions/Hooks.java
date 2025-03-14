package bdd.step_definitions;

import bdd.TestRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    TestRunner testRunner = new TestRunner();

    @Before(value = "@createDriver")
    public void createDriver() {
        testRunner.initDriver();
    }

    @After(value = "@removeDriver")
    public void removeDriver() {
        testRunner.quitDriver();
    }
}
