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

if (!Function.prototype.bind) {
  Function.prototype.bind = function (oThis) {
    if (typeof this !== "function") {
      // closest thing possible to the ECMAScript 5 internal IsCallable function
      throw new TypeError("Function.prototype.bind - what is trying to be bound is not callable");
    }
 
    var aArgs = Array.prototype.slice.call(arguments, 1), 
        fToBind = this, 
        fNOP = function () {},
        fBound = function () {
          return fToBind.apply(this instanceof fNOP && oThis
                                 ? this
                                 : oThis,
                               aArgs.concat(Array.prototype.slice.call(arguments)));
        };
 
    fNOP.prototype = this.prototype;
    fBound.prototype = new fNOP();
 
    return fBound;
  };
}

//override callHandler to support WarningOnAddRow functionality for date pickers
Calendar.prototype.callHandler = function () {
	if (this.onSelected) {
		this.onSelected(this, this.date.print(this.dateFormat));
	}
	jQuery(this.params.inputField).trigger('change');
};

//Fix rice ids for use with jquery
//Takes an id and escapes the . : ] and [ and adds #
function jq_escape(myid) { 
  return '#' + myid.replace(/(:|\.|\[|\])/g,'\\$1');
}


var jq = jQuery.noConflict();
jq(document).ready(function() {
    createLoading(false);
});

function hasFormAlreadyBeenSubmitted() {
	try {
		// save the current scroll position
		saveScrollPosition();
	} catch ( ex ) {
		// do nothing - don't want to stop submit
	}

	if ( document.getElementById( "formComplete" ) ) { 
	    if (formHasAlreadyBeenSubmitted && !excludeSubmitRestriction) {
	       alert("Page already being processed by the server.");
	       return false;
	    } else {
	       createLoading(true);
	       if (excludeSubmitRestriction) {
	    	   timeout = setTimeout(function() {
	    		   createLoading(false);
	    	   }, 1000);
	    	   excludeSubmitRestriction = false;
	       } else {
	    	   formHasAlreadyBeenSubmitted = true;
	       }
	       return true;
	    }
    } else {
	       alert("Page has not finished loading.");
	       return false;
	} 
}

/**
 * Uses jQuery plug-in to show a loading notification for a page request. See
 * <link>http://plugins.jquery.com/project/showLoading</link> for documentation
 * on options.
 *
 * @param showLoading -
 *          boolean that indicates whether the loading indicator should be shown
 *          (true) or hidden (false)
 */
function createLoading(showLoading) {

    var processingMessage = '<h1><img src="' + getUrlWithContext() + '/krad/images/loading.gif" alt="working..." />Page is being processed by the server....</h1>';
    
        if (showLoading) {
            getContext().blockUI({message: processingMessage});
        }
        else {
            getContext().unblockUI();
        }
}

/**
 * Get the current context
 *
 * @returns the jQuery context that can be used to perform actions that must be global to the entire page
 * ie, showing lightBoxes and growls etc
 */
function getContext(){
    if (usePortalForContext()) {
        return top.jQuery;
    }
    else {
        return jq;
    }
}
function generateInputParams(reportSelect) {	
	
	var reportId = dwr.util.getValue(reportSelect);
	var reportLabel = reportSelect.options[reportSelect.selectedIndex].text;
	if(reportId > 0) {
	document.forms[0].action=extractUrlBase()+ "/reporting.do?methodToCall=getReportParametersFromDesign&reportId="+reportId+"&reportLabel="+reportLabel;
	document.forms[0].submit();
	}
}

function resetBirtSelectDiv() {
	document.getElementById("custReportDetails.reportLabelDisplay").selectedIndex =0;
	
}

/**
 * Check if portal should be used for context
 *
 * <p>
 * To avoid cross server script errors the local context is used in case the portal window is on a different host.
 * </p>
 *
 * @return true if portal is used for context, false otherwise
 */
function usePortalForContext() {
    var usePortal = false;

    // for iframe use the outer window's context unless the outer window is hosted on a different domain.
    try {
        // For security reasons the browsers will not allow cross server scripts and
        // throw an exception instead.
        // Note that bad browsers (e.g. google chrome) will not catch the exception
        usePortal = (top != self) && (top.location.host == location.host);
    }
    catch (e) {
        usePortal = false;
    }

    return usePortal;
}


function updateSourceNameEditable(fundingSourceTypeCodeFieldName, fundingSourceNumberFieldName, fundingSourceNameFieldName, fundingSourceTitleFieldName, protocolModule) {
	var fundingSourceTypeCode = dwr.util.getValue( fundingSourceTypeCodeFieldName );
	var allowEdit;
	
	clearRecipients( fundingSourceNameFieldName);
	clearRecipients( fundingSourceTitleFieldName);
	if (fundingSourceTypeCode=='') {
		changeObjectVisibility( fundingSourceNameFieldName + ".error.div", "none" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					isEditable = data;
					if ( isEditable ) {
					   	changeObjectVisibility( fundingSourceNameFieldName + ".edit.div", "block" );
					   	changeObjectVisibility( fundingSourceNameFieldName + ".display.div", "none" );
					} else {
					   	changeObjectVisibility( fundingSourceNameFieldName + ".edit.div", "none" );
					   	changeObjectVisibility( fundingSourceNameFieldName + ".display.div", "block" );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				changeObjectVisibility( fundingSourceNameFieldName + ".error.div", "block" );
			}
		};
		if(protocolModule == 'iacuc') {
			//alert("calling iacuc service now");
			IacucProtocolFundingSourceService.isEditable(fundingSourceTypeCode, dwrReply);
		}
		else {
			ProtocolFundingSourceService.isEditable(fundingSourceTypeCode, dwrReply);
		}
		loadFundingSourceNameTitle(fundingSourceTypeCodeFieldName, fundingSourceNumberFieldName, fundingSourceNameFieldName, fundingSourceTitleFieldName, protocolModule);
		if (fundingSourceTypeCode == '4' ) {
			changeObjectVisibility(fundingSourceTypeCodeFieldName + ".startproposal.image.div", "inline");
		} else {
			changeObjectVisibility(fundingSourceTypeCodeFieldName + ".startproposal.image.div", "none"); 
		}

	}
}

/*
 * Load the Funding Source Name field based on the source and type passed in.
 */
function loadFundingSourceNameTitle(fundingSourceTypeCodeField, fundingSourceNumberFieldName, fundingSourceNameFieldName, fundingSourceTitleFieldName, protocolModule) {
	var fundingSourceTypeCode = dwr.util.getValue( fundingSourceTypeCodeField );
	var fundingSource = "";
	var fundingSourceNumber = dwr.util.getValue ( fundingSourceNumberFieldName );
	var fundingSourceName = dwr.util.getValue( fundingSourceNameFieldName );
	var fundingSourceTitle = dwr.util.getValue( fundingSourceTitleFieldName );
	var docFormKey = dwr.util.getValue( "docFormKey" );

	changeObjectVisibility( fundingSourceNumberFieldName + ".lookup.div", "inline" );
	if (fundingSourceTypeCode != '') {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					isLookupable = data;
					if ( isLookupable ) {
						changeObjectVisibility( fundingSourceNumberFieldName + ".lookup.div", "inline" );
					} else {
						changeObjectVisibility( fundingSourceNumberFieldName + ".lookup.div", "none" );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				changeObjectVisibility( fundingSourceNameFieldName + ".error.div", "block" );
			}
		};
		if(protocolModule == 'iacuc') {
			//alert("calling iacuc service now");
			IacucProtocolFundingSourceService.isLookupable(fundingSourceTypeCode, dwrReply);
		}
		else {
			ProtocolFundingSourceService.isLookupable(fundingSourceTypeCode, dwrReply);
		}
	}
	
	clearRecipients( fundingSourceNameFieldName);
	clearRecipients( fundingSourceNameFieldName + ".display.div");
	changeObjectVisibility( fundingSourceNameFieldName + ".error.div", "none" );
	clearRecipients( fundingSourceTitleFieldName, "" );
	if (fundingSourceTypeCode != '' && fundingSourceTypeCode != 'select' && fundingSourceNumber != '') {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					if ( data.fundingSourceName != null && data.fundingSourceName != '' ) {
						setRecipientValue( fundingSourceNameFieldName, data.fundingSourceName );
						setRecipientValue( fundingSourceNameFieldName + ".display.div", data.fundingSourceName );
					} else {
						if ( data.fundingSourceLookupable ){
							changeObjectVisibility( fundingSourceNameFieldName + ".error.div", "block" );
						} else {
							changeObjectVisibility( fundingSourceNameFieldName + ".error.div", "none" );
						}
					}
					if ( data.fundingSourceTitle != null && data.fundingSourceTitle != '' ) {
						setRecipientValue( fundingSourceTitleFieldName, data.fundingSourceTitle );
					}
				} else {
					changeObjectVisibility( fundingSourceNameFieldName + ".error.div", "block" );
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				changeObjectVisibility( fundingSourceNameFieldName + ".error.div", "block" );
			}
		};
		if(protocolModule == 'iacuc') {
			//alert("calling iacuc service now");
			IacucProtocolFundingSourceService.updateProtocolFundingSource(fundingSourceTypeCode+":"+docFormKey, fundingSourceNumber, fundingSourceName, dwrReply);
		}
		else {
			ProtocolFundingSourceService.updateProtocolFundingSource(fundingSourceTypeCode+":"+docFormKey, fundingSourceNumber, fundingSourceName, dwrReply);
		}
	}
}
 

function clearCommitteeScheduleRecurrenceData() {
	var list = document.getElementsByName("committeeHelper.scheduleData.recurrenceType");
	for(var i= 0; i < list.length; i++) {
		var element = list[i];
		if(element.value == 'NEVER' && element.checked) {
			document.getElementById("committeeHelper.scheduleData.dailySchedule.day").value = 1;
			document.getElementById("committeeHelper.scheduleData.weeklySchedule.week").value = 1;
			document.getElementById("committeeHelper.scheduleData.monthlySchedule.day").value = 1;
			document.getElementById("committeeHelper.scheduleData.monthlySchedule.option1Month").value = 1;
			document.getElementById("committeeHelper.scheduleData.monthlySchedule.option2Month").value = 1;
			document.getElementById("committeeHelper.scheduleData.yearlySchedule.day").value = 1;
			document.getElementById("committeeHelper.scheduleData.yearlySchedule.option1Year").value = 1;
			document.getElementById("committeeHelper.scheduleData.yearlySchedule.option2Year").value = 1;
		}
		if(element.value == 'DAILY' && element.checked) {
			document.getElementById("committeeHelper.scheduleData.weeklySchedule.week").value = 1;
			document.getElementById("committeeHelper.scheduleData.monthlySchedule.day").value = 1;
			document.getElementById("committeeHelper.scheduleData.monthlySchedule.option1Month").value = 1;
			document.getElementById("committeeHelper.scheduleData.monthlySchedule.option2Month").value = 1;
			document.getElementById("committeeHelper.scheduleData.yearlySchedule.day").value = 1;
			document.getElementById("committeeHelper.scheduleData.yearlySchedule.option1Year").value = 1;
			document.getElementById("committeeHelper.scheduleData.yearlySchedule.option2Year").value = 1;
			var dailylist = document.getElementsByName("committeeHelper.scheduleData.dailySchedule.dayOption");
			for(var j=0; j < dailylist.length; j++) {
				var dailyelement = dailylist[j];
				if(dailyelement.value == 'WEEKDAY' && dailyelement.checked) {
					document.getElementById("committeeHelper.scheduleData.dailySchedule.day").value = 1;
				}
			}
		}
		if(element.value == 'WEEKLY' && element.checked) {
			document.getElementById("committeeHelper.scheduleData.dailySchedule.day").value = 1;
			document.getElementById("committeeHelper.scheduleData.monthlySchedule.day").value = 1;
			document.getElementById("committeeHelper.scheduleData.monthlySchedule.option1Month").value = 1;
			document.getElementById("committeeHelper.scheduleData.monthlySchedule.option2Month").value = 1;
			document.getElementById("committeeHelper.scheduleData.yearlySchedule.day").value = 1;
			document.getElementById("committeeHelper.scheduleData.yearlySchedule.option1Year").value = 1;
			document.getElementById("committeeHelper.scheduleData.yearlySchedule.option2Year").value = 1;
		}
		if(element.value == 'MONTHLY' && element.checked) {
			document.getElementById("committeeHelper.scheduleData.dailySchedule.day").value = 1;
			document.getElementById("committeeHelper.scheduleData.weeklySchedule.week").value = 1;
			document.getElementById("committeeHelper.scheduleData.yearlySchedule.day").value = 1;
			document.getElementById("committeeHelper.scheduleData.yearlySchedule.option1Year").value = 1;
			document.getElementById("committeeHelper.scheduleData.yearlySchedule.option2Year").value = 1;
			var monthlylist = document.getElementsByName("committeeHelper.scheduleData.monthlySchedule.monthOption");
			for(var j=0; j < monthlylist.length; j++) {
				var monthlyelement = monthlylist[j];
				if(monthlyelement.value == 'XDAYANDXMONTH' && monthlyelement.checked) {
					document.getElementById("committeeHelper.scheduleData.monthlySchedule.option2Month").value = 1;
				}
				if(monthlyelement.value == 'XDAYOFWEEKANDXMONTH' && monthlyelement.checked) {
					document.getElementById("committeeHelper.scheduleData.monthlySchedule.day").value = 1;
					document.getElementById("committeeHelper.scheduleData.monthlySchedule.option1Month").value = 1;
				}				
			}
		}
		if(element.value == 'YEARLY' && element.checked) {
			document.getElementById("committeeHelper.scheduleData.dailySchedule.day").value = 1;
			document.getElementById("committeeHelper.scheduleData.weeklySchedule.week").value = 1;
			document.getElementById("committeeHelper.scheduleData.monthlySchedule.day").value = 1;
			document.getElementById("committeeHelper.scheduleData.monthlySchedule.option1Month").value = 1;
			document.getElementById("committeeHelper.scheduleData.monthlySchedule.option2Month").value = 1;
			var yearlylist = document.getElementsByName("committeeHelper.scheduleData.yearlySchedule.yearOption");
			for(var j=0; j < yearlylist.length; j++) {
				var yearlyelement = yearlylist[j];
				if(yearlyelement.value == 'XDAY' && yearlyelement.checked) {
					document.getElementById("committeeHelper.scheduleData.yearlySchedule.option2Year").value = 1;
				}
				if(yearlyelement.value == 'CMPLX' && yearlyelement.checked) {
					document.getElementById("committeeHelper.scheduleData.yearlySchedule.day").value = 1;
					document.getElementById("committeeHelper.scheduleData.yearlySchedule.option1Year").value = 1;
				}
			}
		}		
	}
}

