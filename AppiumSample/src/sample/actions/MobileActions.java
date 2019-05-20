package actions;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class MobileActions {

    private IOSDriver driver;
    private TouchAction _touchAction;

    private TouchAction touchAction() {
        if (_touchAction == null) {
            _touchAction = new TouchAction(driver);
        }
        return _touchAction;
    }

    public MobileActions(IOSDriver driver) {
        this.driver = driver;
    }

    public void swipeElementByPercentage(IOSElement element, Direction direction) {

        double endXPercen = 0.9;
        int startX, startY, endX, endY;

        int screenWidth = driver.manage().window().getSize().width;
        int screenHeight = driver.manage().window().getSize().height;

        switch (direction) {
            case TOP:
                startX = element.getCenter().getX();
                startY = element.getSize().getHeight();

                endX = startX;
                endY = screenHeight - (int) (screenHeight * endXPercen);
                break;
            case RIGHT:
                startX = element.getLocation().getX();
                startY = element.getLocation().getY();

                endX = (int) (screenWidth * endXPercen);
                endY = startY;
                break;
            case LEFT:
                startX = element.getSize().getWidth();
                startY = element.getCenter().getY();

                endX = screenWidth - (int) (screenWidth * endXPercen);
                endY = startY;
                break;

            default:
                startX = element.getCenter().getX();
                startY = element.getLocation().getY();

                endX = startX;
                endY = (int) (screenHeight * endXPercen);
                break;
        }

        Point startPoint = new Point(startX, startY);
        PointOption startPointOption = new PointOption().withCoordinates(startPoint);

        Point endPoint = new Point(endX, endY);
        PointOption endPointOption = new PointOption().withCoordinates(endPoint);

        WaitOptions waitOptions = new WaitOptions().withDuration(Duration.ofMillis(200));

        touchAction().press(startPointOption).waitAction(waitOptions).moveTo(endPointOption).waitAction().release().perform();

    }

    //screen capture

    /**
     *
     * @param imageName
     * @return: AbsolutePath of Image. (Image has been saved at folder "Report/.../Images")
     */
    public String takeScreenshot(String imageName) {

        String pathName = Utils.createDir(Utils.getReportDir() + "/Images");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File image = new File(pathName, imageName + ".png");
        try {
            FileUtils.copyFile(scrFile, image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image.getAbsolutePath();
    }

}
