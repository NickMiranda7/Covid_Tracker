package com.nick.CoronavirusTracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nick.CoronavirusTracker.models.LocationStats;

@Service

public class CoronavirusDataService {

	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
	
	public List<LocationStats> allStats = new ArrayList<>();
	
	// this annotation executes this method whenever the application is started
	@PostConstruct
	// schedules when this method will run
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		// to populate all stats with the new stats
		List<LocationStats> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient(); //create a new http client
		
		HttpRequest request = HttpRequest.newBuilder() //create a request using the builder
			.uri(URI.create(VIRUS_DATA_URL))
			.build();
		
		// client sends request then takes the body and returns it as a string
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString()); 

		// string reader is an instance of reader which parses through string
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		
		int totalCases = 0;
		int previousDayTotal = 0;
		int changeSincePreviousDayTotal = 0;
		int changeSincePreviousDayLocal = 0;
		for (CSVRecord record : records) {
			LocationStats locationStat = new LocationStats(); //for jon, so when this is made is make an isntance of a class and the List above is just an array list of all those instances
		    locationStat.setState(record.get("Province_State"));
		    locationStat.setProvince(record.get("Admin2"));
		    locationStat.setCountry(record.get("Country_Region"));
		    // convert the cases from a string into an integer
		    locationStat.setLatestTotalCases(Integer.parseInt(record.get(record.size() - 1)));
		    // System.out.println(locationStat);
		    
		    // where I stopped watching the tutorial entirely
		    int currentDay = Integer.parseInt(record.get(record.size() - 1));
		    int previousDay = Integer.parseInt(record.get(record.size() - 2));
		    
		    
		    // adding the total cases of all locations in the us
		    totalCases = totalCases + currentDay;
		    locationStat.setTotalUSACases(totalCases);
		    
		    // getting changes from previous day total
		    previousDayTotal = previousDayTotal + previousDay;
		    changeSincePreviousDayTotal = totalCases - previousDayTotal;
		    locationStat.setChangesSinceLastDayTotal(changeSincePreviousDayTotal);
		    
		    // getting changes from previous day local
		    changeSincePreviousDayLocal = currentDay - previousDay;
		    locationStat.setChangesSinceLastDayLocal(changeSincePreviousDayLocal);

		    
		    // appending to list (this last line, line 83 came from the tutorial)
		    newStats.add(locationStat);
		}
//		System.out.println(newStats.get(newStats.size() - 1).getTotalUSACases());
//		System.out.println(newStats.get(newStats.size() - 1).getChangesSinceLastDayTotal());

		this.allStats = newStats;
	}
	
	
}
