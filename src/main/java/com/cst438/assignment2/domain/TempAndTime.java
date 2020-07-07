package com.cst438.assignment2.domain;

import java.time.Instant;
import java.time.ZoneOffset;

public class TempAndTime {
	public double temp;
	public long time;
	public int timezone;

	public TempAndTime(double temp, long time, int timezone){
		this.temp = temp;
		this.time = time;
		this.timezone = timezone;
	}

	public double getTemp() { return temp; }

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public long getTime() { return time; }

	public void setTime(long time) {
		this.time = time;
	}

	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	public double getFahr() {
		return Math.round(((getTemp() - 273.15) * 9.0 / 5.0 + 32.0) * 100) / 100;
	}

	public String getLocalTime() {
//		long offset = getTime() + getTimezone();
//		Instant javaTime = Instant.ofEpochSecond(offset);
//		int h = javaTime.atZone(ZoneOffset.UTC).getHour();
//		int m = javaTime.atZone(ZoneOffset.UTC).getMinute();
//		String localTime = h + ":" + m;
//		return localTime;
		long timeOffset = getTime() + getTimezone();
		Instant unixTime = Instant.ofEpochSecond(timeOffset);
		int hour = unixTime.atZone(ZoneOffset.UTC).getHour();
		int minute = unixTime.atZone(ZoneOffset.UTC).getMinute();
		String strTime = hour + ":" + minute;
		return strTime;
	}
 }
