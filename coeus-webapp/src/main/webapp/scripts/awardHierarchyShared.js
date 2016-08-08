/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
function RequestTracker(liNode, callback) {
	this.liNode = liNode;
	this.callback = callback;
	this.startDate = new Date;
	this.children = null;
}

var DEBUG = false;
function debugLog(str) {
	console.log(str);
}

jQuery(document).ready(function(){
	jQuery('#awardHierarchyScrollable').scroll(function() {saveScrollPosition(this);});
	jQuery.ajaxSettings.cache = false; 
	jQuery("#awardhierarchy").treeview({
             toggle: function() {
	  				 //this method must be implemented by the page, not here in shared funcs
	  				 treeViewToggle(this);			 
                },
            animated: "fast",
            collapsed: true,
            control: "#treecontrol"
          });
	jQuery('#treecontrol').hide();
	jQuery('#shownCollapseLink').click(function() {
		jQuery('#treecontrol a:eq(0)').click();
  });
  
	jQuery('#shownExpandLink').click(function() {
	  if (jQuery('#awardhierarchy').find('li.expandable').length > 0) {
		  forceLoading();
		  jQuery('li.expandable').each(function() {
			  var liItem = this;
			  queueToggle(liItem, expandAll); 
		  });
	  }
  });
}); // $(document).ready

function expandAll(requestTracker) {
	if (requestTracker.liNode != null) {
		jQuery(requestTracker.liNode).find('div.expandable-hitarea:first').click();
	}
	jQuery(requestTracker.children).each(function() {
		queueToggle(this, expandAll);
	});
	if (activeRequest != null) {
		debugLog('Active Request - ' + getAwardNumber(activeRequest.liNode));
	}
	logPending();
	if (activeRequest == null && pendingRequests.length == 0) {
		clearForceLoading();
	}
	
}

var pendingRequests = [];
var activeRequest;

var forceLoadingMessage = null;
function showLoading() {
	jQuery('#loading span.statusMessage').html('Loading ' + (pendingRequests.length) + ' items');
	jQuery('#loading').show();
}
function logPending() {
	var str = 'pending requests = ';
	jQuery(pendingRequests).each(function() {
		str += getAwardNumber(this.liNode) + ',';
	});
	debugLog(str);
}
function finishLoading(requestTracker) {
	debugLog('finished loading something');
	logPending();
	if (requestTracker == activeRequest) {
		activeRequest = null;
	}
	if (requestTracker != null && requestTracker.callback != null) {
		requestTracker.callback(requestTracker);
	}
    if (pendingRequests.length > 0 && activeRequest == null) {
    	activeRequest = pendingRequests.shift() 
    	loadChildren(activeRequest);
    } else if (activeRequest == null) {
    	if(!forceLoadingMessage) {
    		jQuery('#loading').hide();
    	}
    	fixDatePickers();	
	}
}
function forceLoading() {
	debugLog('forcing loading message to be displayed until cleared');
	forceLoadingMessage = true;
	showLoading();
}
function clearForceLoading() {
	debugLog('clearing forced loading message');
	forceLoadingMessage = false;
	finishLoading(null);
}
function queueToggle(liNode, callback) {
	var requestTracker = new RequestTracker(liNode, callback);
	showLoading();
	if (pendingRequests.length <= 0 && activeRequest == null) {
		activeRequest = requestTracker;
		loadChildren(requestTracker);
	} else {
		pendingRequests.push(requestTracker);
	}
}
         
function fixDatePickers(listItem) {
    //when loading children, we must remove all of the previous images and scripts.  Otherwise, there will be multiple
    //datepickers added to each cell depending on how deep we dig into the hierarchy and how many times we toggle.
	jQuery('.datepickerImage').remove();
	jQuery('.datepickerScript').remove();
	jQuery('.datepicker').each(
   			function() {
   				var id = jQuery(this).attr("id");
   			    var img1 =jQuery("<img class='datepickerImage' src='kr/static/images/cal.gif' id='" + id +"_datepicker' style='cursor: pointer;'  title='Date selector' alt='Date selector' onmouseover=\"this.style.backgroundColor='red';\" onmouseout=\"this.style.backgroundColor='transparent';\"/>");
   			    img1.insertAfter(jQuery(this));
   			    Calendar.setup({ inputField : id, ifFormat : '%m/%d/%Y',  button : id + '_datepicker'});
   			});
}
    
    /*
	 * load children area of research when parents RA is expanding.
	 */
  function loadChildren(requestTracker) {
	  var liNode = null;
	  if (requestTracker != null) {
		  liNode = requestTracker.liNode;
	  }
	  var ulNode = jQuery('#awardhierarchy');
	  var awardNumber = '';
	  var addRA = 'N';	  
	  if (liNode != null) {
	      var ulNode = jQuery(liNode).children('ul:eq(0)');
	      var awardNumber = getAwardNumber(liNode);
	      var addRA = 'E';
	  } 
	  
	  debugLog('Loading children for ' + awardNumber);
      if ((liNode != null && !jQuery(liNode).is('.loaded'))
    	    || jQuery(ulNode).find('li.awardhierarchy').length == 0) {
    	  jQuery.ajax({
           url: AJAX_LOCATION,
           type: 'GET',
           dataType: 'html',
           data:'awardNumber='+awardNumber+'&addRA=' + addRA + '&' + ROOT_AWARD_LOCATION +'=' + jQuery(jq_escape(ROOT_AWARD_LOCATION)).attr("value") + '&currentAwardNumber='+ jQuery("#currentAwardNumber").attr("value") + '&currentSeqNumber='+ jQuery("#currentSeqNumber").attr("value") + '&csrfToken=' + jQuery('[name=csrfToken]').val(),
           cache: false,
           async: true,
           timeout: 90000,
           error: function(error){
    		  console.log(error);
              alert('Error loading Award Hierarchy information. Check to see if Award was canceled.');
              if (liNode != null) {
            	  jQuery(liNode).find('div.collapsable-hitarea:first').click();
              }
              finishLoading(requestTracker);
           },
           success: function(xml){
         	  try {
         		  var newChildren = [];
	        	  var json = jQuery(xml).find('#json').html();
	        	  debugLog(json);
	        	  var hierarchyArray = eval(json);
	        	  jQuery(hierarchyArray).each(function(){
	        		  var newChild = addAwardToHierarchy(this, ulNode);
	            	  newChildren.push(newChild);
	              });
	              if (liNode != null) {
	            	  jQuery(liNode).addClass('loaded');
	              }
	              requestTracker.children = newChildren;
        	  } catch (e) {
        		  alert('Error loading Award Hierarchy information' + e);
                  if (liNode != null) {
                	  jQuery(liNode).find('div.collapsable-hitarea:first').click();
                  }  
        	  }
        	  finishLoading(requestTracker);
           }
          });
      } else {
    	  requestTracker.children = jQuery(liNode).find('li.awardhierarchy').toArray();
    	  finishLoading(requestTracker);
      }
  } // end loadChildren 
