package Tests;

import Pages.P01_LoginPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.IinvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;
@Listeners({IinvokedMethodListenerClass.class,ITestResultListenerClass.class})
public class TC01_LoginTest {
 @BeforeMethod
 public void Setup() throws IOException {
     //condition ? true : false
     String browser = System.getProperty("browser")!= null ? System.getProperty("browser"):getPropertyValue("enviroments","Browser");
    LogsUtils.info(System.getProperty("browser"));
     setupDriver(browser);
     LogsUtils.info("Edge driver is opened");
     getDriver().get(getPropertyValue("enviroments","Base_URL"));
     LogsUtils.info("page is redirected to the URL");

     getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 }
 @Test
public void ValidLoginTC() throws IOException {
     new P01_LoginPage(getDriver()).enterUsername(DataUtils.getJsonData("validLogin","username")).enterPassword(DataUtils.getJsonData("validLogin","PassWord")).ClickOnLoginButton();
        Assert.assertTrue(new P01_LoginPage(getDriver()).assertLoginTc(getPropertyValue("enviroments","Home_URL")));
 }
@AfterMethod
    public void quit(){
    QuitDriver();
}
}
