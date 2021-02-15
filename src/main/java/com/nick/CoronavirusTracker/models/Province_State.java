package com.nick.CoronavirusTracker.models;


public class Province_State {

	private int UID;
	private String name;
	private Double Lat;
	private Double Long;
	private int province_population;
	
	//each province or state has one set of CoronaVirus stats
	private CoronavirusStats CoronavirusStats;
	
	
	
	public Province_State() {
		
	}

	public Province_State(int UID, String name, Double Lat, Double Long, 
							CoronavirusStats CoronavirusStats) {
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

	public int getUID() {
		return UID;
	}
	public void setUID(int uID) {
		UID = uID;
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
	public int getProvince_population() {
		return province_population;
	}
	public void setProvince_population(int province_statePopulation) {
		this.province_population = province_statePopulation;
	}
	public CoronavirusStats getCoronavirusStats() {
		return CoronavirusStats;
	}
	public void setCoronavirusStats(CoronavirusStats coronavirusStats) {
		CoronavirusStats = coronavirusStats;
	}
	
	public void addToPopulation(int provincePopulation) {
		this.province_population = this.province_population + provincePopulation;
	}
	
	
}
