package com.example.android.quakereport;

public class Earthquake {
    private String magnitude;
    private String location;
    private long milliSeconds;
    private String url;

    public Earthquake(String magnitude, String location, long milliSeconds, String url) {
        this.magnitude = magnitude;
        this.location = location;
        this.milliSeconds = milliSeconds;
        this.url = url;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getMilliSeconds() {
        return milliSeconds;
    }

    public String getUrl() {
        return url;
    }
}
