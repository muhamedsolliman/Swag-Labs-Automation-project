package Tests;

import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Pages.P03_CartPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
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
public class TC03_CartTest {
    @BeforeMethod
    public void Setup() throws IOException {
        setupDriver(getPropertyValue("enviroments","Browser"));
        LogsUtils.info("Edge driver is opened");
        getDriver().get(getPropertyValue("enviroments","Base_URL"));
        LogsUtils.info("page is redirected to the URL");

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void ComparingPricesTC()  {
        String TotalPrice= new P01_LoginPage(getDriver()).enterUsername(DataUtils.getJsonData("validLogin","username")).enterPassword(DataUtils.getJsonData("validLogin","PassWord")).ClickOnLoginButton()
                        .addingRandomProducts(2,6).getTotalPriceOfSelectedProducts();
        new P02_LandingPage(getDriver()).clickOnCartIcon();
        new P03_CartPage(getDriver()).ComparingPrices(TotalPrice);
    }
    @AfterMethod
    public void quit(){
        QuitDriver();
    }
}
