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
import com.nick.CoronavirusTracker.models.CoronavirusStats;
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
	
	
	
	// this annotation executes this method whenever the application is started
	@PostConstruct
	// schedules when this method will run
	@Scheduled(cron = "* * 1 * * *")
	public World fetchVirusData() throws IOException, InterruptedException {
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
		return world;

	}
	
	private World generateWorld(Iterable<CSVRecord> records){
		
		World world = new World(1, "Earth");
		
		for (CSVRecord record : records) {
			generateNewCountry(world, record);
			Country_Region country = world.getCountry_Regions().get(world.getCountry_Regions().size()-1);
			
			generateNewState(country, record);
			States state = country.getStates().get(country.getStates().size() -1);
			
			generateNewCounty(state, record);
			
		}	
		return world;
	}
	
	private void generateNewCountry(World world, CSVRecord record) {
		String countryRegionName = record.get("Country_Region");
		// TODO: Create helper attribute to contain CSV file headings -- do this last
		
		
		for (Country_Region countries : world.getCountry_Regions())
		{
			if (countries.getName().equals(countryRegionName)) {
				break;
			} else {
				// TODO: make ID generator
				Country_Region country = new Country_Region(1, countryRegionName);
				world.addCountry(country);
			}
		}
	
	}
	
	private void generateNewState(Country_Region country, CSVRecord record) {
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
	
	private void generateNewCounty(States state, CSVRecord record) {
		String countyName = record.get("Admin2");
		Double latitude = Double.parseDouble(record.get("Lat"));
		Double longitude = Double.parseDouble(record.get("Long_"));
		boolean isAvailable = true;
		
		for(USAStateCounty county : state.getStateCounty())
		{
			if (county.getName().equals(countyName)) {
				isAvailable = false;
			}
		}
		
		if(isAvailable = true) {
			// TODO: generate stats and add it to the county
			CoronavirusStats stats = getCoronavirusStats(record);
			USAStateCounty stateCounty = new USAStateCounty(1, countyName, latitude, longitude, stats);
			state.addStateCounty(stateCounty);
		}
		
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
	
	private CoronavirusStats getCoronavirusStats(CSVRecord record) {
		int cases = Integer.parseInt(record.get(record.size() - 1));
		int yesterdayCases = Integer.parseInt(record.get(record.size() - 2));
		CoronavirusStats stats = new CoronavirusStats(cases, yesterdayCases);
		return stats;
	}
	
	
}

// for loop on list and check list inside if contains list