function showTable(id) {
    if (document.getElementById) {
        document.getElementById("calendar_never_table").style.display = "none";
        document.getElementById("calendar_daily_table").style.display = "none";
        document.getElementById("calendar_weekly_table").style.display = "none";
        document.getElementById("calendar_monthly_table").style.display = "none";
        document.getElementById("calendar_yearly_table").style.display = "none";
        document.getElementById(id).style.display = "block";
    } else {
        document.all["calendar_never_table"].style.display = "none";
        document.all["calendar_daily_table"].style.display = "none";
        document.all["calendar_weekly_table"].style.display = "none";
        document.all["calendar_monthly_table"].style.display = "none";
        document.all["calendar_yearly_table"].style.display = "none";
        document.all[id].style.display = "block";
    }
}

/*
 * Load the Sponsor Name field based on the Sponsor Code passed in.
 */
function loadSponsorName(sponsorCodeFieldName, sponsorNameFieldName ) {
	var sponsorCode = dwr.util.getValue( sponsorCodeFieldName );

	if (sponsorCode=='') {
		clearRecipients( sponsorNameFieldName, "" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					if ( sponsorNameFieldName != null && sponsorNameFieldName != "" ) {
						setRecipientValue( sponsorNameFieldName, data );
					}
				} else {
					if ( sponsorNameFieldName != null && sponsorNameFieldName != "" ) {
						setRecipientValue(  sponsorNameFieldName, wrapError( "not found" ), true );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				setRecipientValue( sponsorNameFieldName, wrapError( "not found" ), true );
			}
		};
		SponsorService.getSponsorName(sponsorCode,dwrReply);
	}
}
 /*
  * This function is used to populate a dropdown based on the value selected in
  * another dropdown. The function performs an ajax call to a method in jqueryAjaxAction
  * that should return the values in the following json format
  * [ { 'key' :'400', 'value' : 'Disclosed Interests Unmanageable'} , ] 
  */
function populateSelect(methodToCall, firstSelectId, secondSelectId) {
	var valueSelected = $j(jq_escape(firstSelectId)).attr("value");
	var valueSelectedSec = $j(jq_escape(secondSelectId)).attr("value");
	
	var secondSelectIdEscaped = jq_escape(secondSelectId);
	callAjaxByPath('jqueryAjax.do', methodToCall, valueSelected,
			function(data) {
				valuesForSecondSelect = eval('(' + $j(data).find('#ret_value').html() + ')');
				$j(secondSelectIdEscaped).html('');
				if (valuesForSecondSelect == undefined || valuesForSecondSelect.length == 0) {
					$j(secondSelectIdEscaped).attr('disabled', 'disabled');
				} else {
					var options = '';
					for (var i = 0; i < valuesForSecondSelect.length; i++) {
						var item = valuesForSecondSelect[i];
						options += "<option value='" + item.key + "'>" + item.value + "</option>";
					}
					
					$j(secondSelectIdEscaped).html(options);
					$j(secondSelectIdEscaped).removeAttr('disabled');
					$j(secondSelectIdEscaped).val(valueSelectedSec);
				}
			},
			function(error) {
				alert("error is" + error);
			}
	);
}


/*
 * Load Start and End Dates based on the Fiscal Year
 */ 
