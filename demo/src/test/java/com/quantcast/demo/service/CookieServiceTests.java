package com.quantcast.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.quantcast.demo.exception.CookieException;
import com.quantcast.demo.model.Cookie;
import com.quantcast.demo.utils.CookieUtils;

class CookieServiceTests {

	List<Cookie> testCookieList;
	
	@Before
    void setup() throws CookieException {
        testCookieList = new LinkedList<>();
        testCookieList.add(new Cookie("AtY0laUfhglK3lC7", CookieUtils.getDateFromString("2018-12-09T14:19:00+00:00")));
        testCookieList.add(new Cookie("SAZuXPGUrfbcn5UA", CookieUtils.getDateFromString("2018-12-09T10:13:00+00:00")));
        testCookieList.add(new Cookie("5UAVanZf6UtGyKVS", CookieUtils.getDateFromString("2018-12-09T07:25:00+00:00")));
        testCookieList.add(new Cookie("AtY0laUfhglK3lC7", CookieUtils.getDateFromString("2018-12-09T06:19:00+00:00")));
        testCookieList.add(new Cookie("SAZuXPGUrfbcn5UA", CookieUtils.getDateFromString("2018-12-08T22:03:00+00:00")));
        testCookieList.add(new Cookie("4sMM2LxV07bPJzwf", CookieUtils.getDateFromString("2018-12-08T21:30:00+00:00")));
        testCookieList.add(new Cookie("fbcn5UAVanZf6UtG", CookieUtils.getDateFromString("2018-12-08T09:30:00+00:00")));
        testCookieList.add(new Cookie("4sMM2LxV07bPJzwf", CookieUtils.getDateFromString("2018-12-07T23:30:00+00:00")));
    }
    
    @Test
    void testGetMostActiveCookies() {
    	try {
    		this.setup();
    		Date targetDate=CookieUtils.getDateFromString("2018-12-09");
    		List<String> actualMostActiveCookieList=CookieService.getInstance().getMostActiveCookies(testCookieList, targetDate);
    		List<String> expectedMostActiveCookieList=new LinkedList<>();
    		expectedMostActiveCookieList.add("AtY0laUfhglK3lC7");
    		assertLinesMatch(expectedMostActiveCookieList, actualMostActiveCookieList);
    		
    	}catch(Exception e) {
			assertEquals(e.getClass(), CookieException.class);
		}
    	
    	try {
    		List<String> mostActiveCookieList=CookieService.getInstance().getMostActiveCookies(testCookieList, CookieUtils.getDateFromString("2018-12-08"));
    		Set<String> mostActiveCookieSet=new HashSet<>();
    		mostActiveCookieSet.add("SAZuXPGUrfbcn5UA");
    		mostActiveCookieSet.add("4sMM2LxV07bPJzwf");
    		mostActiveCookieSet.add("fbcn5UAVanZf6UtG");
    		assertEquals(mostActiveCookieSet.size(), mostActiveCookieList.size());
    		for(String str: mostActiveCookieList) {
    			assertTrue(mostActiveCookieSet.contains(str));
    		}
    		
    	}catch(Exception e) {
			assertEquals(e.getClass(), CookieException.class);
		}
    	
    }
}
