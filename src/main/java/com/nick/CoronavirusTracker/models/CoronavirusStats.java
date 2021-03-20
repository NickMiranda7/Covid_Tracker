package com.nick.CoronavirusTracker.models;

import java.util.Date;

public class CoronavirusStats {

	
	private String date;
	private Integer cases = null;
	private Integer deaths = null;
	private Integer recovered = null;
	
	public CoronavirusStats() {
		
	}

	public CoronavirusStats(String date, Integer cases) {
		this.date = date;
		this.cases = cases;
	}

	public Integer getCases() {
		return cases;
	}

	public void setCases(Integer latestTotalCases) {
		this.cases = latestTotalCases;
	}

	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public Integer getDeaths() {
		return deaths;
	}


	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}


	public Integer getRecovered() {
		return recovered;
	}


	public void setRecovered(Integer recovered) {
		this.recovered = recovered;
	}

}
