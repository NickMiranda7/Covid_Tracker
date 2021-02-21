package com.nick.CoronavirusTracker.helpers;

import java.io.IOException;
import java.io.StringReader;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CSVHelpers {
	
	@Autowired
	private HttpHelpers helper;
	
	private static String VIRUS_DATA_USA = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
	private static String VIRUS_DATA_WORLD = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	
	public Iterable<CSVRecord> fetchUSAData() throws IOException, InterruptedException {
		
		//pull the data from http file
		HttpClient httpClient = helper.createHttpClient();
		HttpRequest httpRequestUSA = helper.createHttpRequest(VIRUS_DATA_USA);
		
		// client sends request then takes the body and returns it as a string
		@SuppressWarnings("unchecked")
		HttpResponse<String> httpResponseUSA = helper.getHttpResponseInStrings(httpClient, httpRequestUSA);
		
		// string reader is an instance of reader which parses through string
		StringReader csvBodyUSA = helper.csvBodyReader(httpResponseUSA);
		
		Iterable<CSVRecord> USArecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyUSA);
		return USArecords;
	}
	
	public Iterable<CSVRecord> fetchWorldData() throws IOException, InterruptedException {
		
		//pull the data from http file
		HttpClient httpClient = helper.createHttpClient();
		HttpRequest httpRequestWORLD = helper.createHttpRequest(VIRUS_DATA_WORLD);
		
		// client sends request then takes the body and returns it as a string
		@SuppressWarnings("unchecked")
		HttpResponse<String> httpResponseWORLD = helper.getHttpResponseInStrings(httpClient, httpRequestWORLD);
		
		// string reader is an instance of reader which parses through string
		StringReader csvBodyWORLD = helper.csvBodyReader(httpResponseWORLD);
		
		Iterable<CSVRecord> WORLDrecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyWORLD);
		return WORLDrecords;
	}
	
}
