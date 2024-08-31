package Listeners;

import Pages.P02_LandingPage;
import Utilities.LogsUtils;
import Utilities.Utility;
import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static DriverFactory.DriverFactory.getDriver;

public class IinvokedMethodListenerClass implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        File logfile =Utility.getLatestFile(LogsUtils.LOGS_PATH);
        try {
            Allure.addAttachment("logs.log", Files.readString(Path.of(logfile.getPath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (testResult.getStatus()==ITestResult.FAILURE){
            LogsUtils.info("Test case "+testResult.getName()+ "failed");
            Utility.takeScreenShot(getDriver(),testResult.getName());
           // Utility.takeFullScreenShoot(getDriver(),new P02_LandingPage(getDriver()).getNumberOfSelectedProductsOnCart());
    }}
}
