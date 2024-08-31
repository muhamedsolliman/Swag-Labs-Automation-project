package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_OverviewPage {
    private final WebDriver driver;
    private final By subTotal = By.className("summary_subtotal_label");
    private final By TAX = By.className("summary_tax_label");
    private final By total = By.className("summary_total_label");
    private final By FinishButton = By.id("finish");

    public P05_OverviewPage(WebDriver driver) {
        this.driver =driver;
    }
    public Float getSubTotal(){
        return Float.parseFloat(Utility.getText(driver,subTotal).replace("Item total: $",""));
    }
    public Float getTax(){
        return Float.parseFloat(Utility.getText(driver,TAX).replace("Tax: $",""));
    }
    public Float getTotal(){
        LogsUtils.info("Actual Total price:"+Utility.getText(driver,total).replace("Total: $",""));
        return Float.parseFloat(Utility.getText(driver,total).replace("Total: $",""));
    }
    public String calculateTotalPrice(){
        LogsUtils.info("Calculated Total price:"+(getSubTotal()+getTax()));
        return String.valueOf(getSubTotal()+getTax());
    }
    public boolean ComparingPrices(){
        return calculateTotalPrice().equals(String.valueOf(getTotal()));
    }
    public P06_FinishingOrderPage clickOnFinishButton(){
        Utility.clickOnElement(driver,FinishButton);
        return new P06_FinishingOrderPage(driver);
    }



}
