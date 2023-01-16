package com.quantcast.demo.model;

public class CookieFreq {

    private String cookie;
    private int frequency;

    public CookieFreq(String cookie, int frequency) {
        super();
        this.cookie = cookie;
        this.frequency = frequency;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }


}
