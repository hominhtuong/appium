package test;

import actions.Direction;
import io.appium.java_client.ios.IOSElement;
import objects.HomeObjects;
import objects.ProfileObjects;
import org.testng.annotations.Test;

public class TestClass extends BaseTest {

    @Test()
    public void openLeftMenu() {

        driver.findElementByXPath(HomeObjects.openLeftMenu_XPATH).click();
    }

    @Test(priority = 1)
    public void openProfile() {

        driver.findElementByXPath(ProfileObjects.openProfile_XPATH).click();
    }

    @Test(priority = 2)
    public void checkProfile() {

        IOSElement nickName = (IOSElement) driver.findElementByXPath(ProfileObjects.nickName_XPATH);
        System.out.println("Nick Name: " + nickName.getText());

        IOSElement fullName = (IOSElement) driver.findElementByXPath(ProfileObjects.fullName_XPATH);
        System.out.println("Full Name: " + fullName.getText());

        IOSElement phoneNumber = (IOSElement) driver.findElementByXPath(ProfileObjects.phoneNumber_XPATH);
        System.out.println("Phone Number: " + phoneNumber.getText());

        IOSElement email = (IOSElement) driver.findElementByXPath(ProfileObjects.email_XPATH);
        System.out.println("Email: " + email.getText());


        driver.findElementByXPath(ProfileObjects.btnClose_XPATH).click();
    }

    @Test(priority = 3)
    public void closeLeftMenu() {

        IOSElement leftMenu = (IOSElement) driver.findElementByXPath(HomeObjects.leftMenu_XPATH);
        actions.swipeElementByPercentage(leftMenu, Direction.LEFT);
    }

    @Test(priority = 4)
    public void openFastBooking() {
        driver.findElementByAccessibilityId(HomeObjects.ic_fast_booking_ID).click();
    }
}