function loadStartAndEndDates(fiscalYear,startDate,endDate){
	var fiscalYearValue = dwr.util.getValue( fiscalYear );

	if (fiscalYearValue=='') {
		clearRecipients( startDate, "" );
		clearRecipients( endDate, "" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {					
					if ( fiscalYear != null && fiscalYear != "" ) {						
						setRecipientValue( startDate, data[0] );
						setRecipientValue( endDate, data[1] );
					}
				} else {
					if ( fiscalYear != null && fiscalYear != "" ) {
						setRecipientValue(  startDate, wrapError( "not found" ), true );
						setRecipientValue(  endDate, wrapError( "not found" ), true );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				setRecipientValue( startDate, wrapError( "not found" ), true );
				setRecipientValue( endDate, wrapError( "not found" ), true );
			}
		};
		AwardFandaRateService.getStartAndEndDatesBasedOnFiscalYear(fiscalYearValue,dwrReply);
	}
}

/*
 * Load the Unit Name field based on the Unit Number passed in.
 */
function loadUnitNameTo(unitNumberFieldName, unitNameFieldName) {
	var unitNumber = dwr.util.getValue( unitNumberFieldName );
	if (unitNumber=='') {
		setRecipientValue( unitNameFieldName, "(select)" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					if ( unitNameFieldName != null && unitNameFieldName != "" ) {
						setRecipientValue( unitNameFieldName, data );
					}
				} else {
					if ( unitNameFieldName != null && unitNameFieldName != "" ) {
						setRecipientValue(  unitNameFieldName, wrapError( "not found" ), true );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				setRecipientValue( unitNameFieldName, wrapError( "not found" ), true );
			}
		};
		UnitService.getUnitName(unitNumber,dwrReply);
	}
}

/*
 * Load the JobCode Title field based on the Job Code passed in.
 */
function loadJobCodeTitle(jobCodeFieldName, jobCodeTitleFieldName ) {
	var jobCode = dwr.util.getValue( jobCodeFieldName );

	if (jobCode=='') {
		clearRecipients( jobCodeTitleFieldName, "" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					if ( jobCodeTitleFieldName != null && jobCodeTitleFieldName != "" ) {
						setRecipientValue( jobCodeTitleFieldName, data );
					}
				} else {
					if ( jobCodeTitleFieldName != null && jobCodeTitleFieldName != "" ) {
						setRecipientValue(  jobCodeTitleFieldName, wrapError( "not found" ), true );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				setRecipientValue( jobCodeTitleFieldName, wrapError( "not found" ), true );
			}
		};
		JobCodeService.findJobCodeTitle(jobCode,dwrReply);
	}
}

function extractUrlBase(){
  url=window.location.href;
  pathname=window.location.pathname;
  idx1=url.indexOf(pathname);
  idx2=url.indexOf("/",idx1+1);
  extractUrl=url.substr(0,idx2);
  return extractUrl; 
}
function openNewWindow(action,methodToCall,lineNumber,docFormKey, sessionDocument){
  var documentWebScope
  if (sessionDocument == "true") {
      documentWebScope="session"
  }

  window.open(extractUrlBase()+"/"+action+".do?methodToCall="+methodToCall+"&line="+lineNumber+"&docFormKey="+docFormKey+"&documentWebScope="+documentWebScope);
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

/**
 * Display the Proposal's set of Roles and their Rights.
 * The roles are Aggregator, Budget Creator, etc.
 */
var permissionsRoleRightsWindow = null;

function permissionsRoleRightsPop(name, docFormKey, sessionDocument) {

	var documentWebScope = "";
	if (sessionDocument == true) {
		documentWebScope = "session";
	}

	if (permissionsRoleRightsWindow != null) {
	    permissionsRoleRightsWindow.close();
	} 

    permissionsRoleRightsWindow = window.open(extractUrlBase() +
    	                               "/" + name + "Permissions.do?methodToCall=getPermissionsRoleRights" +
    	                               "&docFormKey=" + docFormKey + 
    	                               "&documentWebScope=" + documentWebScope, 
    	                               "permissionsRoleRights", 
    	                               "width=800, height=750, scrollbars=yes, resizable=yes");   
}

/**
 * Display the Edit Roles popup window.  This window allows users
 * to change the roles for a user within a proposal.
 */
var permissionsEditRolesWindow;

function permissionsEditRolesPop(name, lineNumber, docFormKey, sessionDocument) {

    var documentWebScope = "";
    if (sessionDocument == "true") {
        documentWebScope="session"
    }
    
	if (permissionsEditRolesWindow != null) {
	    permissionsEditRolesWindow.close();
	} 

    protocolEditRolesWindow = window.open(extractUrlBase() +
    	                               "/" + name + "Permissions.do?methodToCall=editRoles" +
    	                               "&line=" + lineNumber +
    	                               "&docFormKey=" + docFormKey + 
    	                               "&documentWebScope=" + documentWebScope, 
    	                               "permissionsEditRoles", 
    	                               "width=800, height=350, scrollbars=yes, resizable=yes");
}

/**
 * Utility function for trimming a string.
 */
String.prototype.trim = function() {
  return this.replace(/^\s+|\s+$/g, "");
}

/**
 * The User Class.  A user has a name, its line number
 * within the user table, and a set of roles.
 */
function User(name, lineNumber) {
    this._name = name;
    this._lineNumber = lineNumber;
    this._roles = new Array();
}

User.prototype._name;
User.prototype._lineNumber;
User.prototype._roles;

User.prototype.getName = function() {
    return this._name;
}

User.prototype.getLineNumber = function() {
    return this._lineNumber;
}

User.prototype.getRoles = function() {
	return this._roles;
}

User.prototype.addRole = function(role) {
    this._roles[this._roles.length] = role;
}

User.prototype.clearRoles = function() {
    this._roles.length = 0;
}

User.prototype.hasRole = function(role) {
    for (var i = 0; i < this._roles.length; i++) {
        if (role == this._roles[i]) {
            return true;
        }
    }
    return false;
}

/**
 * The PermissionsRoleState Class.  Stores the states of the roles as 
 * selected by the Edit Roles web page.  We store the name of the
 * role and it's state (true or false).  A value of true indicates
 * that the role was selected by the user; otherwise false is
 * unselected.
 */
function PermissionsRoleState(name, displayName, state) {
    this._name = name;
    this._displayName = displayName;
    this._state = state;
}

PermissionsRoleState.prototype._name;
PermissionsRoleState.prototype._displayName;
PermissionsRoleState.prototype._state;

PermissionsRoleState.prototype.getName = function() {
    return this._name;
}

PermissionsRoleState.prototype.getDisplayName = function() {
    return this._displayName;
}

PermissionsRoleState.prototype.getState = function() {
    return this._state;
}


/**
 * When the Edit Roles popup window is closed, this function is invoked in
 * order to update the parent window and to close the popup window.  We need
 * to change the roles for the user that was modified.  We also need to 
 * update the listing of assigned roles.
 */
function updateEditRoles(lineNumber, roleStates, unassignedRoleDisplayName) {

	var users = getUsers();
	updateUserRoles(users[lineNumber], roleStates);
    displayUserRoles(users[lineNumber]);
    
    for (var i = 0; i < roleStates.length; i++) {
        if (roleStates[i].getDisplayName() != unassignedRoleDisplayName) {
            displayAssignedRoles(users, roleStates[i].getName(), roleStates[i].getDisplayName());
        }
    }
    
    self.close();
}

/**
 * Display the roles for a user.  This visibly changes the roles
 * for a user in the User Permissions panel.
 */
function displayUserRoles(user) {
	var html = "";
    var roles = user.getRoles();
    for (var i = 0; i < roles.length; i++) {
        if (i != 0) html += "<BR>";
        html += "<NOBR>" + roles[i] + "</NOBR>";
    }
     
    var roleElement = opener.document.getElementById("role" + user.getLineNumber());
    roleElement.innerHTML = html;
}

/**
 * Displays the names of users for a specific role.
 */
function displayAssignedRoles(users, elementId, role) {
    
	var usernames = new Array();
	for (var i = 0; i < users.length; i++) {
		if (users[i].hasRole(role)) {
		    usernames[usernames.length] = users[i].getName();
		}
	}
	var node = opener.document.getElementById(elementId);
	node.innerHTML = usernames.join("; ");
}

/**
 * Changes the roles for a user.  The current set of roles is cleared and
 * a new set of roles is added.
 */
function updateUserRoles(user, roleStates) {
    user.clearRoles();
    for (var i = 0; i < roleStates.length; i++) {
        var state = roleStates[i].getState();
        if (state.toLowerCase() == 'true') {
            user.addRole(roleStates[i].getDisplayName());
        }
    }
 }
    
/**
 * Get the users in the Permission's Users panel.  Extract the information
 * from the HTML table.  We will store each user's name, line number in
 * the table, and the user's set of roles.
 */
function getUsers() {
    var users = new Array();
    var tableElement = opener.document.getElementById("user-roles");
    var numRows = tableElement.tBodies[0].rows.length;
    for (var i = 2; i < numRows; i++) {
    	var rowElement = tableElement.tBodies[0].rows[i];
    	var nameCell = rowElement.cells[2];
    	var name = nameCell.childNodes[0].innerHTML;
    	var user = new User(name, i - 2);
    	
    	var roleCell = rowElement.cells[5];
    	var numRoles = roleCell.childNodes.length;
    	for (var j = 0; j < numRoles; j++) {
    		var node = roleCell.childNodes[j];
    		if (node.nodeName.toUpperCase() == "NOBR") {
    		    var roleName = node.innerHTML.trim();
    		    user.addRole(roleName);
    		}
    	}
    	users[users.length] = user;
    }
    return users;
}

/*
 * Load the person's full name based on the person's username.
 */
function loadPersonName(usernameFieldName, fullnameElementId, 
						unitNumberElementId, unitNameElementId) {
    if (document.getElementById(fullnameElementId) != null) {
		var username = dwr.util.getValue( usernameFieldName );
		var fullNameElement = document.getElementById(fullnameElementId);
		var unitNumberElement= document.getElementById(unitNumberElementId);
		var unitNameElement= document.getElementById(unitNameElementId);
		
		if (username == '') {
			fullNameElement.innerHTML = "&nbsp;";
		} else {
			var dwrReply = {
				callback:function(data) {
					if ( data != null ) {
						fullNameElement.innerHTML = data.fullName;
						unitNumberElement.innerHTML= data.unit['unitNumber'];
						unitNameElement.innerHTML= data.unit['unitName'];
						
					} else {
						fullNameElement.innerHTML = wrapError( "not found" );
						unitNameElement.innerHTML= wrapError("not found");
						unitNumberElement.innerHTML= wrapError("not found");
					}
				},
				errorHandler:function( errorMessage ) {
					window.status = errorMessage;
					fullNameElement.innerHTML = wrapError( "not found" );
					unitNameElement.innerHTML= wrapError("not found");
					unitNumberElement.innerHTML= wrapError("not found");
				}
			};
			KraPersonService.getKcPersonByUserName(username, dwrReply);
		}
	}
}


/*
 * Load the contact person's full name based on the person's username.
 */
function loadContactPersonName(usernameFieldName, fullnameElementId, 
						unitNumberElementId, phoneNumberElementId, emailElementId , personIdElementId) {
    if (document.getElementById(fullnameElementId) != null) {
		var username = dwr.util.getValue( usernameFieldName );
		var fullNameElement = document.getElementById(fullnameElementId);
		var unitNumberElement= document.getElementById(unitNumberElementId);
		var phoneNumberElement = document.getElementById(phoneNumberElementId);
		var emailElement = document.getElementById(emailElementId);
		var personIdElement = document.getElementById(personIdElementId)

		if (username == '') {
			if (fullNameElement != null) fullNameElement.innerHTML = "&nbsp;";
			if (personIdElement != null) personIdElement.value = "";
		} else {
			var dwrReply = {
				callback:function(data) {
					if ( data != null ) {
						if (fullNameElement != null) fullNameElement.innerHTML = data.fullName;
						if (phoneNumberElement != null) phoneNumberElement.innerHTML= data.phoneNumber;
						if (emailElement != null) emailElement.innerHTML= data.emailAddress;
						if (personIdElement != null) personIdElement.value= data.personId;
						if (unitNumberElement != null) unitNumberElement.innerHTML= data.unit['unitNumber']
					} else {
						if (personIdElement != null) personIdElement.value = "";
						if (fullNameElement != null) fullNameElement.innerHTML = wrapError( "not found" );
						if (phoneNumberElement != null) phoneNumberElement.innerHTML= wrapError( "not found" );
						if (emailElement != null) emailElement.innerHTML= wrapError( "not found" );
						if (unitNumberElement != null) unitNumberElement.innerHTML= wrapError( "not found" );
					}
				},
				errorHandler:function( errorMessage ) {
					window.status = errorMessage;
					if (personIdElement != null) personIdElement.value = null;
					if (fullNameElement != null) fullNameElement.innerHTML = wrapError( "not found!" );
					if (phoneNumberElement != null) phoneNumberElement.innerHTML= wrapError( "not found!" );
					if (emailElement != null) emailElement.innerHTML= wrapError( "not found!" );
					if (unitNumberElement != null) unitNumberElement.innerHTML= wrapError( "not found" );
				}
			};
			KraPersonService.getKcPersonByUserName(username, dwrReply);
		}
	}
}

	/*
	 * Load the phone number and email address from rolodex info from rolodex id.
	 */
	function loadRolodexInfo(rolodexFieldName, fullnameElementId, 
							 phoneNumberElementId, emailElementId, rolodexElementId) {
	    if (document.getElementById(fullnameElementId) != null) {			
	    var rolodexId = dwr.util.getValue( rolodexFieldName );
		var fullNameElement = document.getElementById(fullnameElementId);
		var phoneNumberElement = document.getElementById(phoneNumberElementId);
		var emailElement = document.getElementById(emailElementId);
		var rolodexElement = document.getElementById(rolodexElementId);

		if (rolodexId == '') {				
			fullNameElement.innerHTML = "&nbsp;";
		} else {
			var dwrReply = {
				callback:function(data) {
					if ( data != null ) {
						if(data.fullName == null)
							fullNameElement.innerHTML = data.organization;
						else
							fullNameElement.innerHTML = data.fullName;
						phoneNumberElement.innerHTML= data.phoneNumber;
						emailElement.innerHTML= data.emailAddress;
						rolodexElement.value= data.rolodexId;
					} else {
						fullNameElement.innerHTML = wrapError( "not found" );
						phoneNumberElement.innerHTML= wrapError( "not found" );
						emailElement.innerHTML= wrapError( "not found" );
					}
				},
				errorHandler:function( errorMessage ) {
					window.status = errorMessage;
					fullNameElement.innerHTML = wrapError( "not found" );
					phoneNumberElement.innerHTML= wrapError( "not found" );
					emailElement.innerHTML= wrapError( "not found" );
				}
			};
			RolodexService.getRolodex(rolodexId, dwrReply);
		}
	}
}
	
	
	function loadRolodexInfo2(rolodexFieldName, fullnameElementId, 
				unitNumberElementId, phoneNumberElementId, emailElementId, rolodexElementId) {
		if (document.getElementById(fullnameElementId) != null) {			
		var rolodexId = dwr.util.getValue( rolodexFieldName );
		var fullNameElement = document.getElementById(fullnameElementId);
		var unitNumberElement= document.getElementById(unitNumberElementId);
		var phoneNumberElement = document.getElementById(phoneNumberElementId);
		var emailElement = document.getElementById(emailElementId);
		var rolodexElement = document.getElementById(rolodexElementId);

		if (rolodexId == '') {				
			fullNameElement.innerHTML = "&nbsp;";
		} else {
			var dwrReply = {
					callback:function(data) {
						if ( data != null ) {
							fullNameElement.innerHTML = data.fullName;
							phoneNumberElement.innerHTML= data.phoneNumber;
							emailElement.innerHTML= data.emailAddress;
							rolodexElement.value= data.rolodexId;
							unitNumberElement.innerHTML= data.unit['unitNumber']
						} else {
							fullNameElement.innerHTML = wrapError( "not found" );
							phoneNumberElement.innerHTML= wrapError( "not found" );
							emailElement.innerHTML= wrapError( "not found" );
							unitNumberElement.innerHTML= wrapError( "not found" );
						}
					},
					errorHandler:function( errorMessage ) {
						window.status = errorMessage;
						fullNameElement.innerHTML = wrapError( "not found" );
						phoneNumberElement.innerHTML= wrapError( "not found" );
						emailElement.innerHTML= wrapError( "not found" );
						unitNumberElement.innerHTML= wrapError( "not found" );
					}
			};
			RolodexService.getRolodex(rolodexId, dwrReply);
		}
	}
}

	
	/*
	 * Load the fullname from rolodex info from rolodex id.
	 */
	function loadRolodexPersonName(rolodexFieldName, fullnameElementId,rolodexIdElementId) {
		if (document.getElementById(fullnameElementId) != null) {			
		var rolodexId = dwr.util.getValue( rolodexFieldName );
		var fullNameElement = document.getElementById(fullnameElementId);
		var rolodexIdElement = document.getElementById(rolodexIdElementId);		
		rolodexIdElement.value = null;
		if (rolodexId == '') {
		fullNameElement.innerHTML = "&nbsp;";
		} else {
			var dwrReply = {
					callback:function(data) {
						if ( data != null ) {							
						    if ( data.fullName != null ) {
							    fullNameElement.innerHTML = "&nbsp;&nbsp;" + data.fullName;
							} else if (data.organization == undefined) {
							    fullNameElement.innerHTML = wrapError( "not found" );
							} else {
							    fullNameElement.innerHTML = "&nbsp;&nbsp;" + data.organization;
							}
							rolodexIdElement.value = data.rolodexId;
							
						} else {							
							fullNameElement.innerHTML = wrapError( "not found" );
							rolodexIdElement.innerHTML = wrapError( "not found" );							
						}
					},
					errorHandler:function( errorMessage ) {		
						window.status = errorMessage;
						fullNameElement.innerHTML = wrapError( "not found" );
						rolodexIdElement.innerHTML = wrapError( "not found" );
					}
			};
			RolodexService.getRolodex(rolodexId, dwrReply);
		}
	  }
    }

/*
 * functions for award template report term 
 */	
 var reportTypeName;
 function updateReportType( reportClassField, callbackFunction ) {

    if (reportClassField.name.lastIndexOf("reportClassCode") == 0) {
    	reportTypeName = "reportCode" ;
    } else if (reportClassField.name.lastIndexOf("reportClassCode") > 0) {
    	reportTypeName =  findElPrefix( reportClassField.name ) + ".reportCode" ;
    }
    var reportClass = reportClassField.value;
    
  //  alert ("reportType is " + reportTypeName + ". ReportClass is " + reportClass );
 
	if ( reportClass != "") {
		var dwrReply = {
			callback:callbackFunction,
			errorHandler:function( errorMessage ) { 
				window.status = errorMessage;
			}};;
		AwardTemplateReportTermService.getReportTypeForAjaxCall(reportClass, dwrReply );
	} else {
	    kualiElements[reportType].options.length=1;
	}
}
 function updateReportType_Callback( data ) {
	// alert("in callback function with data = " + data);
	kualiElements[reportTypeName].options.length=0; //reset 
	var option_array=data.split(",");
	var optionNum=0;
	var nameLabelPair;
	while (optionNum < option_array.length)
	 {
		  if (optionNum == 0) {
		        kualiElements[reportTypeName].options[0]=new Option("select","", true, true);
		  } else {
		        nameLabelPair = option_array[optionNum].split(";");
		        kualiElements[reportTypeName].options[optionNum]=new Option(nameLabelPair[1], nameLabelPair[0]);
		  }
		  optionNum+=1;
	 }
}

 var frequencyName;
 function updateFrequency(reportTypeField, callbackFunction ) {

	var reportClassName;
    if (reportTypeField.name.lastIndexOf("reportCode") == 0) {
    	frequencyName = "frequencyCode" ;
    	reportClassName="reportClassCode";
    } else if (reportTypeField.name.lastIndexOf("reportCode") > 0) {
    	frequencyName =  findElPrefix( reportTypeField.name ) + ".frequencyCode" ;
    	reportClassName = findElPrefix( reportTypeField.name ) + ".reportClassCode" ;
    }
  //  alert("frequencyName is " + frequencyName );
    var reportCode = reportTypeField.value;
    var reportClass = kualiElements[reportClassName].value;
   // alert ("ReportCode is " + reportCode );
    //alert ("ReportClass is " + reportClass);
 
	if ( reportCode != "") {
		var dwrReply = {
			callback:callbackFunction,
			errorHandler:function( errorMessage ) { 
				window.status = errorMessage;
			}};
		AwardTemplateReportTermService.getFrequencyForAjaxCall(reportCode, reportClass, dwrReply );
	} else {
	    kualiElements[reportType].options.length=1;
	}
}
 function updateFrequency_Callback( data ) {
//	alert("in callback function with data = " + data);
	kualiElements[frequencyName].options.length=0; //reset 
	var option_array=data.split(",");
	var optionNum=0;
	var nameLabelPair;
	while (optionNum < option_array.length)
	 {
		  if (optionNum == 0) {
		        kualiElements[frequencyName].options[0]=new Option("select","", true, true);
		  } else {
		        nameLabelPair = option_array[optionNum].split(";");
		        kualiElements[frequencyName].options[optionNum]=new Option(nameLabelPair[1], nameLabelPair[0]);
		  }
		  optionNum+=1;
	 }
}

 var frequencyBaseName;
 function updateFrequencyBase(frequencyField, callbackFunction ) {
    if (frequencyField.name.lastIndexOf("frequencyCode") == 0) {
    	frequencyBaseName = "frequencyBaseCode" ;
    } else if (frequencyField.name.lastIndexOf("frequencyCode") > 0) {
    	frequencyBaseName =  findElPrefix( frequencyField.name ) + ".frequencyBaseCode" ;
    }
 //   alert("frequencyBaseName is " + frequencyBaseName );
    var frequency = frequencyField.value;
	if ( frequency != "") {
		var dwrReply = {
			callback:callbackFunction,
			errorHandler:function( errorMessage ) { 
				window.status = errorMessage;
			}};
		AwardTemplateReportTermService.getFrequencyBaseForAjaxCall(frequency, dwrReply );
	} else {
	    kualiElements[reportType].options.length=1;
	}
}
 
 function updateFrequencyBase_Callback( data ) {
//	alert("in callback function with data = " + data);
	kualiElements[frequencyBaseName].options.length=0; //reset 
	var option_array=data.split(",");
	var optionNum=0;
	var nameLabelPair;
	while (optionNum < option_array.length)
	 {
		  if (optionNum == 0) {
		        kualiElements[frequencyBaseName].options[0]=new Option("select","", true, true);
		  } else {
		        nameLabelPair = option_array[optionNum].split(";");
		        kualiElements[frequencyBaseName].options[optionNum]=new Option(nameLabelPair[1], nameLabelPair[0]);
		  }
		  optionNum+=1;
	 }
} 
 
 
/*
 * functions for custom attribute maintenance 
 */	
  
  var lookupReturnName ;
 function updateLookupReturn( lookupClassField, callbackFunction ) {
   
    if (lookupClassField.name.lastIndexOf("lookupClass") == 0) {
    	lookupReturnName = "lookupReturn" ;
    } else if (lookupClassField.name.lastIndexOf("lookupClass") > 0) {
	    lookupReturnName =  findElPrefix( lookupClassField.name ) + ".lookupReturn" ;
    }

    //alert ("in update" +lookupClassField+"-"+lookupClassField.name+"-"+lookupReturn+lookupClassField.value);
	//var lookupClass = getElementValue( lookupClassField.name ); // ie7 get nothing out of this
    var lookupClass = lookupClassField.value;
    //alert ('update ' +lookupClass);
	if ( lookupClass != "") {
		var dwrReply = {
			callback:callbackFunction,
			errorHandler:function( errorMessage ) { 
				window.status = errorMessage;
			}
		};

		// argvaluelookup is handled in customattributeservice
		    CustomAttributeService.getLookupReturnsForAjaxCall( lookupClass, dwrReply );
		    document.getElementById("document.newMaintainableObject.defaultValue").disabled = true;
			document.getElementById("document.newMaintainableObject.defaultValue").value = "";
	} else {
	    kualiElements[lookupReturnName].options.length=1;
	}
}

function updateLookupReturn_Callback( data ) {
            //alert ("enter callback" +lookupReturnName);
			
			kualiElements[lookupReturnName].options.length=0; //reset 
			var option_array=data.split(",");
			var optionNum=0;
			var nameLabelPair;
			while (optionNum < option_array.length)
			 {
				  if (optionNum == 0) {
				        //alert(optionNum+option_array[0])
				        kualiElements[lookupReturnName].options[0]=new Option("select:","", true, true);
				  } else {
				        //alert("else"+optionNum+option_array[optionNum])
				        nameLabelPair = option_array[optionNum].split(";");
				        kualiElements[lookupReturnName].options[optionNum]=new Option(nameLabelPair[1], nameLabelPair[0]);
				  }
				  optionNum+=1;
			 }

}

function getURLParameters(paramName) 
{
        var sURL = top.window.location.href.toString();  
    if (sURL.indexOf("?") > 0)
    {
       var arrParams = sURL.split("?");         
       var arrURLParams = arrParams[1].split("&");      
       var arrParamNames = new Array(arrURLParams.length);
       var arrParamValues = new Array(arrURLParams.length);     
       var i = 0;
       for (i=0;i<arrURLParams.length;i++)
       {
        var sParam =  arrURLParams[i].split("=");
        arrParamNames[i] = sParam[0];
        if (sParam[1] != "")
            arrParamValues[i] = unescape(sParam[1]);
        else
            arrParamValues[i] = "No Value";
       }

       for (i=0;i<arrURLParams.length;i++)
       {
                if(arrParamNames[i] == paramName){
                return arrParamValues[i];
             }
       }
       return "No Parameters Found";
    }

}

function lookupUrl()
{
  return getURLParameters("channelUrl");
}

function refreshAddressBookLookup() {
		var sponsorFlagValue = "";
		var issponsorAddressValue = "";
		jQuery("input[id='document.newMaintainableObject.organization']").attr("readonly",true);
		jQuery("input[id='document.newMaintainableObject.organization']").css("background-color","#EFEFE9");
		if(document.getElementById('document.newMaintainableObject.isSponsorAddress')) {
			sponsorFlagValue = document.getElementById('document.newMaintainableObject.isSponsorAddress').value;	
			if(sponsorFlagValue == 'Y') {
				jQuery("input[id='document.newMaintainableObject.organization']").parent().children('input:image').remove();
				jQuery("input[id='document.newMaintainableObject.organization']").parent().append("<input type='image' tabindex='1000000' name='methodToCall.performLookup.(!!org.kuali.coeus.common.framework.sponsor.Sponsor!!).(((sponsorCode:document.newMaintainableObject.sponsorCode,))).((`document.newMaintainableObject.sponsorCode:sponsorCode,`)).((&lt;&gt;)).(([])).((**)).((^^)).((&amp;&amp;)).((//)).((~~)).(::::;"+lookupUrl()+";::::).anchor4' src='../kr/static/images/searchicon.gif' border='0' class='tinybutton' valign='middle' alt='Search Organization' title='Search Organization'>");
			} else if(sponsorFlagValue == 'N') {
				jQuery("input[id='document.newMaintainableObject.organization']").parent().children('input:image').remove();
				jQuery("input[id='document.newMaintainableObject.organization']").parent().append("<input type='image' tabindex='1000000' name='methodToCall.performLookup.(!!org.kuali.coeus.common.framework.org.Organization!!).(((organizationId:document.newMaintainableObject.organization,))).((`document.newMaintainableObject.organization:organizationId,`)).((&lt;&gt;)).(([])).((**)).((^^)).((&amp;&amp;)).((//)).((~~)).(::::;"+lookupUrl()+";::::).anchor4' src='../kr/static/images/searchicon.gif' border='0' class='tinybutton' valign='middle' alt='Search Organization' title='Search Organization'>");
			}
		}

		if(document.getElementById('isSponsorAddress')) {
			issponsorAddressValue = document.getElementById('isSponsorAddress').value;
			if(issponsorAddressValue == 'Y') {
				jQuery("input[id='organization']").parent().children('input:image').remove();
				jQuery("input[id='organization']").parent().append("<input type='image' tabindex='1000000' name='methodToCall.performLookup.(!!org.kuali.coeus.common.framework.sponsor.Sponsor!!).(((sponsorName:organization))).((`organization:sponsorName`)).((&lt;&gt;)).(([])).((**)).((^^)).((&amp;&amp;)).((//)).((~~)).(::::;"+lookupUrl()+";::::).anchor' src='../kr/static/images/searchicon.gif' border='0' class='tinybutton' valign='middle' alt='Search Organization' title='Search Organization'>");
			} else if(issponsorAddressValue == 'N') {
				jQuery("input[id='organization']").parent().children('input:image').remove();
				jQuery("input[id='organization']").parent().append("<input type='image' tabindex='1000000' name='methodToCall.performLookup.(!!org.kuali.coeus.common.framework.org.Organization!!).(((organizationName:organization))).((`organization:organizationName`)).((&lt;&gt;)).(([])).((**)).((^^)).((&amp;&amp;)).((//)).((~~)).(::::;"+lookupUrl()+";::::).anchor' src='../kr/static/images/searchicon.gif' border='0' class='tinybutton' valign='middle' alt='Search Organization' title='Search Organization'>");
			}
		}
			
	}
	 function updateOrganizationLookupReturn(lookupClassField) {
		 if(lookupClassField.value == "N") {
			 lookupOrganization();
		 } else if(lookupClassField.value == "Y") {
			lookupSponsor();
		 }
	 }
	 
	function lookupOrganization(){
		jQuery("input[id='organization']").parent().children('input:image').remove();
		jQuery("input[id='organization']").parent().append("<input type='image' tabindex='1000000' name='methodToCall.performLookup.(!!org.kuali.coeus.common.framework.org.Organization!!).(((organizationName:organization))).((`organization:organizationName`)).((&lt;&gt;)).(([])).((**)).((^^)).((&amp;&amp;)).((//)).((~~)).(::::;"+lookupUrl()+";::::).anchor' src='../kr/static/images/searchicon.gif' border='0' class='tinybutton' valign='middle' alt='Search Organization' title='Search Organization'>");
		
		
		jQuery("input[id='document.newMaintainableObject.organization']").parent().children('input:image').remove();
		jQuery("input[id='document.newMaintainableObject.organization']").parent().append("<input type='image' tabindex='1000000' name='methodToCall.performLookup.(!!org.kuali.coeus.common.framework.org.Organization!!).(((organizationId:document.newMaintainableObject.organization,))).((`document.newMaintainableObject.organization:organizationId,`)).((&lt;&gt;)).(([])).((**)).((^^)).((&amp;&amp;)).((//)).((~~)).(::::;"+lookupUrl()+";::::).anchor4' src='../kr/static/images/searchicon.gif' border='0' class='tinybutton' valign='middle' alt='Search Organization' title='Search Organization'>");
	}

	function lookupSponsor() {
		jQuery("input[id='organization']").parent().children('input:image').remove();
		jQuery("input[id='organization']").parent().append("<input type='image' tabindex='1000000' name='methodToCall.performLookup.(!!org.kuali.coeus.common.framework.sponsor.Sponsor!!).(((sponsorName:organization))).((`organization:sponsorName`)).((&lt;&gt;)).(([])).((**)).((^^)).((&amp;&amp;)).((//)).((~~)).(::::;"+lookupUrl()+";::::).anchor' src='../kr/static/images/searchicon.gif' border='0' class='tinybutton' valign='middle' alt='Search Organization' title='Search Organization'>");
		
		
		jQuery("input[id='document.newMaintainableObject.organization']").parent().children('input:image').remove();
		jQuery("input[id='document.newMaintainableObject.organization']").parent().append("<input type='image' tabindex='1000000' name='methodToCall.performLookup.(!!org.kuali.coeus.common.framework.sponsor.Sponsor!!).(((sponsorCode:document.newMaintainableObject.sponsorCode,))).((`document.newMaintainableObject.sponsorCode:sponsorCode,`)).((&lt;&gt;)).(([])).((**)).((^^)).((&amp;&amp;)).((//)).((~~)).(::::;"+lookupUrl()+";::::).anchor4' src='../kr/static/images/searchicon.gif' border='0' class='tinybutton' valign='middle' alt='Search Organization' title='Search Organization'>");
	}

var oldDisplayValue;
var displayValue;
var dataType;
var hasLookup;
var lookupArgument;
var lookupPkReturn;
var fieldPrefix;
var changedValue;
var comments;
var lookupReturn;

function getIndex(what) {
    for (var i = 0; i < document.KualiForm.elements.length; i++) {
        if (what == document.KualiForm.elements[i]) {
            return i;
          }
       }
       return -1;
}

function setupBudgetStatusSummary(document) {
	  var finalVersionFlag = document.getElementById('document.budget.finalVersionFlag');
	  var temp = document.getElementById('hack');
	  var hackIndex = getIndex(temp);
	  var finalVersionFlagIndex = getIndex(finalVersionFlag);
	  var statusHidden = document.KualiForm.elements[hackIndex + 1];
	  var status = document.KualiForm.elements[hackIndex + 2];
	  var finalVersionFlagHidden = document.KualiForm.elements[finalVersionFlagIndex + 1];
	  if (finalVersionFlag != null) { //if not read only
	    if(finalVersionFlag.checked) {
	  	  statusHidden.disabled=true;
	  	  status.disabled=false;
	    } else {
	   	  statusHidden.disabled=false;
	  	  status.disabled=true;
	    }
	  }
	 }

function toggleFinalCheckboxSummary(document) {
	var completed = false;
	var finalVersionFlag = document.getElementById('document.budget.finalVersionFlag');
	var finalVersionFlagIndex = getIndex(finalVersionFlag);
	var finalVersionFlagHidden = document.KualiForm.elements[finalVersionFlagIndex + 2];
	var temp = document.getElementById('hack');
	var hackIndex = getIndex(temp);
	var statusHidden = document.KualiForm.elements[hackIndex + 1];
	var status = document.KualiForm.elements[hackIndex + 2];
	if(status.value == '1'){
		completed = true;
		}
	if(completed) {
		finalVersionFlag.disabled = true;
		finalVersionFlagHidden.disabled = false;
		finalVersionFlagHidden.value = true;
	}else {
		finalVersionFlag.disabled = false;
		finalVersionFlagHidden.disabled = true;
		finalVersionFlagHidden.value = false;
	}
}


//For Award module
function selectAllAwardKeywords(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	var name = 'document.award.keyword[' + j + '].selectKeyword';
	  	if (e.name == name) {
 		    e.checked = true;
	  		j++; 
	  	}
	  }
	}
}

