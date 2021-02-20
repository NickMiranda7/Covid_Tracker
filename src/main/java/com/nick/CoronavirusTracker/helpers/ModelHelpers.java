package com.nick.CoronavirusTracker.helpers;

import org.springframework.stereotype.Component;

import com.nick.CoronavirusTracker.models.Country_Region;
import com.nick.CoronavirusTracker.models.States;
import com.nick.CoronavirusTracker.models.World;

@Component
public class ModelHelpers {

	public boolean checkWorldContainsCountry(World world, final String name){
	    return world.getCountry_Regions().stream().anyMatch(o -> o.getName().equals(name));
	}
	public boolean checkCountryContainsState(Country_Region country, final String name){
		return country.getStates().stream().anyMatch(o -> o.getName().equals(name));
	}
	public boolean checkCountryContainsProvince(Country_Region country, final String name){
		return country.getProvince().stream().anyMatch(o -> o.getName().equals(name));
	}
	public boolean checkStateContainsCounty(States state, final String name){
		return state.getStateCounties().stream().anyMatch(o -> o.getName().equals(name));
	}
}
