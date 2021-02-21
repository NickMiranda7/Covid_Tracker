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

import com.nick.CoronavirusTracker.helpers.HttpHelpers;
import com.nick.CoronavirusTracker.helpers.ModelHelpers;
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
	@Autowired
	private ModelHelpers modelHelper;
	
	private static String VIRUS_DATA_USA = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
	private static String VIRUS_DATA_WORLD = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	
	
	// this annotation executes this method whenever the application is started
	@PostConstruct
	// schedules when this method will run
	@Scheduled(cron = "* * 1 * * *")
	public World fetchVirusData() throws IOException, InterruptedException  {

		//pull the data from any http file
		HttpClient httpClient = helper.createHttpClient();
		HttpRequest httpRequestUSA = helper.createHttpRequest(VIRUS_DATA_USA);
		HttpRequest httpRequestWORLD = helper.createHttpRequest(VIRUS_DATA_WORLD);
		
		// client sends request then takes the body and returns it as a string
		@SuppressWarnings("unchecked")
		HttpResponse<String> httpResponseUSA = helper.getHttpResponseInStrings(httpClient, httpRequestUSA);
		@SuppressWarnings("unchecked")
		HttpResponse<String> httpResponseWORLD = helper.getHttpResponseInStrings(httpClient, httpRequestWORLD);
		// string reader is an instance of reader which parses through string
		StringReader csvBodyUSA = helper.csvBodyReader(httpResponseUSA);
		StringReader csvBodyWORLD = helper.csvBodyReader(httpResponseWORLD);
		
		Iterable<CSVRecord> USArecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyUSA);
		Iterable<CSVRecord> WORLDrecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyWORLD);
		
		World world = generateWorld(USArecords, WORLDrecords);
		return world;

	}
	
	private World generateWorld(Iterable<CSVRecord> USArecords, Iterable<CSVRecord> WorldRecords){
		String uniqueID = UUID.randomUUID().toString();
		World world = new World(uniqueID, "Earth");
		
		for (CSVRecord record : USArecords) {
			
			generateNewCountry(world, record);
			Country_Region country = world.getCountry_Regions().get(world.getCountry_Regions().size()-1);
			
			generateNewState(country, record);
			States state = country.getStates().get(country.getStates().size() -1);
			
			generateNewCounty(state, record);
			
		}	
		return world;
	}
	

	private void generateNewCountry(World world, CSVRecord record) {
		// TODO: Create helper attribute to contain CSV file headings -- do this last
		String countryRegionName = record.get("Country_Region");
	
		String uniqueID = UUID.randomUUID().toString();
		Country_Region country = new Country_Region(uniqueID, countryRegionName);
		
		boolean notAvailable = modelHelper.checkWorldContainsCountry(world, countryRegionName);			
		
		if (notAvailable) {
			//do nothing
		} else {
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
	
	
}

// for loop on list and check list inside if contains list
