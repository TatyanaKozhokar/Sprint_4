package tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.OrderFormPage_UserInformation;
import pages.Scooter_and_OrderInformationPage;
import static org.junit.Assert.assertEquals;


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

    @Before
    public void openBrowserAndAcceptCookies(){
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objectHP = new HomePage(driver); //Создаем объект класса HomePage
        objectHP.waitForCookie();
    }



    @Test
    public void orderButtonOnTopTest() { //Проверка кнопки вверху страницы
        HomePage objectHP = new HomePage(driver); //Создаем объект класса HomePage
        objectHP.clickOrderButtonOnTop(); //Вызываем метод нажатия на кнопку
        objectHP.waitingForNextPage(); //Вызываем метод ожидания прогрузки следующей страницы
        OrderFormPage_UserInformation objectOFP_UI = new OrderFormPage_UserInformation(driver); //Создаем объект OrderFormPage_part1
        objectOFP_UI.filInTheForm(firstName, lastName, address, metroStation, phoneNumber); //Вносим в поля параметрические данные
        objectOFP_UI.clickNextButton(); //Нажимаем кнопку Далее
        Scooter_and_OrderInformationPage objectSOIP = new Scooter_and_OrderInformationPage(driver); //Создаем объект OrderFormPage_part2
        objectSOIP.filTheScooterForm(date,duration, colour, comment);
        objectSOIP.clickOrderButton(); //Нажимаем кнопку Заказать
        objectSOIP.clickYesButton(); //Подтверждаем заказ
        String finalWindow = driver.findElement(objectSOIP.getOrderHasBeenPlaced()).getText(); //Вносим окно с созданным заказом в переменную
        assertEquals("Заказ оформлен", finalWindow);
    }

    @Test
    public void orderButtonMiddleTest() { //Проверка кнопки в середине страницы
        HomePage objectHP = new HomePage(driver);
        objectHP.clickOrderButtonMiddle();
        objectHP.waitingForNextPage();
        OrderFormPage_UserInformation objectOFP_UI = new OrderFormPage_UserInformation(driver);
        objectOFP_UI.filInTheForm(firstName, lastName, address, metroStation, phoneNumber);
        objectOFP_UI.clickNextButton();
        Scooter_and_OrderInformationPage objectSOIP = new Scooter_and_OrderInformationPage(driver);
        objectSOIP.filTheScooterForm(date,duration, colour, comment);
        objectSOIP.clickOrderButton();
        objectSOIP.clickYesButton();
        String finalWindow = driver.findElement(objectSOIP.getOrderHasBeenPlaced()).getText(); //Вносим окно с созданным заказом в переменную
        assertEquals("Заказ оформлен", finalWindow);
    }

    @After
    public void teardown() {
        driver.quit();

    }
}

