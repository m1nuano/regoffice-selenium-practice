import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class CreateApplicationTest {

    @Test
    void CreateMarriageApplication(){
        // init
        String username = "user";
        String password = "senlatest";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // 0
        driver.get("https://" + username + ":" + password + "@regoffice.senla.eu/");
        WebElement chooseApplicationOption = driver.findElement(By.xpath("//button[contains(text(), 'Войти как пользователь')]"));
        chooseApplicationOption.click();
        // 1
        WebElement applicantLastName = driver.findElement(By.xpath("//input[@id='TextInputField-1']"));
        WebElement applicantFirstName = driver.findElement(By.xpath("//input[@id='TextInputField-2']"));
        WebElement applicantMiddleName = driver.findElement(By.xpath("//input[@id='TextInputField-3']"));
        WebElement applicantTelephoneNumber = driver.findElement(By.xpath("//input[@id='TextInputField-4']"));
        WebElement applicantPassportNumber = driver.findElement(By.xpath("//input[@id='TextInputField-5']"));
        WebElement applicantResidentialAddress = driver.findElement(By.xpath("//input[@id='TextInputField-6']"));

        applicantLastName.sendKeys("aaaaa");
        applicantFirstName.sendKeys("aaaaa");
        applicantMiddleName.sendKeys("aaaaa");
        applicantTelephoneNumber.sendKeys("12345678");
        applicantPassportNumber.sendKeys("12345678");
        applicantResidentialAddress.sendKeys("12345678");

        WebElement chooseOptionNext = driver.findElement(By.xpath("//button[contains(text(), 'Далее')]"));
        chooseOptionNext.click();
        // 2
        WebElement chooseApplicationType = driver.findElement(By.xpath("//button[contains(text(), 'Регистрация брака')]"));
        chooseApplicationType.click();
        // 3
        WebElement citizenLastName = driver.findElement(By.xpath("//input[@id='TextInputField-7']"));
        WebElement citizenFirstName = driver.findElement(By.xpath("//input[@id='TextInputField-8']"));
        WebElement citizenMiddleName = driver.findElement(By.xpath("//input[@id='TextInputField-9']"));
        WebElement citizenDateOfBirth = driver.findElement(By.xpath("//input[@id='TextInputField-10']"));
        WebElement citizenNumberOfPassports = driver.findElement(By.xpath("//input[@id='TextInputField-11']"));
        WebElement citizenGender = driver.findElement(By.xpath("//input[@id='TextInputField-12']"));
        WebElement citizenResidenceAddress = driver.findElement(By.xpath("//input[@id='TextInputField-13']"));

        citizenLastName.sendKeys("aaaaa");
        citizenFirstName.sendKeys("aaaaa");
        citizenMiddleName.sendKeys("aaaaa");
        citizenDateOfBirth.sendKeys("1111-11-11");
        citizenNumberOfPassports.sendKeys("aaaaa");
        citizenGender.sendKeys("aaaa");
        citizenResidenceAddress.sendKeys("aaaaa");

        WebElement chooseOptionNext2 = driver.findElement(By.xpath("//button[contains(text(), 'Далее')]"));
        chooseOptionNext2.click();
        // 4
        WebElement dateOfRegistration = driver.findElement(By.xpath("//input[@id='TextInputField-14']"));
        WebElement newFamily = driver.findElement(By.xpath("//input[@id='TextInputField-15']"));
        WebElement lastNameOfSpouse = driver.findElement(By.xpath("//input[@id='TextInputField-16']"));
        WebElement firstNameOfSpouse = driver.findElement(By.xpath("//input[@id='TextInputField-17']"));
        WebElement middleNameOfSpouse = driver.findElement(By.xpath("//input[@id='TextInputField-18']"));
        WebElement dateOfBirthOfSpouse = driver.findElement(By.xpath("//input[@id='TextInputField-19']"));
        WebElement passportNumberOfSpouse = driver.findElement(By.xpath("//input[@id='TextInputField-20']"));

        dateOfRegistration.sendKeys("1111-11-11");
        newFamily.sendKeys("11111");
        lastNameOfSpouse.sendKeys("aaaaa");
        firstNameOfSpouse.sendKeys("aaaaa");
        middleNameOfSpouse.sendKeys("aaaaa");
        dateOfBirthOfSpouse.sendKeys("1111-11-11");
        passportNumberOfSpouse.sendKeys("11111");

        WebElement endThisApplication = driver.findElement(By.xpath("//button[contains(text(), 'Завершить')]"));
        endThisApplication.click();
        // 5 - проверка
        WebElement checkIfThisApplicationSubmitted = driver.findElement(By.xpath("//span[contains(text(), '№')]"));

        /*
        Проверил на наличие созданной заявки, ибо regoffice выдает либо №***** заявки или же null в случае ошибки, поэтому выбрал такой вариант прохождения теста
         */
        assert checkIfThisApplicationSubmitted.isDisplayed();

        driver.quit();
    }
}
