/*
 * Copyright 2006-2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Toggles a tab to show / hide and changes the source image to properly reflect this
// change. Returns false to avoid post. Example usage:
// onclick="javascript: return toggleTab(document, this, ${currentTabIndex}) }
function toggleTab(doc, tabKey) {
	if (doc.forms[0].elements['tabStates(' + tabKey + ')'].value == 'CLOSE') {
        showTab(doc, tabKey);
    } else {
        hideTab(doc, tabKey);
	}
	return false;
}

function expandAllTab(doc, tabStatesSize) {
	for (var tabIndex = 0; tabIndex <= tabStatesSize.value; tabIndex++) {
        showTab(doc, tabIndex);
	}
	return false;
}

function collapseAllTab(doc, tabStatesSize) {
	for (var tabIndex = 0; tabIndex <= tabStatesSize.value; tabIndex++) {
        hideTab(doc, tabIndex);
	}
	return false;
}

function showTab(doc, tabKey) {
    // replaced 'block' with '' to make budgetExpensesRow.tag happy.
    doc.getElementById('tab-' + tabKey + '-div').style.display = '';
    doc.forms[0].elements['tabStates(' + tabKey + ')'].value = 'OPEN';
    var image = doc.getElementById('tab-' + tabKey + '-imageToggle');
    image.src = jsContextPath + '/kr/images/tinybutton-hide.gif';
    image.alt = image.alt.replace(/^show/, 'hide');
    image.alt = image.alt.replace(/^open/, 'close');
    image.title = image.title.replace(/^show/, 'hide');
    image.title = image.title.replace(/^open/, 'close');
    return false;
}

function hideTab(doc, tabKey) {
    doc.getElementById('tab-' + tabKey + '-div').style.display = 'none';
    doc.forms[0].elements['tabStates(' + tabKey + ')'].value = 'CLOSE';
    var image = doc.getElementById('tab-' + tabKey + '-imageToggle');
    image.src = jsContextPath + '/kr/images/tinybutton-show.gif';
    image.alt = image.alt.replace(/^hide/, 'show');
    image.alt = image.alt.replace(/^close/, 'open');
    image.title = image.title.replace(/^hide/, 'show');
    image.title = image.title.replace(/^close/, 'open');
    return false;
}

var formHasAlreadyBeenSubmitted = false;
var excludeSubmitRestriction = false;
function hasFormAlreadyBeenSubmitted() {

	if ( document.getElementById( "formComplete" ) ) { 
    if (formHasAlreadyBeenSubmitted && !excludeSubmitRestriction) {
       alert("Page already being processed by the server.");
       return false;
    } else {
       formHasAlreadyBeenSubmitted = true;
       return true;
    }
    excludeSubmitRestriction = false;
    } else {
	       alert("Page has not finished loading.");
	       return false;
	} 
}

function submitForm() {
    document.forms[0].submit();
}

function saveScrollPosition() {
//	alert( document.forms[0].formKey );
	if ( document.forms[0].formKey ) {
		formKey = document.forms[0].formKey.value;
		if( document.documentElement ) { 
			x = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft); 
		  	y = Math.max(document.documentElement.scrollTop, document.body.scrollTop); 
		} else if( document.body && typeof document.body.scrollTop != "undefined" ) { 
			x = document.body.scrollLeft; 
		  	y = document.body.scrollTop; 
		} else if ( typeof window.pageXOffset != "undefined" ) { 
			x = window.pageXOffset; 
		  	y = window.pageYOffset; 
		} 
		document.cookie = "KulScrollPos"+formKey+"="+x+","+y+"; path="+document.location.pathname;
	}
	// test read cookie back
//	matchResult = document.cookie.match(new RegExp("KulScrollPos"+formKey+"=([^;]+);?"));
//	if ( matchResult ) {
//		alert( "Cookie: " + matchResult[1] );
//	}
}

function restoreScrollPosition() {
    if ( document.forms[0].formKey ) {
        formKey = document.forms[0].formKey.value;
        var cookieName = "KulScrollPos"+formKey;
        var matchResult = document.cookie.match(new RegExp(cookieName+"=([^;]+);?"));
        if ( matchResult ) {
            var coords = matchResult[1].split( ',' );
            window.scrollTo(coords[0],coords[1]);
            expireCookie( cookieName );
            return true;
        } else { // check for entry before form key set
        	cookieName = "KulScrollPos";
	        var matchResult = document.cookie.match(new RegExp(cookieName+"=([^;]+);?"));
	        if ( matchResult ) {
	            var coords = matchResult[1].split( ',' );
	            window.scrollTo(coords[0],coords[1]);
	            expireCookie( cookieName );
	            return true;
	        }
        }
    }
    return false;
}

function expireCookie( cookieName ) {
	var date = new Date();
	date.setTime( date.getTime() - 60000 );
	document.cookie = cookieName+"=0,0; expires="+date.toGMTString()+"; path="+document.location.pathname;
}

/* script to prevent the return key from submitting a form unless the user is on a button or on a link. fix for KULFDBCK-555 */ 
function isReturnKeyAllowed(buttonPrefix , event) {
	/* use IE naming first then firefox. */
    var elemType = event.srcElement ? event.srcElement.type : event.target.type;
    if (elemType != null && elemType.toLowerCase() == 'textarea') {
      // KULEDOCS-1728: textareas need to have the return key enabled
      return true;
    }
	var initiator = event.srcElement ? event.srcElement.name : event.target.name;
	var key = event.keyCode;
	/* initiator is undefined check is to prevent return from doing anything if not in a form field since the initiator is undefined */
	/* 13 is return key code */
	/* length &gt; 0 check is to allow user to hit return on links */
	if ( key == 13 ) {
		if( initiator == undefined || ( initiator.indexOf(buttonPrefix) != 0 && initiator.length > 0) ) {
		  // disallow enter key from fields that dont match prefix.
		  return false;
		}
	}
    return true;
}

