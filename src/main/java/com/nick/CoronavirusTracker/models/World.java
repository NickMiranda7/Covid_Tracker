package com.nick.CoronavirusTracker.models;

import java.util.ArrayList;
import java.util.List;

public class World {

	private String uid;
	private String name;
	private Header header;
	private List<Country_Region> country_Regions;

	public World() {

	}

	public World(String uid, String name) {
		
		this.uid = uid;
		this.name = name;
		this.country_Regions = new ArrayList<Country_Region>();
	}


	public String getUID() {
		return uid;
	}

	public void setUID(String uID) {
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

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}
}
