package com.nick.CoronavirusTracker.models;

import java.util.ArrayList;
import java.util.List;

public class State_Province{

	private String UID;
	private String name;
	private Double Lat;
	private Double Long;
	private int totalCases = 0;
	private int totalDeaths = 0;
	private int changeCasesSinceLastDay = 0;
	private int changeDeathsSinceLastDay = 0;

	private List<CoronavirusStats> CoronavirusStats;
	private List<USAStateCounty> USAStateCounties;

	
	public State_Province() {
		
	}
	
	public State_Province(String UID, String name) {
		this.UID = UID;
		this.name = name;
		this.USAStateCounties = new ArrayList<USAStateCounty>();
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

	public void setUID(String uID) {
		UID = uID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<USAStateCounty> getStateCounties() {
		return USAStateCounties;
	}

	public void setStateCounty(List<USAStateCounty> stateCounty) {
		this.USAStateCounties = stateCounty;
	}

	public void addStateCounty(USAStateCounty stateCounty) {
		this.USAStateCounties.add(stateCounty);
	}

	public Double getLat() {
		return Lat;
	}

	public void setLat(Double lat) {
		this.Lat = lat;
	}

	public Double getLong() {
		return Long;
	}

	public void setLong(Double Long) {
		this.Long = Long;
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
