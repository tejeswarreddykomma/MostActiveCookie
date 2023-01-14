package com.quantcast.demo.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;

import com.quantcast.demo.exception.CookieException;
import com.quantcast.demo.model.Cookie;
import com.quantcast.demo.model.CookieCLIObject;
import com.quantcast.demo.service.CookieService;
import com.quantcast.demo.utils.CookieUtils;

//@Controller
public class CookieController {
	
	private static Logger logger = LoggerFactory.getLogger(CookieController.class);
	private static CookieController uniqueInstance=new CookieController();
	
	//Singleton
	private CookieController(){
		
	}
	
	public static CookieController getInstance() {
		return uniqueInstance;
	}
	//@Autowired
	//private static final CookieService cookieService;

    public void getMostFrequentCookie(String[] args){
        try {

            CookieCLIObject cliObject=CookieUtils.getCookieCLIObject(args);
            List<Cookie> cookieList = CookieUtils.getCookieList(cliObject.getFilePath());
            List<String> mostActiveCookies = CookieService.getInstance().getMostActiveCookies(cookieList,  CookieUtils.getDateFromString(cliObject.getDate()));
            for (String cookie : mostActiveCookies) {
                System.out.println(cookie);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (CookieException e) {
        	logger.error(e.getMessage());
        }
    }
}
