package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderFormPage_part1 {
    private final WebDriver driver;
    private final By firstName = By.xpath("//input[@placeholder='* Имя']");
    private final By lastName = By.xpath("//input[@placeholder='* Фамилия']");
    private final By address = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStation = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneNumber = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    public void filInTheForm(String fName, String lName, String address, String metro, String phoneN) {
        inputFirstName(fName);
        inputLastName(lName);
        inputAddress(address);
        inputMetroStation(metro);
        inputPhoneNumber(phoneN);
    }

    public OrderFormPage_part1(WebDriver driver) {
        this.driver = driver;
    }

    public void inputFirstName(String data) { //Метод для заполнения поля Имя
        driver.findElement(firstName).sendKeys(data);
    }

    public void inputLastName(String data) { //Метод для заполнения поля Фамилия
        driver.findElement(lastName).sendKeys(data);
    }

    public void inputAddress(String data) { //Метод для заполнения поля Адрес
        driver.findElement(address).sendKeys(data);
    }

    public void inputMetroStation(String data) { ////Метод для выбора станции метро
        driver.findElement(metroStation).click();
        driver.findElement(metroStation).sendKeys(data);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(text(), '" + data + "')]")));
        driver.findElement(By.xpath("//div[contains(text(), '" + data + "')]")).click();

    }

    public void inputPhoneNumber(String data) { //Метод для ввода номера телефона
        driver.findElement(phoneNumber).sendKeys(data);
    }

    public void clickNextButton() { //Метод для нажатия кнопки Далее
        driver.findElement(nextButton).click();
    }


}
