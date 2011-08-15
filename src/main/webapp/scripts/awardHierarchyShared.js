function RequestTracker(liNode, callback) {
	this.liNode = liNode;
	this.callback = callback;
	this.startDate = new Date;
	this.children = null;
}

var DEBUG = false;
function debugLog(str) {
	if (DEBUG) {
		$('#debugLog').show();
		if ($('#debugLog').find('div').length > 1000) {
			$('#debugLog').find('div').first().remove();
		}
		if (str.length > 180) {
			str = str.substring(0, 180) + "...";
		}
		$('#debugLog').append('<span style="text-align: left; float: left; width: 100%; border: 1px yellow solid;">' + str + '</span>');
	}
}

$(document).ready(function(){
  $('#awardHierarchyScrollable').scroll(function() {saveScrollPosition(this);});
  $.ajaxSettings.cache = false; 
  $("#awardhierarchy").treeview({
             toggle: function() {
	  				 //this method must be implemented by the page, not here in shared funcs
	  				 treeViewToggle(this);			 
                },
            animated: "fast",
            collapsed: true,
            control: "#treecontrol"
          });
  $('#treecontrol').hide();
  $('#shownCollapseLink').click(function() {
	  $('#treecontrol a:eq(0)').click();
  });
  
  $('#shownExpandLink').click(function() {
	  if ($('#awardhierarchy').find('li.expandable').length > 0) {
		  forceLoading();
		  $('li.expandable').each(function() {
			  var liItem = this;
			  queueToggle(liItem, expandAll); 
		  });
	  }
  });
}); // $(document).ready

function expandAll(requestTracker) {
	if (requestTracker.liNode != null) {
		$(requestTracker.liNode).find('div.expandable-hitarea:first').click();
	}
	$(requestTracker.children).each(function() {
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
function findPendingRequest(liNode) {
	if ($.inArray(liNode, pendingRequests) != -1) {
		return pendingRequests[$.inArray(liNode, pendingRequests)];
	} else {
		return null;
	}
}
var forceLoadingMessage = null;
function showLoading() {
	$('#loading span.statusMessage').html('Loading ' + (pendingRequests.length) + ' items');
	$('#loading').show();
}
function logPending() {
	var str = 'pending requests = ';
	$(pendingRequests).each(function() {
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
    } else if (activeRequest == null && !forceLoadingMessage) {
		$('#loading').hide();
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
         
function fixDatePickers() {
    //when loading children, we must remove all of the previous images and scripts.  Otherwise, there will be multiple
    //datepickers added to each cell depending on how deep we dig into the hierarchy and how many times we toggle.
    $('.datepickerImage').remove();
	 $('.datepickerScript').remove();
    $('.datepicker').each(
   			function() {
   				var id = $(this).attr("id");
   			    var img1 =$("<img class='datepickerImage' src='kr/static/images/cal.gif' id='" + id +"_datepicker' style='cursor: pointer;'  title='Date selector' alt='Date selector' onmouseover=\"this.style.backgroundColor='red';\" onmouseout=\"this.style.backgroundColor='transparent';\"/>");
   			    img1.insertAfter($(this));
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
	  var ulNode = $('#awardhierarchy');
	  var awardNumber = '';
	  var addRA = 'N';	  
	  if (liNode != null) {
	      var ulNode = $(liNode).children('ul:eq(0)');
	      var awardNumber = getAwardNumber(liNode);
	      var addRA = 'E';
	  } 
	  
	  debugLog('Loading children for ' + awardNumber);
      if ((liNode != null && !$(liNode).is('.loaded'))
    	    || $(ulNode).find('li.awardhierarchy').length == 0) {
          $.ajax({
           url: AJAX_LOCATION,
           type: 'GET',
           dataType: 'html',
           data:'awardNumber='+awardNumber+'&addRA=' + addRA + '&' + ROOT_AWARD_LOCATION +'=' + $(jq(ROOT_AWARD_LOCATION)).attr("value") + '&currentAwardNumber='+ $("#currentAwardNumber").attr("value") + '&currentSeqNumber='+ $("#currentSeqNumber").attr("value"),
           cache: false,
           async: true,
           timeout: 30000,
           error: function(){
              alert('Error loading Award Hierarchy information');
              if (liNode != null) {
            	  $(liNode).find('div.collapsable-hitarea:first').click();
              }
              finishLoading(requestTracker);
           },
           success: function(xml){
         	  try {
         		  var newChildren = [];
	        	  var json = $(xml).find('#json').html();
	        	  debugLog(json);
	        	  var hierarchyArray = eval(json);
	              $(hierarchyArray).each(function(){
	            	  newChildren.push(addAwardToHierarchy(this, ulNode));
	              });
	              if (liNode != null) {
	            	  $(liNode).addClass('loaded');
	              }
	              requestTracker.children = newChildren;
        	  } catch (e) {
        		  alert('Error loading Award Hierarchy information' + e);
                  if (liNode != null) {
                	  $(liNode).find('div.collapsable-hitarea:first').click();
                  }  
        	  }
        	  fixDatePickers();
              finishLoading(requestTracker);
           }
          });    
      } else {
    	  requestTracker.children = $(liNode).find('li.awardhierarchy').toArray();
    	  finishLoading(requestTracker);
      }
  } // end loadChildren 