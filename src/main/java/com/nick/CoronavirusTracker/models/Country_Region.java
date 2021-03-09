package com.nick.CoronavirusTracker.models;

import java.util.ArrayList;
import java.util.List;

public class Country_Region {

	private String uid;
	private String name;
	private List<State_Province> State_Province;
	// private CoronavirusStats CoronavirusStats;
	
	
	
	public Country_Region() {
		
	}
	
	public Country_Region(String uid, String name) {
		this.uid = uid;
		this.name = name;
		this.State_Province = new ArrayList<State_Province>();
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
	
	public List<State_Province> getStates() {
		return State_Province;
	}

	public void setStates(List<State_Province> states_Province) {
		State_Province = states_Province;
	}
	
//	public CoronavirusStats getCoronavirusStats() {
//		return CoronavirusStats;
//	}
//
//	public void setCoronavirusStats(CoronavirusStats coronavirusStats) {
//		CoronavirusStats = coronavirusStats;
//	}

	public void addState_Province(State_Province state) {
		this.State_Province.add(state);
	}
	
}
