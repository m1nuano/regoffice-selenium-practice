package com.test.test_data;

import com.test.models.AppData;
import com.test.models.BirthData;
import com.test.models.DeathData;
import com.test.models.MarriageData;
import org.testng.annotations.DataProvider;

import static com.test.constants.TestConstants.*;

public interface DataProviders {

    @DataProvider(name = "sendEveryUserRequest")
    static Object[][] sendEveryUserRequest() {
        return new Object[][]{
                {
                        MODE_WEDDING,
                        new AppData(
                                TEST_NAME,
                                TEST_PHONE,
                                TEST_PASSPORT,
                                TEST_ADDRESS,
                                TEST_DATE,
                                TEST_GENDER,
                                MODE_WEDDING,
                                null,
                                null,
                                new MarriageData(TEST_DATE, TEST_LASTNAME, TEST_NAME, TEST_NAME, TEST_NAME, TEST_DATE, TEST_PASSPORT)
                        )
                },
                {
                        MODE_DEATH,
                        new AppData(
                                TEST_NAME,
                                TEST_PHONE,
                                TEST_PASSPORT,
                                TEST_ADDRESS,
                                TEST_DATE,
                                TEST_GENDER,
                                MODE_DEATH,
                                null,
                                new DeathData(TEST_DATE, TEST_ADDRESS),
                                null
                        )
                },
                {
                        MODE_BIRTH,
                        new AppData(
                                TEST_NAME,
                                TEST_PHONE,
                                TEST_PASSPORT,
                                TEST_ADDRESS,
                                TEST_DATE,
                                TEST_GENDER,
                                MODE_BIRTH,
                                new BirthData(TEST_ADDRESS, TEST_NAME, TEST_NAME, TEST_NAME, TEST_NAME),
                                null,
                                null
                        )
                }
        };
    }
}
