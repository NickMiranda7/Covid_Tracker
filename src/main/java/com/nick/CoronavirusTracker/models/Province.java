package com.nick.CoronavirusTracker.models;


public class Province {

	private String UID;
	private String name;
	private Double Lat;
	private Double Long;
	
	//each province has one set of CoronaVirus stats
	private CoronavirusStats CoronavirusStats;
	
	
	
	public Province() {
		
	}

	public Province(String UID, String name, Double Lat, Double Long, CoronavirusStats CoronavirusStats) {
		this.name = name;
		this.UID = UID;
		this.Lat = Lat;
		this.Long = Long;
		this.CoronavirusStats = CoronavirusStats;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUID() {
		return UID;
	}
	public void setUID(String UID) {
		this.UID = UID;
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
