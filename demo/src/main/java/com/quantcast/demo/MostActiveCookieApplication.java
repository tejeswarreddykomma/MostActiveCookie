package com.quantcast.demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.quantcast.demo.controller.CookieController;

@SpringBootApplication
public class MostActiveCookieApplication {

	private static Logger logger = LoggerFactory.getLogger(MostActiveCookieApplication.class);

	public static void main(String[] args) {
		logger.info("Application Started");
		//SpringApplication.run(MostActiveCookieApplication.class, args);
		CookieController.getInstance().getMostFrequentCookie(args);
		logger.info("Application Finished");
	}

}
