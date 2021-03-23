package com.nick.CoronavirusTracker.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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

	public void generateNewState_Province(World world, CSVRecord record, Set<String> headers) {
		// TODO: Create helper attribute to contain CSV file headings -- do this last
		
		String state_ProvinceName = record.get(world.getHeader().getState_province());
		String uniqueID = UUID.randomUUID().toString();
		
		State_Province state_province = new State_Province(uniqueID, state_ProvinceName);
		Country_Region country = world.getCountry_Regions().get(world.getCountry_Regions().size()-1);
		boolean available = modelHelper.checkCountryContainsState_Province(country, state_ProvinceName);

		if (!available) {
		
			if(headers.contains("Country/Region")) {
				
				if(!(record.get(world.getHeader().getLat())=="")) { 
					Double latitude = Double.parseDouble(record.get(world.getHeader().getLat()));
					state_province.setLat(latitude); 
				} 
				
				if(!(record.get(world.getHeader().getLong())== "")) {
					Double longitude = Double.parseDouble(record.get(world.getHeader().getLong()));
					state_province.setLong(longitude); 
				}
				
			}
		
		state_province.setCoronavirusStats(generateCoronavirusStats(record, world));
		country.addState_Province(state_province);
		
		} else {
			updateCoronavirusStats(record, world);
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

		String uniqueID = UUID.randomUUID().toString();
		List<CoronavirusStats> CoronavirusStats = generateCoronavirusStats(record, world);
		USAStateCounty county = new USAStateCounty(uniqueID, countyName, latitude, longitude, CoronavirusStats);
		
		Country_Region country = world.getCountry_Regions().get(world.getCountry_Regions().size()-1);
		State_Province state = country.getStates_Provinces().get(country.getStates_Provinces().size() -1);

		boolean available = modelHelper.checkStateContainsCounty(state, countyName);

		if (!available) {
			state.addStateCounty(county);
		} else
		{
			updateCoronavirusStats(record, world);
		}
	}
	
	private ArrayList<CoronavirusStats> generateCoronavirusStats(CSVRecord record, World world) {
		
		ArrayList<CoronavirusStats> listOfStats = new ArrayList<CoronavirusStats>();
		
		Date startingDate = world.getHeader().getStartingDate();	
		Calendar c = Calendar.getInstance(); 
		c.setTime(startingDate);
		
		int recordSize = record.size() - world.getHeader().getAmountOfDates();
		for(int i = 0; i < recordSize; i++) {
			
			SimpleDateFormat format = new SimpleDateFormat("M/d/yy");
			String dateAsString = format.format(c.getTime());
			
			int cases = Integer.parseInt(record.get(dateAsString));
			CoronavirusStats coronavirusStat = new CoronavirusStats(dateAsString, cases);
			
			listOfStats.add(coronavirusStat);
			
			c.add(Calendar.DATE, 1);
		}
		
		
		//check to see if object exists with current date to add data
		//run check to see what file is deaths vs recovered
		
		//CoronavirusStats stats = new CoronavirusStats(cases, yesterdayCases);
		
		return listOfStats;
	}

	private void updateCoronavirusStats(CSVRecord record, World world){
		
		Date startingDate = world.getHeader().getStartingDate();	
		Calendar c = Calendar.getInstance(); 
		c.setTime(startingDate);
		
		int recordSize = record.size() - world.getHeader().getAmountOfDates();
	
		if (world.getHeader().getCounty() != null) {
			
			USAStateCounty county = getCountyInWorld(record, world);
			for(int i = 0; i < recordSize; i++) {
				
				SimpleDateFormat format = new SimpleDateFormat("M/d/yy");
				String dateAsString = format.format(c.getTime());
				
				if(county.getCoronavirusStats().get(0).getDeaths() == null) {					
					int deaths = Integer.parseInt(record.get(dateAsString));
					county.getCoronavirusStats().forEach(CoronavirusStats -> {
						CoronavirusStats.setDeaths(deaths);
					});
			
				}
				
				c.add(Calendar.DATE, 1);
			}
			
		} 
		else 
		{
		
			State_Province stateProvince = getStateProvinceInWorld (record, world);
			for(int i = 0; i < recordSize; i++) {
				
				SimpleDateFormat format = new SimpleDateFormat("M/d/yy");
				String dateAsString = format.format(c.getTime());
				
				if(stateProvince.getCoronavirusStats().get(0).getDeaths() == null) {					
					int deaths = Integer.parseInt(record.get(dateAsString));
					stateProvince.getCoronavirusStats().forEach(CoronavirusStats -> {
						CoronavirusStats.setDeaths(deaths);
					});
			
				} else if(stateProvince.getCoronavirusStats().get(0).getDeaths() != null) {					
						int recovered = Integer.parseInt(record.get(dateAsString));
						stateProvince.getCoronavirusStats().forEach(CoronavirusStats -> {
							CoronavirusStats.setRecovered(recovered);
						});
				}
				
				c.add(Calendar.DATE, 1);
			}
			
		}
		
		
		
		//in county loop update stats
		
	}
	
	private State_Province getStateProvinceInWorld (CSVRecord record, World world) {
		String countryRegionName = record.get(world.getHeader().getCountry_region());
		String state_ProvinceName = record.get(world.getHeader().getState_province());
		
		Country_Region foundCountry = new Country_Region();
		List<Country_Region> countries = world.getCountry_Regions();
		for (Country_Region country : countries) {
			if (country.getName() == countryRegionName) {
				foundCountry = country;
				break;
			}
		}
		
		List<State_Province> states = foundCountry.getStates_Provinces();
		for (State_Province state_province : states) {
			if (state_province.getName() == state_ProvinceName) {
				return state_province;
			}
		}
		return null;
	}
	
	private USAStateCounty getCountyInWorld (CSVRecord record, World world) {
		String countryRegionName = record.get(world.getHeader().getCountry_region());
		String state_ProvinceName = record.get(world.getHeader().getState_province());
		
		Country_Region foundCountry = new Country_Region();
		List<Country_Region> countries = world.getCountry_Regions();
		for (Country_Region country : countries) {
			if (country.getName() == countryRegionName) {
				foundCountry = country;
				break;
			}
		}
		
		State_Province foundStateProvince = new State_Province();
//		world.getCountry_Regions().stream().filter(Country_Region -> countryRegionName.equals(countryRegionName))
//		.findAny();
		List<State_Province> states = foundCountry.getStates_Provinces();
		for (State_Province state_province : states) {
			if (state_province.getName() == state_ProvinceName) {
				foundStateProvince = state_province;
				break;
			}
		}
		
		String countyName = record.get(world.getHeader().getCounty());	
			
		List<USAStateCounty> counties = foundStateProvince.getStateCounties();
		for (USAStateCounty USACounty : counties) {
			if (USACounty.getName() == countyName) {
				return USACounty;
			}
		}
		return null;
	}
	

	public Header generateHeader(Set<String> headers) throws ParseException  { 

		ArrayList<String> headerArray = new ArrayList<String>();
		headerArray.addAll(headers);
		
		if (headers.contains("Country_Region")) {
			//USA CSV
			Header header = new Header(headerArray.get(7), headerArray.get(6), headerArray.get(9), headerArray.get(8));
			header.setCounty(headerArray.get(5));
			header.setAmountOfDates(11);
			
			String sourceDate = headerArray.get(11);
			SimpleDateFormat format = new SimpleDateFormat("M/dd/yy");
			Date myDate = format.parse(sourceDate);
			header.setStartingDate(myDate);
			
			return header;
		}
		else
		{
			//WORLD CSV
			Header header = new Header(headerArray.get(1), headerArray.get(0), headerArray.get(3), headerArray.get(2));
			header.setAmountOfDates(4);
			header.setRecovered("recovered");
			
			String sourceDate = headerArray.get(4);
			SimpleDateFormat format = new SimpleDateFormat("M/dd/yy");
			Date myDate = format.parse(sourceDate);
			header.setStartingDate(myDate);

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