function loadApplicableTransactionIds(versionId, transactionId, awardNumber) {
	var sequenceNumber = jQuery(versionId).val();
	var docFormKey = jQuery('input[name="docFormKey"]').val();
	var dwrReply = {
		callback:function(data) {
			if ( data != null ) {
				//clear all current options
				jQuery(transactionId).html("");
			    for (key in data) {
			    	jQuery(transactionId).append("<option value='"+key+"'>"+data[key]+"</option>");
				}
			}
		},
		errorHandler:function( errorMessage ) {
			window.status = errorMessage;
			//clear all current options
			jQuery(transactionId).html("");		
		}
	};
	AwardTransactionLookupService.getApplicableTransactionIds(awardNumber+":"+docFormKey, sequenceNumber, dwrReply);
}


function setAllItemsIn(id, value) {
	jQuery("#" + id + " INPUT[type='checkbox']").attr('checked', value);
}

function selectAllInstitutionalProposalKeywords(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	var name = 'document.institutionalProposal.institutionalProposalScienceKeywords[' + j + '].selectKeyword';
	  	if (e.name == name) {
 		    e.checked = true;
	  		j++; 
	  	}
	  }
	}
}

/*
 * Load the Organization Data field based on the Organization Code passed in and a fetch function that takes an organization Code and a dwr callback.
 */
function loadOrganizationData(organizationIdFieldName, organizationDataFieldName, organizationFetchFunction) {

	var organizationId = dwr.util.getValue( organizationIdFieldName );
	if (organizationId=='') {
		clearRecipients( organizationDataFieldName, "" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					if ( organizationDataFieldName != null && organizationDataFieldName != "" ) {
						setRecipientValue( organizationDataFieldName, data );
					}
				} else {
					if ( organizationDataFieldName != null && organizationDataFieldName != "" ) {
						setRecipientValue(  organizationDataFieldName, wrapError( "not found" ), true );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				setRecipientValue( organizationDataFieldName, wrapError( "not found" ), true );
			}
		};
		organizationFetchFunction(organizationId,dwrReply);
	}
}


/*
 * Load the Organization Name field based on the Organization Code passed in.
 */
function loadOrganizationName(organizationIdFieldName, organizationNameFieldName ) {
	loadOrganizationData(organizationIdFieldName, organizationNameFieldName, OrganizationService.getOrganizationName);
}

/*
 * Load the Organization Duns field based on the Organization Code passed in.
 */
