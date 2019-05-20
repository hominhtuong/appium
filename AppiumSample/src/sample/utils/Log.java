package utils;

import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Log {
    public static void addLog(String message) {

        try {
            // create Log file
            String logPath = Utils.getReportDir() + "/log.txt";

            File file = new File(logPath);

            if (!file.exists()) {

                println("CREATING a LOG file");

                try {

                    FileWriter fw = new FileWriter(logPath);
                    BufferedWriter bw = new BufferedWriter(fw);
                    String title = "\nAutomation Test Log.\n =============================================\n\n";
                    bw.write(title);
                    bw.close();

                    println("Logfile has created");
                } catch (IOException e) {
                    e.printStackTrace();
                    println("[Error] When create log file, please check: " + e);
                }
            }


            String Log_line = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

            Path path = Paths.get(logPath);

            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            int position = lines.size();
            String log = "[" + Log_line + "]: " + message;

            lines.add(position, log);
            Files.write(path, lines, StandardCharsets.UTF_8);

            System.out.println(log);

        } catch (Exception ex) {
            print("CANNOT CREATE A DIRECTORY");
        }

    }
    public static void takeScreenShot(String imageName, IOSDriver driver) {

        // create log_report folder

        String pathName = Utils.createDir(Utils.getReportDir() + "/Images");

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(pathName, imageName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * quickly print log to console
     * @param log
     */

    public static void print(String log) {
        System.out.print("MTLog:========" + log);
    }
    public static void print(int log) {
        System.out.print("MTLog:========" + log);
    }
    public static void print(long log) {
        System.out.print("MTLog:========" + log);
    }
    public static void print(char log) {
        System.out.print("MTLog:========" + log);
    }
    public static void print(boolean log) {
        System.out.print("MTLog:========" + log);
    }
    public static void print(Object log) {
        System.out.print("MTLog:========" + log);
    }


    public static void println(String log) {
        System.out.println("MTLog:========" + log);
    }
    public static void println(int log) {
        System.out.println("MTLog:========" + log);
    }
    public static void println(long log) {
        System.out.println("MTLog:========" + log);
    }
    public static void println(char log) {
        System.out.println("MTLog:========" + log);
    }
    public static void println(boolean log) {
        System.out.println("MTLog:========" + log);
    }
    public static void println(Object log) {
        System.out.println("MTLog:========" + log);
    }
}
