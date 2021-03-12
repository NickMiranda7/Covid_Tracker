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
			
<title>main page insert title here</title>
</head>
<body>
 
 <div class="main-container container">
 	<div class="row">
		<div class="col col-lg"> table
			<div class="table-navigation-header">
				<span class="mainDataTableTitle">UNITED STATES</span>
				<select name="states/provinces" id="states/provinces-select">
    			<option value="">Show all</option>
    			<!-- for each state show -->
				</select>
			</div>
			<div class="mini-table-header-wrap">
				<table class="mini-table-header">
					<tr>
						<td class="mini-table-header-head"> 
							<span class="mini-table-title">CASES</span>
							<div class="miniTable-casesData" id="miniTable-confirmedCases">99999999</div>
							<div class="miniTable-changePreviousDay"> 
								<span id="miniTable-confimredCasesChange">+"123"</span>
							</div>
						</td>
						<td class="mini-table-header-head"> 
							<span class="mini-table-title">DEATHS</span>
							<div class="miniTable-casesData" id="miniTable-confirmedDeaths">9999999</div>
							<div class="miniTable-changePreviousDay"> 
								<span id="miniTable-confimredDeathsChange">+"123"</span>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="main-table-wrap">
				<table class="mainDataTable">
					<thead>
						<tr class="mainDataTableHeaderRow">
							<th class="mainDataTableHeader" id="mainDataTable-Location">Location</th>
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
		
		
		<div class="col col-lg"> header and graph</div> 	
 	</div>
 	
 	
 </div>
 <div class="container-fluid">
 	<div class="row" id="map_row">
 		<span>MAP</span>
 	</div>
</div>
</body>
</html>