function loadOrganizationDuns(organizationIdFieldName, organizationDunsFieldName ) {
	loadOrganizationData(organizationIdFieldName, organizationDunsFieldName, OrganizationService.getOrganizationDuns);
}

function loadAwardBasisOfPaymentCodes( awardTypeCode, basisOfPaymentCodeFieldName ) {

	if ( awardTypeCode=='' || awardTypeCode== "") {
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  					var e = document.KualiForm.elements[i];
	  					if(e.type == 'select-one' && e.name == basisOfPaymentCodeFieldName) {
	  						e.options.length=0;
							var option_array=data.split(",");
							var optionNum=0;
							var nameLabelPair;
							while (optionNum < option_array.length)
							{
							   nameLabelPair = option_array[optionNum].split(";");
							   e.options[optionNum]=new Option(nameLabelPair[1], nameLabelPair[0]);
							   optionNum+=1;
							}
						}
					}		
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
			}
		};
		AwardPaymentAndInvoicesService.getEncodedValidAwardBasisPaymentsByAwardTypeCode( awardTypeCode, dwrReply )
	}
}


function loadAwardMethodOfPaymentCodes( basisOfPaymentCodeFieldName, methodOfPaymentCodeFieldName) {    
    var basisOfPaymentCode = dwr.util.getValue( basisOfPaymentCodeFieldName );
	if ( basisOfPaymentCode=='' || basisOfPaymentCode== "") {
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  					var e = document.KualiForm.elements[i];
	  					if(e.type == 'select-one' && e.name == methodOfPaymentCodeFieldName) {
	  						e.options.length=0;
							if ( basisOfPaymentCodeFieldName != null && basisOfPaymentCodeFieldName != "" ) {
								var option_array=data.split(",");
								var optionNum=0;
								var nameLabelPair;
								while (optionNum < option_array.length)
								{
								   nameLabelPair = option_array[optionNum].split(";");
								   e.options[optionNum]=new Option(nameLabelPair[1], nameLabelPair[0]);
								   optionNum+=1;
								}
							}
						}
					}		
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
			}
		};
		AwardPaymentAndInvoicesService.getEncodedValidBasisMethodPaymentsByBasisCode( basisOfPaymentCode, dwrReply )
	}

}

function loadFrequencyCode(reportClassCode, reportCodeFieldName,frequencyCodeFieldName) {    
    var reportCode = dwr.util.getValue( reportCodeFieldName );
	if (reportClassCode=='' || reportCode == "") {
		clearRecipients( frequencyCodeFieldName, "" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  					var e = document.KualiForm.elements[i];
	  					if(e.type == 'select-one' && e.name == frequencyCodeFieldName) {
	  						e.options.length=0;
							if ( frequencyCodeFieldName != null && frequencyCodeFieldName != "" ) {
								var option_array=data.split(",");
								var optionNum=0;
								var nameLabelPair;
								while (optionNum < option_array.length)
								{
								   nameLabelPair = option_array[optionNum].split(";");
								   e.options[optionNum]=new Option(nameLabelPair[1], nameLabelPair[0]);
								   optionNum+=1;
								}
							}
						}
					}		
				} else {
					if ( frequencyCodeFieldName != null && frequencyCodeFieldName != "" ) {
						setRecipientValue(  frequencyCodeFieldName, wrapError( "not found" ), true );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				setRecipientValue( frequencyCodeFieldName, wrapError( "not found" ), true );
			}
		};
		AwardReportsService.getFrequencyCodes(reportClassCode,reportCode,dwrReply);
	}

}

function loadFrequencyBaseCode(frequencyCodeFieldName, frequencyBaseCodeFieldName) {    
    var frequencyCode = dwr.util.getValue( frequencyCodeFieldName );
	if (frequencyCode=='') {
		clearRecipients( frequencyBaseCodeFieldName, "" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  					var e = document.KualiForm.elements[i];
	  					if(e.type == 'select-one' && e.name == frequencyBaseCodeFieldName) {
	  						e.options.length=0;
							if ( frequencyBaseCodeFieldName != null && frequencyBaseCodeFieldName != "" ) {
								var option_array=data.split(",");
								var optionNum=0;
								var nameLabelPair;
								while (optionNum < option_array.length)
								{
								   nameLabelPair = option_array[optionNum].split(";");
								   e.options[optionNum]=new Option(nameLabelPair[1], nameLabelPair[0]);
								   optionNum+=1;
								}
							}
						}
					}		
				} else {
					if ( frequencyBaseCodeFieldName != null && frequencyBaseCodeFieldName != "" ) {
						setRecipientValue(  frequencyCodeFieldName, wrapError( "not found" ), true );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				setRecipientValue( frequencyBaseCodeFieldName, wrapError( "not found" ), true );
			}
		};
		AwardReportsService.getFrequencyBaseCodes(frequencyCode,dwrReply);
	}

}

/**
 * Function for Finding Expedicted Date
 * @param approveDateElementId
 * @param expiredDateElementId
 * @return
 */
function loadExpeditedDates(approveDateElementId, expiredDateElementId, differenceElementId){	
	
	var dateValue = $j(jq_escape(approveDateElementId)).val();
	var defaultDifference = 1;
	
	var intRegex = /^\+?(0|[1-9]\d*)$/; 
	if(intRegex.test($j(jq_escape(differenceElementId)).val())) {
		defaultDifference = $j(jq_escape(differenceElementId)).val();
	}
	
	if(dateValue!= null){		
		if(checkdate(dateValue)){		
			var myDate = new Date(dateValue);		
			myDate.setDate(myDate.getDate() - 1);
			myDate.setYear((myDate.getFullYear() + parseInt(defaultDifference, 10)));
				var MM = myDate.getMonth()+1;
				var DD = myDate.getDate();
				var YY = myDate.getFullYear();
				  if(MM<10) MM="0"+MM;
				  if(DD<10) DD="0"+DD;

				  var expirationDate = MM+"/"+DD+"/"+YY;			

				  $j(jq_escape(expiredDateElementId)).val(expirationDate);
		}
	}
			
}

/**
*
* Validate Date Field script 
* //Basic check for format validity=mm/dd/yyyy
* //Detailed check for valid date ranges(Feb 30 or..)
**/

function checkdate(input){	
	var validformat=/^\d{2}\/\d{2}\/\d{4}$/ 	
	var returnval=true;
	if(!validformat.test(input)){
		returnval=false;
		alert("Invalid Date Format. Please correct and submit again.");
		return false;
	}
	else{ 
		var monthfield=input.split("/")[0];
		var dayfield=input.split("/")[1];
		var yearfield=input.split("/")[2];
		var dayobj = new Date(yearfield, monthfield-1, dayfield);
		if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield)){
			returnval=false;
			alert("Invalid Day, Month, or Year range detected. Please correct and submit again.");
			return false;
		}		
	}	
	return returnval;
}



/*
 * Based upon the selected committe, load the scheduled dates for that committee
 * into the drop-down menu for scheduled dates.
 */
function loadScheduleDates(committeeElementId, protocolElementId, scheduleElementId) {
    reviewersElement = document.getElementById("reviewers");
    if (reviewersElement != null) {
        reviewersElement.style.display = 'none';
    }
    onlyLoadScheduleDates(committeeElementId, protocolElementId, scheduleElementId);
}

function onlyLoadScheduleDates(committeeElementId, protocolId, scheduleElementId) {
	var committeeId = dwr.util.getValue(committeeElementId);
	var scheduleElement = document.getElementsByName(scheduleElementId);
	var dwrReply = {
		callback:function(data) {
			if ( data == null ) {
			    scheduleElement[0].innerHTML = "";
			} else {
				var dateOptions = data.split(";");
				
				removeAllChildren(scheduleElement[0]);
				
				/*
				 * Add in the new set of schedules to select from.
				 */
				for (var i = 0; i < dateOptions.length; i += 2) {
				    var option = document.createElement('option');
				    option.setAttribute('value', dateOptions[i]);
				    option.text = dateOptions[i + 1];
				    addSelectOption(scheduleElement[0], option);
				}
			}
		},
		errorHandler:function( errorMessage ) {
			window.status = errorMessage;	
			scheduleElement[0].innerHTML = "";	
		}
	};
	ProtocolActionAjaxService.getValidCommitteeDates(committeeId, protocolId, dwrReply);
}

/*
 * The Expedited Review and Exempt Studies CheckList are embedded within the
 * web page but are initially invisible.  Based upon what is selected, either
 * make one of the CheckList visible or both invisible.
 */
function updateCheckList(protocolReviewTypeCodeElementId) {
	var protocolReviewTypeCode = dwr.util.getValue(protocolReviewTypeCodeElementId);
	if (protocolReviewTypeCode == "2") {
	    document.getElementById('expeditedReviewCheckList').style.display = '';
	}
	else {
	    document.getElementById('expeditedReviewCheckList').style.display = 'none';
	}
	if (protocolReviewTypeCode == "3") {
	    document.getElementById('exemptStudiesCheckList').style.display = '';
	}
	else {
	    document.getElementById('exemptStudiesCheckList').style.display = 'none';
	}
}

/**
 * Display the ProtocolFundingSource document or BO.
 */
var protocolFundingSourceWindow = null;

function protocolFundingSourcePop(name, docFormKey, sessionDocument, line, currentTabIndex, protocolModule) {

	var documentWebScope = "";
	var action = "/protocolProtocol.do"
	if (sessionDocument == true) {
		documentWebScope = "session";
	}

	if (protocolFundingSourceWindow != null) {
		protocolFundingSourceWindow.close();
	} 
	//alert(protocolModule);
	
	if(protocolModule == 'iacuc') {
		action = "/iacucProtocolProtocol.do";
	}

	//alert(action);
	
	protocolFundingSourceWindow = window.open(extractUrlBase() +  
			action + "?methodToCall=viewProtocolFundingSource&methodToCallAttribute=methodToCall.viewProtocolFundingSource.line="+line +".anchor"+currentTabIndex
			+".x&line="+line 
			+ "&currentTabIndex="+currentTabIndex
			+"&docFormKey="+docFormKey+"&documentWebScope="+documentWebScope,
    	                               "protocolFundingSourceWindow", 
    	                               "width=800, height=750, scrollbars=yes, resizable=yes");   
}

/*
 * Based upon the selected committee and scheduled date, display the list
 * reviewers.
 */
