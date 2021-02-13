package com.nick.CoronavirusTracker.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class World {

	private int UID;

	private String name;
	private Long worldPopulation;
	
	@OneToMany(mappedBy="world", fetch = FetchType.LAZY)
	private List<Country_Region> Country_Regions;

	
	
	public World() {

	}

	public World(int uID, String name, Long population, List<Country_Region> country_Regions) {
		UID = uID;
		this.name = name;
		this.worldPopulation = population;
		Country_Regions = country_Regions;
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
