package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06_FinishingOrderPage {
    private final WebDriver driver;
    private final By thanksMessage= By.className("complete-header");

    public P06_FinishingOrderPage(WebDriver driver) {
        this.driver=driver;
    }
    public boolean checkVisibilityOfThanksMessage(){
        return Utility.findWebElement(driver,thanksMessage).isDisplayed();
    }
}
