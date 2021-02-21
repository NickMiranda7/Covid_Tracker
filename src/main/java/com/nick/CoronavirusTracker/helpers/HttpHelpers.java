package com.nick.CoronavirusTracker.helpers;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

@Component
public class HttpHelpers {
	
	
	public HttpClient createHttpClient() {
		
		return HttpClient.newHttpClient(); //create a new http client
	}
	
	public HttpRequest createHttpRequest(String url) {
		
		return HttpRequest.newBuilder() //create a request using the builder
				.uri(URI.create(url))
				.build();
		
	}
	
	public HttpResponse getHttpResponseInStrings(HttpClient httpClient, HttpRequest httpRequest) throws IOException, InterruptedException {

		return (httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())); 
	}
	

}
