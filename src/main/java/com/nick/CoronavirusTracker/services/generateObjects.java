package com.nick.CoronavirusTracker.services;

import java.util.UUID;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nick.CoronavirusTracker.helpers.ModelHelpers;
import com.nick.CoronavirusTracker.models.CoronavirusStats;
import com.nick.CoronavirusTracker.models.Country_Region;
import com.nick.CoronavirusTracker.models.States;
import com.nick.CoronavirusTracker.models.USAStateCounty;
import com.nick.CoronavirusTracker.models.World;

@Service
public class generateObjects {

	@Autowired
	private ModelHelpers modelHelper;
	
	public World generateWorld(String name){
		
		String uniqueID = UUID.randomUUID().toString();
		World world = new World(uniqueID, name);
	
		return world;
	}
	
	public void generateNewCountry(World world, CSVRecord record) {
		// TODO: Create helper attribute to contain CSV file headings -- do this last
		String countryRegionName = record.get("Country_Region");
		// String countryRegionName2 = record.get("Country/Region");
		// System.out.println(countryRegionName2);
		
		String uniqueID = UUID.randomUUID().toString();
		Country_Region country = new Country_Region(uniqueID, countryRegionName);		
		
		if (!modelHelper.checkWorldContainsCountry(world, countryRegionName)) {
			world.addCountry(country);
		}
	}
	
	public void generateNewState(Country_Region country, CSVRecord record) {
		// TODO: Create helper attribute to contain CSV file headings -- do this last
		String stateName = record.get("Province_State");
		
		String uniqueID = UUID.randomUUID().toString();
		States state = new States(uniqueID, stateName);
		
		boolean notAvailable = modelHelper.checkCountryContainsState(country, stateName);
		
		if(notAvailable) {
			//do nothing
		} else {
			country.addState(state);
		}
		
	}
	
	
	public void generateNewCounty(States state, CSVRecord record) {
		// TODO: Create helper attribute to contain CSV file headings -- do this last
		String countyName = record.get("Admin2");
		Double latitude = Double.parseDouble(record.get("Lat"));
		Double longitude = Double.parseDouble(record.get("Long_"));
		
		CoronavirusStats stats = getCoronavirusStats(record);
		String uniqueID = UUID.randomUUID().toString();
		USAStateCounty county = new USAStateCounty(uniqueID, countyName, latitude, longitude, stats);
		
		boolean isAvailable = modelHelper.checkStateContainsCounty(state, countyName);
		
		if(isAvailable ) {
			//do nothing
		} else {
			state.addStateCounty(county);
		}
		
	}
	
	private CoronavirusStats getCoronavirusStats(CSVRecord record) {
		int cases = Integer.parseInt(record.get(record.size() - 1));
		int yesterdayCases = Integer.parseInt(record.get(record.size() - 2));
		CoronavirusStats stats = new CoronavirusStats(cases, yesterdayCases);
		return stats;
	}	
		
//	private void generateNewProvince(Country_Region country, CSVRecord record) { 
//		String provinceName = record.get("Province/State");
//		Double latitude = Double.parseDouble(record.get("Lat"));
//		Double longitude = Double.parseDouble(record.get("Long"));
//		boolean isAvailable = true;
//		
//		for(Province province : country.getProvince()) 
//		{
//			if (province.getName().equals(provinceName) || province.getName().equals("")) {
//				isAvailable = false;
//			}
//		}
//		
//		if(isAvailable = true) {
//			// TODO: generate stats and add it to the province
//			CoronavirusStats stats = fetchCoronavirusStats(record);
//			Province province = new Province(1, provinceName, latitude, longitude, stats);
//			country.addProvince(province);
//		}
//	}
		
		
}