function displayReviewers(protocolId) {
	
    var committeeId = dwr.util.getValue('actionHelper.protocolSubmitAction.committeeId');
    var scheduleId = dwr.util.getValue('actionHelper.protocolSubmitAction.scheduleId');
    var protocolReviewTypeCode = dwr.util.getValue('actionHelper.protocolSubmitAction.protocolReviewTypeCode');
    
    // we suppress the reviewer display if a committee is not selected, or if a schedule is not selected in case of a non-expedited review type
    if ( ((scheduleId === "") && (protocolReviewTypeCode != '2' && protocolReviewTypeCode != '3')) || (committeeId === "") ) {
    	document.getElementById("reviewers").style.display = 'none';
    }
    else {
		var dwrReplyReviewers = {
			callback:function(data) {
				if ( data != null ) {
					getReviewerTypes(data);
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
			}
		};
		ProtocolActionAjaxService.getReviewers(protocolId, committeeId, scheduleId, dwrReplyReviewers);
	}
}

function setDefaultReviewerTypeCode(methodToCall, committeeId, scheduleId, protocolId, beanName) {	
	var reviewerBean = "actionHelper." + beanName + ".reviewer[";			
	var cmtId = dwr.util.getValue(committeeId); 
	var schedId = $j(jq_escape(scheduleId)).attr("value");
	var queryString = "&committeeId="+cmtId+"&scheduleId=" + schedId+"&protocolId="+protocolId;
	callAjaxByPath('jqueryAjax.do', methodToCall, queryString,
			function(jQueyrData) {
				reviewersReturned = $j(jQueyrData).find('#ret_value').html();
				getProtocolReviewerTypes(reviewersReturned, beanName);
				
				var reviewersArr = reviewersReturned.split(";");
				
				var defaultReviewTyper;
				//just to note, this will probably be higher than the actual number of reviewers, but is a good number to loop through.
   			var numberOfRevierwers = reviewersArr.length;
   			var dwrReply = {
   					callback:function(data) {
   						if ( data != null ) {	
   							defaultReviewTyper = data;
   						} else {
   							defaultReviewTyper = '';
   						}
   						
   						for (i=0; i<numberOfRevierwers; i++) {
					  		var selectField = document.getElementsByName(reviewerBean + i + '].reviewerTypeCode')[0];
					  		if (selectField != null) {
						  		for (j=0; j<selectField.length; j++) {
						  			if (selectField.options[j].value == defaultReviewTyper) {
						  				selectField.options[j].setAttribute("selected", "selected");
						  				selectField.selectedIndex = j;
						  			}
						  		}
					  		}
					  		
					  	}
   					},
   					errorHandler:function( errorMessage ) {	
   						window.status = errorMessage;
   						window.alert('C data: unknown, there is an error: ' + errorMessage);
   						defaultReviewTyper = '';
   					}
   			};
   			IacucProtocolActionAjaxService.getDefaultCommitteeReviewTypeCode(dwrReply);
   			return defaultReviewTyper;
				
			},
			function(error) {
				alert("error is" + error);
			}
	);
}

var REVIEWERS_ARRAY_ELEMENTS_PER_RECORD = 4;

var IRB_REVIEWERS_ARRAY_ELEMENTS_PER_RECORD = 3;

function setModifySubmissionDefaultReviewerTypeCode(methodToCall, committeeId, scheduleId, protocolId, beanName, protocolReviewTypeCode) {	
	var reviewerBean = "actionHelper." + beanName + ".reviewer[";
	var cmtId = dwr.util.getValue(committeeId); 
	var schedId = $j(jq_escape(scheduleId)).attr("value");
	var reviewTypeCode = $j(jq_escape(protocolReviewTypeCode)).attr("value");

	if ( (committeeId == "select" || cmtId == "" ) ||
			(reviewTypeCode == "3" && (schedId == "" || scheduleId == "select")) ) {
		document.getElementById("reviewers").style.display = 'none';
	} else {
		var queryString = "&committeeId="+cmtId+"&scheduleId=" + schedId+"&protocolId="+protocolId+"&protocolReviewTypeCode="+reviewTypeCode;
		callAjaxByPath('jqueryAjax.do', methodToCall, queryString,
				function(jQueyrData) {
					reviewersReturned = $j(jQueyrData).find('#ret_value').html();
					getProtocolReviewerTypes(reviewersReturned, beanName);

					var reviewersArr = reviewersReturned.split(";");

					var defaultReviewTyper;
					var numberOfRevierwers = reviewersArr.length/REVIEWERS_ARRAY_ELEMENTS_PER_RECORD;
					var dwrReply = {
						callback:function(data) {
							if ( data != null ) {
								defaultReviewTyper = data;
							} else {
								defaultReviewTyper = '';
							}

							for (i=0; i<numberOfRevierwers; i++) {
								var selectField = document.getElementsByName(reviewerBean + i + '].reviewerTypeCode')[0];
								if (selectField != null) {
									var rt = reviewersArr[(i * REVIEWERS_ARRAY_ELEMENTS_PER_RECORD) + 3];
									for (j=0; j<selectField.length; j++) {
										if (rt) {
											if (selectField.options[j].value == rt.trim()) {
												selectField.options[j].setAttribute("selected", "selected");
												selectField.selectedIndex = j;
											}
										} else if (selectField.options[j].value == defaultReviewTyper) {
											selectField.options[j].setAttribute("selected", "selected");
											selectField.selectedIndex = j;
										}
									}
								}

							}
						},
						errorHandler:function( errorMessage ) {
							window.status = errorMessage;
							window.alert('C data: unknown, there is an error: ' + errorMessage);
							defaultReviewTyper = '';
						}
					};
					IacucProtocolActionAjaxService.getDefaultCommitteeReviewTypeCode(dwrReply);
					return defaultReviewTyper;

				},
				function(error) {
					alert("error is" + error);
				}
		);
	}
}

function protocolDisplayReviewers(methodToCall, committeeId, scheduleId, protocolId, beanName) {
	var cmtId = dwr.util.getValue(committeeId); 
	var schedId = $j(jq_escape(scheduleId)).attr("value");
    if (scheduleId == "select") {
    	document.getElementById("reviewers").style.display = 'none';
    }
    else {
    	var queryString = "&committeeId="+cmtId+"&scheduleId=" + schedId+"&protocolId="+protocolId;
    	callAjaxByPath('jqueryAjax.do', methodToCall, queryString,
    			function(data) {
    				reviewersReturned = $j(data).find('#ret_value').html();
					getProtocolReviewerTypes(reviewersReturned, beanName);
    				
    			},
    			function(error) {
    				alert("error is" + error);
    			}
    	);
	}
}

function protocolModifySubmissionReviewers(methodToCall, committeeId, scheduleId, protocolId, beanName, protocolReviewTypeCode) {
	var cmtId = dwr.util.getValue(committeeId); 
	var schedId = $j(jq_escape(scheduleId)).attr("value");
	var reviewTypeCode = dwr.util.getValue(protocolReviewTypeCode);

	if ( (committeeId == "select" || cmtId == "" ) ||
    	 (reviewTypeCode == "3" && (schedId == "" || scheduleId == "select")) ) {
    	document.getElementById("reviewers").style.display = 'none';
    }
    else {
    	var queryString = "&committeeId="+cmtId+"&scheduleId=" + schedId+"&protocolId="+protocolId+"&protocolReviewTypeCode="+reviewTypeCode;
    	callAjaxByPath('jqueryAjax.do', methodToCall, queryString,
    			function(data) {
    				reviewersReturned = $j(data).find('#ret_value').html();
					getProtocolReviewerTypes(reviewersReturned, beanName);
					document.getElementById("reviewers").style.display = 'table-row';
    			},
    			function(error) {
    				alert("error is" + error);
    			}
    	);
	}
}

function getProtocolReviewerTypes(reviewerData, beanName) {
		var methodToCall= 'getProtocolReviewerTypes';
		var queryString = '';
		callAjaxByPath('jqueryAjax.do', methodToCall, queryString,
			function(data) { 
				types = $j(data).find('#ret_value').html();
				updateProtocolReviewerHtml(reviewerData, types, beanName);
			},
			function( error ) {
				window.status = errorMessage;	
			}
		);
}
/*
 * Get the set of reviewer types to display in the drop-down menu.
 */
function getReviewerTypes(reviewerData) {
	var dwrReplyTypes = {
		callback:function(data) {
			if ( data != null ) {
				updateReviewerHtml(reviewerData, data);
			}
		},
		errorHandler:function( errorMessage ) {
			window.status = errorMessage;	
		}
	};
	ProtocolActionAjaxService.getReviewerTypes(dwrReplyTypes);
}

/*
 * Update the HTML for the reviewers.  Half of the reviewers are shown on the
 * left and half on the right.
 */
function updateReviewerHtml(reviewerData, reviewerTypesData) {
	reviewerTypes = reviewerTypesData.split(";");
	document.getElementById("reviewers").style.display = '';
	var reviewersArr = reviewerData.split(";");
	var arrLength = reviewersArr.length;
	var numReviewers = Math.floor(reviewersArr.length / IRB_REVIEWERS_ARRAY_ELEMENTS_PER_RECORD);
	var numRows = Math.floor((numReviewers+1) / 2);
	var reviewersTableLeft = document.getElementById("reviewersTableLeft");
	var reviewersTableRight = document.getElementById("reviewersTableRight");
	setReviewers(reviewersArr, 0, IRB_REVIEWERS_ARRAY_ELEMENTS_PER_RECORD*numRows, reviewerTypes, reviewersTableLeft);
	setReviewers(reviewersArr, IRB_REVIEWERS_ARRAY_ELEMENTS_PER_RECORD*numRows, IRB_REVIEWERS_ARRAY_ELEMENTS_PER_RECORD*numReviewers, reviewerTypes, reviewersTableRight);
	//finally set the number of reviewers for proper trucation
	document.getElementById("numberOfReviewers").value = numReviewers;
}

function updateProtocolReviewerHtml(reviewerData, reviewerTypesData, beanName) {
	reviewerTypes = reviewerTypesData.split(";");
	document.getElementById("reviewers").style.display = '';
	var reviewersArr = reviewerData.split(";");
	var arrLength = reviewersArr.length;
	var numReviewers = Math.floor(reviewersArr.length / REVIEWERS_ARRAY_ELEMENTS_PER_RECORD);
	var numRows = Math.floor((numReviewers+1) / 2);
	var reviewersTableLeft = document.getElementById("reviewersTableLeft");
	var reviewersTableRight = document.getElementById("reviewersTableRight");
	setProtocolReviewers(reviewersArr, 0, REVIEWERS_ARRAY_ELEMENTS_PER_RECORD*numRows, reviewerTypes, reviewersTableLeft, beanName);
	setProtocolReviewers(reviewersArr, REVIEWERS_ARRAY_ELEMENTS_PER_RECORD*numRows, REVIEWERS_ARRAY_ELEMENTS_PER_RECORD*numReviewers, reviewerTypes, reviewersTableRight, beanName);
	//finally set the number of reviewers for proper trucation
	document.getElementById("numberOfReviewers").value = numReviewers;
}

function setProtocolReviewers(reviewers, beginIndex, endIndex, reviewerTypes, htmlElement, beanName) {
	removeAllChildren(htmlElement);
	var reviewerBean = "actionHelper." + beanName + ".reviewer[";			
    var tbody = document.createElement('tbody');
	for (var i = beginIndex; i < endIndex; i += REVIEWERS_ARRAY_ELEMENTS_PER_RECORD) {
		reviewerIndex = i/REVIEWERS_ARRAY_ELEMENTS_PER_RECORD;
		
		var row = document.createElement('tr');
		var data = document.createElement('td');
		
		data.style.border = "0 none";
		var text = document.createTextNode(reviewers[i+1]);
		data.appendChild(text);
		row.appendChild(data);
		
		data = document.createElement('td');
		data.style.border = "0 none";
		data.appendChild(makeProtocolReviewerTypesDropDown(reviewerTypes, reviewerIndex, reviewerBean));
		
		hidden = document.createElement('input');
		hidden.setAttribute("id", reviewerBean + reviewerIndex + "].personId");
		hidden.setAttribute("type", "hidden");
		hidden.setAttribute("name", reviewerBean + reviewerIndex + "].personId");
	    var reviewer = reviewers[i].replace(/^\t*/, '');
		hidden.setAttribute("value", reviewer);
		data.appendChild(hidden);
		
		hidden = document.createElement('input');
		hidden.setAttribute("id", reviewerBean + reviewerIndex + "].fullName");
		hidden.setAttribute("type", "hidden");
		hidden.setAttribute("name", reviewerBean + reviewerIndex + "].fullName");
		hidden.setAttribute("value", reviewers[i+1]);
		data.appendChild(hidden);
		
		hidden = document.createElement('input');
		hidden.setAttribute("id", reviewerBean + reviewerIndex + "].nonEmployeeFlag");
		hidden.setAttribute("type", "hidden");
		hidden.setAttribute("name", reviewerBean + reviewerIndex + "].nonEmployeeFlag");
		if (reviewers[i+2] == "Y") {
			hidden.setAttribute("checked", "checked");
		}
		hidden.setAttribute("value", reviewers[i+2]);
		data.appendChild(hidden);
		
		row.appendChild(data);
		
		tbody.appendChild(row);
	}
	
	htmlElement.appendChild(tbody);
}
/*
 * Populates the inner HTML of a table, putting a table cell for each name stored in reviewersArr.
 * Only every other index of reviewersArr is used, starting at beginIndex+1.
 */
function setReviewers(reviewers, beginIndex, endIndex, reviewerTypes, htmlElement) {
	
	removeAllChildren(htmlElement);
				
    var tbody = document.createElement('tbody');
	for (var i = beginIndex; i < endIndex; i += IRB_REVIEWERS_ARRAY_ELEMENTS_PER_RECORD) {
		reviewerIndex = i/IRB_REVIEWERS_ARRAY_ELEMENTS_PER_RECORD;
		
		var row = document.createElement('tr');
		var data = document.createElement('td');
		
		data.style.border = "0 none";
		var text = document.createTextNode(reviewers[i+1]);
		data.appendChild(text);
		row.appendChild(data);
		
		data = document.createElement('td');
		data.style.border = "0 none";
		data.appendChild(makeReviewerTypesDropDown(reviewerTypes, reviewerIndex));
		
		hidden = document.createElement('input');
		hidden.setAttribute("type", "hidden");
		hidden.setAttribute("name", "actionHelper.protocolSubmitAction.reviewer[" + reviewerIndex + "].personId");
		hidden.setAttribute("value", reviewers[i]);
		data.appendChild(hidden);
		
		hidden = document.createElement('input');
		hidden.setAttribute("type", "hidden");
		hidden.setAttribute("name", "actionHelper.protocolSubmitAction.reviewer[" + reviewerIndex + "].fullName");
		hidden.setAttribute("value", reviewers[i+1]);
		data.appendChild(hidden);
		
		hidden = document.createElement('input');
		hidden.setAttribute("type", "hidden");
		hidden.setAttribute("name", "actionHelper.protocolSubmitAction.reviewer[" + reviewerIndex + "].nonEmployeeFlag");
		if (reviewers[i+2] == "Y") {
			hidden.setAttribute("checked", "checked");
		}
		hidden.setAttribute("value", reviewers[i+2]);
		data.appendChild(hidden);
		
		row.appendChild(data);
		
		tbody.appendChild(row);
	}
	
	htmlElement.appendChild(tbody);
}

function makeProtocolReviewerTypesDropDown(reviewerTypes, reviewerIndex, reviewerBean) {
    var selectElement = document.createElement('select');
    selectElement.setAttribute("name", reviewerBean + reviewerIndex + "].reviewerTypeCode");
    selectElement.setAttribute("id", reviewerBean + reviewerIndex + "].reviewerTypeCode");
    var option = document.createElement('option');
	option.setAttribute("value", "");
	option.setAttribute("selected", "selected");
	option.text = "select";
	addSelectOption(selectElement, option);
	
    for (var i = 0; i < reviewerTypes.length; i += 2) {
	    var reviewerType = reviewerTypes[i].replace(/^\t*/, '');
        option = document.createElement('option');
		option.setAttribute("value", reviewerType);
		option.text = reviewerTypes[i+1];
		addSelectOption(selectElement, option);
    }
    
    return selectElement;
}
/*
 * Create the select (drop-down menu) of reviewer types.
 */
function makeReviewerTypesDropDown(reviewerTypes, reviewerIndex) {
    var selectElement = document.createElement('select');
    selectElement.setAttribute("name", "actionHelper.protocolSubmitAction.reviewer[" + reviewerIndex + "].reviewerTypeCode");
    
    var option = document.createElement('option');
	option.setAttribute("value", "");
	option.setAttribute("selected", "selected");
	option.text = "select";
	addSelectOption(selectElement, option);
	
    for (var i = 0; i < reviewerTypes.length; i += 2) {
        option = document.createElement('option');
		option.setAttribute("value", reviewerTypes[i]);
		option.text = reviewerTypes[i+1];
		addSelectOption(selectElement, option);
    }
    
    return selectElement;
}

/*
 * Thanks to IE, we can't use appendChild() for adding an option
 * to a select element.
 */
function addSelectOption(selectElement, optionElement) {
    try {
       selectElement.add(optionElement,null); // standards compliant
	}
	catch (ex) {
	    selectElement.add(optionElement); // IE only
	}
}

/*
 * Remove all of the child nodes for an element.
 */
function removeAllChildren(element) {
	var length = element.childNodes.length;
	for (var i = 0; i < length; i++) {
		element.removeChild(element.childNodes[0]);
	}
}

/*
 * If JavaScript is enabled, this function will be executed. 
 * By changing the value from "0" to "1", the server will know
 * the JavaScript is enabled.
 */
function enableJavaScript() {
	var element = document.getElementById("javaScriptFlag");
	element.value = "1";
}

var personnelDetailsWindow;
function personnelDetailsPopup(budgetPeriod, lineNumber, personNumber, docFormKey, sessionDocument){
var documentWebScope;
  if (sessionDocument == "true") {
      documentWebScope="session";
  }
  if (personnelDetailsWindow && personnelDetailsWindow.open && !personnelDetailsWindow.closed){
  	personnelDetailsWindow.focus();
  }else{
    personnelDetailsWindow = window.open(extractUrlBase()+"/awardBudgetPersonnel.do?methodToCall=personnelDetails&budgetPeriod="+budgetPeriod+"&line="+lineNumber+"&personNumber="+personNumber+"&docFormKey="+docFormKey+"&documentWebScope="+documentWebScope, "personnelDetailsWindow", "width=650, height=400, scrollbars=yes");
    if (window.focus) {
         personnelDetailsWindow.focus();
    }
  }
}

var costElementFieldName;

 function updateCostElement(budgetId, costElement, personSequenceNumberField, budgetCategoryTypeCode, callbackFunction ) {
	var personSequenceNumber = personSequenceNumberField.value;
	var docFormKey = dwr.util.getValue( "docFormKey" );
	costElementFieldName = costElement;
	if ( personSequenceNumber != "") {
		var dwrReply = {
			callback:callbackFunction,
			errorHandler:function( errorMessage ) { 
				window.status = errorMessage;
			}
		};
		BudgetService.getApplicableCostElementsForAjaxCall(budgetId, personSequenceNumber, budgetCategoryTypeCode + ":" + docFormKey, dwrReply );
	} else {
	    kualiElements[costElementFieldName].options.length=1;
	    var ceLookupDiv = document.getElementById("ceLookupDiv");
	 	ceLookupDiv.style.display = '';
	}
}

function updateCostElement_Callback( data ) {
	var ceLookupDiv = document.getElementById("ceLookupDiv");
	ceLookupDiv.style.display = '';
	 	
	kualiElements[costElementFieldName].options.length=0; //reset 
	var data_array=data.split(",");
	var optionNum=0;
	var nameLabelPair;
	var ceLookupFlag;
	
	while (optionNum < data_array.length)
	 {
 		if (optionNum == 0) {
		     kualiElements[costElementFieldName].options[0]=new Option("select:","", true, true);
		} else if (optionNum > 0 && optionNum < data_array.length-1) {
	  		nameLabelPair = data_array[optionNum].split(";");
			kualiElements[costElementFieldName].options[optionNum]=new Option(nameLabelPair[1], nameLabelPair[0]);
		} else if (optionNum == data_array.length-1) {
			nameLabelPair = data_array[optionNum].split(";");
			ceLookupFlag = nameLabelPair[1];
		}
     	optionNum+=1;
	 }
	 
	 if(ceLookupFlag != '' && ceLookupFlag == 'false') {
	 	//disable Cost Element Lookup icon
	 	ceLookupDiv.style.display = 'none';
	 }
}

function disableGrpNameTextbox(groupNameSelectField) {
	var selectedIndex = groupNameSelectField.selectedIndex;
	var selectedText = groupNameSelectField.options[groupNameSelectField.selectedIndex].text;
	//alert(selectedText);
	
	var groupNameTextField = document.getElementById("newGroupName");
	groupNameTextField.value="";
	
	if(selectedText != 'select') {
		groupNameTextField.disabled = true;
	} else {
		groupNameTextField.disabled = false;
	}
}

function resetGrpNameTextbox() {
	var groupNameTextField = document.getElementById("newGroupName");
	if(groupNameTextField.value != '' && groupNameTextField.value == '(new group)') {
		groupNameTextField.value="";
	}
}

function previousPeriodSet() {
	document.forms[0].methodToCall.value = "previousPeriodSet";
	document.forms[0].submit();
}

function nextPeriodSet() {
	document.forms[0].methodToCall.value = "nextPeriodSet";
	document.forms[0].submit();
}

function showAllPanels() {
	var test = showTab(document, 'Summary');
	expandAll('true', false);
}

function selectAllFundedAwards(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if (e.type == 'checkbox') {
	  	if (e.name == 'selectedAwardFundingProposals') {
 		    if (e.disabled == false) {
 		    	e.checked = true;
 		    }
	  		j++; 
	  	}
	  }
	}
}

