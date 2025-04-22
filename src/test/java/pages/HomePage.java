package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final By orderButtonOnTop = By.className("Button_Button__ra12g"); //Кнопка Заказать вверху страницы
    private final By orderButtonMiddle = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM"); //Кнопка Заказать в середине страницы

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void waitForCookie(){ //Метод для ожидания и принятия куки
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("App_CookieConsent__1yUIN")));

        WebElement cookieButton = driver.findElement(By.xpath("//button[text()='да все привыкли']"));
        cookieButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("App_CookieConsent__1yUIN")));
    }

    public void clickOrderButtonOnTop(){ //Метод нажатия кнопки вверху страницы
        driver.findElement(orderButtonOnTop).click();
    }
    public void clickOrderButtonMiddle(){ //Метод прокрутки страницы до кнопки в середине страницы и нажатия на кнопку
        WebElement button = driver.findElement(orderButtonMiddle);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        driver.findElement(orderButtonMiddle).click();
    }

    public void waitingForNextPage(){ //Метод для ожидания открытия следующей страницы
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Form__17u6u")));
    }

}
