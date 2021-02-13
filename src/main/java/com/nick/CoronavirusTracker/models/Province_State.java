package com.nick.CoronavirusTracker.models;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class Province_State {

	private int UID;
	
	private Double Lat;
	private Double Long;
	private int province_statePopulation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="country_region_UID")
	private Country_Region country_region;
	
	@OneToOne(mappedBy="province_state", fetch = FetchType.EAGER)
	private List<CoronavirusStats> CoronavirusStats;
	
	
}
