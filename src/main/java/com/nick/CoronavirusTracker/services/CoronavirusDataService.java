package com.nick.CoronavirusTracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.util.Strings;
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
	private CSVHelpers CSVHelper;
	@Autowired
	private generateObjects objectGenerator;
	
	private static String VIRUS_DATA_USA = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
	private static String VIRUS_DATA_WORLD = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	// this annotation executes this method whenever the application is started
	@PostConstruct
	// schedules when this method will run
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException  {

	
		World worldUS = objectGenerator.generateWorld("EarthUS");
		
		//fetches all US data
		Iterable<CSVRecord> usRecords = fetchCVSData(VIRUS_DATA_USA);
		//worldUS.setHeader(CSVHelper.getHeader(usRecords.getHeaderNames());
		//List<String> headers = parser.getHeaderNames();
		
		
		iterateRecord(usRecords, worldUS);
		
		
		World world = objectGenerator.generateWorld("Earth");
		
		//fetches all World data
		Iterable<CSVRecord> worldRecords = fetchCVSData(VIRUS_DATA_WORLD);
		//worldUS.setHeader(CSVHelper.getHeader(usRecords.getHeaderNames());
		//List<String> headers = parser.getHeaderNames();
		
		iterateRecord(worldRecords, world);
		
	}

	
	//TODO:Can be made into one method hint:parameters
	private World iterateRecord(Iterable<CSVRecord> records, World world) {
		
		Set<String> headers = records.iterator().next().toMap().keySet();
		//objectGenerator.generateHeader(headers);
		// iterate through header
		// get fields
		// set to variables
		// create header objects with variables in constuctor 
	//	world.setHeader(objectGenerator.generateHeader(headers));
		
		for (CSVRecord record : records) {
			
			objectGenerator.generateNewCountry(world, record);
			Country_Region country = world.getCountry_Regions().get(world.getCountry_Regions().size()-1);
			
			objectGenerator.generateNewState(country, record);
			States state = country.getStates().get(country.getStates().size() -1);
			
			objectGenerator.generateNewCounty(state, record);
			
			
		}	
		return world;
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
