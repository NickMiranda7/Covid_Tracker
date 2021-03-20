package com.nick.CoronavirusTracker.models;

import java.util.ArrayList;
import java.util.Date;

public class Header {

	private String country_region;
	private String state_province;
	private String Long;
	private String Lat;
	private String county;
	private String recovered;
	private Date startingDate;
	private int amountOfDates;
	private ArrayList<String> dates;
	

	public Header() {

	}

	public Header(String country_region, String state_province, String Lat, String Long) {
		this.country_region = country_region;
		this.state_province = state_province;
		this.Lat = Lat;
		this.Long = Long;
	}

	public String getCountry_region() {
		return country_region;
	}

	public void setCountry_region(String country_region) {
		this.country_region = country_region;
	}

	public String getState_province() {
		return state_province;
	}

	public void setState_province(String state_province) {
		this.state_province = state_province;
	}

	public String getLong() {
		return Long;
	}

	public void setLong(String l) {
		Long = l;
	}

	public String getLat() {
		return Lat;
	}

	public void setLat(String lat) {
		Lat = lat;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public ArrayList<String> getDates() {
		return dates;
	}

	public void setDates(ArrayList<String> dates) {
		this.dates = dates;
	}


	public String getRecovered() {
		return recovered;
	}

	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}

	public int getAmountOfDates() {
		return amountOfDates;
	}

	public void setAmountOfDates(int amountOfDates) {
		this.amountOfDates = amountOfDates;
	}


}
