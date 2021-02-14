package com.nick.CoronavirusTracker.models;

import java.util.List;

public class Country_Region {

	private int UID;
	
	private String name;
	private int countryPopulation;
	private int latestTotalCases;
	private int changeInTotalSinceYesterday;
	
	
	private World World;
	//country_regions can have many provinces_stats
	private List<Province_State> Province_States;
	
	
	
	public Country_Region() {
		
	}
	
	public Country_Region(int UID, String name, int countryPopulation, int latestTotalCases,
			int changeInTotalSinceYesterday, World World,
			List<Province_State> Province_States) {
		this.UID = UID;
		this.name = name;
		this.countryPopulation = countryPopulation;
		this.latestTotalCases = latestTotalCases;
		this.changeInTotalSinceYesterday = changeInTotalSinceYesterday;
		this.World = World;
		this.Province_States = Province_States;
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
	public int getCountryPopulation() {
		return countryPopulation;
	}
	public void setCountryPopulation(int countryPopulation) {
		this.countryPopulation = countryPopulation;
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
	public World getWorld() {
		return World;
	}
	public void setWorld(World world) {
		World = world;
	}
	public List<Province_State> getProvince_States() {
		return Province_States;
	}
	public void setProvince_States(List<Province_State> province_States) {
		Province_States = province_States;
	}
	
	
}
