package tests;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

//Всплывающее окно куки className("App_CookieConsent__1yUIN")
//Кнопка принятия куки xpath("//button[text()='да все привыкли']")
//Блок с вопросами className("Home_FAQ__3uVm4")
//Вопросы xpath(".//div[@class='accordion__item']")

public class DropDownListTest {
    private WebDriver driver;

    @Test
    public void testDropDownList() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //Ждем когда прогрузятся куки, принимаем и ждем когда уведомление исчезнет
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("App_CookieConsent__1yUIN")));

        WebElement cookieButton = driver.findElement(By.xpath("//button[text()='да все привыкли']"));
        cookieButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("App_CookieConsent__1yUIN")));

        //Прокручиваем до блока с вопросами
        WebElement faqSection = driver.findElement(By.className("Home_FAQ__3uVm4"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", faqSection);

        //Создаем список вопросов
        List<WebElement> questions =
                driver.findElements(By.xpath(".//div[@class='accordion__item']"));

        //Создаем цикл для проверки того, что по клику на каждый вопрос будет открываться ответ
        for (WebElement question : questions) {

            //Ищем вопрос
            question.click();

            //Кладем ответ в переменную
            WebElement answer = question.findElement(By.xpath(".//following-sibling::div[@class='accordion__panel']/p"));

            //Проверяем, что переменная answer не null
            MatcherAssert.assertThat(answer, notNullValue());
        }

    }

    @After
    public void teardown() {
        driver.quit();
    }

}

