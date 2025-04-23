package tests;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class DropDownListTest {
    private final String question;
    private WebDriver driver;

    public DropDownListTest(String question) {
        this.question = question;
    }

    @Parameterized.Parameters
    public static Object[][] checkTheAccordion() {
        return new Object[][]{
                {"Сколько это стоит? И как оплатить?"},
                {"Хочу сразу несколько самокатов! Так можно?"},
                {"Как рассчитывается время аренды?"},
                {"Можно ли заказать самокат прямо на сегодня?"},
                {"Можно ли продлить заказ или вернуть самокат раньше?"},
                {"Вы привозите зарядку вместе с самокатом?"},
                {"Можно ли отменить заказ?"},
                {"Я жизу за МКАДом, привезёте?"},

        };
    }

    @Test
    public void testDropDownList() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //Ждем когда прогрузятся куки, принимаем и ждем когда уведомление исчезнет
        HomePage objectHM = new HomePage(driver);
        objectHM.waitForCookie();

        //Прокручиваем до блока с вопросами
        objectHM.scrollToFaqSection();

        //Нажимаем на вопрос
        objectHM.clickOnQuestion(question);

        //Кладем ответ в переменную
        WebElement answer = driver.findElement(objectHM.getAnswer());

        //Проверяем, что переменная answer не null
        MatcherAssert.assertThat(answer, notNullValue());


    }

    @After
    public void teardown() {
        driver.quit();
    }

}

