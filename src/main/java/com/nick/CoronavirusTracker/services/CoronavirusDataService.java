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

@Service
public class CoronavirusDataService {

	@Autowired
	private HttpHelpers helper;
	
	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
	
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
		HttpRequest httpRequest = helper.createHttpRequest(VIRUS_DATA_URL);
		
		// client sends request then takes the body and returns it as a string
		@SuppressWarnings("unchecked")
		HttpResponse<String> httpResponse = helper.getHttpResponseInStrings(httpClient, httpRequest);

		// string reader is an instance of reader which parses through string
		StringReader csvBody = helper.csvBodyReader(httpResponse);
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBody);
		
		int totalCases = 0;
		int previousDayTotal = 0;
		int totalCasesToday = 0;
		for (CSVRecord record : records) {
			LocationStats locationStat = new LocationStats();
			
			int currentDay = Integer.parseInt(record.get(record.size() - 1));
			int previousDay = Integer.parseInt(record.get(record.size() - 2));
			totalCases = totalCases + currentDay;
		    previousDayTotal = previousDayTotal + previousDay;
		    totalCasesToday = totalCasesToday + currentDay;
			
		    locationStat.setState(record.get("Province_State"));
		    locationStat.setProvince(record.get("Admin2"));
		    locationStat.setCountry(record.get("Country_Region"));
		    
		    locationStat.setLatestTotalCases(currentDay);
		    locationStat.setTotalUSACases(totalCases);
		    locationStat.setChangesSinceLastDayTotal(totalCases - previousDayTotal);
		    locationStat.setChangesSinceLastDayLocal(currentDay - previousDay);
		   
		    // appending to list (this last line, line 83 came from the tutorial)
		    newStats.add(locationStat);
		}
//		System.out.println(newStats.get(newStats.size() - 1).getTotalUSACases());
//		System.out.println(newStats.get(newStats.size() - 1).getChangesSinceLastDayTotal());

		this.allStats = newStats;
	}
	
	
}
