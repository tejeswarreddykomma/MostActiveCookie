package com.quantcast.demo.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.util.Assert;

import com.quantcast.demo.exception.CookieException;
import com.quantcast.demo.model.CookieCLIObject;

class CookieUtilsTests {
	
	
    @BeforeTestClass
    private void setup() {
    	File csvOutputFile = new File("cookie_log.csv");
    }
	
	@Test
	void testGetCookieCLIObject() throws CookieException{
		String[] args=new String[3];
		args[0]="cookie_log.csv";
		args[1]="-d";
		args[2]="2018-12-09";
		CookieCLIObject cliObj=CookieUtils.getCookieCLIObject(args);
		assertEquals(args[0], cliObj.getFilePath());
		assertEquals(args[2], cliObj.getDate());
		
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
	}
}
