package com.nick.CoronavirusTracker.models;

import java.util.ArrayList;
import java.util.List;

public class Country_Region {

	private String uid;
	private String name;
	private List<Province> Provinces;
	private List<States> States;
	// private CoronavirusStats CoronavirusStats;
	
	
	
	public Country_Region() {
		
	}
	
	public Country_Region(String uid, String name) {
		this.uid = uid;
		this.name = name;
		this.Provinces = new ArrayList<Province>();
		this.States = new ArrayList<States>();
		// this.CoronavirusStats = CoronavirusStats;
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

	public List<Province> getProvince() {
		return Provinces;
	}
	public void setProvince(List<Province> province) {
		Provinces = province;
	}
	
	public List<States> getStates() {
		return States;
	}

	public void setStates(List<States> states) {
		States = states;
	}
	

//	public CoronavirusStats getCoronavirusStats() {
//		return CoronavirusStats;
//	}
//
//	public void setCoronavirusStats(CoronavirusStats coronavirusStats) {
//		CoronavirusStats = coronavirusStats;
//	}

	public void addProvince(Province province) {
		this.Provinces.add(province); 
	}
	public void addState(States state) {
		this.States.add(state);
	}
	
}
