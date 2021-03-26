package com.nick.CoronavirusTracker.models;

import java.util.ArrayList;
import java.util.List;

public class Country_Region {

	private String uid;
	private String name;
	private int totalCases = 0;
	private int totalDeaths = 0;
	private int totalRecovered = 0;
	private int changeCasesSinceLastDay = 0;
	private int changeDeathsSinceLastDay = 0;
	private int changeRecoveredSinceLastDay = 0;
	
	private List<State_Province> State_Province;

	public Country_Region() {
		
	}

	public Country_Region(String uid, String name) {
		this.uid = uid;
		this.name = name;
		this.State_Province = new ArrayList<State_Province>();
		// this.CoronavirusStats = CoronavirusStats;
	}
	
	public int getChangeCasesSinceLastDay() {
		return changeCasesSinceLastDay;
	}
	
	public void setChangeCasesSinceLastDay(int changeCasesSinceLastDayAdd) {
		this.changeCasesSinceLastDay = this.changeCasesSinceLastDay + changeCasesSinceLastDayAdd;
	}
	
	public int getChangeDeathsSinceLastDay() {
		return changeDeathsSinceLastDay;
	}
	
	public void setChangeDeathsSinceLastDay(int changeDeathsSinceLastDayAdd) {
		this.changeDeathsSinceLastDay = this.changeDeathsSinceLastDay + changeDeathsSinceLastDayAdd;
	}
	
	public int getChangeRecoveredSinceLastDay() {
		return changeRecoveredSinceLastDay;
	}
	
	public void setChangeRecoveredSinceLastDay(int changeRecoveredSinceLastDayAdd) {
		this.changeRecoveredSinceLastDay = this.changeRecoveredSinceLastDay + changeRecoveredSinceLastDayAdd;
	}
	
	public int getTotalCases() {
		return totalCases;
	}
	
	public void setTotalCases(int totalCasesAdd) {
		this.totalCases = this.totalCases + totalCasesAdd;
	}
	
	public int getTotalDeaths() {
		return totalDeaths;
	}
	
	public void setTotalDeaths(int totalDeathsAdd) {
		this.totalDeaths = this.totalDeaths + totalDeathsAdd;
	}
	
	public int getTotalRecovered() {
		return totalRecovered;
	}
	
	public void setTotalRecovered(int totalRecoveredAdd) {
		this.totalRecovered = this.totalRecovered + totalRecoveredAdd;
	}
	
	public String getUID() {
		return uid;
	}
	public void setUID(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<State_Province> getStates_Provinces() {
			return State_Province;
	}

	public void setStates_Provinces(List<State_Province> states_Province) {
		State_Province = states_Province;
	}	

	public void addState_Province(State_Province state) {
		this.State_Province.add(state);
	}
	
}
