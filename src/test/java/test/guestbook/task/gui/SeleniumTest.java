package test.guestbook.task.gui;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import test.guestbook.task.AbstractTest;
import test.guestbook.task.util.DbPopulator;

import java.util.List;

/**
 * Created by Qouer on 07.07.2016.
 */

public class SeleniumTest extends AbstractTest{

    String driverChromePath = "C:\\chromedriver.exe";

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @After
    public void rollBack() throws Exception {
        dbPopulator.execute();
    }

    //Тест нажатия кнопки "Отправить" при пустых полях ввода
    @Test
    public void testEmptyInputValuesSubmit() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", driverChromePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/gb/main");

        WebElement submitButton = driver.findElement(new By.ById("sendButton"));
        submitButton.click();

        WebElement nameField = driver.findElement(new By.ById("nameField"));
        WebElement messageField = driver.findElement(new By.ById("messageField"));

        String actualValue = nameField.getAttribute("value") + " " +  messageField.getAttribute("value");
        Assert.assertEquals("Введите значение Введите значение", actualValue);

    }

    //Тест нажатия кнопки "Отправить" при значении "Введите значение" в полях ввода
    @Test
    public void testWrongInputValueSubmit() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", driverChromePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/gb/main");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('nameField').setAttribute('value', 'Введите значение')");
        js.executeScript("document.getElementById('messageField').setAttribute('value', 'Введите значение')");

        WebElement submitButton = driver.findElement(new By.ById("sendButton"));
        submitButton.click();

        WebElement nameField = driver.findElement(new By.ById("nameField"));
        WebElement messageField = driver.findElement(new By.ById("messageField"));

        String actualValue = nameField.getAttribute("value") + " " +  messageField.getAttribute("value");
        Assert.assertEquals("Введите значение Введите значение", actualValue);

    }

    //Тест нажатия на кнопку "Отправить" при правильных значениях в полях ввода
    //(значение: не пусто и не "Введите значение")
    @Test
    public void testRightInputValueSubmit() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", driverChromePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/gb/main");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('nameField').setAttribute('value', 'Игорь')");
        js.executeScript("document.getElementById('messageField').innerHTML = 'Бла-бла'");

        WebElement submitButton = driver.findElement(new By.ById("sendButton"));
        submitButton.click();

        WebElement nameField = driver.findElement(new By.ById("nameField"));
        WebElement messageField = driver.findElement(new By.ById("messageField"));

        WebElement messageBox = driver.findElement(By.className("MessageBox"));
        WebElement nameOfMessage = messageBox.findElement(By.className("nameOfMessage"));
        WebElement textOfMessage = messageBox.findElement(By.className("textOfMessage"));

        String actualNameAndText = nameOfMessage.getText() + " " + textOfMessage.getText();

        String actualInputValues = nameField.getAttribute("value") + " " +  messageField.getAttribute("value") + " ";
        Assert.assertEquals("  Игорь Бла-бла", actualInputValues + actualNameAndText);

    }

    //Тест авторизации при неверных данных
    //(поля ввода логина и пароля должны изменить класс на "has-error")
    @Test
    public void testWrongInputAuthValueLogin() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", driverChromePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/gb/main");

        WebElement loginButton = driver.findElement(new By.ById("loginButton"));
        loginButton.click();

        List<WebElement> elements = driver.findElements(By.className("has-error"));
        int count = elements.size();

        Assert.assertEquals(2, count);

    }

    //Тест авторизации при корретнфх данных
    //(должна появится кнопка "Мой профиль" и "Выход")
    @Test
    public void testRightInputAuthValueLogin() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", driverChromePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/gb/main");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('emailInput').setAttribute('value', 'user1@ya.ru')");
        js.executeScript("document.getElementById('passwordInput').setAttribute('value', '111111Qw')");

        WebElement loginButton = driver.findElement(new By.ById("loginButton"));
        loginButton.click();

        Thread.sleep(500);

        WebElement profileButton = driver.findElement(new By.ById("profileButton"));
        WebElement logoutButton = driver.findElement(new By.ById("logoutButton"));

        boolean displayed = profileButton.isDisplayed() && logoutButton.isDisplayed();
        Assert.assertEquals(displayed, true);

    }

    //Тест нажатия на лайк
    //(класс элемента лайк должен измениться на "selectLikeButton")
    @Test
    public void testLike() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", driverChromePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/gb/main");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('emailInput').setAttribute('value', 'user1@ya.ru')");
        js.executeScript("document.getElementById('passwordInput').setAttribute('value', '111111Qw')");
        WebElement loginButton = driver.findElement(new By.ById("loginButton"));

        loginButton.click();

        Thread.sleep(500);

        WebElement message_3 = driver.findElement(new By.ById("message_3"));
        WebElement likeButton = message_3.findElement(new By.ById("likeButton"));

        likeButton.click();

        Thread.sleep(500);

        WebElement new_message_3_1 = driver.findElement(new By.ById("message_3"));
        WebElement selectLikeButton = new_message_3_1.findElement(new By.ById("selectLikeButton"));

        boolean displayed = selectLikeButton.isDisplayed();

        Assert.assertEquals(displayed, true);

    }

    //Тест повторного нажатия на лайк
    //(класс элемента лайк должен измениться c "selectLikeButton" на "likeButton")
    @Test
    public void testRepeatLike() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", driverChromePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/gb/main");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('emailInput').setAttribute('value', 'user1@ya.ru')");
        js.executeScript("document.getElementById('passwordInput').setAttribute('value', '111111Qw')");
        WebElement loginButton = driver.findElement(new By.ById("loginButton"));
        loginButton.click();

        Thread.sleep(500);

        WebElement message_3 = driver.findElement(new By.ById("message_3"));
        WebElement likeButton = message_3.findElement(new By.ById("likeButton"));

        likeButton.click();

        Thread.sleep(500);

        WebElement message_3_1 = driver.findElement(new By.ById("message_3"));
        WebElement selectLikeButton = message_3_1.findElement(new By.ById("selectLikeButton"));

        selectLikeButton.click();

        Thread.sleep(500);

        WebElement message_3_2 = driver.findElement(new By.ById("message_3"));
        WebElement likeButton_1 = message_3_2.findElement(new By.ById("likeButton"));

        boolean displayed = likeButton_1.isDisplayed();

        Assert.assertEquals(displayed, true);

    }

    //Тест нажатия на дизлайк
    //(класс элемента лайк должен измениться на "selectNotLikeButton")
    @Test
    public void testNotLike() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", driverChromePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/gb/main");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('emailInput').setAttribute('value', 'user1@ya.ru')");
        js.executeScript("document.getElementById('passwordInput').setAttribute('value', '111111Qw')");
        WebElement loginButton = driver.findElement(new By.ById("loginButton"));
        loginButton.click();

        Thread.sleep(500);

        WebElement message_3 = driver.findElement(new By.ById("message_3"));
        WebElement notLikeButton = message_3.findElement(new By.ById("notLikeButton"));

        notLikeButton.click();

        Thread.sleep(500);

        WebElement message_3_1 = driver.findElement(new By.ById("message_3"));
        WebElement selectNotLikeButton = message_3_1.findElement(new By.ById("selectNotLikeButton"));

        boolean displayed = selectNotLikeButton.isDisplayed();

        Assert.assertEquals(displayed, true);

    }

    //Тест повторого нажатия на дизлайк
    //(класс элемента лайк должен измениться c "selectNotLikeButton" на "NotLikeButton")
    @Test
    public void testRepeatNotLike() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", driverChromePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/gb/main");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('emailInput').setAttribute('value', 'user1@ya.ru')");
        js.executeScript("document.getElementById('passwordInput').setAttribute('value', '111111Qw')");
        WebElement loginButton = driver.findElement(new By.ById("loginButton"));
        loginButton.click();

        Thread.sleep(500);

        WebElement message_3 = driver.findElement(new By.ById("message_3"));
        WebElement notLikeButton = message_3.findElement(new By.ById("notLikeButton"));

        notLikeButton.click();

        Thread.sleep(500);

        WebElement message_3_1 = driver.findElement(new By.ById("message_3"));
        WebElement selectNotLikeButton = message_3_1.findElement(new By.ById("selectNotLikeButton"));

        selectNotLikeButton.click();

        Thread.sleep(500);

        WebElement message_3_2 = driver.findElement(new By.ById("message_3"));
        WebElement notLikeButton_1 = message_3_2.findElement(new By.ById("notLikeButton"));

        boolean displayed = notLikeButton_1.isDisplayed();

        Assert.assertEquals(displayed, true);

    }

    //Тест нажатия на лайк после нажатия на дизлайк
    //(класс элемента лайк должен измениться на "selectLikeButton", а элемента дизлайк на "notLikeButton")
    @Test
    public void testLikeAfterNotLike() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", driverChromePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/gb/main");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('emailInput').setAttribute('value', 'user1@ya.ru')");
        js.executeScript("document.getElementById('passwordInput').setAttribute('value', '111111Qw')");
        WebElement loginButton = driver.findElement(new By.ById("loginButton"));
        loginButton.click();

        Thread.sleep(500);

        WebElement for_dislike_message_3 = driver.findElement(new By.ById("message_3"));
        WebElement notLikeButton = for_dislike_message_3.findElement(new By.ById("notLikeButton"));

        notLikeButton.click();

        Thread.sleep(500);

        WebElement for_like_message_3 = driver.findElement(new By.ById("message_3"));
        WebElement likeButton = for_like_message_3.findElement(new By.ById("likeButton"));

        likeButton.click();

        Thread.sleep(500);

        WebElement for_like_message_3_1 = driver.findElement(new By.ById("message_3"));
        WebElement selectLikeButton = for_like_message_3_1.findElement(new By.ById("selectLikeButton"));
        WebElement notLikeButton_1 = for_like_message_3_1.findElement(new By.ById("notLikeButton"));

        boolean displayed = selectLikeButton.isDisplayed() && notLikeButton_1.isDisplayed();

        Assert.assertEquals(displayed, true);

    }

    //Тест нажатия на дизлайк после нажатия на лайк
    //(класс элемента дизлайк должен измениться на "selectNotLikeButton", а элемента лайк на "likeButton")
    @Test
    public void testNotLikeAfterLike() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", driverChromePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/gb/main");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('emailInput').setAttribute('value', 'user1@ya.ru')");
        js.executeScript("document.getElementById('passwordInput').setAttribute('value', '111111Qw')");
        WebElement loginButton = driver.findElement(new By.ById("loginButton"));
        loginButton.click();

        Thread.sleep(500);

        WebElement for_like_message_3 = driver.findElement(new By.ById("message_3"));
        WebElement likeButton = for_like_message_3.findElement(new By.ById("likeButton"));

        likeButton.click();

        Thread.sleep(500);

        WebElement for_dislike_message_3 = driver.findElement(new By.ById("message_3"));
        WebElement notLikeButton = for_dislike_message_3.findElement(new By.ById("notLikeButton"));

        notLikeButton.click();

        Thread.sleep(500);

        WebElement for_dislike_message_3_1 = driver.findElement(new By.ById("message_3"));
        WebElement selectNotLikeButton = for_dislike_message_3_1.findElement(new By.ById("selectNotLikeButton"));
        WebElement likeButton_1 = for_dislike_message_3_1.findElement(new By.ById("likeButton"));

        boolean displayed = selectNotLikeButton.isDisplayed() && likeButton_1.isDisplayed();

        Assert.assertEquals(displayed, true);

    }

    //Тест регистрации пользователя с уже существующим email'ом
    //(в поле ввода email'а должно быть выведено соообщение: "Пользователь с таким email'ом уже существует")
    @Test
    public void testRegisterWithExistingEmail() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", driverChromePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/gb/register");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('registerEmailField').setAttribute('value', 'user1@ya.ru')");
        js.executeScript("document.getElementById('registerPasswordField').setAttribute('value', '111111Qw')");
        js.executeScript("document.getElementById('registerNameField').setAttribute('value', 'NewName')");
        WebElement loginButton = driver.findElement(new By.ById("saveUserButton"));
        loginButton.click();

        Thread.sleep(500);

        WebElement registerEmailField = driver.findElement(new By.ById("registerEmailField"));
        String text = registerEmailField.getAttribute("value");

        Assert.assertEquals("Пользователь с таким email'ом уже существует", text);

    }

}
