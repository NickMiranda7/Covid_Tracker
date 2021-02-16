package com.nick.CoronavirusTracker.models;

public class States {

	private int UID;
	private String name;
	private Double Lat;
	private Double Long;
	
	//each province has one set of CoronaVirus stats
	private CoronavirusStats CoronavirusStats;
	
	public States() {
		
	}

	
	public States(int UID, String name, Double Lat, Double Long, 
			CoronavirusStats coronavirusStats) {
		this.UID = UID;
		this.name = name;
		this.Lat = Lat;
		this.Long = Long;
		CoronavirusStats = coronavirusStats;
	}


	public int getUID() {
		return UID;
	}

	public void setUID(int uID) {
		UID = uID;
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