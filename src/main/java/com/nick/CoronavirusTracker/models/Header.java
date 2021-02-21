package com.nick.CoronavirusTracker.models;



public class Header {

	private String country_region;
	private String state_province;
	private String Long;
	private String Lat;
	private String cases;
	private String deaths;
	
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

	public String getCases() {
		return cases;
	}

	public void setCases(String cases) {
		this.cases = cases;
	}

	public String getDeaths() {
		return deaths;
	}

	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}




}