function unselectAllFundedAwards(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	if (e.name == 'selectedAwardFundingProposals') {
 		    if (e.disabled == false) {
 		    	e.checked = false;
 		    }
	  		j++; 
	  	}
	  }
	}
}

/*
 * get standard review comment for meeting from ProtocolContingency table
 */
function loadStandardReviewComment(protocolContingencyCodeFieldName, protocolContingencyDescriptionFieldName ) {
	// TODO : not sure why dwr.util.getvalue is not working
	//var protocolContingencyCode = dwr.util.getValue( protocolContingencyCodeFieldName );
	var protocolContingencyCode = document.getElementById(protocolContingencyCodeFieldName).value;

	//alert(protocolContingencyCodeFieldName+"-"+document.getElementById(protocolContingencyCodeFieldName).value);
	if (protocolContingencyCode=='') {
		//clearRecipients( protocolContingencyDescriptionFieldName, "" );
		document.getElementById(protocolContingencyDescriptionFieldName).value="";
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					if ( protocolContingencyDescriptionFieldName != null && protocolContingencyDescriptionFieldName != "" ) {
						//setRecipientValue( protocolContingencyDescriptionFieldName, data );
						//alert (protocolContingencyDescriptionFieldName+"-"+data);
						document.getElementById(protocolContingencyDescriptionFieldName).value=data;
					}
				} else {
					if ( protocolContingencyDescriptionFieldName != null && protocolContingencyDescriptionFieldName != "" ) {
						//setRecipientValue(  protocolContingencyDescriptionFieldName, wrapError( "not found" ), true );
						document.getElementById(protocolContingencyDescriptionFieldName).value=protocolContingencyCode + " Not found";
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				setRecipientValue( protocolContingencyDescriptionFieldName, wrapError( "not found" ), true );
			}
		};
		MeetingService.getStandardReviewComment(protocolContingencyCode,dwrReply);
	}
}

/*
 * For meeting page :
 * meeting minutes to hide/show 'standard review comment lookup'/'generate attendance check box'/'other business lookup' based on minute entry type
 */
function showHideDiv(minuteEntryTypeCode) {
    if (minuteEntryTypeCode.value == '2') {
    	document.getElementById('meetingHelper.newCommitteeScheduleMinute.defaultHeaderDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.genAttDiv').style.display = 'block'; 
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.genAttHeaderDiv').style.display = 'block';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.pcHeaderDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.pcSelectDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.pcCommentDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.actionItemHeaderDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.actionItemDiv').style.display = 'none';
    } else if (minuteEntryTypeCode.value == '3') {
    	document.getElementById('meetingHelper.newCommitteeScheduleMinute.defaultHeaderDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.genAttDiv').style.display = 'none'; 
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.genAttHeaderDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.pcHeaderDiv').style.display = 'block';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.pcSelectDiv').style.display = 'block';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.pcCommentDiv').style.display = 'block';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.actionItemHeaderDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.actionItemDiv').style.display = 'none';
    } else if (minuteEntryTypeCode.value == '4') {
    	document.getElementById('meetingHelper.newCommitteeScheduleMinute.defaultHeaderDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.genAttDiv').style.display = 'none'; 
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.genAttHeaderDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.pcHeaderDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.pcSelectDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.pcCommentDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.actionItemHeaderDiv').style.display = 'block';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.actionItemDiv').style.display = 'block';
    } else {	
    	document.getElementById('meetingHelper.newCommitteeScheduleMinute.defaultHeaderDiv').style.display = 'block';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.genAttDiv').style.display = 'none'; 
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.genAttHeaderDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.pcHeaderDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.pcSelectDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.pcCommentDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.actionItemHeaderDiv').style.display = 'none';
        document.getElementById('meetingHelper.newCommitteeScheduleMinute.actionItemDiv').style.display = 'none';
    } 
		
}

/*
 * For meeting page :
 * generate attendance comments if 'attendance' minute entry type is selected and 'generate attendance' is checked.
 */
function generateAttendance(genAtt, noMember, noOther) {
    var comment="";
    if (genAtt.checked) {
    	if (noMember > 0 || noOther > 0) {
	        for (var i = 0; i < noMember; i++) {
	            if (comment != "") {
	                comment = comment +"\n";
		        }    
	            comment = comment + document.getElementById('meetingHelper.memberPresentBeans['+i+'].attendance.personName').value 
	            if (document.getElementById('alternatePerson['+i+']')) {
	                comment = comment + " Alternate For : "+ document.getElementById('alternatePerson['+i+']').value
	            } 
	        }
	        for (var i = 0; i < noOther; i++) {
			    if (comment != "") {
				    comment = comment +"\n";
			    }    
		    	comment = comment + document.getElementById('meetingHelper.otherPresentBeans['+i+'].attendance.personName').value  + " Guest";
		    }
    	} else {
    		genAtt.checked = false;
    		alert("Attendance information not saved for this schedule.  Please input attendance information and save schedule details before generating attendance");
    	}

        document.getElementById('meetingHelper.newCommitteeScheduleMinute.minuteEntry').value = comment;
    }
}


var viewQuestionnaireWindow = null;
function questionnairePop(protocolNumber, submissionNumber, docFormKey, sessionDocument, summary) {

	var documentWebScope = "";
	if (sessionDocument == true) {
		documentWebScope = "session";
	}

	if (summary == true) {
        viewQuestionnaireWindow = window.open(extractUrlBase() +
    	                               "/questionnaire.do?methodToCall=summaryQuestionnairePop" +
    	                               "&docFormKey=" + docFormKey + 
    	                               "&documentWebScope=" + documentWebScope +
    	                               "&protocolNumber=" + protocolNumber +
    	                               "&sequenceNumber=" + submissionNumber, 
    	                               "viewQuestionnaire", 
    	                               "width=1200, height=800, scrollbars=yes, resizable=yes");
	} else {
	    viewQuestionnaireWindow = window.open(extractUrlBase() +
                "/questionnaire.do?methodToCall=submissionQuestionnairePop" +
                "&docFormKey=" + docFormKey + 
                "&documentWebScope=" + documentWebScope +
                "&protocolNumber=" + protocolNumber +
                "&submissionNumber=" + submissionNumber, 
                "viewQuestionnaire", 
                "width=1200, height=800, scrollbars=yes, resizable=yes");
	}
}

function closeQuestionnairePop() {
	if (viewQuestionnaireWindow != null) {
		viewQuestionnaireWindow.close();
	} 

}


function callAjaxByPath(url, methodToCall, codeValue, successCallback, errorCallback) {
	$j.ajax( {
		url : url,
		type : 'POST',
		dataType : 'html',
		data : 'methodToCall='+methodToCall+'&code=' + codeValue,
		cache : false,
		async : false,
		timeout : 5000,
		error : errorCallback,
		success : successCallback
	});
}

function callAjax(methodToCall, codeValue, successCallback, errorCallback) {
	callAjaxByPath('jqueryAjax.do', methodToCall, codeValue, successCallback, errorCallback);
}

