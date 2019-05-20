package utils;

import constants.Constants;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {

    private static String currentTime = new SimpleDateFormat("dd-MM-yyyy-HHmmss").format(Calendar.getInstance().getTime());
    private static String reportDir;

    /**
     * Create a directory at path name
     * @return : return path of directory
     */
    public static String getReportDir() {

        if (reportDir == null) {
            String pathName = Constants.rootReport + currentTime;

            File theDir = new File(pathName);

            if (!theDir.exists()) {

                Log.println("CREATING A DIRECTORY");

                try{

                    theDir.mkdirs();
                    Log.println("a directory is created: " + theDir.getPath());
                }
                catch(SecurityException se){

                    Log.println("CREATE A DIR IS FAILED!" + se.getMessage());
                    return null;
                }
            }

            reportDir = theDir.getPath();
        }
        return reportDir;
    }

    /**
     * create Dir
     */

    public static String createDir(String dirName) {

        File theDir = new File(dirName);

        if (!theDir.exists()) {

            Log.println("CREATING A DIRECTORY");

            try{

                theDir.mkdirs();
                Log.println("a directory is created: " + theDir.getPath());
            }
            catch(SecurityException se){

                Log.println("CREATE A DIR IS FAILED!" + se.getMessage());
                return null;
            }
        }

        return theDir.getPath();
    }
}
