package com.nick.CoronavirusTracker.models;


public class USAStateCounty {
	
	private int UID;
	private String name;
	private Double Lat;
	private Double Long;
	
	//each province has one set of CoronaVirus stats
	private CoronavirusStats CoronavirusStats;
		
	
	
	public USAStateCounty() {
	
	}
	

	public USAStateCounty(int UID, String name, Double Lat, Double Long, CoronavirusStats CoronavirusStats) {
		
		this.UID = UID;
		this.name = name;
		this.Lat = Lat;
		this.Long = Long;
		this.CoronavirusStats = CoronavirusStats;
		
	}


	public int getUID() {
		return UID;
	}

	public void setUID(int UID) {
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


	public CoronavirusStats getCoronavirusStats() {
		return CoronavirusStats;
	}

	public void setCoronavirusStats(CoronavirusStats coronavirusStats) {
		CoronavirusStats = coronavirusStats;
	}
}
