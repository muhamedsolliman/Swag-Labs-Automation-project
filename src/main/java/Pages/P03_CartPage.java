package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_CartPage {
    private final WebDriver driver;
    static float TotalPrice = 0;
    private final By PricesOfSelectedProductsLocators = By.xpath("//button[.='Remove']//preceding-sibling::div[@class=\"inventory_item_price\"]");
    private final By checkOutButton = By.id("checkout");
    public P03_CartPage(WebDriver driver) {
        this.driver =driver;
    }
    public String getTotalPrice(){
        try {
            List<WebElement> PricingOfSelectedProducts = driver.findElements(PricesOfSelectedProductsLocators);
            for (int i=1;i<=PricingOfSelectedProducts.size();i++){
                By elements =By.xpath("(//button[.='Remove']//preceding-sibling::div[@class=\"inventory_item_price\"])["+i+"]");
                String fullText =  Utility.getText(driver,elements);
                TotalPrice += Float.parseFloat(fullText.replace("$",""));
            }
            LogsUtils.info("Total Price "+TotalPrice);
            return String.valueOf(TotalPrice);
        }catch (Exception e){
            LogsUtils.error(e.getMessage());
            return "0";
        }

    }
    public boolean ComparingPrices(String price){
        return getTotalPrice().equals(price);
    }
    public P04_CheckOutPage ClickOnCheckoutButton(){
        Utility.clickOnElement(driver,checkOutButton);
        return new P04_CheckOutPage(driver);
    }

}
