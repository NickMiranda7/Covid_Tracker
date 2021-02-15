package com.nick.CoronavirusTracker.models;

import java.util.ArrayList;
import java.util.List;

public class Country_Region {

	private int uid;
	private String name;
	private int countryPopulation;
	private List<Province_State> Province_States;
	
	
	
	public Country_Region() {
		
	}
	
	public Country_Region(int uid, String name) {
		this.uid = uid;
		this.name = name;
		this.countryPopulation = 0;
		this.Province_States = new ArrayList<Province_State>();
	}
	
	public int getUID() {
		return uid;
	}
	public void setUID(int uID) {
		uid = uID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCountryPopulation() {
		return countryPopulation;
	}
	public void setCountryPopulation(int countryPopulation) {
		this.countryPopulation = countryPopulation;
	}
	public List<Province_State> getProvince_States() {
		return Province_States;
	}
	public void setProvince_States(List<Province_State> province_States) {
		Province_States = province_States;
	}
	
	public void addProvince(Province_State province_State) {
		this.Province_States.add(province_State); 
	}
	
	public void addToPopulation(int countryPopulation) {
		this.countryPopulation = this.countryPopulation + countryPopulation;
	}
	
}
