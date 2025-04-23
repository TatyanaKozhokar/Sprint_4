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
import pages.OrderFormPage_UserInformation;
import pages.Scooter_and_OrderInformationPage;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class OrderButtonsTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String duration;
    private final String colour;
    private final String comment;

    public OrderButtonsTest(String firstName, String lastName, String address, String metroStation,
                            String phoneNumber, String date, String duration, String colour, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }


    @Parameterized.Parameters
    public static Object[][] filTheForm() {
        return new Object[][]{
                {"Иван", "Иванов", "Демьяна Бедного 7", "Полежаевская", "+79999999999", "02.06.2025", "трое суток", "чёрный", "Это комментарий"},
                {"Джордж", "Клуни", "Авиационная 66", "Щукинская", "+791234567899", "22.08.2025", "пятеро суток", "серый", "Это комментарий"},
        };
    }

    private WebDriver driver;

    @Test
    public void orderButtonOnTopTest() { //Проверка кнопки вверху страницы
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objectHP = new HomePage(driver); //Создаем объект класса HomePage
        objectHP.waitForCookie(); //Вызываем метод принятия куки
        objectHP.clickOrderButtonOnTop(); //Вызываем метод нажатия на кнопку
        objectHP.waitingForNextPage(); //Вызываем метод ожидания прогрузки следующей страницы
        OrderFormPage_UserInformation objectOFP1 = new OrderFormPage_UserInformation(driver); //Создаем объект OrderFormPage_part1
        objectOFP1.filInTheForm(firstName, lastName, address, metroStation, phoneNumber); //Вносим в поля параметрические данные
        objectOFP1.clickNextButton(); //Нажимаем кнопку Далее
        Scooter_and_OrderInformationPage objectOFP2 = new Scooter_and_OrderInformationPage(driver); //Создаем объект OrderFormPage_part2
        objectOFP2.filTheScooterForm(date,duration, colour, comment);
        objectOFP2.clickOrderButton(); //Нажимаем кнопку Заказать
        objectOFP2.clickYesButton(); //Подтверждаем заказ
        WebElement finalWindow = driver.findElement(objectOFP2.getOrderHasBeenPlaced()); //Вносим окно с созданным заказом в переменную
        MatcherAssert.assertThat(finalWindow, notNullValue()); //Сравниваем, что эта переменная не пустая
    }

    @Test
    public void orderButtonMiddleTest() { //Проверка кнопки в середине страницы
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objectHP = new HomePage(driver);
        objectHP.waitForCookie();
        objectHP.clickOrderButtonMiddle();
        objectHP.waitingForNextPage();
        OrderFormPage_UserInformation objectOFP1 = new OrderFormPage_UserInformation(driver);
        objectOFP1.filInTheForm(firstName, lastName, address, metroStation, phoneNumber);
        objectOFP1.clickNextButton();
        Scooter_and_OrderInformationPage objectOFP2 = new Scooter_and_OrderInformationPage(driver);
        objectOFP2.filTheScooterForm(date,duration, colour, comment);
        objectOFP2.clickOrderButton();
        objectOFP2.clickYesButton();
        WebElement finalWindow = driver.findElement(objectOFP2.getOrderHasBeenPlaced()); //Вносим окно с созданным заказом в переменную
        MatcherAssert.assertThat(finalWindow, notNullValue());
    }

    @After
    public void teardown() {
        driver.quit();

    }
}

