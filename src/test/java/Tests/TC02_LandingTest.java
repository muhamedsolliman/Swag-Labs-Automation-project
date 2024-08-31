package Tests;

import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.*;
import Listeners.IinvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;
import static Utilities.Utility.*;

@Listeners({IinvokedMethodListenerClass.class, ITestResultListenerClass.class})

public class TC02_LandingTest {
    private Set<Cookie> Cookies;
    @BeforeClass
    public void Login() throws IOException {
        setupDriver(getPropertyValue("enviroments","Browser"));
        LogsUtils.info("Edge driver is opened");
        getDriver().get(getPropertyValue("enviroments","Base_URL"));
        LogsUtils.info("page is redirected to the URL");

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new P01_LoginPage(getDriver()).enterUsername(DataUtils.getJsonData("validLogin","username")).enterPassword(DataUtils.getJsonData("validLogin","PassWord")).ClickOnLoginButton();
    Cookies =getAllCookies(getDriver());
    QuitDriver();
    }
    @BeforeMethod
    public void Setup() throws IOException {
        setupDriver(getPropertyValue("enviroments","Browser"));
        LogsUtils.info("Edge driver is opened");
        getDriver().get(getPropertyValue("enviroments","Base_URL"));
        LogsUtils.info("page is redirected to the URL");
        restoreSession(getDriver(),Cookies);
        getDriver().get(getPropertyValue("enviroments","Home_URL"));
        getDriver().navigate().refresh();
    }
    @Test
    public void CheckingNumberOfSelectedProducts()  {
new P02_LandingPage(getDriver()).addAllProductsToCart();
        Assert.assertTrue(new P02_LandingPage(getDriver()).ComparingNumberofSelectedProductsWithCart());
    }
    @Test
    public void addingRandomProductsToCartTC()  {
        new P02_LandingPage(getDriver()).addingRandomProducts(3,6);
        Assert.assertTrue(new P02_LandingPage(getDriver()).ComparingNumberofSelectedProductsWithCart());
    }
    @Test
    public void ClickOnCartIcon() throws IOException {
        new P02_LandingPage(getDriver()).clickOnCartIcon();
        Assert.assertTrue(VerifyURL(getDriver(),DataUtils.getPropertyValue("enviroments","Cart_URL")));
    }

    @AfterMethod
    public void quit(){
        QuitDriver();
    }
    @AfterClass
    public void deleteSession(){
        Cookies.clear();
    }
}
