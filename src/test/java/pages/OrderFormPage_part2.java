package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderFormPage_part2 {
    private final WebDriver driver;
    private final By date = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By duration = By.className("Dropdown-root");
    private final By colourBlack = By.id("black");
    private final By colourGrey = By.id("grey");
    private final By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private final By yesButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");


    public OrderFormPage_part2(WebDriver driver) {
        this.driver = driver;
    }

    public void inputDate(String data) { //Метод для ввода даты
        driver.findElement(date).click();
        driver.findElement(date).sendKeys(data);
        driver.findElement(By.className("Order_Header__BZXOb")).click();
    }

    public void inputDuration(String data) { //Метод для выбора длительности аренды
        driver.findElement(duration).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@class='Dropdown-option' and text()='сутки']")));

        switch (data) {
            case "сутки":
                driver.findElement(By.xpath("//div[@class='Dropdown-option' and text()='сутки']")).click();
                break;
            case "двое суток":
                driver.findElement(By.xpath("//div[@class='Dropdown-option' and text()='двое суток']")).click();
                break;
            case "трое суток":
                driver.findElement(By.xpath("//div[@class='Dropdown-option' and text()='трое суток']")).click();
                break;
            case "четверо суток":
                driver.findElement(By.xpath("//div[@class='Dropdown-option' and text()='четверо суток']")).click();
                break;
            case "пятеро суток":
                driver.findElement(By.xpath("//div[@class='Dropdown-option' and text()='пятеро суток']")).click();
                break;
            case "шестеро суток":
                driver.findElement(By.xpath("//div[@class='Dropdown-option' and text()='шестеро суток']")).click();
                break;
            case "семеро суток":
                driver.findElement(By.xpath("//div[@class='Dropdown-option' and text()='семеро суток']")).click();
                break;
        }
    }

    public void inputColour(String data) { //Метод выбора цвета самоката
        if (data.equals("чёрный")) {
            driver.findElement(colourBlack).click();
        } else if (data.equals("серый")) {
            driver.findElement(colourGrey).click();
        }
    }

    public void inputComment(String data) { //Метод для заполнения поля Комментарий для курьера
        driver.findElement(comment).sendKeys(data);
    }

    public void clickOrderButton() {//Метод для нажатия кнопки Заказать
        driver.findElement(orderButton).click();
    }

    public void clickYesButton() { //Метод для подтверждения заказа
        driver.findElement(yesButton).click();
    }

}


