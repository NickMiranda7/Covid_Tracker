package com.nick.CoronavirusTracker.models;


public class Province_State {

	private int UID;
	
	private Double Lat;
	private Double Long;
	private int province_statePopulation;
	
	//each state belongs to one country or region
	private Country_Region Country_Region;
	//each province or state has one set of CoronaVirus stats
	private CoronavirusStats CoronavirusStats;
	
	
	
	public Province_State() {
		
	}

	public Province_State(int UID, Double Lat, Double Long, 
							int province_statePopulation,
							Country_Region Country_Region,
							CoronavirusStats CoronavirusStats) {
		
		this.UID = UID;
		this.Lat = Lat;
		this.Long = Long;
		this.province_statePopulation = province_statePopulation;
		this.Country_Region = Country_Region;
		this.CoronavirusStats = CoronavirusStats;
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
	public int getProvince_statePopulation() {
		return province_statePopulation;
	}
	public void setProvince_statePopulation(int province_statePopulation) {
		this.province_statePopulation = province_statePopulation;
	}
	public Country_Region getCountry_Region() {
		return Country_Region;
	}
	public void setCountry_Region(Country_Region country_Region) {
		Country_Region = country_Region;
	}
	public CoronavirusStats getCoronavirusStats() {
		return CoronavirusStats;
	}
	public void setCoronavirusStats(CoronavirusStats coronavirusStats) {
		CoronavirusStats = coronavirusStats;
	}
	
	
}
