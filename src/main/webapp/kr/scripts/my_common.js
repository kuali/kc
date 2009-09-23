/*
 * Copyright 2005-2007 The Kuali Foundation
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
//window.onerror=myErrorHandler;

var resizeTimerId = ""; // holds the timer for the iframe resizer
var passedFrameName = "";
var passedFocusHeight = "";
var currentHeight = "";
var isChanged = 0;

var safari = navigator.userAgent.toLowerCase().indexOf('safari');

function myErrorHandler() {
   // we should turn this to false and deal with our errors at some point, but for now I am leaving to true for phase 1 release...
   return true;
}

function getDocHeight(doc) {
  var docHt = 0, sh, oh;
  if (doc.height) {
    docHt = doc.height;
  } else if (doc.body) {
    if (doc.body.offsetHeight) { docHt = oh = 0; }
    if (doc.body.scrollHeight) { docHt = sh = doc.body.scrollHeight; }
    if (sh && oh) { docHt = Math.max(sh, oh); }
  }
  return docHt;
}

function doTheResize() { 
  if (passedFrameName != "" && passedFocusHeight != ""){
      setFocusedIframeDimensions(passedFrameName, passedFocusHeight, false );
  }
}

function setFocusedIframeDimensions(iframeName, focusHeight, resetToDefaultWidth ) {
  try {
    passedFrameName = iframeName;
    passedFocusHeight = focusHeight;
    if (currentHeight == "") {
      currentHeight = focusHeight;
    }
    var iframe_portlet_container_table = document.getElementById('iframe_portlet_container_table');
    var iframeWin = window.frames[iframeName];
    var iframeEl = document.getElementById? document.getElementById(iframeName): document.all? document.all[iframeName]: null;
  
    if ( iframeEl && iframeWin && iframe_portlet_container_table) {
      var docHeight = getDocHeight(iframeWin.document);      
  	  if ( resetToDefaultWidth ) {
  	    iframe_portlet_container_table.width = "100%";
  	  }
  	  var frameScrollWidth = iframeEl.contentWindow.document.documentElement.scrollWidth;
	  //window.status = frameScrollWidth + "/" + iframe_portlet_container_table.scrollWidth + "/" + iframe_portlet_container_table.width; 
      if (docHeight > 150) {
        //console.log( "iframeEl.contentDocument.documentElement.scrollWidth="+iframeEl.contentDocument.documentElement.scrollWidth
        //			+"\niframe_portlet_container_table.width="+iframe_portlet_container_table.width
        //			+"\niframe_portlet_container_table.scrollWidth="+iframe_portlet_container_table.scrollWidth );
        
        if ( Math.abs( frameScrollWidth - iframe_portlet_container_table.scrollWidth ) > 10 ) {
        	if ( frameScrollWidth > iframe_portlet_container_table.scrollWidth ) {
        		iframe_portlet_container_table.width = frameScrollWidth + 30;
        	}
        }

        if ((Math.abs(docHeight - currentHeight)) > 20 ) {
          if (safari > -1) {
            if ((Math.abs(docHeight - currentHeight)) > 59 ) {
              iframeEl.style.height = docHeight + 30 + "px";
              currentHeight = getDocHeight(iframeWin.document);
            }
          } else { 
            if (parseInt(iframeEl.style.height) < currentHeight || parseInt(iframeEl.style.height) < docHeight) {
              iframeEl.style.height = docHeight + 30 + "px";
              currentHeight = getDocHeight(iframeWin.document);
            } else {
              if (docHeight < currentHeight &&  (currentHeight - 150 > docHeight)) {
                iframeEl.style.height = docHeight + 30 + "px";
                currentHeight = getDocHeight(iframeWin.document);
              }
            }
          }
        }
      }
    }
  }
  catch(err)
  {
  	window.status = err.description;
  }
}

window.setInterval(doTheResize,500);

function setIframeAnchor(iframeName) {
  var iframeWin = window.frames[iframeName];
  if (iframeWin && iframeWin.location.href.indexOf("#") > -1) {
    iframeWin.location.replace(iframeWin.location);
  }  
}

function jumpToAnchorName(anchor){
	var anchors = document.getElementsByName(anchor);
	if (anchors != null)
		location.href = '#'+anchors[0].name;
}
