package bdd.step_definitions;

import com.test.drivers.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Before(value = "@createDriver")
    public void createDriver() throws Exception {
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory.createDriver(browser);
    }

    @After(value = "@removeDriver")
    public void removeDriver() {
        WebDriverFactory.quitDriver();
    }
}
