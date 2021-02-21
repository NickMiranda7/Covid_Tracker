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

import com.nick.CoronavirusTracker.models.Header;

@Component
public class CSVHelpers {
	
	@Autowired
	private HttpHelpers helper;
	
	public StringReader csvBodyReader(HttpResponse<String> httpResponse) {
		StringReader csvBody = new StringReader(httpResponse.body());
		return csvBody;
	}
	
	public Iterable<CSVRecord> parseCSVBody(StringReader csvBody) throws IOException{
	
	//Figure out a way to get CSV header and save it...(can be one long string or separated)
	return CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBody);
	}
/*
public Header getHeader(Iterable<CSVRecord> usRecords) {
		
		
	//	Header header = new();
		return header;
	}
	

}*/


//country,state,city,town
//parse string
//Object header
//country =country
//country =state
