function openData(cityName, elmnt, color) {
  // Hide all elements with class="mainContent" by default */
  var i, mainContent, tablinks;
  mainContent = document.getElementsByClassName("mainContent");
  for (i = 0; i < mainContent.length; i++) {
    mainContent[i].style.display = "none";
  }

  // Remove the background color of all tablinks/buttons
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].style.backgroundColor = "";
  }

  // Show the specific tab content
  document.getElementById(cityName).style.display = "block";

}

// Get the element with id="defaultOpen" and click on 
	var main = document.getElementById("defualtOpen");
	console.log(main);
