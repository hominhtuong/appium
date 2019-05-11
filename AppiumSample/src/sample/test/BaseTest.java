package test;

import actions.MobileActions;
import appium.AppiumServer;
import constants.Constants;
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

        driver = new IOSDriver(AppiumServer.shared().initAppiumService().getUrl(),capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        actions = new MobileActions(driver);
        System.out.println("[Driver] setup is completed!");
    }

    @AfterTest
    private void teardown() {

        if (driver!= null)
            driver.quit();
        AppiumServer.shared().stop();
    }
}
