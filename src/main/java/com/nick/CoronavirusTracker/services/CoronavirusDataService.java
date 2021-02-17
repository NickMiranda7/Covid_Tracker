package com.nick.CoronavirusTracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nick.CoronavirusTracker.helpers.HttpHelpers;
import com.nick.CoronavirusTracker.models.LocationStats;
import com.nick.CoronavirusTracker.models.World;
import com.nick.CoronavirusTracker.models.Country_Region;
import com.nick.CoronavirusTracker.models.Province;
import com.nick.CoronavirusTracker.models.States;
import com.nick.CoronavirusTracker.models.USAStateCounty;

@Service
public class CoronavirusDataService {

	@Autowired
	private HttpHelpers helper;
	
	private static String VIRUS_DATA_USA = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
	private static String VIRUS_DATA_WORLD = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	public List<LocationStats> allStats = new ArrayList<>();
	
	
	// this annotation executes this method whenever the application is started
	@PostConstruct
	// schedules when this method will run
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		// to overwrite with updated stats
		List<LocationStats> newStats = new ArrayList<>();

		//OOP not from tutorial
		HttpClient httpClient = helper.createHttpClient();
		HttpRequest httpRequest = helper.createHttpRequest(VIRUS_DATA_USA);
		
		// client sends request then takes the body and returns it as a string
		@SuppressWarnings("unchecked")
		HttpResponse<String> httpResponse = helper.getHttpResponseInStrings(httpClient, httpRequest);

		// string reader is an instance of reader which parses through string
		StringReader csvBody = helper.csvBodyReader(httpResponse);
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBody);
		
		World world = generateWorld(records);
		
		int totalCases = 0;
		int previousDayTotal = 0;
		// int totalCasesToday = 0;
		
		    
		    locationStat.setLatestTotalCases(currentDay);
		    locationStat.setTotalUSACases(totalCases);
		    locationStat.setChangesSinceLastDayTotal(totalCases - previousDayTotal);
		    locationStat.setChangesSinceLastDayLocal(currentDay - previousDay);
		   
		    // appending to list (this last line, line 83 came from the tutorial)
		    newStats.add(locationStat);
		
//		System.out.println(newStats.get(newStats.size() - 1).getTotalUSACases());
//		System.out.println(newStats.get(newStats.size() - 1).getChangesSinceLastDayTotal());

		this.allStats = newStats;
	}
	
	private World generateWorld(Iterable<CSVRecord> records){
		
		World world = new World(1, "Earth");
		
		for (CSVRecord record : records) {
			
			generateNewCountry(world, record);
			Country_Region country = world.getCountry_Regions().get(world.getCountry_Regions().size()-1);
			Province province = generateNewProvince(country, record);
		
			LocationStats locationStat = new LocationStats();
			
			int currentDay = Integer.parseInt(record.get(record.size() - 1));
			int previousDay = Integer.parseInt(record.get(record.size() - 2));
			totalCases = totalCases + currentDay;
		    previousDayTotal = previousDayTotal + previousDay;
		   // totalCasesToday = totalCasesToday + currentDay;
			
		    locationStat.setState(record.get("Province_State"));
		    locationStat.setProvince(record.get("Admin2"));
		    locationStat.setCountry(record.get("Country_Region"));
		
		return world;
		}	
	}
	
	private void generateNewCountry(World world, CSVRecord record) {
		String countryRegionName = record.get("Country_Region");
		boolean isAvailable = true;
		// TODO: Create helper attribute to contain CSV file headings -- do this last
		
		
		for (Country_Region country : world.getCountry_Regions())
		{
			if (country.getName().equals(countryRegionName)) {
				isAvailable = false;
			}
		}
		
		if (isAvailable = true){
			// TODO: make ID generator
			Country_Region country = new Country_Region(1, countryRegionName);
			world.addCountry(country);
		}
	
	}
	
	private Province generateNewState(Country_Region country, CSVRecord record) {
		String stateName = record.get("Province_State");
		boolean isAvailable = true;
		
		for(States state : country.getStates()) 
		{
			if (state.getName().equals(stateName)) {
				isAvailable = false;
			}
		}
		
		if (isAvailable = true) {
			// TODO: make ID generator
			States state = new States(1, stateName);
			country.addState(state);
		}
		
	}
	
	Province province = new Province(1, provinceName, latitude, longitude, //Stats Object);
			country.addProvince(province);
	Double latitude = Double.parseDouble(record.get("Lat"));
	Double longitude = Double.parseDouble(record.get("Long_"));
	// TODO: Generate stats object  hint: make new method to generate stats per record on province
	// int stats = Integer.parseInt(record.get(record.size() - 1));
	
}

// for loop on list and check list inside if contains list
