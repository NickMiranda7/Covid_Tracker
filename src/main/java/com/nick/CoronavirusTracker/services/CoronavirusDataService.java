package com.nick.CoronavirusTracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nick.CoronavirusTracker.helpers.CSVHelpers;
import com.nick.CoronavirusTracker.helpers.HttpHelpers;
import com.nick.CoronavirusTracker.models.World;

@Service
public class CoronavirusDataService {

	@Autowired
	private HttpHelpers helper;
	@Autowired
	private CSVHelpers CSVHelper;
	@Autowired
	private generateObjects objectGenerator;
	
	private static String VIRUS_DATA_USA_CASES = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
	private static String VIRUS_DATA_WORLD_CASES = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private static String VIRUS_DATA_USA_DEATHS = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_US.csv";
	private static String VIRUS_DATA_WORLD_DEATHS = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	private static String VIRUS_DATA_WORLD_RECOVERED = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
	
	
	public List<World> worlds = new ArrayList<>();
	// this annotation executes this method whenever the application is started
	@PostConstruct
	// schedules when this method will run
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException  {
	
		World worldUS = objectGenerator.generateWorld("EarthUS");
		//fetches all US data
		Iterable<CSVRecord> usRecords = fetchCVSData(VIRUS_DATA_USA_CASES);
		iterateRecord(usRecords, worldUS);
		
		/*
		 * Iterable<CSVRecord> usdeathsRecords = fetchCVSData(VIRUS_DATA_USA_DEATHS);
		 * iterateRecord(usdeathsRecords, worldUS);
		 */
		

		World world = objectGenerator.generateWorld("Earth");
		//fetches all World data
		Iterable<CSVRecord> worldCasesRecords = fetchCVSData(VIRUS_DATA_WORLD_CASES);
		iterateRecord(worldCasesRecords, world);
		
		/*
		 * Iterable<CSVRecord> worldDeathsRecords =
		 * fetchCVSData(VIRUS_DATA_WORLD_DEATHS); iterateRecord(worldDeathsRecords,
		 * world);
		 * 
		 * Iterable<CSVRecord> worldRecoveredRecords =
		 * fetchCVSData(VIRUS_DATA_WORLD_RECOVERED);
		 * iterateRecord(worldRecoveredRecords, world);
		 */
		

		worlds.add(worldUS);
		worlds.add(world);
	}

	
	//TODO:Can be made into one method hint:parameters
	private World iterateRecord(Iterable<CSVRecord> records, World world) {
		
		Set<String> headers = records.iterator().next().toMap().keySet();
		try {
			world.setHeader(objectGenerator.generateHeader(headers));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (CSVRecord record : records) {
			
			objectGenerator.generateNewCountry(world, record);
			objectGenerator.generateNewState_Province(world, record, headers);
			
			
			//if record contains "admin2" then run this method or else will break
			if(world.getHeader().getCounty() != null) {
				objectGenerator.generateNewCounty(world, record);
			}
			
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
