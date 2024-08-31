package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Utility {
    private static final String ScreenShots_Path = "test-outputs/ScreenShots/";

    public static void clickOnElement(WebDriver driver, By Locator){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(Locator));
        driver.findElement(Locator).click();

    }

    public static void SendData(WebDriver driver , By Locator , String text){
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(Locator));
        driver.findElement(Locator).sendKeys(text);
    }

    public static String getText(WebDriver driver,By Locator){
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(Locator));
        return driver.findElement(Locator).getText();
    }

    public static WebDriverWait generalWait(WebDriver driver){
        return new WebDriverWait(driver,Duration.ofSeconds(20));
    }

    public static void Scrolling(WebDriver driver, By locator){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",findWebElement(driver,locator));
    }

    public static WebElement findWebElement(WebDriver driver, By Locator){
        return driver.findElement(Locator);
    }
    public static String getTimeStamp(){
        return new SimpleDateFormat("yyyy-mm-dd-h-m-ssa").format(new Date());
    }

    //TODO: TAKING SCREENSHOT
    public static void takeScreenShot(WebDriver driver,String ScreenShotName)  {
        try{
            File ScreenSrc =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File ScreenDest =new File(ScreenShots_Path+ScreenShotName+"-"+getTimeStamp()+".png");
            FileUtils.copyFile(ScreenSrc,ScreenDest);
            Allure.addAttachment(ScreenShotName, Files.newInputStream(Path.of(ScreenDest.getPath())));}
        catch (Exception e){
            LogsUtils.error(e.getMessage());        }
    }
    public static void takeFullScreenShoot(WebDriver driver ,By locator){
        try {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL).highlight(findWebElement(driver,locator)).save(ScreenShots_Path);
        }
        catch (Exception e)
        {LogsUtils.error(e.getMessage());}
    }


    public static void SelectingFromDropDown(WebDriver driver , By Locator, String option){
       new Select(findWebElement(driver,Locator)).selectByVisibleText(option);
    }
    public static int generateRandoNumber(int upperBound){
        return new Random().nextInt(upperBound)+1;
    }
    public static Set<Integer> generateUniqueNumber(int NumberOfProductsNeeded ,int totalNumberOfProducts){
    Set<Integer> generatedNumbers = new HashSet<>();
    while (generatedNumbers.size()<NumberOfProductsNeeded)
    {int RandomNumber = generateRandoNumber(totalNumberOfProducts);
        generatedNumbers.add(RandomNumber);
    }
    return generatedNumbers;
}
    public static boolean VerifyURL(WebDriver driver, String expectedURL){
        try{
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public static File getLatestFile(String folderPath){
        File folder = new File(folderPath);
        File[] files =folder.listFiles();
        assert files != null;
        if (files.length==0)
            return null;
        Arrays.sort(files,Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }
    public static Set<Cookie> getAllCookies(WebDriver driver){
        return driver.manage().getCookies();
    }
    public static void restoreSession(WebDriver driver,Set<Cookie> cookies){
        for (Cookie cookie:cookies)
            driver.manage().addCookie(cookie);
    }

}
