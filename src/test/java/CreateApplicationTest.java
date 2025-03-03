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
        String username = System.getenv("APP_USERNAME");
        String password = System.getenv("APP_PASSWORD");
        String baseUrl = "https://%s:%s@regoffice.senla.eu/";
        String formattedUrl = String.format(baseUrl, username, password);

        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.get(formattedUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String testName = "aaaaa";
        String testPhone = "12345678";
        String testPassport = "12345678";
        String testAddress = "12345678";
        String testDate = "1111-11-11";
        String testGender = "1111";
        String testFamilyName = "11111";

        WebElement chooseApplicationOption = driver.findElement(By.xpath("//button[contains(text(), 'Войти как пользователь')]"));
        chooseApplicationOption.click();

        WebElement applicantLastName = driver.findElement(By.xpath("//div[label[contains(text(), 'Фамилия')]]/following-sibling::input"));
        WebElement applicantFirstName = driver.findElement(By.xpath("//div[label[contains(text(), 'Имя')]]/following-sibling::input"));
        WebElement applicantMiddleName = driver.findElement(By.xpath("//div[label[contains(text(), 'Отчество')]]/following-sibling::input"));
        WebElement applicantTelephoneNumber = driver.findElement(By.xpath("//div[label[contains(text(), 'Телефон')]]/following-sibling::input"));
        WebElement applicantPassportNumber = driver.findElement(By.xpath("//div[label[contains(text(), 'Номер паспорта')]]/following-sibling::input"));
        WebElement applicantResidentialAddress = driver.findElement(By.xpath("//div[label[contains(text(), 'Адрес')]]/following-sibling::input"));

        applicantLastName.sendKeys(testName);
        applicantFirstName.sendKeys(testName);
        applicantMiddleName.sendKeys(testName);
        applicantTelephoneNumber.sendKeys(testPhone);
        applicantPassportNumber.sendKeys(testPassport);
        applicantResidentialAddress.sendKeys(testAddress);

        WebElement chooseOptionNext = driver.findElement(By.xpath("//button[contains(text(), 'Далее')]"));
        chooseOptionNext.click();

        WebElement chooseApplicationType = driver.findElement(By.xpath("//button[contains(text(), 'Регистрация брака')]"));
        chooseApplicationType.click();

        WebElement citizenLastName = driver.findElement(By.xpath("//div[label[contains(text(), 'Фамилия')]]/following-sibling::input"));
        WebElement citizenFirstName = driver.findElement(By.xpath("//div[label[contains(text(), 'Имя')]]/following-sibling::input"));
        WebElement citizenMiddleName = driver.findElement(By.xpath("//div[label[contains(text(), 'Отчество')]]/following-sibling::input"));
        WebElement citizenDateOfBirth = driver.findElement(By.xpath("//div[label[contains(text(), 'Дата рождения')]]/following-sibling::input"));
        WebElement citizenNumberOfPassports = driver.findElement(By.xpath("//div[label[contains(text(), 'Номер паспорта')]]/following-sibling::input"));
        WebElement citizenGender = driver.findElement(By.xpath("//div[label[contains(text(), 'Пол')]]/following-sibling::input"));
        WebElement citizenResidenceAddress = driver.findElement(By.xpath("//div[label[contains(text(), 'Адрес прописки')]]/following-sibling::input"));

        citizenLastName.sendKeys(testName);
        citizenFirstName.sendKeys(testName);
        citizenMiddleName.sendKeys(testName);
        citizenDateOfBirth.sendKeys(testDate);
        citizenNumberOfPassports.sendKeys(testPassport);
        citizenGender.sendKeys(testGender);
        citizenResidenceAddress.sendKeys(testAddress);

        WebElement chooseOptionNext2 = driver.findElement(By.xpath("//button[contains(text(), 'Далее')]"));
        chooseOptionNext2.click();
        // 4
        WebElement dateOfRegistration = driver.findElement(By.xpath("//div[label[contains(text(), 'Дата регистрации')]]/following-sibling::input"));
        WebElement newFamily = driver.findElement(By.xpath("//div[label[contains(text(), 'Новая фамилия')]]/following-sibling::input"));
        WebElement lastNameOfSpouse = driver.findElement(By.xpath("//div[label[contains(text(), 'Фамилия супруга')]]/following-sibling::input"));
        WebElement firstNameOfSpouse = driver.findElement(By.xpath("//div[label[contains(text(), 'Имя супруга')]]/following-sibling::input"));
        WebElement middleNameOfSpouse = driver.findElement(By.xpath("//div[label[contains(text(), 'Отчество супруга')]]/following-sibling::input"));
        WebElement dateOfBirthOfSpouse = driver.findElement(By.xpath("//div[label[contains(text(), 'Дата рождения супруга')]]/following-sibling::input"));
        WebElement passportNumberOfSpouse = driver.findElement(By.xpath("//div[label[contains(text(), 'Номер паспорта супруга')]]/following-sibling::input"));

        dateOfRegistration.sendKeys(testDate);
        newFamily.sendKeys(testFamilyName);
        lastNameOfSpouse.sendKeys(testName);
        firstNameOfSpouse.sendKeys(testName);
        middleNameOfSpouse.sendKeys(testName);
        dateOfBirthOfSpouse.sendKeys(testDate);
        passportNumberOfSpouse.sendKeys(testPassport);

        WebElement endThisApplication = driver.findElement(By.xpath("//button[contains(text(), 'Завершить')]"));
        endThisApplication.click();

        WebElement checkIfThisApplicationSubmitted = driver.findElement(By.xpath("//span[contains(text(), '№')]"));
        assert checkIfThisApplicationSubmitted.isDisplayed();

        driver.quit();
    }
}
