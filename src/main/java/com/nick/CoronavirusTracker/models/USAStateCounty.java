package com.nick.CoronavirusTracker.models;

import java.util.List;

public class USAStateCounty {
	
	private String UID;
	private String name;
	private Double Lat;
	private Double Long;
	private int totalCases = 0;
	private int totalDeaths = 0;
	private int changeCasesSinceLastDay = 0;
	private int changeDeathsSinceLastDay = 0;
	
	private List<CoronavirusStats> CoronavirusStats;
		
	
	
	public USAStateCounty() {
	
	}
	

	public USAStateCounty(String UID, String name, Double Lat, Double Long, List<CoronavirusStats> CoronavirusStats) {
		
		this.UID = UID;
		this.name = name;
		this.Lat = Lat;
		this.Long = Long;
		this.CoronavirusStats = CoronavirusStats;
		
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


	public String getUID() {
		return UID;
	}

	public void setUID(String UID) {
		this.UID = UID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLat() {
		return Lat;
	}

	public void setLat(Double lat) {
		Lat = lat;
	}

	public Double getLong() {
		return Long;
	}

	public void setLong(Double l) {
		Long = l;
	}


	public List<CoronavirusStats> getCoronavirusStats() {
		return CoronavirusStats;
	}

	public void setCoronavirusStats(List<CoronavirusStats> coronavirusStats) {
		this.CoronavirusStats = coronavirusStats;
	}
	
	public void addCoronavirusStats(CoronavirusStats coronavirusStats) {
		this.CoronavirusStats.add(coronavirusStats);
	}
}
