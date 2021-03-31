package com.nick.CoronavirusTracker.helpers;

import java.util.Optional;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import com.nick.CoronavirusTracker.models.Country_Region;
import com.nick.CoronavirusTracker.models.State_Province;
import com.nick.CoronavirusTracker.models.USAStateCounty;
import com.nick.CoronavirusTracker.models.World;

@Component
public class ModelHelpers {
		
	public Optional<Country_Region> findCountryInWorld (World world, CSVRecord record) {
		
		String countryRegionName = record.get(world.getHeader().getCountry_region());
		Optional<Country_Region> country =
				world.getCountry_Regions().stream().filter(Country_Region ->  Country_Region.getName().equals(countryRegionName))
				.findAny();
		return country;
	}
	
	public Optional<State_Province> findStateProvinceInWorld (World world, CSVRecord record) {
		
		String countryRegionName = record.get(world.getHeader().getCountry_region());
		Optional<Country_Region> country =
				world.getCountry_Regions().stream().filter(Country_Region -> Country_Region.getName().equals(countryRegionName))
				.findAny();
		
		String stateProvinceName = record.get(world.getHeader().getState_province());
		Optional<State_Province> stateProvince = 
				country.get().getStates_Provinces().stream().filter(State_Province -> State_Province.getName().equals(stateProvinceName))
				.findAny();
		return stateProvince;
	}
	
	public Optional<USAStateCounty> findCountyInWorld (World world, CSVRecord record) {
		
		String countryRegionName = record.get(world.getHeader().getCountry_region());
		Optional<Country_Region> country =
				world.getCountry_Regions().stream().filter(Country_Region -> Country_Region.getName().equals(countryRegionName))
				.findAny();
		
		String stateProvinceName = record.get(world.getHeader().getState_province());
		Optional<State_Province> stateProvince = 
				country.get().getStates_Provinces().stream().filter(State_Province -> State_Province.getName().equals(stateProvinceName))
				.findAny();
		
		String countyName = record.get(world.getHeader().getCounty());
		Optional<USAStateCounty> county =
				stateProvince.get().getStateCounties().stream().filter(USAStateCounty -> USAStateCounty.getName().equals(countyName))
				.findAny();
		
		return county;
	}
	
	public void runTotalCasesWorld(World world, CSVRecord record, State_Province state_province) {
		
		int totalCases = state_province.getCoronavirusStats().get(state_province.getCoronavirusStats().size() -1).getCases();
		int totalPreviousDayCases = state_province.getCoronavirusStats().get(state_province.getCoronavirusStats().size() -2).getCases();
		
		Optional<Country_Region> foundCountry = findCountryInWorld(world, record);
		foundCountry.get().setTotalCases(totalCases);
		foundCountry.get().setChangeCasesSinceLastDay(totalCases - totalPreviousDayCases);
		
		world.setTotalCases(totalCases);
		world.setChangeCasesSinceLastDay(totalCases - totalPreviousDayCases);
	}
	
	public void runTotalDeathsWorld(Optional<State_Province> stateProvince, World world, CSVRecord record) {
		
		int totalDeaths = stateProvince.get().getCoronavirusStats().get(stateProvince.get().getCoronavirusStats().size() -1).getDeaths();
		int totalPreviousDayDeaths = stateProvince.get().getCoronavirusStats().get(stateProvince.get().getCoronavirusStats().size() -2).getDeaths();
		
		Optional<Country_Region> foundCountry = findCountryInWorld(world, record);
		foundCountry.get().setTotalDeaths(totalDeaths);
		foundCountry.get().setChangeDeathsSinceLastDay(totalDeaths - totalPreviousDayDeaths);
		
		world.setTotalDeaths(totalDeaths);
		world.setChangeDeathsSinceLastDay(totalDeaths - totalPreviousDayDeaths);
	}
	
	public void runTotalRecoveredWorld(Optional<State_Province> stateProvince, World world, CSVRecord record) {
		
		int totalRecovered = stateProvince.get().getCoronavirusStats().get(stateProvince.get().getCoronavirusStats().size() -1).getRecovered();
		int totalPreviousDayRecovered = stateProvince.get().getCoronavirusStats().get(stateProvince.get().getCoronavirusStats().size() -2).getRecovered();
		
		Optional<Country_Region> foundCountry = findCountryInWorld(world, record);
		foundCountry.get().setTotalRecovered(totalRecovered);
		foundCountry.get().setChangeRecoveredSinceLastDay(totalRecovered - totalPreviousDayRecovered);
		
		world.setTotalRecovered(totalRecovered);
		world.setChangeRecoveredSinceLastDay(totalRecovered - totalPreviousDayRecovered);
	}
	
	public void runTotalCasesWorldUS(USAStateCounty county, World world, CSVRecord record) {
		
		int totalCases = county.getCoronavirusStats().get(county.getCoronavirusStats().size() - 1).getCases();
		int totalPreviousDayCases = county.getCoronavirusStats().get(county.getCoronavirusStats().size() - 2).getCases();
		
		Optional<Country_Region> foundCountry = findCountryInWorld(world, record);
		foundCountry.get().setTotalCases(totalCases);
		foundCountry.get().setChangeCasesSinceLastDay(totalCases - totalPreviousDayCases); 
		
		Optional<State_Province> foundState = findStateProvinceInWorld(world, record);
		foundState.get().setTotalCases(totalCases);
		foundState.get().setChangeCasesSinceLastDay(totalCases - totalPreviousDayCases); 
		
		county.setTotalCases(totalCases);
		county.setChangeCasesSinceLastDay(totalCases - totalPreviousDayCases);
		
		world.setTotalCases(totalCases);
		world.setChangeCasesSinceLastDay(totalCases - totalPreviousDayCases); 
	}
	
	public void runTotalDeathsWorldUS(Optional<USAStateCounty> county, World world, CSVRecord record) {
		
		int totalDeaths = county.get().getCoronavirusStats().get(county.get().getCoronavirusStats().size() - 1).getDeaths();
		int totalPreviousDayDeaths = county.get().getCoronavirusStats().get(county.get().getCoronavirusStats().size() - 2).getDeaths();
		
		Country_Region unitedStates = world.getCountry_Regions().get(world.getCountry_Regions().size() -1);
		unitedStates.setTotalDeaths(totalDeaths);
		unitedStates.setChangeDeathsSinceLastDay(totalDeaths - totalPreviousDayDeaths);
		
		Optional<State_Province> foundState = findStateProvinceInWorld(world, record);
		foundState.get().setTotalDeaths(totalDeaths);
		foundState.get().setChangeDeathsSinceLastDay(totalDeaths - totalPreviousDayDeaths);
		
		county.get().setTotalDeaths(totalDeaths);
		county.get().setChangeDeathsSinceLastDay(totalDeaths - totalPreviousDayDeaths);
		
		world.setTotalDeaths(totalDeaths);
		world.setChangeDeathsSinceLastDay(totalDeaths - totalPreviousDayDeaths);
	}
}
