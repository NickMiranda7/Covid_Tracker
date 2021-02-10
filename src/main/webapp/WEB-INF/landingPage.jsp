<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
			<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
			rel="stylesheet" 
			integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
			crossorigin="anonymous">
			<link rel="stylesheet" href="/css/style.css">
<title>COVID-19 TRACKER</title>
</head>
<body>

<div class="container" id="landingPageContainer" >
	
	<p>This application is used to track COVID-19 cases throughout the United States of America</p>
	
	<div class="background" id="main-header">
		<h1 class="totalCasesNumber">${locationDataTotal.totalUSACases}</h1> 
		<p>Total cases as of today</p>
		<br>
		<p>New cases reported since yesterday: ${locationDataTotal.changesSinceLastDayTotal} </p>
	</div>
	
	<div>
		<table class="table table-hover" id="main-table">
			<thead>
				<tr>
					<th>State</th>
					<th>Province</th>
					<th>Country</th>
					<th>Total Cases Reported</th>
					<th>Changes since last day</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${locationData}" var="data">
				<tr>
					<td><c:out value="${data.state}"></c:out></td>
					<td><c:out value="${data.province}"></c:out></td>
					<td><c:out value="${data.country}"></c:out></td>
					<td class="totalCasesNumber"><c:out value="${data.latestTotalCases}"></c:out></td>
					<td><c:out value="${data.changesSinceLastDayLocal}"></c:out></td>
				</tr>			
			</c:forEach>
			</tbody>	
		</table>
	</div>
	
	
	
</div>

</body>
</html>