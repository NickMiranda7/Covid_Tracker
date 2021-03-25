package com.nick.CoronavirusTracker.models;

import java.util.ArrayList;
import java.util.List;

public class Country_Region {

	private String uid;
	private String name;
	private int totalCases = 0;
	private int totalDeaths = 0;
	private int totalRecovered = 0;
	private List<State_Province> State_Province;
	

	public Country_Region() {
		
	}
	

	public Country_Region(String uid, String name) {
		this.uid = uid;
		this.name = name;
		this.State_Province = new ArrayList<State_Province>();
		// this.CoronavirusStats = CoronavirusStats;
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
	
	public int calculateAllStateProvinceCases() {
		int totalCases = 0;
		for (State_Province stateProvince : this.State_Province) {
			totalCases = totalCases + stateProvince.getCoronavirusStats().get(stateProvince.getCoronavirusStats().size() - 1).getCases();
		}
		return totalCases;
	}
	
	public int calculateAllStateProvinceDeaths() {
		int totalDeaths = 0;
		for (State_Province stateProvince : this.State_Province) {
			totalDeaths = totalDeaths + stateProvince.getCoronavirusStats().get(stateProvince.getCoronavirusStats().size() - 1).getDeaths();
		}
		return totalDeaths;
	}
	
	public int calculateAllStateProvinceRecovered() {
		int totalRecovered = 0;
		for (State_Province stateProvince : this.State_Province) {
			totalRecovered = totalRecovered + stateProvince.getCoronavirusStats().get(stateProvince.getCoronavirusStats().size() - 1).getRecovered();
		}
		return totalRecovered;
	}
	
}
