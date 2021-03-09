package com.nick.CoronavirusTracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
		world.setHeader(objectGenerator.generateHeader(headers));

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
