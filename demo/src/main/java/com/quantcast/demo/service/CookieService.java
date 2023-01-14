package com.quantcast.demo.service;

import java.util.*;

//import org.springframework.stereotype.Service;

import com.quantcast.demo.model.Cookie;
import com.quantcast.demo.model.CookieFreq;

//@Service
public class CookieService {
	
	private static CookieService uniqueInstance=new CookieService();
	
	//Singleton
	private CookieService() {
		
	}
	
	public static CookieService getInstance() {
		return uniqueInstance;
	}

	private List<CookieFreq> getCookieFreqList(List<Cookie> cookieList, Date targetDate){
		List<CookieFreq> cookieFreqList=new LinkedList<CookieFreq>();
		Map<String, Integer> cookieFreqMap=new HashMap<>();
		for(Cookie cookie: cookieList) {
			if(cookie.getTimeStamp().equals(targetDate)) {
				cookieFreqMap.put(cookie.getCookie(), cookieFreqMap.getOrDefault(cookie.getCookie(), 0)+1);
			}
		}
		for(Map.Entry<String, Integer> entry: cookieFreqMap.entrySet()) {
			cookieFreqList.add(new CookieFreq(entry.getKey(), entry.getValue()));
		}
		return cookieFreqList;
	}
	
	public List<String> getMostActiveCookies(List<Cookie> cookieList, Date targetDate){
		List<String> mostActiveCookieList=new LinkedList<>();
		Queue<CookieFreq> maxHeap=new PriorityQueue<>((a,b)->Integer.compare(b.getFrequency(), a.getFrequency()));
		List<CookieFreq> cookieFreqList=getCookieFreqList(cookieList, targetDate);
		
		for(CookieFreq c: cookieFreqList) {
			maxHeap.add(c);
		}
		if(maxHeap.isEmpty()==false) {
			CookieFreq tempVar=maxHeap.poll();
			mostActiveCookieList.add(tempVar.getCookie());
			while(!maxHeap.isEmpty() && maxHeap.peek().getFrequency()==tempVar.getFrequency()) {
				mostActiveCookieList.add(maxHeap.poll().getCookie());
			}
		}
		return mostActiveCookieList;
	}
}
