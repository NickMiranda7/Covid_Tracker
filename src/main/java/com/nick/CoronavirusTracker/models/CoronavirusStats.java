package com.nick.CoronavirusTracker.models;


public class CoronavirusStats {

	// private LocalDate date;
	// private HashMap<LocalDate, Integer> casesRecorded = new HashMap<LocalDate, Integer>();
	private int cases;
	private int yesterdayCases;
	// private int deaths;
	// Recovered?
	
	public CoronavirusStats() {
		
	}


	public CoronavirusStats(int cases, int yesterdayCases) {
		this.cases = cases;
		this.yesterdayCases = yesterdayCases;
		// this.deaths = deaths;
	}


	public int Cases() {
		return cases;
	}

	public void Cases(int latestTotalCases) {
		this.cases = latestTotalCases;
	}


	public int getYesterdayCases() {
		return yesterdayCases;
	}


	public void setYesterdayCases(int yesterdayCases) {
		this.yesterdayCases = yesterdayCases;
	}

//
//	public int getDeaths() {
//		return deaths;
//	}
//
//
//	public void setDeaths(int deaths) {
//		this.deaths = deaths;
//	}

}
