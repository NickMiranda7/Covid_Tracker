package com.nick.CoronavirusTracker.models;

public class CoronavirusStats {

	private int latestTotalCases;
	private int changeInTotalSinceYesterday;
	
	//each set of stats belong to one province or state
	private Province_State Province_State;
	
	
	
	public CoronavirusStats() {
		
	}

	public CoronavirusStats(int latestTotalCases, int changeInTotalSinceYesterday,
			Province_State Province_State) {
		this.latestTotalCases = latestTotalCases;
		this.changeInTotalSinceYesterday = changeInTotalSinceYesterday;
		this.Province_State = Province_State;
	}

	public int getLatestTotalCases() {
		return latestTotalCases;
	}

	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}

	public int getChangeInTotalSinceYesterday() {
		return changeInTotalSinceYesterday;
	}

	public void setChangeInTotalSinceYesterday(int changeInTotalSinceYesterday) {
		this.changeInTotalSinceYesterday = changeInTotalSinceYesterday;
	}

	public Province_State getProvince_State() {
		return Province_State;
	}

	public void setProvince_State(Province_State province_State) {
		Province_State = province_State;
	}
	
}
