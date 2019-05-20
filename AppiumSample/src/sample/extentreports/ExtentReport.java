package extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utils.Log;
import utils.Utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class ExtentReport {


    private static ExtentReport instance;
    public static ExtentReport shared() {
        if (instance == null) {
            instance = new ExtentReport();
        }
        return instance;
    }

    private ExtentReports extent;
    private ExtentHtmlReporter htmlReporter;

    public ExtentReports getExtent() {
        if (extent == null) {
            extent = new ExtentReports();

            try {

                // setup HTML Report
                List<String> jsList = Files.readAllLines(Paths.get("js.js"));

                StringBuilder sb = new StringBuilder();

                for (String line : jsList)
                    sb.append(line).append("\n");
                String js = sb.toString();

                htmlReporter = new ExtentHtmlReporter(Utils.getReportDir() + "/report.html");
                htmlReporter.loadXMLConfig(new File("src/sample/extentreports/extent-config.xml"));
                htmlReporter.config().setChartVisibilityOnOpen(true);
                htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
                htmlReporter.setAppendExisting(false);
                htmlReporter.config().setJS(js);

                extent.attachReporter(htmlReporter);
                extent.setSystemInfo("OS Name",System.getProperty("os.name"));
                extent.setSystemInfo("OS Version",System.getProperty("os.version"));
                extent.setSystemInfo("Java Version",System.getProperty("java.version"));
                extent.setSystemInfo("User Name",System.getProperty("user.name"));

            } catch (Exception ex) {
                Log.addLog(ex.getMessage());
            }
        }
        return extent;
    }

    public ExtentTest getExtentTest(String name) {
        ExtentTest extentTest = getExtent().createTest(name);
        extentTest.assignCategory(name);
        extentTest.assignAuthor(System.getProperty("user.name"));

        return extentTest;
    }

    public ExtentTest getExtentTest(String name, String description) {

        ExtentTest extentTest = getExtent().createTest(name, description);
        extentTest.assignCategory(name);
        extentTest.assignAuthor(System.getProperty("user.name"));

        return extentTest;
    }

    public ExtentTest getChildExtentTest(ExtentTest extent, String name) {

        ExtentTest childExtentTest = extent.createNode(name);
        return childExtentTest;
    }

    public ExtentTest getChildExtentTest(ExtentTest extent, String name, String description) {

        ExtentTest childExtentTest = extent.createNode(name, description);
        return childExtentTest;
    }


    // Add log to report file
    public static void Log(ExtentTest extentTest, Status status, String description) {

        extentTest.log(status, "[<b>" + status.toString().toUpperCase() +"</b>] " + description);
    }
    public static void Log(ExtentTest extentTest, boolean isSuccess, String description) {

        Status status = Status.FAIL;

        if(isSuccess) {
            status = Status.PASS;
        }
        extentTest.log(status, "[<b>" + status.toString().toUpperCase() +"</>] " + description);
    }
}
