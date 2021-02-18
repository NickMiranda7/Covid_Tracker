package com.nick.CoronavirusTracker.models;

import java.util.ArrayList;
import java.util.List;

public class States {

	private int UID;
	private String name;
	
	private List<USAStateCounty> USAStateCounty;

	
	public States() {
		
	}
	
	public States(int UID, String name) {
		this.UID = UID;
		this.name = name;
		this.USAStateCounty = new ArrayList<USAStateCounty>();
	}

	public int getUID() {
		return UID;
	}

	public void setUID(int uID) {
		UID = uID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<USAStateCounty> getStateCounty() {
		return USAStateCounty;
	}

	public void setStateCounty(List<USAStateCounty> stateCounty) {
		this.USAStateCounty = stateCounty;
	}

	public void addStateCounty(USAStateCounty stateCounty) {
		this.USAStateCounty.add(stateCounty);
	}
}
