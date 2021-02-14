package com.nick.CoronavirusTracker.models;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class World {

	private int UID;

	private String name;
	private Long worldPopulation;
	private int latestTotalCases;
	private int changeInTotalSinceYesterday;
	
	//world has many country_regions
	private List<Country_Region> Country_Regions;

	
	
	public World() {

	}

	public World(int UID, String name, Long population, List<Country_Region> Country_Regions) {
		this.UID = UID;
		this.name = name;
		this.worldPopulation = population;
		this.Country_Regions = Country_Regions;
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

	public Long getWorldPopulation() {
		return worldPopulation;
	}

	public void setWorldPopulation(Long population) {
		this.worldPopulation = population;
	}

	public List<Country_Region> getCountry_Regions() {
		return Country_Regions;
	}

	public void setCountry_Regions(List<Country_Region> country_Regions) {
		Country_Regions = country_Regions;
	}
	
	
}
