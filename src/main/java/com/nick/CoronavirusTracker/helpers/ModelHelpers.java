package com.nick.CoronavirusTracker.helpers;

import java.util.Optional;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import com.nick.CoronavirusTracker.models.Country_Region;
import com.nick.CoronavirusTracker.models.State_Province;
import com.nick.CoronavirusTracker.models.USAStateCounty;
import com.nick.CoronavirusTracker.models.World;

@Component
public class ModelHelpers {

	public boolean checkWorldContainsCountry(World world, final String name){
	    return world.getCountry_Regions().stream().anyMatch(o -> o.getName().equals(name));
	}
	public boolean checkCountryContainsState_Province(Country_Region country, final String name){
		return country.getStates_Provinces().stream().anyMatch(o -> o.getName().equals(name));
	}
	public boolean checkStateContainsCounty(Optional<State_Province> state, final String name){
		return state.get().getStateCounties().stream().anyMatch(o -> o.getName().equals(name));
	}
		
	public Optional<Country_Region> findCountryInWorld (World world, CSVRecord record) {
		
		String countryRegionName = record.get(world.getHeader().getCountry_region());
		Optional<Country_Region> country =
				world.getCountry_Regions().stream().filter(Country_Region ->  Country_Region.getName().equals(countryRegionName))
				.findAny();
		return country;
	}
	
	public Optional<State_Province> findStateProvinceInWorld (World world, CSVRecord record) {
		
		String countryRegionName = record.get(world.getHeader().getCountry_region());
		Optional<Country_Region> country =
				world.getCountry_Regions().stream().filter(Country_Region -> Country_Region.getName().equals(countryRegionName))
				.findAny();
		
		String stateProvinceName = record.get(world.getHeader().getState_province());
		Optional<State_Province> stateProvince = 
				country.get().getStates_Provinces().stream().filter(State_Province -> State_Province.getName().equals(stateProvinceName))
				.findAny();
		return stateProvince;
	}
	
	public Optional<USAStateCounty> findCountyInWorld (World world, CSVRecord record) {
		
		String countryRegionName = record.get(world.getHeader().getCountry_region());
		Optional<Country_Region> country =
				world.getCountry_Regions().stream().filter(Country_Region -> Country_Region.getName().equals(countryRegionName))
				.findAny();
		
		String stateProvinceName = record.get(world.getHeader().getState_province());
		Optional<State_Province> stateProvince = 
				country.get().getStates_Provinces().stream().filter(State_Province -> State_Province.getName().equals(stateProvinceName))
				.findAny();
		
		String countyName = record.get(world.getHeader().getCounty());
		Optional<USAStateCounty> county =
				stateProvince.get().getStateCounties().stream().filter(USAStateCounty -> USAStateCounty.getName().equals(countyName))
				.findAny();
		
		return county;
	}
}
