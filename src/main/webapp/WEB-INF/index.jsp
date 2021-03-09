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
<div class="headerTabs">
  <button class="tablinks" onclick="openData('USA', this, 'red')" id="defaultOpen">USA</button>
  <button class="tablinks" onclick="openData('WORLD', this, 'WORLD')">WORLD</button>
</div>

<div id="USA" class="mainContent">
	<h1>United States Coronavirus Data</h1>
	<p>stats and such</p>
</div>

<div id="WORLD" class="mainContent">
	<h1>World Coronavirus Data</h1>
	<p>stats and such</p>
</div>

<script type="text/javascript" src="/scripts/mainScript.js"></script>
</body>
</html>