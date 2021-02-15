package com.nick.CoronavirusTracker.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class World {

	private int uid;
	private String name;
	private List<Country_Region> country_Regions;

	public World() {

	}

	public World(int uid, String name) {
		
		this.uid = uid;
		this.name = name;
		this.country_Regions = new ArrayList<Country_Region>();
	}


	public int getUID() {
		return uid;
	}

	public void setUID(int uID) {
		uid = uID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Country_Region> getCountry_Regions() {
		return country_Regions;
	}

	public void setCountry_Regions(List<Country_Region> country_Regions) {
		this.country_Regions = country_Regions;
	}
	
	public void addCountry(Country_Region country_Region) {
		this.country_Regions.add(country_Region); 
	}
}
