<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
			<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
			rel="stylesheet" 
			integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
			crossorigin="anonymous">
			<link rel="stylesheet" href="/css/styles.css">
			<script src="/scripts/tagScript.js" defer></script>
			<script src="/scripts/script.js" defer></script>
			
<title>Coronavirus Tracker</title>
</head>
<body>
<div class="mainWrapper">
	 	 	
<div class="tab-content">
	<div id="usaData" data-tab-content class="active">
	<div class="container-fluid" id="topContainer">
	<div class="row">
			<div class="col col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6 col-top">
				<div class="mainPills"> 
				 	<span class="mainTableTitle">Coronavirus Statistics</span>
					<div class="table-navigation-header">
						<span class="mainDataTableTitle">UNITED STATES</span>
						
						<select name="states/provinces" id="states/provinces-select">
		    			<option value="showAll">Show all</option>
		    			<c:forEach var="country" items="${USA}">
   							 <c:forEach var="state" items="${country.getStates_Provinces()}"> 
        						<option value="${state.name}">${state.name}</option>
    						 </c:forEach> 
						</c:forEach>
						</select>
					</div>			
					<div class="mini-table-header-wrap">
						<table class="mini-table-header">
							<tr>
								<td class="mini-table-header-head">
								 	<div class="mini-table-header-Cases">
										<span class="mini-table-title">CASES</span>
										<div class="miniTable-casesData" id="miniTable-confirmedCases">99999999</div>
										<div class="miniTable-changePreviousDay"> 
											<span id="miniTable-confimredCasesChange">+"123"</span>
										</div>
									</div>
								</td>
								<td class="mini-table-header-head">
									<div id="mini-table-header-Deaths">
										<span class="mini-table-title">DEATHS</span>
										<div class="miniTable-casesData" id="miniTable-confirmedDeaths">9999999</div>
										<div class="miniTable-changePreviousDay"> 
											<span id="miniTable-confimredDeathsChange">+"123"</span>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div class="main-table-wrap">
						<table class="mainDataTable">
							<thead class="mainDataTableHead">
								<tr class="mainDataTableHeaderRow">
									<th class="mainDataTableHeader mainDataTable-Location">Location</th>
									<th class="mainDataTableHeader" id="mainDataTable-Cases">Cases</th>
									<th class="mainDataTableHeader" id="mainDataTable-Deaths">Deaths</th>
								</tr>
							</thead>
							<tbody class="mainDataTableBody">
								<tr>
									<td>Alabama</td>
									<td>999999</td>
									<td>12345</td>
								</tr>
								<!-- if show all selected show all states
								if specific state/province selection show counties -->
							</tbody>
						</table>
					</div>
				</div>
			</div> 	
			
		<div class="col col-12 col-sm-12 col-md-12 col-lg-5 col-xl-5 col-bottom">
				<div class="tabs mainTabs">
					<button data-tab-target="#usaData">United States</button>
					<button data-tab-target="#worldData">World-Wide</button>
				</div>			
				<div class="mainDataHeader">
					<div class="dataHeaderWrap">
						<div class="dataTitle">CONFIRMED</div>
						<div class="dataNumber">9999999</div>
						<div class="changeDailyHeader">+"34"</div>
					</div>
					<div class="dataHeaderWrap">
						<div class="dataTitle">DEATHS</div>
						<div class="dataNumber">1234567</div>
						<div class="changeDailyHeader">+"0034"</div>
					</div>
					<div class="dataHeaderWrap">
						<div class="dataTitle">RECOVERED</div>
						<div class="dataNumber">000345</div>
						<div class="changeDailyHeader">+"534"</div>
					</div>
				</div>
				<div class="mainPills">
					<div class="mainGraphWrap">
						<div class="graphDropdown">
							<select name="cases/deaths-select">
				    			<option value="Cases">Cases</option>
				    			<option value="Deaths">Deaths</option>
				    			<option value="Recovered">Recovered</option>	
							</select>
						</div>
						<div class="mainGraph">
							<span>graph</span>
						</div>
					</div>
				</div>
			</div>	
	</div>
	</div>
	</div>	
		
	<div id="worldData" data-tab-content>	
	<div class="container-fluid" id="topContainer">
	<div class="row">
			<div class="col col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6 col-top">
				<div class="mainPills"> 
				 	<span class="mainTableTitle">Coronavirus Statistics</span>
					<div class="table-navigation-header">
						<span class="mainDataTableTitle">WORLDWIDE</span>
						<select name="states/provinces" id="states/provinces-select">
		    			<option value="">Show all</option>
		    			<c:forEach items="${worldCountries}" var="worldCountries">
		    				<option value="${worldCountries.name}">${worldCountries.name}</option>
		    			</c:forEach>
		    			<!-- for each state show -->
						</select>
					</div>
					<div class="mini-table-header-wrap">
						<table class="mini-table-header">
							<tr>
								<td class="mini-table-header-head">
								 	<div class="mini-table-header-Cases">
										<span class="mini-table-title">CASES</span>
										<div class="miniTable-casesData" id="miniTable-confirmedCases">99999999</div>
										<div class="miniTable-changePreviousDay"> 
											<span id="miniTable-confimredCasesChange">+"123"</span>
										</div>
									</div>
								</td>
								<td class="mini-table-header-head">
									<div id="mini-table-header-Deaths">
										<span class="mini-table-title">DEATHS</span>
										<div class="miniTable-casesData" id="miniTable-confirmedDeaths">9999999</div>
										<div class="miniTable-changePreviousDay"> 
											<span id="miniTable-confimredDeathsChange">+"123"</span>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div class="main-table-wrap">
						<table class="mainDataTable">
							<thead class="mainDataTableHead">
								<tr class="mainDataTableHeaderRow">
									<th class="mainDataTableHeader mainDataTable-Location">Location</th>
									<th class="mainDataTableHeader" id="mainDataTable-Cases">Cases</th>
									<th class="mainDataTableHeader" id="mainDataTable-Deaths">Deaths</th>
								</tr>
							</thead>
							<tbody class="mainDataTableBody">
								<tr>
									<td>afghanistan</td>
									<td>999999</td>
									<td>12345</td>
								</tr>
								<!-- if show all selected show all states
								if specific state/province selection show counties -->
							</tbody>
						</table>
					</div>
				</div>
			</div> 	
			
			<div class="col col-12 col-sm-12 col-md-12 col-lg-5 col-xl-5 col-bottom">
				<div class="tabs mainTabs">
					<button data-tab-target="#usaData">United States</button>
					<button data-tab-target="#worldData">World-Wide</button>
				</div>			
				<div class="mainDataHeader">
					<div class="dataHeaderWrap">
						<div class="dataTitle">CONFIRMED</div>
						<div class="dataNumber">89034589083</div>
						<div class="changeDailyHeader">+"34"</div>
					</div>
					<div class="dataHeaderWrap">
						<div class="dataTitle">DEATHS</div>
						<div class="dataNumber">73847843</div>
						<div class="changeDailyHeader">+"0034"</div>
					</div>
					<div class="dataHeaderWrap">
						<div class="dataTitle">RECOVERED</div>
						<div class="dataNumber">00565</div>
						<div class="changeDailyHeader">+"534"</div>
					</div>
				</div>
				<div class="mainPills">
					<div class="mainGraphWrap">
						<div class="graphDropdown">
							<select name="cases/deaths-select">
				    			<option value="Cases">Cases</option>
				    			<option value="Deaths">Deaths</option>
				    			<option value="Recovered">Recovered</option>	
							</select>
						</div>
						<div class="mainGraph">
							<span>graph</span>
						</div>
					</div>
				</div>
			</div> 	
	</div>
	</div>
	</div>
		
</div>		 			 	 	
</div>

<div class="container-fluid mainPills" id="mapContainer">
	<div class="mainPills">
	 	<div class="row" id="map_row">
	 		<span>MAP</span>
	 	</div>
	 </div>
</div>


</body>
</html>