//The following javascript is intended to resize the route log iframe
// to stay at an appropriate height based on the size of the documents
// contents contained in the iframe.
//  NOTE: this will only work when the domain serving the content of kuali
//         is the same as the domain serving the content of workflow.
var routeLogResizeTimer = ""; // holds the timer for the  route log iframe resizer
var currentHeight = 500; // holds the current height of the iframe
var safari = navigator.userAgent.toLowerCase().indexOf('safari');

function setRouteLogIframeDimensions() {
  var routeLogFrame = document.getElementById("routeLogIFrame");
  var routeLogFrameWin = window.frames["routeLogIFrame"];
  var frameDocHeight = 0;
  try {
    frameDocHeight = routeLogFrameWin.document.height;
  } catch ( e ) {
    // unable to set due to cross-domain scripting
    frameDocHeight = 0;
  }

  if ( frameDocHeight > 0 ) {
	  if (routeLogFrame && routeLogFrameWin) {
	  	
	    if ((Math.abs(frameDocHeight - currentHeight)) > 20 ) {
	      if (safari > -1) {
	        if ((Math.abs(docHt - currentHeight)) > 59 ) {
	          routeLogFrame.style.height = (frameDocHeight + 30) + "px";
	          currentHeight = frameDocHeight;
	        }
	      } else {    
	        routeLogFrame.style.height = (frameDocHeight + 30) + "px";
	        currentHeight = frameDocHeight;
	      }
	    }
	  
	    if (routeLogResizeTimer == "" ) {
	      routeLogResizeTimer = setInterval("resizeTheRouteLogFrame()",300);
	    }
	  }
  }
}

function resizeTheRouteLogFrame() {
  setRouteLogIframeDimensions();
}

// should be in rice for direct inquiry 
 function inquiryPop(boClassName, inquiryParameters){
  parameterPairs = inquiryParameters.split(",");
  queryString="businessObjectClassName="+boClassName+"&methodToCall=start"
  for (i in parameterPairs) {
  
    parameters = parameterPairs[i].split(":");
  	if (document.forms[0].elements[parameters[0]].value=="") 
  	{
  		alert("Please enter a value in the appropriate field.");
  		//queryString=queryString+"&"+parameters[1]+"=directInquiryParameterNotSpecified";
		return false;
  	} else {
    	queryString=queryString+"&"+parameters[1]+"="+document.forms[0].elements[parameters[0]].value;
  	}
  }
  url=window.location.href
  pathname=window.location.pathname
  idx1=url.indexOf(pathname);
  idx2=url.indexOf("/",idx1+1);
  baseUrl=url.substr(0,idx2)
  window.open(baseUrl+"/kr/directInquiry.do?"+queryString, "_blank", "width=640, height=600, scrollbars=yes");
}
 
function textAreaPop(textAreaName,
                     htmlFormAction,
                     textAreaLabel,
                     docFormKey,
                     sessionDocument) {
  var documentWebScope
  if (sessionDocument == "true") {
      documentWebScope="session"
  }
  url=window.location.href
  pathname=window.location.pathname
  idx1=url.indexOf(pathname);
  idx2=url.indexOf("/",idx1+1);
  baseUrl=url.substr(0,idx2)
  window.open(baseUrl+"/updateTextArea.do?&textAreaFieldName="+textAreaName+"&htmlFormAction="+htmlFormAction+"&textAreaFieldLabel="+textAreaLabel+"&docFormKey="+docFormKey+"&documentWebScope="+documentWebScope);
}

var textAreaFieldName;
function setTextArea() {
  passData=document.location.search.substring(1);
  var idx=passData.indexOf("&textAreaFieldName=");
  var idx2=passData.indexOf("&htmlFormAction=");
  textAreaFieldName=passData.substring(idx+19,idx2);
  var ta = window.opener.document.getElementsByName(textAreaFieldName)[0];
  text = ta.value;
  document.getElementsByName(textAreaFieldName)[0].value = text;
  
}

function postValueToParentWindow() {
  opener.document.getElementsByName(textAreaFieldName)[0].value = document.getElementsByName(textAreaFieldName)[0].value;
  self.close();
}

function showHide(showId,hideId){
  var style_sheet = getStyleObject(showId);
  if (style_sheet)
  {
	changeObjectVisibility(showId, "block");
	changeObjectVisibility(hideId, "none");
  }
  else 
  {
    alert("sorry, this only works in browsers that do Dynamic HTML");
  }
}

function changeObjectVisibility(objectId, newVisibility) {
    // first get the object's stylesheet
    var styleObject = getStyleObject(objectId);

    // then if we find a stylesheet, set its visibility
    // as requested
    //
    if (styleObject) {
		styleObject.display = newVisibility;
	return true;
    } else {
	return false;
    } 
}

function getStyleObject(objectId) {
  // checkW3C DOM, then MSIE 4, then NN 4.
  //
  if(document.getElementById && document.getElementById(objectId)) {
	return document.getElementById(objectId).style;
   }
   else if (document.all && document.all(objectId)) {  
	return document.all(objectId).style;
   } 
   else if (document.layers && document.layers[objectId]) { 
	return document.layers[objectId];
   } else {
	return false;
   }
}
