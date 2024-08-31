package Tests;

import Pages.P01_LoginPage;
import Pages.P05_OverviewPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import Utilities.Utility;
import com.github.javafaker.Faker;
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
@Listeners({IinvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC05_OverviewTest {
    private final String FIRSTNAME = DataUtils.getJsonData("information","FName")+"-" + Utility.getTimeStamp();
    private final String LASTNAME =DataUtils.getJsonData("information","LName")+"-" + Utility.getTimeStamp();
    private final String ZipCode = new Faker().number().digits(5);
    @BeforeMethod
    public void Setup() throws IOException {
        setupDriver(getPropertyValue("enviroments","Browser"));
        LogsUtils.info("Edge driver is opened");
        getDriver().get(getPropertyValue("enviroments","Base_URL"));
        LogsUtils.info("page is redirected to the URL");

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void checkoutStepTwoTC() throws IOException {
        new P01_LoginPage(getDriver()).enterUsername(DataUtils.getJsonData("validLogin","username"))
                .enterPassword(DataUtils.getJsonData("validLogin","PassWord"))
                .ClickOnLoginButton()
                .addingRandomProducts(2,6)
                .clickOnCartIcon().ClickOnCheckoutButton().fillingInformationForm(FIRSTNAME,LASTNAME,ZipCode).ClickOnContinueButton();
        LogsUtils.info(FIRSTNAME+" "+LASTNAME+" "+ZipCode);
        Assert.assertTrue(new P05_OverviewPage(getDriver()).ComparingPrices());

    }
    @AfterMethod
    public void quit(){
        QuitDriver();
    }
}