function ajaxLoad(methodToCall, codeField, fieldToUpdate) {
	//codeField = codeField.replace(/\./g, "\\.");
	codeField = codeField.replace(/([ #;&,.+*~\':"!^$[\]()=>|\/@])/g, "\\$1");
	//fieldToUpdate = fieldToUpdate.replace(/\./g, "\\.");
	fieldToUpdate = fieldToUpdate.replace(/([ #;&,.+*~\':"!^$[\]()=>|\/@])/g, "\\$1");
	callAjax(methodToCall, $j("#"+codeField).attr("value"),
		//success callback
	  function(xml) {
		$j(xml).find('#ret_value').each(function() {
			$j('#'+fieldToUpdate+'\\.div').html($j(this).html());

			});
		$j(xml).find('#code_value').each(function() {
			$j('#'+ codeField).val($j(this).html());

			});
	  },
	  //error callback
	  function() {
			alert('Error loading XML document');
	  }); //end callAjax method call.

    return false;
}

/**
 * Display the Protocol document linked through Special Review
 */
var specialReviewProtocolWindow = null;
function specialReviewProtocolPop(sessionDocument, action, methodToCall, line, docFormKey) {
	var documentWebScope = "";
	if (sessionDocument == true) {
		documentWebScope = "session";
	}

	if (specialReviewProtocolWindow != null) {
		specialReviewProtocolWindow.close();
	} 

	specialReviewProtocolWindow = window.open(extractUrlBase() +  
		"/" + action + ".do?methodToCall=" + methodToCall 
			+ "&methodToCallAttribute=methodToCall." + methodToCall + ".x" 
			+ "&line=" + line 
			+ "&docFormKey=" + docFormKey 
			+ "&documentWebScope=" + documentWebScope,
	    "specialReviewProtocolWindow", 
		"width=800, height=750, scrollbars=yes, resizable=yes");   
}
function setRateOverrideFlag(budgetPeriod){
	dwr.util.setValue("document.budget.budgetPeriods["+(budgetPeriod-1)+"].rateOverrideFlag","true");
}
function updateFringeCalcAmounts(budgetPeriodFringeTotal,budgetPeriod,calcAmontsCount){
	var fringeTotal = dwr.util.getValue("document.budget.budgetPeriods["+(budgetPeriod-1)+"].totalFringeAmount");
	if(budgetPeriodFringeTotal!=fringeTotal){
		dwr.util.setValue("document.budget.budgetPeriods["+(budgetPeriod-1)+"].prevTotalFringeAmount",budgetPeriodFringeTotal);
		setRateOverrideFlag(budgetPeriod);
		for(var i= 0; i < calcAmontsCount; i++) {
			dwr.util.setValue("document.budget.budgetPeriods["+(budgetPeriod-1)+"].awardBudgetPeriodFringeAmounts["+i+"].calculatedCost","");
		}
		changeObjectVisibility("personnelFringeCalc"+(budgetPeriod-1)+".div.object","none");
		changeObjectVisibility("personnelFringeTotal.div.object","none");
	}
}
function updateFringeTotal(budgetPeriod,calcAmontsCount){
	var fringeTotal=0;
	for(var i= 0; i < calcAmontsCount; i++) {
		var fringeAmount = dwr.util.getValue("document.budget.budgetPeriods["+(budgetPeriod-1)+"].awardBudgetPeriodFringeAmounts["+i+"].calculatedCost");
		fringeTotal+=parseFloat(fringeAmount);
	}
	setRateOverrideFlag(budgetPeriod);
	dwr.util.setValue("document.budget.budgetPeriods["+(budgetPeriod-1)+"].totalFringeAmount",fringeTotal);
	changeObjectVisibility("personnelFringeCalc"+(budgetPeriod-1)+".div.object","none");
	changeObjectVisibility("personnelFringeTotal.div.object","none");
}

function updateStateFromCountry() {
	var countryCode = dwr.util.getValue( 'document.newMaintainableObject.countryCode' );
	
	var dwrReply = {
		callback:function(data) {
			if ( data != null ) {
				dwr.util.removeAllOptions( 'document.newMaintainableObject.state' );
				dwr.util.addOptions( 'document.newMaintainableObject.state', data, 'code', 'name' );
			} 
		},
		errorHandler:function( errorMessage ) {
			window.status = errorMessage;
		}
	};

	StateService.findAllStatesInCountryByAltCode(countryCode, dwrReply);
}



/*
 * Load the Sponsor Name field based on the Sponsor Code passed in.
 */
function loadSponsor(sponsorCodeFieldName, sponsorNameFieldName, entityNameFieldName, prevSponsorCodeFieldName ) {
    var sponsorCode = dwr.util.getValue( sponsorCodeFieldName );
    var prevSponsorCode = dwr.util.getValue( prevSponsorCodeFieldName );

    if (sponsorCode=='') {
        clearRecipients( sponsorNameFieldName, "" );
        dwr.util.setValue(prevSponsorCodeFieldName, "");
    } else {
        var dwrReply = {
            callback:function(data) {
                if ( data != null ) {
                    if ( sponsorNameFieldName != null && sponsorNameFieldName != "" ) {
                        setRecipientValue( sponsorNameFieldName, data.sponsorName );
                        setRecipientValue(entityNameFieldName, data.sponsorName);
                     //   if (sponsorCode!=prevSponsorCode) {
                            dwr.util.setValue(prevSponsorCodeFieldName, data.sponsorCode);
                            loadEntityContactInfoFromRolodex(data.rolodexId, findElPrefix( sponsorCodeFieldName )+".finEntityContactInfos[0]");
                     //   }
                    }
                } else {
                    if ( sponsorNameFieldName != null && sponsorNameFieldName != "" ) {
                        setRecipientValue(  sponsorNameFieldName, wrapError( "not found" ), true );
                    }
                }
            },
            errorHandler:function( errorMessage ) {
                window.status = errorMessage;
                setRecipientValue( sponsorNameFieldName, wrapError( "not found" ), true );
            }
        };
        SponsorService.getSponsor(sponsorCode,dwrReply);
    }
}


function updateNotificationRecipients(moduleCode) {
	var dwrReply = {
		callback:updateNotificationRecipients_Callback,
		errorHandler:function( errorMessage ) { 
			window.status = errorMessage;
		}
	};
	KcNotificationModuleRoleService.getNotificationModuleRolesString(moduleCode, dwrReply);
}

function updateNotificationRecipients_Callback(data) {
	//alert(data);
	
	var element = document.getElementById('document.newMaintainableObject.add.notificationTypeRecipients.roleName');
	
	if (element) {
		element.options.length=0;
		var option_array=data.split(",");
		var optionNum=0;	
		while (optionNum < option_array.length) {
			  if (optionNum == 0) {
			        element.options[0]=new Option("select","", true, true);
			  } else {
			        element.options[optionNum]=new Option(option_array[optionNum], option_array[optionNum]);
			  }
			  optionNum+=1;			
		}
	}
}
function loadRolodexInfoById() {
	var fullNameElement = jq('input[name="document.newMaintainableObject.rolodexId"]');
	if (jq(fullNameElement).length > 0) {
		var rolodexId = jq(fullNameElement).val();
		if (jq(fullNameElement).parent().find('div').length == 0) {
			jq(fullNameElement).parent().append('<div>&nbsp;</div>');
		}
		if (rolodexId == '') {
			jq(fullNameElement).parent().find('div').html("&nbsp;");
		}
		else
		{
			var dwrReply = {
					callback:function(data) {
					if ( data != null ) {
						jq(fullNameElement).parent().find('div').html(data.fullName);
					} else {
						jq(fullNameElement).parent().find('div').html( "not found" );
					}
					},
				errorHandler:function( errorMessage ) {
					window.status = errorMessage;
					jq(fullNameElement).parent().find('div').html(wrapError( "not found" ));
				}
			};
			RolodexService.getRolodex(rolodexId, dwrReply);
		}
	}
}

function selectAllPersonMassChangeCategory(prefix) {
	for (var i = 1; i < arguments.length; i++) {
		var item = prefix + '.' + arguments[i];
		$j('input[id=' + item + ']').attr('checked', true);
	}
}

function unselectAllPersonMassChangeCategory(prefix) {
	for (var i = 1; i < arguments.length; i++) {
		var item = prefix + '.' + arguments[i];
		$j('input[id=' + item + ']').attr('checked', false);
	}
}

var personSelectedIndex;
function showBudgetPersonSalaryDetails(flag, personIndex, budgetId, personSequenceNumber, personId, callbackFunction) {
	
	if(flag) {
     document.getElementById("paramDiv+"+personIndex).style.display = 'block';
     document.getElementById("disablingDiv").style.display='block';
	}
	else {
		document.getElementById("paramDiv+"+personIndex).style.display = 'none';
		document.getElementById("disablingDiv").style.display='none';
		
	}
	personSelectedIndex = personIndex;
	var dwrReply = {
			callback:callbackFunction,
			errorHandler:function( errorMessage ) { 
				window.status = errorMessage;
			}
		};
	BudgetService.populateBudgetPersonSalaryDetailsInPeriods(budgetId, personSequenceNumber, personId, dwrReply);
		
}

function showBudgetPersonSalaryDetails_Callback( data ) {
	var value_array = data.split(",");
	var counter=0;
	if (value_array != ""){
	while (counter < value_array.length)
	{
		var cell = document.getElementById("BudgetPersonSalaryInPeriodsCol+"+personSelectedIndex+counter);		
		for(var prop in cell.childNodes){
			 if(cell.childNodes[prop].nodeName == 'INPUT'){
				 cell.childNodes[prop].value = value_array[counter];				
			 }
		}
		
		counter+=1;

	}
	}
		
	
	
}


function submitFormToMethod(formId, methodName) {
	var hidden = document.createElement('input');
	hidden.setAttribute("id", "start");
	hidden.setAttribute("type", "hidden");
	hidden.setAttribute("name", "methodToCall." + methodName);
	
	var formIdEscaped = jq_escape(formId);
	jQuery(formIdEscaped).append(hidden);
	jQuery(formIdEscaped).submit();
}

function loadUnitFormulatedCost(unitNumber, propertyFieldName ) {
	var formulatesTypeCode = dwr.util.getValue(propertyFieldName+".formulatedTypeCode");
	var unitCostFieldName = propertyFieldName+".unitCost";
	if (formulatesTypeCode=='') {
		clearRecipients( unitCostFieldName, "" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					if ( unitCostFieldName != null && unitCostFieldName != "" ) {
						setRecipientValue( unitCostFieldName, data );
					}
				}
			},
			errorHandler:function( errorMessage ) {
			}
		};

		BudgetRatesService.getUnitFormulatedCost(unitNumber, formulatesTypeCode ,dwrReply);
	}
}

/** Gets the full url with context.  Ex: Http://127.0.0.1:8080/kc-dev */
function getUrlWithContext() {
	return document.URL.substr(0, document.URL.indexOf("/", document.URL.indexOf("/", document.URL.indexOf("//") + 2) + 1));
}

var WarningOnAddRow = (function($) {
	return {
		emptyValues: [' ', '0.00', '(new group)'],
		inputs: '.addline input, .addline select, .addline textarea',
		elementsToIgnore: ['input[name="multiSelectToReset"]', 'input[name="checkboxToReset"]', 'input[name^="document.budget.budgetCategoryType["]'],
		asterisk: $('<img class="changedNotice changedAsterisk" src="' + getUrlWithContext() + '/static/images/asterisk_orange.png"/>'),
		resetBtn: $('<img class="changedNotice changedResetBtn" src="' + getUrlWithContext() + '/static/images/tinybutton-reset1.gif"/>'),
		pageNotice: $('<div class="changedNotice changedPageNotice">Unsaved changes will be lost.</div>'),
		init: function() {
			this.checkModification = this.checkModification.bind(this);
			this.valueChanged = this.valueChanged.bind(this);
			this.rowChanged = this.rowChanged.bind(this);
			this.resetRow = this.resetRow.bind(this);
			this.panelChanged = this.panelChanged.bind(this);
			this.pageChanged = this.pageChanged.bind(this);
			this.recursePanelChanged = this.recursePanelChanged.bind(this);
			this.isEmptyValue = this.isEmptyValue.bind(this);
			//if the page reloads and there is input in the add line then mark it as such, then after monitor for changes.
			$(this.inputs).each(this.checkModification);
			$(this.inputs).change(this.valueChanged);
			$(this.inputs).keyup(this.valueChanged);
			refreshAddressBookLookup();
		},
		isEmptyValue : function(element) {
			var value = $(element).val();
			
			var elementEmptyValues = $(element).closest('div.defaultData').data('emptyvalues');
			if (elementEmptyValues != undefined) {
				for (var i = 0; i < elementEmptyValues.length; i++) {
					this.emptyValues.push(elementEmptyValues[i]);
				}
			}
			
			for (var i = 0; i < this.emptyValues.length; i++) {
				if (this.emptyValues[i] === value) {
					return true;
				}
			}
			return false;
		},
		checkModification : function(idx, element) {
			var addLine = $(element).parents('.addline').first();
			if ($(element).val() != undefined && $(element).val().length > 0 && !this.isEmptyValue(element)
					&& !this.isIgnoredElement(element)) {
				$(element).addClass('changed');
			} else {
				$(element).removeClass('changed');
			}
			this.rowChanged($(element).parents('.addline').first());
		},
		valueChanged: function(event) {
			var element = event.target;
			this.checkModification(0, element);
		},
		rowChanged: function(row) {
			if ($(row).find('input.changed, select.changed, textarea.changed').length > 0) {
				if (!$(row).is('.changedRow')) {
					$(row).addClass('changedRow');
					$(row).find('.addButton').before(this.asterisk.clone());
					$(row).find('.addButton').after(this.resetBtn.clone());
					$(row).find('.changedResetBtn').click(this.resetRow);
				}
			} else {
				$(row).removeClass('changedRow');
				$(row).find('.changedNotice').remove();
			}
			this.panelChanged($(row).parents('div[id^="tab-"]').first());
		},
		panelChanged: function(panelDiv) {
			this.recursePanelChanged(panelDiv);
			this.pageChanged();
		},
		recursePanelChanged: function(panelDiv) {
			if (typeof panelDiv !== "undefined" && panelDiv.length > 0) {
				if ($(panelDiv).find(this.inputs).filter('.changed').length > 0) {
					if (!$(panelDiv).is('.changed')) {
						$(panelDiv).addClass('changed');
						$(panelDiv).prev('table.tab').find('.tabtable1-left').append(this.asterisk.clone());
						$(panelDiv).prev('.innerTab-head').append(this.asterisk.clone());
					}
				} else {
					$(panelDiv).removeClass('changed');
					$(panelDiv).prev('table.tab').find('.changedNotice').remove();
					$(panelDiv).prev('.innerTab-head').find('.changedNotice').remove();
				}
				this.recursePanelChanged($(panelDiv).parents('div[id^="tab-"]').first());
			}
		},
		pageChanged: function() {
			if ($('#workarea').find(this.inputs).filter('.changed').length > 0) {
				if (!$('#workarea').is('.changed')) {
					$('#workarea').addClass('changed');
					$('div.msg-excol div.left-errmsg').append(this.pageNotice.clone().prepend(this.asterisk.clone()));
				} 
			} else {
					$('#workarea').removeClass('changed');
					$('div.msg-excol div.left-errmsg').find('.changedNotice').remove();					
			}
		},
		resetRow: function(event) {
			var row = $(event.target).parents('.addline').first();
			$(row).find('.changed').val('');
			$(row).find('.changed option:selected').removeAttr('selected');
			$(row).find('.changed').trigger('change');
			$(row).find('.changedClearOnReset').html('');
		},
		isIgnoredElement: function(element) {
			var matchingDiv = $(element).closest('div.ignoreMeFromWarningOnAddRow');
			
			if(matchingDiv.length > 0) {
				return true;
			}else {
				for (idx in this.elementsToIgnore) {
					if ($(element).is(this.elementsToIgnore[idx])) {
						return true;
					}
				}
			}
			return false;
		}
		
	}
}(jQuery));
jQuery(document).ready(function() {WarningOnAddRow.init();});
