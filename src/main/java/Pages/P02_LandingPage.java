package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

import static Utilities.Utility.generalWait;

public class P02_LandingPage {
    static float TotalPrice = 0;

    private final WebDriver driver;

    public P02_LandingPage(WebDriver driver) {
        this.driver = driver;
    }
    public By getNumberOfSelectedProductsOnCart(){
        return NumberOfProductsOnCartIcon;
    }
   private final By addToCartButtonForAllProducts =By.xpath("//button[@class]");
    private final By NumberOfProductsOnCartIcon =By.className("shopping_cart_badge");
    private final By NumberOfSelectedProducts =By.xpath("//button[.='Remove']");
    private static List<WebElement> allProducts ;
    private static List<WebElement> SelectedProducts ;
    private  final By CartIcon = By.className("shopping_cart_link");
    private final By PricesOfSelectedProductsLocators = By.xpath("//button[.='Remove']//preceding-sibling::div[@class=\"inventory_item_price\"]");

    public P02_LandingPage addAllProductsToCart(){
    allProducts =driver.findElements(addToCartButtonForAllProducts);
    LogsUtils.info("Number of all products"+allProducts.size());
    for (int i =1 ;i<=allProducts.size();i++){
        By addToCartButtonForAllProducts =By.xpath("(//button[@class])["+ i +"]");//dynanic locator
        Utility.clickOnElement(driver,addToCartButtonForAllProducts);
    }
        return this;

    }
public String getNumberOfProductsOnCartIcon(){
        try{
            LogsUtils.info("Number of Products On Cart Icon "+ Utility.getText(driver,NumberOfProductsOnCartIcon));
            return Utility.getText(driver,NumberOfProductsOnCartIcon);}
        catch (Exception e){
            LogsUtils.error(e.getMessage());
        }
        return "0";
}
public  String getNumberOfSelectedProducts(){
        try {
           //
            SelectedProducts =driver.findElements(NumberOfSelectedProducts);
            LogsUtils.info("Selected products:"+SelectedProducts.size());
            return String.valueOf(SelectedProducts.size());

        }catch (Exception e){LogsUtils.error(e.getMessage());
        return "0";}
}
public     P02_LandingPage addingRandomProducts(int NumberOfProductsNeeded ,int totalNumberOfProducts){
    Set<Integer>randomNumbers = Utility.generateUniqueNumber(NumberOfProductsNeeded ,totalNumberOfProducts);
    for (int random :randomNumbers){
        LogsUtils.info("randomNumber"+random);
        By addToCartButtonForAllProducts =By.xpath("(//button[@class])["+ random +"]");//dynanic locator
        Utility.clickOnElement(driver,addToCartButtonForAllProducts);
    }
    return this;


}
public boolean ComparingNumberofSelectedProductsWithCart(){
        return getNumberOfProductsOnCartIcon().equals(getNumberOfSelectedProducts());
}
public P03_CartPage clickOnCartIcon(){
Utility.clickOnElement(driver,CartIcon);
return new P03_CartPage(driver);
}

public String getTotalPriceOfSelectedProducts(){
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

}
