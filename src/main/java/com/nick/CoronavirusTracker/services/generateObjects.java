package com.nick.CoronavirusTracker.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
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
		
		Optional<Country_Region> foundCountryRegion = modelHelper.findCountryInWorld(world, record);
		
		if (foundCountryRegion.isEmpty()) {
			
			String countryRegionName = record.get(world.getHeader().getCountry_region());
			String uniqueID = UUID.randomUUID().toString();
			Country_Region country = new Country_Region(uniqueID, countryRegionName);

			world.addCountry(country);
			//System.out.println("we added country : " + country.getName());
		}
	}

	public void generateNewState_Province(World world, CSVRecord record) {
	
		Optional<State_Province> foundStateProvince = modelHelper.findStateProvinceInWorld(world, record);

		if (foundStateProvince.isEmpty()) {
		
			String state_ProvinceName = record.get(world.getHeader().getState_province());
			String uniqueID = UUID.randomUUID().toString();
			State_Province state_province = new State_Province(uniqueID, state_ProvinceName);
			
			if(world.getHeader().getCountry_region().contains("Country/Region")) {
				
				if(!(record.get(world.getHeader().getLat())=="")) { 
					Double latitude = Double.parseDouble(record.get(world.getHeader().getLat()));
					state_province.setLat(latitude); 
				} 
				
				if(!(record.get(world.getHeader().getLong())== "")) {
					Double longitude = Double.parseDouble(record.get(world.getHeader().getLong()));
					state_province.setLong(longitude); 
				}
				
				state_province.setCoronavirusStats(generateCoronavirusStats(record, world));
			}
		
		Optional<Country_Region> foundCountry = modelHelper.findCountryInWorld(world, record);
		foundCountry.get().addState_Province(state_province);
		//System.out.println("added state/province : " + state_province.getName());
		
		} else if (world.getHeader().getCountry_region().contains("Country/Region")){
			updateCoronavirusStats(record, world);
		}
	}	

	public void generateNewCounty(World world, CSVRecord record) {
		
		Optional<State_Province> state = modelHelper.findStateProvinceInWorld(world, record);
		Optional<USAStateCounty> foundCounty = modelHelper.findCountyInWorld(world, record);
		
		if (foundCounty.isEmpty()) {

			String countyName = record.get(world.getHeader().getCounty());
			Double latitude = Double.parseDouble(record.get(world.getHeader().getLat()));
			Double longitude = Double.parseDouble(record.get(world.getHeader().getLong()));

			String uniqueID = UUID.randomUUID().toString();
			List<CoronavirusStats> CoronavirusStats = generateCoronavirusStats(record, world);
			USAStateCounty county = new USAStateCounty(uniqueID, countyName, latitude, longitude, CoronavirusStats);

			state.get().addStateCounty(county);
			//System.out.println("added county : " + county.getName());
			
		} 
		else
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
		
		return listOfStats;
	}

	private void updateCoronavirusStats(CSVRecord record, World world){
		
		Date startingDate = world.getHeader().getStartingDate();	
		Calendar c = Calendar.getInstance(); 
		c.setTime(startingDate);
		
		int recordSize = record.size() - world.getHeader().getAmountOfDates();
	
		if (world.getHeader().getCounty() != null) {
			
			Optional<USAStateCounty> county = modelHelper.findCountyInWorld(world, record);
			for(int i = 0; i < recordSize; i++) {
				
				SimpleDateFormat format = new SimpleDateFormat("M/d/yy");
				String dateAsString = format.format(c.getTime());
				
				if(county.get().getCoronavirusStats().get(0).getCases() != null) {	
					
					int deaths = Integer.parseInt(record.get(dateAsString));
					
					Optional<CoronavirusStats> currentStat =
					county.get().getCoronavirusStats().stream().filter(stats -> stats.getDate().equals(dateAsString))
					.findAny();
					
					if(currentStat.isPresent()) {
						currentStat.get().setDeaths(deaths);
					}	
				}	
				
				c.add(Calendar.DATE, 1);
			}
		} 
		else 
		{
		
			Optional<State_Province> stateProvince = modelHelper.findStateProvinceInWorld(world, record);
			for(int i = 0; i < recordSize; i++) {
				
				SimpleDateFormat format = new SimpleDateFormat("M/d/yy");
				String dateAsString = format.format(c.getTime());
				
				if(world.isRecovered() == false) {					
					int deaths = Integer.parseInt(record.get(dateAsString));
					
					Optional<CoronavirusStats> currentStat =
					stateProvince.get().getCoronavirusStats().stream().filter(stats -> stats.getDate().equals(dateAsString))
					.findAny();
					
					if(currentStat.isPresent()) {
						currentStat.get().setDeaths(deaths);
					}
			
				} 
				else if(world.isRecovered() == true) 
				{					
					int recovered = Integer.parseInt(record.get(dateAsString));
						
					Optional<CoronavirusStats> currentStat =
					stateProvince.get().getCoronavirusStats().stream().filter(stats -> stats.getDate().equals(dateAsString))
					.findAny();
						
					if(currentStat.isPresent()) {
						currentStat.get().setRecovered(recovered);
					}
				}
				
				c.add(Calendar.DATE, 1);
			}
		}
		
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
			
			if(headerArray.get(11).contains("Population")) {
				header.setAmountOfDates(12);
				sourceDate = headerArray.get(12);
			}
			
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
