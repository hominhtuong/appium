package test;

import actions.MobileActions;
import appium.AppiumServer;
import com.aventstack.extentreports.ExtentReports;
import constants.Constants;
import extentreports.ExtentReport;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected IOSDriver driver;
    protected MobileActions actions;
    protected static ExtentReports extent;

    @BeforeSuite
    public void beforeSuite() {
        extent = ExtentReport.shared().getExtent();
    }

    @BeforeTest
    public void setupDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Constants.PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Constants.DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.UDID, Constants.UDID);
        capabilities.setCapability(MobileCapabilityType.NO_RESET,Constants.NO_RESET);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability("bundleId", Constants.bundleId);
        capabilities.setCapability("xcodeOrgId", Constants.xcodeOrgId);
        capabilities.setCapability("xcodeSigningId", Constants.xcodeSigningId);
        capabilities.setCapability("xcodeConfigFile",Constants.xcodeConfigFile);

        driver = new IOSDriver(AppiumServer.shared().appiumService().getUrl(),capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        actions = new MobileActions(driver);
        System.out.println("[Driver] setup is completed!");
    }

    @AfterTest
    protected void endTest() {

        if (driver!= null)
            driver.quit();
        AppiumServer.shared().appiumStop();
    }

    @AfterSuite
    protected void flushReport(){
        extent.flush();
    }
}
