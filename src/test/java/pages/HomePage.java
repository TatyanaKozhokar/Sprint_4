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
    private final By faqSection = By.className("Home_FAQ__3uVm4");
    //private final By answer = By.xpath(".//following-sibling::div[@class='accordion__panel']/p");
    private final By app_cookie = By.className("App_CookieConsent__1yUIN");
    private final By cookieButton = By.xpath("//button[text()='да все привыкли']");
    private final By orderForm = By.className("Order_Form__17u6u");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void waitForCookie(){ //Метод для ожидания и принятия куки
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(app_cookie));

        WebElement cookieYesButton = driver.findElement(cookieButton);
        cookieYesButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.invisibilityOfElementLocated(app_cookie));
    }

    public void scrollToFaqSection(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(faqSection));
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
                until(ExpectedConditions.visibilityOfElementLocated(orderForm));
    }

    //Метод для нажатия на вопрос
    public void clickOnQuestion(String textOfTheQuestion){
        driver.findElement(By.xpath("//div[text()='"+ textOfTheQuestion +"']")).click();
    }

    //Получаем ответ
    public String getAnswer(String textOfTheQuestion){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+ textOfTheQuestion +"']/parent::div/following-sibling::div/p")));
        WebElement element = driver.findElement(By.xpath("//div[text()='"+ textOfTheQuestion +"']/parent::div/following-sibling::div/p"));
        return element.getText();
    }

}
