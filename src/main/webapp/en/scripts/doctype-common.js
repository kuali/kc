function displayNewRouteModuleEntry(isVisible)
{
  if(isVisible == 'yes') {
    document.forms[0].elements["newRouteModuleVisible"].value = "yes";
	if (document.getElementById) {
	  document.getElementById("newRouteModule").style.display = "inline";
	  document.getElementById("routeMethod").style.display = "none";
	} else {
	  document.all["newRouteModule"].style.display = "inline";
	  document.all["routeMethod"].style.display = "none";
	}
  } else if(isVisible == 'no'){
    document.forms[0].elements["newRouteModuleVisible"].value = "no";
    document.forms[0].elements["newRouteModuleName"].value = "";
    document.forms[0].elements["routeModuleName"].value = "";
    if (document.getElementById) {
	  document.getElementById("newRouteModule").style.display = "none";
	  document.getElementById("routeMethod").style.display = "inline";
    } else {
	  document.all["newRouteModule"].style.display = "none";
	  document.all["routeMethod"].style.display = "inline";
    }
  }
}

function firstVisibility()
{
  if (document.forms[0].elements["newRouteModuleVisible"].value == 'yes') {
    if (document.getElementById) {
       document.getElementById("newRouteModule").style.display = "inline";
	   document.getElementById("routeMethod").style.display = "none";
    } else {
       document.all["newRouteModule"].style.display = "inline";
	   document.all["routeMethod"].style.display = "none";
    }
  }
}