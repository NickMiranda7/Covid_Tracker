package com.nick.CoronavirusTracker.models;

public class CoronavirusStats {

	private int cases;
	private int yesterdayCases;
	
	public CoronavirusStats() {
		
	}

	public CoronavirusStats(int cases, int yesterdayCases) {
		this.cases = cases;
		this.yesterdayCases = yesterdayCases;
	}

	public int Cases() {
		return cases;
	}

	public void Cases(int latestTotalCases) {
		this.cases = latestTotalCases;
	}

	public int YesterdayCases() {
		return yesterdayCases;
	}

	public void YesterdayCases(int yesterdayCases) {
		this.yesterdayCases = yesterdayCases;
	}
	
}
