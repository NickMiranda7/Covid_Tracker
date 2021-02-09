package com.nick.CoronavirusTracker.models;

public class LocationStats {

	private String state;
	private String country;
	private String province;
	private int latestTotalCases;
	private int totalUSACases;
	private int changesSinceLastDayTotal;
	private int changesSinceLastDayLocal;
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalCases() {
		return latestTotalCases;
	}
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	public int getTotalUSACases() {
		return totalUSACases;
	}
	public void setTotalUSACases(int totalUSACases) {
		this.totalUSACases = totalUSACases;
	}
	
	public int getChangesSinceLastDayTotal() {
		return changesSinceLastDayTotal;
	}
	public void setChangesSinceLastDayTotal(int changesSinceLastDayTotal) {
		this.changesSinceLastDayTotal = changesSinceLastDayTotal;
	}
	public int getChangesSinceLastDayLocal() {
		return changesSinceLastDayLocal;
	}
	public void setChangesSinceLastDayLocal(int changesSinceLastDayLocal) {
		this.changesSinceLastDayLocal = changesSinceLastDayLocal;
	}
	@Override
	public String toString() {
		return "LocationStats [" 
				+ "province=" + province
				+ ", state=" + state + ", country=" + country +  ", latestTotalCases=" + latestTotalCases + 
				", total cases USA=" + totalUSACases + ", changes since last day total=" + changesSinceLastDayTotal 
				+ ", changes since last day local=" + changesSinceLastDayLocal + "]";
	}

	
	
	
}
