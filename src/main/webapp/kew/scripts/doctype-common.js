/*
 * Copyright 2007-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
