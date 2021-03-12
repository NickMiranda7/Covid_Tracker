package com.nick.CoronavirusTracker.models;

import java.util.ArrayList;
import java.util.List;

public class State_Province{

	private String UID;
	private String name;
	private Double Lat;
	private Double Long;
	private CoronavirusStats CoronavirusStats;
	
	private List<USAStateCounty> USAStateCounties;

	
	public State_Province() {
		
	}
	
	public State_Province(String UID, String name) {
		this.UID = UID;
		this.name = name;
		this.USAStateCounties = new ArrayList<USAStateCounty>();
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

	public CoronavirusStats getCoronavirusStats() {
		return CoronavirusStats;
	}

	public void setCoronavirusStats(CoronavirusStats coronavirusStats) {
		CoronavirusStats = coronavirusStats;
	}
	
	
}