package com.nick.CoronavirusTracker.helpers;

import org.springframework.stereotype.Component;

import com.nick.CoronavirusTracker.models.Country_Region;
import com.nick.CoronavirusTracker.models.State_Province;
import com.nick.CoronavirusTracker.models.World;

@Component
public class ModelHelpers {

	public boolean checkWorldContainsCountry(World world, final String name){
	    return world.getCountry_Regions().stream().anyMatch(o -> o.getName().equals(name));
	}
	public boolean checkCountryContainsState_Province(Country_Region country, final String name){
		return country.getStates().stream().anyMatch(o -> o.getName().equals(name));
	}
	public boolean checkStateContainsCounty(State_Province state, final String name){
		return state.getStateCounties().stream().anyMatch(o -> o.getName().equals(name));
	}
}
