package com.nick.CoronavirusTracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nick.CoronavirusTracker.helpers.CSVHelpers;
import com.nick.CoronavirusTracker.helpers.HttpHelpers;
import com.nick.CoronavirusTracker.helpers.ModelHelpers;
import com.nick.CoronavirusTracker.models.LocationStats;
import com.nick.CoronavirusTracker.models.World;
import com.nick.CoronavirusTracker.models.CoronavirusStats;
import com.nick.CoronavirusTracker.models.Country_Region;
import com.nick.CoronavirusTracker.models.Province;
import com.nick.CoronavirusTracker.models.States;
import com.nick.CoronavirusTracker.models.USAStateCounty;
import com.nick.CoronavirusTracker.models.Header;

@Service
public class CoronavirusDataService {

	@Autowired
	private HttpHelpers helper;
	@Autowired
	private ModelHelpers modelHelper;
	@Autowired
	private CSVHelpers CSVHelper;
	
	private static String VIRUS_DATA_USA = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
	private static String VIRUS_DATA_WORLD = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	// this annotation executes this method whenever the application is started
	@PostConstruct
	// schedules when this method will run
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException  {

//		Iterable<CSVRecord> USARecords = CSVHelper.fetchUSAData();
//		Iterable<CSVRecord> WORLDrecords = CSVHelper.fetchWorldData();
	
		World worldUS = generateWorld("EarthUS");
		
		//fetches all US data
		Iterable<CSVRecord> usRecords = fetchCVSData(VIRUS_DATA_USA);
		//worldUS.setHeader(CSVHelper.getHeader(usRecords.getHeaderNames());
		//List<String> headers = parser.getHeaderNames();
		iterateUSARecord(usRecords, worldUS);
				
		World world = generateWorld("Earth");
		//fetches all US data
		Iterable<CSVRecord> worldRecords = fetchCVSData(VIRUS_DATA_WORLD);
		iterateUSARecord(worldRecords, world);
		
	}
	
	private World generateWorld(String name){
		
		String uniqueID = UUID.randomUUID().toString();
		World world = new World(uniqueID, name);
	
		return world;
	}
	
	
	//TODO:Can be made into one method
	private void iterateUSARecord(Iterable<CSVRecord> records, World world) {
		
		for (CSVRecord record : records) {
			
			generateNewCountry(world, record);
			Country_Region country = world.getCountry_Regions().get(world.getCountry_Regions().size()-1);
			
			generateNewState(country, record);
			States state = country.getStates().get(country.getStates().size() -1);
			
			generateNewCounty(state, record);
			
			
		}	
	}

	private void generateNewCountry(World world, CSVRecord record) {
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
	
	private void generateNewState(Country_Region country, CSVRecord record) {
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
	
	
	private void generateNewCounty(States state, CSVRecord record) {
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
	
	public Iterable<CSVRecord> fetchCVSData(String csvData) throws IOException, InterruptedException {
		
		//pull the data from http file
		HttpClient httpClient = helper.createHttpClient();
		HttpRequest httpRequest = helper.createHttpRequest(csvData);
		
		// client sends request then takes the body and returns it as a string
		@SuppressWarnings("unchecked")
		HttpResponse<String> httpResponse = helper.getHttpResponseInStrings(httpClient, httpRequest);
		
		StringReader csvBody = CSVHelper.csvBodyReader(httpResponse);
		Iterable<CSVRecord> records = CSVHelper.parseCSVBody(csvBody);
	
		return records;
	}
	
	
}

// for loop on list and check list inside if contains list
