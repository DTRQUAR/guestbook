package test.guestbook.task.gui;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.task.AbstractTest;
import test.guestbook.task.util.DbPopulator;

/**
 * Created by Qouer on 07.07.2016.
 */

public class SeleniumTest extends AbstractTest{

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

    //Тест нажатия кнопки "Отправить сообщение" при пустых полях ввода
    @Test
    public void textEmptyInputValuesSubmit() throws InterruptedException {
//        File pathBinary = new File("D:\\bundle-windows-x64\\eclipse\\dropins\\tr_win\\Prog_S\\Firefox\\firefox.exe");
//        FirefoxBinary ffBinary = new FirefoxBinary(pathBinary);
//        FirefoxProfile ffProfile = new FirefoxProfile();
//        WebDriver driver = new FirefoxDriver(ffBinary, ffProfile);

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/gb/main");

        WebElement submitButton = driver.findElement(new By.ById("sendButton"));
        submitButton.click();

        WebElement nameField = driver.findElement(new By.ById("nameField"));
        WebElement messageField = driver.findElement(new By.ById("messageField"));

        String actualValue = nameField.getAttribute("value") + " " +  messageField.getAttribute("value");
        Assert.assertEquals("Введите значение Введите значение", actualValue);

    }

    //Тест нажатия кнопки "Отправить сообщение" при значение "Введите значение" в полях ввода
    @Test
    public void textWrongInputValueSubmit() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
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

    //Тест нажатия кнопки "Отправить сообщение" при правильных значениях в полях ввода
    //(значение: не пусто и не "Введите значение")
    @Test
    public void textRightInputValueSubmit() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
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


}
