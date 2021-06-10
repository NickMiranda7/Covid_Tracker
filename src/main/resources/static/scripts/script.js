/**
 * 
 */

const jsonURL= 'http://localhost:8080/jsonobject';
getJson();
earth = {};
earthUS = {};
async function getJson() {
	const response = await fetch(jsonURL);
	const data = await response.json();
	let earthUS = data[0];
	let earth = data[1];
	
	console.log(data);
	console.log(earthUS);
	console.log(earth);
	console.log(Object.keys(earthUS));
	console.log(earthUS.country_Regions[0].name);
}

function setState() {	
	e = document.getElementById('states/provinces-select');
	console.log(e);
	var value = e.options[e.selectedIndex].value;
	console.log(value);
	
	
console.log(earthUS.country_Regions[0].name);
}