package com.quantcast.demo.utils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.quantcast.demo.exception.CookieException;
import com.quantcast.demo.model.Cookie;
import com.quantcast.demo.model.CookieCLIObject;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CookieUtils {

    private static final Logger logger = LogManager.getLogger(CookieUtils.class);

    public static CookieCLIObject getCookieCLIObject(String[] args) throws CookieException {
        Options options = new Options();
        Option dateOption = new Option("d", true, "Date for which most active cookies is needed");
        dateOption.setRequired(true);

        options.addOption(dateOption);
        CommandLineParser parser = new DefaultParser();
        CookieCLIObject cliObj;
        String errorMessage=null;
        try {
            CommandLine commandLine=parser.parse(options, args);
            String date=commandLine.getOptionValue("d");
            String[] remainingArgs=commandLine.getArgs();
            String filePath=remainingArgs[0];
            if(filePath==null)
                errorMessage="File Path is not provided";
            isFilePresent(filePath);
            isFileTypeValid(filePath);
            cliObj=new CookieCLIObject(filePath, date);
        }catch(ParseException e) {
            throw new CookieException("Parse Exception");
        }catch(ArrayIndexOutOfBoundsException e) {
            throw new CookieException("Date in Not Provided");
        }
        if(errorMessage!=null) {
            throw new CookieException(errorMessage);
        }
        return cliObj;
    }

    /**
     * Check if file exits otherwise throw and Exception
     * @param fileName
     * @return
     */
    public static boolean isFilePresent(String fileName)throws CookieException{
        File file = new File(fileName);
        boolean exists = file.exists();
        if (!exists) {
            throw new CookieException("File doesn't exist");
        }
        return true;
    }

    /**
     * Check if file format is valid.
     * @param fileName
     * @return
     * @throws CookieException
     */
    public static boolean isFileTypeValid(String fileName) throws CookieException{
        if (!fileName.endsWith(".csv")) {
            throw new CookieException("File Type is not valid");
        }
        return true;
    }

    public static Date getDateFromString(String dateString) throws CookieException {
        String startDateString = dateString;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = df.parse(startDateString);
        } catch (Exception e) {
            logger.error("error while parsing date string ",e);
            throw new CookieException("date format is invalid");
        }
        return startDate;
    }

    /**
     * Read the file and return List of Cookies
     * @param filePath
     * @return
     */
    public static List<Cookie> getCookieList(String filePath) throws IOException, CookieException {
        List<Cookie> cookieList = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                String[] strings = line.split(",");
                Cookie cookie = new Cookie(strings[0], CookieUtils.getDateFromString(strings[1]));
                cookieList.add(cookie);
                line = br.readLine();
            }
        } catch (IOException e) {
            logger.error("Error while reading file ", e);
            e.printStackTrace();
            throw e;
        } catch (CookieException e) {
            throw e;
        }
        return cookieList;
    }
}
