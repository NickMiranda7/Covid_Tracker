package com.nick.CoronavirusTracker.models;

import java.util.ArrayList;
import java.util.List;

public class World {

	private String uid;
	private String name;
	private Header header;
	private int totalCases = 0;
	private int totalDeaths = 0;
	private int totalRecovered = 0;
	private int changeCasesSinceLastDay = 0;
	private int changeDeathsSinceLastDay = 0;
	private int changeRecoveredSinceLastDay = 0;
	private boolean recovered = false;
	

	private List<Country_Region> country_Regions;

	public World() {

	}

	public World(String uid, String name) {
		
		this.uid = uid;
		this.name = name;
		this.country_Regions = new ArrayList<Country_Region>();
	}

	public int getChangeCasesSinceLastDay() {
		return changeCasesSinceLastDay;
	}
	
	public void setChangeCasesSinceLastDay(int changeCasesSinceLastDayAdd) {
		this.changeCasesSinceLastDay = this.changeCasesSinceLastDay + changeCasesSinceLastDayAdd;
	}
	
	public int getChangeDeathsSinceLastDay() {
		return changeDeathsSinceLastDay;
	}
	
	public void setChangeDeathsSinceLastDay(int changeDeathsSinceLastDayAdd) {
		this.changeDeathsSinceLastDay = this.changeDeathsSinceLastDay + changeDeathsSinceLastDayAdd;
	}
	
	public int getChangeRecoveredSinceLastDay() {
		return changeRecoveredSinceLastDay;
	}
	
	public void setChangeRecoveredSinceLastDay(int changeRecoveredSinceLastDayAdd) {
		this.changeRecoveredSinceLastDay = this.changeRecoveredSinceLastDay + changeRecoveredSinceLastDayAdd;
	}

	public int getTotalCases() {
		return totalCases;
	}
	
	public void setTotalCases(int totalCasesAdd) {
		this.totalCases = this.totalCases + totalCasesAdd;
	}
	
	public int getTotalDeaths() {
		return totalDeaths;
	}
	
	public void setTotalDeaths(int totalDeathsAdd) {
		this.totalDeaths = this.totalDeaths + totalDeathsAdd;
	}
	
	public int getTotalRecovered() {
		return totalRecovered;
	}
	
	public void setTotalRecovered(int totalRecoveredAdd) {
		this.totalRecovered = this.totalRecovered + totalRecoveredAdd;
	}
	
	public String getUID() {
		return uid;
	}

	public void setUID(String uID) {
		uid = uID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Country_Region> getCountry_Regions() {
		return country_Regions;
	}

	public void setCountry_Regions(List<Country_Region> country_Regions) {
		this.country_Regions = country_Regions;
	}
	
	public void addCountry(Country_Region country_Region) {
		this.country_Regions.add(country_Region); 
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public boolean isRecovered() {
		return recovered;
	}

	public void setRecovered(boolean recovered) {
		this.recovered = recovered;
	}
}
