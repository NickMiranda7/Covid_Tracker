package com.nick.CoronavirusTracker.models;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Country_Region {

	private int UID;
	
	private String name;
	private int countryPopulation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="world_UID")
	private World world;
	
	@OneToMany(mappedBy="country_region", fetch = FetchType.LAZY)
	private List<Province_State> Province_States;
	
	
}
