package com.nick.CoronavirusTracker.models;

public class States {

	private int UID;
	private String name;

	
	
	
	public States() {
		
	}

	
	public States(int UID, String name) {
		this.UID = UID;
		this.name = name;
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

}
