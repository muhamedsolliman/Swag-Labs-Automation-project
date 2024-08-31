package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Utilities.Utility.generalWait;

public class P04_CheckOutPage {
    private final WebDriver driver;
    private final By FirstName = By.id("first-name");
    private final By LastName = By.id("last-name");
    private final By ZipCode = By.id("postal-code");
    private final By ContinueButton = By.id("continue");


    public P04_CheckOutPage(WebDriver driver) {
        this.driver =driver;
    }
    public P04_CheckOutPage fillingInformationForm(String FName ,String LName,String zipCode){
        Utility.SendData(driver,FirstName,FName);
        Utility.SendData(driver,LastName,LName);
        Utility.SendData(driver,ZipCode,zipCode);
        return this;
    }
    public P05_OverviewPage ClickOnContinueButton(){
        Utility.clickOnElement(driver,ContinueButton);
        return new P05_OverviewPage(driver) ;
    }

}
