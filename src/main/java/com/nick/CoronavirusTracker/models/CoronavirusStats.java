package com.nick.CoronavirusTracker.models;

import java.util.Date;

public class CoronavirusStats {

	
	private Date date;
	private int cases;
	private int deaths;
	private int recovered;
	
	public CoronavirusStats() {
		
	}

	public CoronavirusStats(int cases) {
		this.cases = cases;
		// this.deaths = deaths;
	}

	public int getCases() {
		return cases;
	}

	public void setCases(int latestTotalCases) {
		this.cases = latestTotalCases;
	}

	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getDeaths() {
		return deaths;
	}


	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}


	public int getRecovered() {
		return recovered;
	}


	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}

}
