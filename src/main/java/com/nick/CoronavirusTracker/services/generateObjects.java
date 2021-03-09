package com.nick.CoronavirusTracker.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nick.CoronavirusTracker.helpers.ModelHelpers;
import com.nick.CoronavirusTracker.models.CoronavirusStats;
import com.nick.CoronavirusTracker.models.Country_Region;
import com.nick.CoronavirusTracker.models.Header;
import com.nick.CoronavirusTracker.models.State_Province;
import com.nick.CoronavirusTracker.models.USAStateCounty;
import com.nick.CoronavirusTracker.models.World;

@Service
public class generateObjects {

	@Autowired
	private ModelHelpers modelHelper;

	public World generateWorld(String name) {

		String uniqueID = UUID.randomUUID().toString();
		World world = new World(uniqueID, name);

		return world;
	}

	public void generateNewCountry(World world, CSVRecord record) {
		// TODO: Create helper attribute to contain CSV file headings -- do this last
		String countryRegionName = record.get(world.getHeader().getCountry_region());
		// String countryRegionName2 = record.get("Country/Region");
		// System.out.println(countryRegionName2);

		String uniqueID = UUID.randomUUID().toString();
		Country_Region country = new Country_Region(uniqueID, countryRegionName);

		if (!modelHelper.checkWorldContainsCountry(world, countryRegionName)) {
			world.addCountry(country);
		}
	}

	public void generateNewState_Province(World world, CSVRecord record) {
		// TODO: Create helper attribute to contain CSV file headings -- do this last
		
		String state_ProvinceName = record.get(world.getHeader().getState_province());
		String uniqueID = UUID.randomUUID().toString();
		
		
		State_Province state_province = new State_Province(uniqueID, state_ProvinceName);
		Double latitude = Double.parseDouble(record.get(world.getHeader().getLat()));
		if(latitude != null) {
			state_province.setLat(latitude);		
		}
		Double longitude = Double.parseDouble(record.get(world.getHeader().getLong()));
		if(longitude != null) {
			state_province.setLong(longitude);			
		}
		// if header = province/state do this
		// which will be set long lat 

		Country_Region country = world.getCountry_Regions().get(world.getCountry_Regions().size()-1);
		boolean available = modelHelper.checkCountryContainsState(country, state_ProvinceName);

		if (!available) {
			country.addState_Province(state_province);
		}
	}	
	
	/*
	 * public void generateNewProvince(Country_Region country, CSVRecord record) {
	 * // TODO: Create helper attribute to contain CSV file headings -- do this last
	 * String provinceName = record.get("Province_State");
	 * 
	 * String uniqueID = UUID.randomUUID().toString(); States province = new
	 * States(uniqueID, provinceName);
	 * 
	 * boolean notAvailable = modelHelper.checkCountryContainsState(country,
	 * provinceName);
	 * 
	 * if (notAvailable) { // do nothing } else { country.addProvince(province); }
	 * 
	 * }
	 */

	public void generateNewCounty(World world, CSVRecord record) {
		// TODO: Create helper attribute to contain CSV file headings -- do this last
		String countyName = record.get(world.getHeader().getCounty());
		Double latitude = Double.parseDouble(record.get(world.getHeader().getLat()));
		Double longitude = Double.parseDouble(record.get(world.getHeader().getLong()));

		CoronavirusStats stats = getCoronavirusStats(record);
		String uniqueID = UUID.randomUUID().toString();
		USAStateCounty county = new USAStateCounty(uniqueID, countyName, latitude, longitude, stats);
		
		Country_Region country = world.getCountry_Regions().get(world.getCountry_Regions().size()-1);
		State_Province state = country.getStates().get(country.getStates().size() -1);
		

		boolean available = modelHelper.checkStateContainsCounty(state, countyName);

		if (!available) {
			state.addStateCounty(county);
		}

	}

	private CoronavirusStats getCoronavirusStats(CSVRecord record) {
		int cases = Integer.parseInt(record.get(record.size() - 1));
		int yesterdayCases = Integer.parseInt(record.get(record.size() - 2));
		CoronavirusStats stats = new CoronavirusStats(cases, yesterdayCases);
		return stats;
	}

	
	public Header generateHeader(Set<String> headers) { 

		ArrayList<String> headerArray = new ArrayList<String>();
		headerArray.addAll(headers);
		
		if (headers.contains("Country_Region")) {
			//USA CSV
			Header header = new Header(headerArray.get(7), headerArray.get(6), headerArray.get(9), headerArray.get(8));
			header.setCounty(headerArray.get(5));
			return header;
		}
		else
		{
			//WORLD CSV
			Header header = new Header(headerArray.get(1), headerArray.get(0), headerArray.get(3), headerArray.get(2));
			return header;
		}	
		
		/*
		Pattern country = Pattern.compile("country", Pattern.CASE_INSENSITIVE);


		
	  
		Iterator<String> itr = headers.iterator(); 
		while(itr.hasNext()){
			String headerValue = itr.next(); 
			Matcher matcher = country.matcher(headerValue);
			boolean containsCountry = matcher.find();
			
			if(containsCountry) {
				System.out.println("contains country");
			} else {
				System.out.println("broke");
			}
			
			Header header = new Header(headerArray.get(7), headerArray.get(6), headerArray.get(9), headerArray.get(8));
	  }
	*/
	}
	
	
}
