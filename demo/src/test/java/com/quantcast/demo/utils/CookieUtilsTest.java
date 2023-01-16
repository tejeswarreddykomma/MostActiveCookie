package com.quantcast.demo.utils;

import com.quantcast.demo.exception.CookieException;
import com.quantcast.demo.model.CookieCLIObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CookieUtilsTest {
    @BeforeEach
    void setUp() {
        File csvOutputFile = new File("cookie_log.csv");
    }

    @Test
    void testGetCookieCLIObject(){
        String[] args;
        CookieCLIObject cliObj;
        try {
            args=new String[3];
            args[0]="cookie_log.csv";
            args[1]="-d";
            args[2]="2018-12-09";
            cliObj=CookieUtils.getCookieCLIObject(args);
            assertEquals(args[0], cliObj.getFilePath());
            assertEquals(args[2], cliObj.getDate());
        }catch(Exception e){
            assertEquals(e.getClass(), CookieException.class);
        }
        try {
            args=new String[2];
            args[0]="cookie_log.csv";
            args[1]="-d";
            cliObj=CookieUtils.getCookieCLIObject(args);
        }catch(Exception e) {
            assertEquals(e.getClass(), CookieException.class);
        }

        try {
            args=new String[2];
            args[0]="-d";
            args[1]="cookie_log.csv";
            cliObj=CookieUtils.getCookieCLIObject(args);
        }catch(Exception e) {
            assertEquals(e.getClass(), CookieException.class);
        }

        try {
            args=null;
            cliObj=CookieUtils.getCookieCLIObject(args);
        }catch(Exception e) {
            assertEquals(e.getClass(), CookieException.class);
        }
    }

    @Test
    void testIsFilePresent() {
        try {
            assertEquals(true, CookieUtils.isFilePresent("cookie_log.csv"));
        }catch(Exception e) {
            assertEquals(e.getClass(), CookieException.class);
        }

        try {
            CookieUtils.isFilePresent("cookie_log2.csv");
        }catch(Exception e) {
            assertEquals(e.getClass(), CookieException.class);
        }
    }

    @Test
    void testIsFileTypeValid(){
        try {
            assertEquals(true, CookieUtils.isFileTypeValid("cookie_log.csv"));
        }catch(Exception e) {
            assertEquals(e.getClass(), CookieException.class);
        }

        try {
            CookieUtils.isFileTypeValid("cookie_log.txt");
        }catch(Exception e) {
            assertEquals(e.getClass(), CookieException.class);
        }
    }

    @Test
    void testGetDateFromString(){
        try {
            Date date=CookieUtils.getDateFromString("2018-12-09");
        }catch(Exception e) {
            assertEquals(e.getClass(), CookieException.class);
        }
    }
}