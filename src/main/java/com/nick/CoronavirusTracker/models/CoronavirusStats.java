package com.nick.CoronavirusTracker.models;

import java.time.*;

public class CoronavirusStats {

	private LocalDate date;
	private int cases;
	// private int yesterdayCases;
	private int deaths;
	// Recovered?
	
	public CoronavirusStats() {
		
	}


	public CoronavirusStats(LocalDate date, int cases, int deaths) {
		this.date = date;
		this.cases = cases;
		this.deaths = deaths;
	}


	public int Cases() {
		return cases;
	}

	public void Cases(int latestTotalCases) {
		this.cases = latestTotalCases;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public int getDeaths() {
		return deaths;
	}


	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	
}
