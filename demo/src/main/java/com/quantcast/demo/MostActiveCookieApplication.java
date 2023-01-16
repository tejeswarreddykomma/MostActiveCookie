package com.quantcast.demo;

import com.quantcast.demo.controller.CookieController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MostActiveCookieApplication {

	private static Logger logger= LoggerFactory.getLogger(MostActiveCookieApplication.class);
	public static void main(String[] args) {
		logger.info("Application Started");
		//SpringApplication.run(MostActiveCookieApplication.class, args);
		CookieController.getInstance().getMostFrequentCookie(args);
		logger.info("Application Finished");
	}

}
