package com.quantcast.demo.utils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	private static Logger logger = LoggerFactory.getLogger(CookieUtils.class);
	
    /*public static String getFilePathFromArguments(String[] args){
        String filePath;
        if(args.length==0)
            throw new RuntimeException("File name is not provided");
        filePath=args[0];
        if (!isFileTypeValid(filePath)){
            throw new RuntimeException("Unsupported file type");
        }
        if(!isFilePresent(filePath))
        	throw new RuntimeException("File doesn't exist");
        return filePath;
    }
    
    public static String getDateFromArguments(String[] args){
        String date;
        if (args.length<=1 || !args[1].equals("-d")){
            throw new RuntimeException("Invalid input arguments");
        }
        try {
            date = args[2];
        } catch (Exception e){
            throw new RuntimeException("Date is not provided");
        }
        if (!isDateValid(date)){
            throw new RuntimeException("Unsupported Date format");
        }
        return date;
    }*/
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
	/*
     * Check if date is valid otherwise throw an Exception
     * @param dateString
     * @return
     
    
    public static boolean isDateValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (Exception e) {
        	return false;
        }
        return true;
    }*/
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
        } catch (java.text.ParseException e) {
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
