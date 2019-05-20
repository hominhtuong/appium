package test;

import actions.Direction;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import extentreports.ExtentReport;
import io.appium.java_client.ios.IOSElement;
import objects.HomeObjects;
import objects.ProfileObjects;
import org.testng.annotations.Test;
import utils.Log;

import java.io.IOException;

public class TestClass extends BaseTest {

    @Test()
    public void openLeftMenu() {

        ExtentTest extentTest = ExtentReport.shared().getExtentTest("LeftMenu");
        driver.findElementByXPath(HomeObjects.openLeftMenu_XPATH).click();
        ExtentReport.Log(extentTest, Status.PASS,"Open Left Menu");
    }


    @Test(priority = 1)
    public void checkProfile() {

        ExtentTest extentTest = ExtentReport.shared().getExtentTest("Profile");

        ExtentTest childExtentTest = ExtentReport.shared().getChildExtentTest(extentTest,"Open Profile");
        driver.findElementByXPath(ProfileObjects.openProfile_XPATH).click();
        ExtentReport.Log(childExtentTest,Status.PASS, "Open Profile");

        IOSElement nickName = (IOSElement) driver.findElementByXPath(ProfileObjects.nickName_XPATH);
        System.out.println("Nick Name: " + nickName.getText());
        ExtentReport.Log(childExtentTest,Status.INFO, nickName.getText());

        IOSElement fullName = (IOSElement) driver.findElementByXPath(ProfileObjects.fullName_XPATH);
        System.out.println("Full Name: " + fullName.getText());
        ExtentReport.Log(childExtentTest,Status.INFO, fullName.getText());

        IOSElement phoneNumber = (IOSElement) driver.findElementByXPath(ProfileObjects.phoneNumber_XPATH);
        System.out.println("Phone Number: " + phoneNumber.getText());
        ExtentReport.Log(childExtentTest,Status.INFO, phoneNumber.getText());

        IOSElement email = (IOSElement) driver.findElementByXPath(ProfileObjects.email_XPATH);
        System.out.println("Email: " + email.getText());
        ExtentReport.Log(childExtentTest,Status.INFO, email.getText());


        driver.findElementByXPath(ProfileObjects.btnClose_XPATH).click();
        ExtentReport.Log(extentTest,Status.PASS, "Check Profile");
    }

    @Test(priority = 3)
    public void closeLeftMenu() {

        ExtentTest extentTest = ExtentReport.shared().getExtentTest("Close LeftMenu");
        IOSElement leftMenu = (IOSElement) driver.findElementByXPath(HomeObjects.leftMenu_XPATH);
        actions.swipeElementByPercentage(leftMenu, Direction.LEFT);
        ExtentReport.Log(extentTest, Status.PASS, "Close Menu");
    }

    /**
     * test thử 1 case fail, ở đây ta cho element null
     */
    @Test(priority = 4)
    public void openFastBooking() throws IOException {

        ExtentTest extentTest = ExtentReport.shared().getExtentTest("Fast-Booking");
        boolean isSuccess = false;
        try {

            driver.findElementByAccessibilityId("fail").click();        // Cố tình cho fail
            isSuccess = true;
        } catch (Exception e) {
            isSuccess = false;
            Log.addLog(e.getMessage());
            String imgPath = actions.takeScreenshot("open-fast-booking");
            extentTest.fail("Open Fast-Booking", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
        } finally {
            ExtentReport.Log(extentTest, isSuccess, "Fast-Booking");
        }

    }
}
