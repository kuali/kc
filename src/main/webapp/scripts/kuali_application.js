/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



//Fix rice ids for use with jquery
//Takes an id and replaces the . with \\. and adds #
function jq(myid) { 
  return '#' + myid.replace(/(:|\.)/g,'\\$1');
}


function updateSourceNameEditable(fundingSourceTypeCodeFieldName, fundingSourceNumberFieldName, fundingSourceNameFieldName, fundingSourceTitleFieldName) {
	var fundingSourceTypeCode = DWRUtil.getValue( fundingSourceTypeCodeFieldName );
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
		ProtocolFundingSourceService.isEditable(fundingSourceTypeCode, dwrReply);
		loadFundingSourceNameTitle(fundingSourceTypeCodeFieldName, fundingSourceNumberFieldName, fundingSourceNameFieldName, fundingSourceTitleFieldName);
	}
}

/*
 * Load the Funding Source Name field based on the source and type passed in.
 */
function loadFundingSourceNameTitle(fundingSourceTypeCodeField, fundingSourceNumberFieldName, fundingSourceNameFieldName, fundingSourceTitleFieldName ) {
	var fundingSourceTypeCode = DWRUtil.getValue( fundingSourceTypeCodeField );
	var fundingSource = "";
	var fundingSourceNumber = DWRUtil.getValue ( fundingSourceNumberFieldName );
	var fundingSourceName = DWRUtil.getValue( fundingSourceNameFieldName );
	var fundingSourceTitle = DWRUtil.getValue( fundingSourceTitleFieldName );
	var docFormKey = DWRUtil.getValue( "docFormKey" );

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
		ProtocolFundingSourceService.isLookupable(fundingSourceTypeCode, dwrReply);
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
		ProtocolFundingSourceService.updateProtocolFundingSource(fundingSourceTypeCode+":"+docFormKey, fundingSourceNumber, fundingSourceName, dwrReply);
		
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

function selectAllGGForms(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	if (e.name == 'document.developmentProposalList[0].s2sOpportunity.s2sOppForms[' + j + '].selectToPrint') {
 		    if(e.disabled == false){
 		    	e.checked = true;
 		    }
	  		j++; 
	  	}
	  }
	}
}

function selectAllSponsorForms(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	if (e.name == 'sponsorFormTemplates[' + j + '].selectToPrint') {
 		    if(e.disabled == false){
 		    	e.checked = true;
 		    }
	  		j++; 
	  	}
	  }
	}
}

function unselectAllSponsorForms(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	if (e.name == 'sponsorFormTemplates[' + j + '].selectToPrint') {
 		    if(e.disabled == false){
 		    	e.checked = false;
 		    }
	  		j++; 
	  	}
	  }
	}
}

/**
 * This function walks through all elements, using the variable e.
 * It looks at the "initiallyIncluded" hidden variable and the checkboxes
 * in the "Include" table column to figure out which "Select to Print"
 * check boxes should be checked and which ones shouldn't.
 * 
 * For each table row, the e2 variable is set in the "Include" column,
 * and read in the "Select to Print" column. If e2 is true, "Select to Print"
 * is checked.
 * At the end of the row, e2 is reset to false.
 */
function selectAllIncludedGGForms(document) {
    var j = 0;
    var e2 = false;
    for (var i = 0; i < document.KualiForm.elements.length; i++) {
        var e = document.KualiForm.elements[i];
        /* the initiallyIncluded variable is set to true when the page is built. If it is true, the "Include" flag is set. */
        if (e.name == 'document.developmentProposalList[0].s2sOpportunity.s2sOppForms[' + j + '].initiallyIncluded') {
            if (e.value == 'true') {
                e2 = true;
            }
        }
        if(e.type == 'checkbox') {
            if (e.name == 'document.developmentProposalList[0].s2sOpportunity.s2sOppForms[' + j + '].include') {
                e2 = e.checked;
            }
            if (e.name == 'document.developmentProposalList[0].s2sOpportunity.s2sOppForms[' + j + '].selectToPrint') {
                if(e.disabled == false && e2 == true){
                    e.checked = true;
                }
                e2 = false;
                j++; 
            }
        }
    }
}

function unselectAllGGForms(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	if (e.name == 'document.developmentProposalList[0].s2sOpportunity.s2sOppForms[' + j + '].selectToPrint') {
 		    if(e.disabled == false){
 		    	e.checked = false;
 		    }
	  		j++; 
	  	}
	  }
	}
}
function selectAllKeywords(document, keywordsArray) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	var name = keywordsArray + '[' + j + '].selectKeyword';
	  	if (e.name == name) {
 		    e.checked = true;
	  		j++; 
	  	}
	  }
	}
}

function viewCommentPop(fieldName,label){
  url=window.location.href
  pathname=window.location.pathname
  idx1=url.indexOf(pathname);
  idx2=url.indexOf("/",idx1+1);
  extractUrl=url.substr(0,idx2)
  window.open(extractUrl+"/viewComment.do?&commentFieldName="+fieldName+"&commentFieldLabel="+label, "_blank", "width=640, height=600, scrollbars=yes");
}

function setComment() {
  passData=document.location.search.substring(1);
  var idx=passData.indexOf("&commentFieldName=")
  var idx2=passData.indexOf("&commentFieldLabel=")
  fieldName=passData.substring(idx+18,idx2)
  text = window.opener.document.getElementById(fieldName).value;
  document.getElementById(fieldName).value = text;
  

}

// dwr functions
// this is a sample function for sponsor code
function loadSponsorCode( sponsorCodeFieldName) {
	var sponsorCode = DWRUtil.getValue( sponsorCodeFieldName );

	//if (sponsorCode == "") {
	//	clearRecipients( sponsorCodeFieldName, "" );
	//} else {
		var dwrReply = {
			callback:function(data) {
			if ( data != null ) {
				if ( sponsorCodeFieldName != null && sponsorCodeFieldName != "" ) {
					setRecipientValue( sponsorCodeFieldName, data );
				}
			} else {
				if ( sponsorCodeFieldName != null && sponsorCodeFieldName != "" ) {
					setRecipientValue( sponsorCodeFieldName, "" );
				}
			} },
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				if ( sponsorCodeFieldName != null && sponsorCodeFieldName != "" ) {
					setRecipientValue( sponsorCodeFieldName, "" );
				}
			}
		};

		SponsorService.getSponsorCode(sponsorCode,dwrReply);

	//}
}

/*
 * Load the Sponsor Name field based on the Sponsor Code passed in.
 */
function loadSponsorName(sponsorCodeFieldName, sponsorNameFieldName ) {
	var sponsorCode = DWRUtil.getValue( sponsorCodeFieldName );

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
 

function checkGrantsGovStatusOnSponsorChange(proposalNumber, sponsorCodeFieldName) {
	var sponsorCode = DWRUtil.getValue( sponsorCodeFieldName );
	var dwrReply = {
			callback:function(data) {
				enableGrantsGov(data);
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				enableGrantsGov(false);
			}
	};
	ProposalDevelopmentService.isGrantsGovEnabledOnSponsorChange(proposalNumber, sponsorCode, dwrReply);
}
function enableGrantsGov(enable) {
	$j('input[name$="navigateTo.grantsGov"]').attr("disabled", !enable);
}

function showS2SAppSubmissionStatusDetails(proposalNumber,trackingId) {
	//alert(proposalNumber);
	var docFormKey = DWRUtil.getValue( "docFormKey" );
	var dwrReply = {
			callback:function(data) {
				//alert(data);
				displayStatusDetails(data);
			},
			errorHandler:function( errorMessage ) {
				//alert("da da da");
				window.status = errorMessage;
			},
			cache : false,
			async : false,
			timeout : 1000
	};
	//alert(trackingId);
	S2SService.getStatusDetails( trackingId, proposalNumber + ":" + docFormKey, dwrReply);
}

function displayStatusDetails(data){
	//alert(data);
	document.getElementById('s2s_status_detail').innerHTML=data;
	changeObjectVisibility("s2s_status_popup","block");
}
function hideStatusDetails(){
	//alert(data);
	changeObjectVisibility("s2s_status_popup","none");
}
/*
 * Load the Budget Category Code based on Object Code(Cost Element)
 */ 
function loadBudgetCategoryCode(objectCode,budgetCategoryCode){
	var objectCodeValue = DWRUtil.getValue( objectCode );

	if (objectCodeValue=='') {
		clearRecipients( budgetCategoryCode, "" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					if ( objectCode != null && objectCode != "" ) {
						setRecipientValue( budgetCategoryCode, data );
					}
				} else {
					if ( objectCode != null && objectCode != "" ) {
						setRecipientValue(  budgetCategoryCode, wrapError( "not found" ), true );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				setRecipientValue( budgetCategoryCode, wrapError( "not found" ), true );
			}
		};
		ObjectCodeToBudgetCategoryCodeService.getBudgetCategoryCodeForCostElment(objectCodeValue,dwrReply);
	}
}

/*
 * Load Start and End Dates based on the Fiscal Year
 */ 
function loadStartAndEndDates(fiscalYear,startDate,endDate){
	var fiscalYearValue = DWRUtil.getValue( fiscalYear );

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
function loadUnitName(unitNumberFieldName) {
	var unitNumber = DWRUtil.getValue( unitNumberFieldName );
    var elPrefix = findElPrefix( unitNumberFieldName );
	var unitNameFieldName = elPrefix + ".unitName";
	if (unitNumber=='') {
		clearRecipients( unitNameFieldName, "(select)" );
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
 * Load the Unit Name field based on the Unit Number passed in.
 */
function loadUnitNameTo(unitNumberFieldName, unitNameFieldName) {
	var unitNumber = DWRUtil.getValue( unitNumberFieldName );
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
	var jobCode = DWRUtil.getValue( jobCodeFieldName );

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

function loadSponsorCode_1( sponsorCodeFieldName) {
    // alternative, delete later
	var sponsorCode = DWRUtil.getValue( sponsorCodeFieldName );
	//alert(sponsorCodeFieldName+" "+sponsorCode)
	//SponsorService.getSponsorCode(sponsorCode,function(data) {
    //DWRUtil.setValue(sponsorCodeFieldName, data);});
	SponsorService.getSponsorCode(sponsorCode,loadinfo);

}

function loadinfo(data) {
  //alert("loadinfo "+data)
  DWRUtil.setValue("document.sponsorCode", data);
}
var propAttRightWindow;
function proposalAttachmentRightsPop(lineNumber,docFormKey, sessionDocument){
  var documentWebScope
  if (sessionDocument == "true") {
      documentWebScope="session"
  }

  if (propAttRightWindow && propAttRightWindow.open && !propAttRightWindow.closed){
  	propAttRightWindow.focus();
  }else{
    propAttRightWindow = window.open(extractUrlBase()+"/proposalDevelopmentAbstractsAttachments.do?methodToCall=getProposalAttachmentRights&line="+lineNumber+"&docFormKey="+docFormKey+"&documentWebScope="+documentWebScope, "mywindow", "width=800, height=300, scrollbars=yes");
  }
}  

var propInstAttRightWindow;
function proposalInstituteAttachmentRightsPop(lineNumber,docFormKey, sessionDocument){
  var documentWebScope
  if (sessionDocument == "true") {
      documentWebScope="session"
  }
  if (propInstAttRightWindow && propInstAttRightWindow.open && !propInstAttRightWindow.closed){
  	propInstAttRightWindow.focus();
  }else{
    propInstAttRightWindow = window.open(extractUrlBase()+"/proposalDevelopmentAbstractsAttachments.do?methodToCall=getInstituteAttachmentRights&line="+lineNumber+"&docFormKey="+docFormKey+"&documentWebScope="+documentWebScope, "instAttWindow", "width=800, height=300, scrollbars=yes");
    if (window.focus) {
         propInstAttRightWindow.focus()
    }
  }
}
var fileBrowseWindow;
function openNewFileBrowseWindow(filePropertyName,fileFieldLabel,htmlFormAction,methodToCall,methodToSave,lineNumber){
  if (fileBrowseWindow && fileBrowseWindow.open && !fileBrowseWindow.closed){
  	fileBrowseWindow.focus();
  }else{
    fileBrowseWindow = window.open(extractUrlBase()+"/proposalDevelopmentAbstractsAttachments.do?methodToCall="+methodToCall+"&methodToSave="+methodToSave+"&line="+lineNumber+"&filePropertyName="+filePropertyName+"&fileFieldLabel="+fileFieldLabel, "mywindow", "width=800, height=300, scrollbars=yes");
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
//function openNewWindow(action,methodToCall,lineNumber){
//  window.open(extractUrlBase()+"/"+action+".do?methodToCall="+methodToCall+"&line="+lineNumber);
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
var propRoleRightsWindow = null;

function proposalRoleRightsPop(docFormKey, sessionDocument) {

	var documentWebScope = "";
	if (sessionDocument == true) {
		documentWebScope = "session";
	}

	if (propRoleRightsWindow != null) {
	    propRoleRightsWindow.close();
	} 

    propRoleRightsWindow = window.open(extractUrlBase() +
    	                               "/proposalDevelopmentPermissions.do?methodToCall=getPermissionsRoleRights" +
    	                               "&docFormKey=" + docFormKey + 
    	                               "&documentWebScope=" + documentWebScope, 
    	                               "permissionsRoleRights", 
    	                               "width=800, height=750, scrollbars=yes, resizable=yes");
    
}

/**
 * Display the Edit Roles popup window.  This window allows users
 * to change the roles for a user within a proposal.
 */
var propEditRolesWindow;

function editRolesPop(lineNumber, docFormKey, sessionDocument) {

    var documentWebScope = "";
    if (sessionDocument == "true") {
        documentWebScope="session"
    }
    
	if (propEditRolesWindow != null) {
	    propEditRolesWindow.close();
	} 

    propEditRolesWindow = window.open(extractUrlBase() +
    	                               "/proposalDevelopmentPermissions.do?methodToCall=editRoles" +
    	                               "&line=" + lineNumber +
    	                               "&docFormKey=" + docFormKey + 
    	                               "&documentWebScope=" + documentWebScope, 
    	                               "permissionsEditRoles", 
    	                               "width=800, height=350, scrollbars=yes, resizable=yes");
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
		var username = DWRUtil.getValue( usernameFieldName );
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
		var username = DWRUtil.getValue( usernameFieldName );
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
	    var rolodexId = DWRUtil.getValue( rolodexFieldName );
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
						fullNameElement.innerHTML = data.organization;
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
		var rolodexId = DWRUtil.getValue( rolodexFieldName );
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
//		if (lookupClass == "org.kuali.kra.bo.ArgValueLookup") {
//			ArgValueLookupService.getArgumentNames( dwrReply );
//		} else {
		// argvaluelookup is handled in customattributeservice
		    CustomAttributeService.getLookupReturnsForAjaxCall( lookupClass, dwrReply );
//		}
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

function updateOtherFields(editableColumnNameField, proposalNumberFieldId, callbackFunction ) {
	var proposalNumber = DWRUtil.getValue( proposalNumberFieldId );

	fieldPrefix = findElPrefix( editableColumnNameField.name );
	oldDisplayValue =  fieldPrefix + ".oldDisplayValue" ;
	displayValue =  fieldPrefix + ".displayValue" ;
	dataType =  fieldPrefix + ".editableColumn.dataType" ;
	hasLookup =  fieldPrefix + ".editableColumn.hasLookup" ;
	lookupArgument =  fieldPrefix + ".editableColumn.lookupClass" ;
	lookupReturn = fieldPrefix + ".editableColumn.lookupReturn" ;
	lookupPkReturn = fieldPrefix + ".editableColumn.lookupPkReturn" ;
	changedValue = fieldPrefix + ".changedValue" ;
	comments = fieldPrefix + ".comments" ;

	var editableColumnNameRef = fieldPrefix + ".editableColumn.columnName" ;
	document.getElementById(editableColumnNameRef).value = editableColumnNameField.value; 
	
	var editableColumnName = editableColumnNameField.value;
	if (editableColumnName != "") {
		var docFormKey = DWRUtil.getValue( "docFormKey" );
		var dwrReply = {
			callback:callbackFunction,
			errorHandler:function( errorMessage ) { 
				window.status = errorMessage;
			}
		};
		ProposalDevelopmentService.populateProposalEditableFieldMetaDataForAjaxCall(proposalNumber + ":" + docFormKey, editableColumnName, dwrReply );
	} else {
			document.getElementById(oldDisplayValue).value = ""; 
			document.getElementById(displayValue).value = ""; 
			document.getElementById(dataType).value = "";
			document.getElementById(hasLookup).value = "";
			document.getElementById(lookupArgument).value = "";
			document.getElementById(lookupReturn).value = "";
			document.getElementById(lookupPkReturn).value = "";
			document.getElementById(changedValue).value = "";
			document.getElementById(changedValue).style.borderColor = "";
			document.getElementById(comments).value = "";
	}
}

function updateOtherFields_Callback( data ) {
	var value_array = data.split(",");
	var counter=0;
	
	//reset
	document.getElementById(oldDisplayValue).value = ""; 
	document.getElementById(displayValue).value = ""; 
	document.getElementById(dataType).value = "";
	document.getElementById(hasLookup).value = "";
	document.getElementById(lookupArgument).value = "";
	document.getElementById(lookupReturn).value = "";
	document.getElementById(lookupPkReturn).value = "";
	document.getElementById(changedValue).value = "";
	document.getElementById(changedValue).style.borderColor = "";
	document.getElementById(comments).value = "";
	
	while (counter < value_array.length)
	{
		if(counter == 0) {
			document.getElementById(lookupPkReturn).value = value_array[counter];
		}

		if(counter == 1) {
			document.getElementById(lookupReturn).value = value_array[counter];
		}
		
		if(counter == 2) {
			document.getElementById(oldDisplayValue).value = value_array[counter];
			document.getElementById(displayValue).value = value_array[counter];
			document.getElementById(oldDisplayValue).disabled = true;
			document.getElementById(displayValue).disabled = true;
		}
		
		if(counter == 3) {
			document.getElementById(dataType).value = value_array[counter];
			
		}
		
		if(counter == 4) {
			document.getElementById(hasLookup).value = value_array[counter];
		}
		
		if(counter == 5) {
			document.getElementById(lookupArgument).value = value_array[counter];
		}
		
		counter+=1;
	}
	
	var displayValueField = document.getElementById(displayValue).name;
	var prefix = findElPrefix( document.getElementById(displayValue).name );
	var imageUrl = document.getElementById("imageUrl").value;
	var tabIndex = document.getElementById("tabIndex").value;
	var lookupClass = document.getElementById(lookupArgument).value;
	var lookupPkReturnValue = document.getElementById(lookupPkReturn).value;
	var changedValueFieldName = document.getElementById(changedValue).name;
	var myDiv = document.getElementById('changedValueExtraBody');
	var dataTypeValue = document.getElementById(dataType).value;
	var lookupReturnValue = document.getElementById(lookupReturn).value;
	dynamicDivUpdate(lookupClass, lookupPkReturnValue, lookupReturnValue, changedValueFieldName, displayValueField, dataTypeValue);
}

function dynamicDivUpdate(lookupClass, lookupPkReturnValue, lookupReturnValue, changedValueFieldName, displayValueField, dataTypeValue) {
   	var imageUrl = document.getElementById("imageUrl").value;
	var tabIndex = document.getElementById("tabIndex").value;
    var myDiv = document.getElementById('changedValueExtraBody');
	var innerDivContent = "";
	if(lookupClass != "" && lookupPkReturnValue != "" && changedValueFieldName != "") {
		innerDivContent = "<input type='image' tabindex='' ";
		innerDivContent = innerDivContent + " name='methodToCall.performLookup.(!!" + lookupClass + "!!).(((" + lookupPkReturnValue + ":" + changedValueFieldName + "," + lookupReturnValue + ":" + displayValueField + "))).((``)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~)).anchorProposalDataOverride' ";
		innerDivContent = innerDivContent + " src='" + imageUrl + "searchicon.gif' border='0' class='tinybutton' valign='middle' alt='Search' title='Search' /> ";
	} 
	
	if(dataTypeValue != "" && (dataTypeValue == 'DATE' || dataTypeValue == 'date')) {
		innerDivContent = innerDivContent + "<img src=\"" + imageUrl + "cal.gif\" id=\"newProposalChangedData.changedValue_datepicker\" style=\"cursor: pointer;\"";
		innerDivContent = innerDivContent + " title=\"Date selector\" alt=\"Date selector\" onmouseover=\"this.style.backgroundColor='red';\" onmouseout=\"this.style.backgroundColor='transparent';\" />";
	}
	
	myDiv.innerHTML = innerDivContent;
	
	if(dataTypeValue != "" && (dataTypeValue == 'DATE' || dataTypeValue == 'date')) {
		Calendar.setup(
			{
			  inputField : "newProposalChangedData.changedValue", // ID of the input field
			  ifFormat : "%m/%d/%Y", // the date format
			  button : "newProposalChangedData.changedValue_datepicker" // ID of the button
		    }
		);
	}
}

function enableBudgetStatus(document, index) {
	var newFinalIndicator;
	var newFinalStatus;
	var newFinalStatusHidden;
	var j = 0;
	var cancelled = false;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	var status = document.KualiForm.elements[i - 1];
	  	var statusHidden = document.KualiForm.elements[i - 2];
	  	if (e.checked && j != index) {
	  		if (confirm("You are changing the final version.  Are you sure?")) {
	  			e.checked = false;
	  			statusHidden.value = status.value;
	  			statusHidden.disabled = false;
	  			status.disabled = true;
	  		} else {
	  			cancelled = true;	
	  		}
	  	} else if (e.checked && j == index) {
	  		newFinalIndicator = e;
	  		newFinalStatus = status;
	  		newFinalStatusHidden = statusHidden;
	  	} else {
	  		statusHidden.value = status.value;
	  		statusHidden.disabled = false;
	  		status.disabled = true;
	  	}
	  	j++;
	  }
	}
	if (!cancelled && newFinalStatus != null) {
		newFinalStatus.disabled = false;
		newFinalStatusHidden.disabled = true;
	}
	if (cancelled && newFinalIndicator != null) {
		newFinalIndicator.checked = false;
	}
}

function getIndex(what) {
    for (var i = 0; i < document.KualiForm.elements.length; i++) {
        if (what == document.KualiForm.elements[i]) {
            return i;
          }
       }
       return -1;
       alert("leaveIndex");
}


function setupBudgetStatuses(document) {
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	var status = document.KualiForm.elements[i - 1];
	  	var statusHidden = document.KualiForm.elements[i - 2];
	  	if (e.checked) {
	  		statusHidden.disabled = true;
	  		status.disabled = false;
	  	} else {
	  		statusHidden.disabled = false;
	  		status.disabled = true;
	  	}
	  }
	}
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
					

function toggleFinalCheckboxes(document) {
	var completed = false;
	var toggledElement;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'select-one' && e.value == '1') {
	  	completed = true;
	  	toggledElement = e;
	  }
	}
	if (completed) {
		for (var i = 0; i < document.KualiForm.elements.length; i++) {
			var el = document.KualiForm.elements[i];
			if (el.type == 'checkbox') {
				var elStatus = document.KualiForm.elements[i - 1];
				if (elStatus != toggledElement) {
					el.disabled = true;
				} else {
					var elHidden = document.KualiForm.elements[i + 2];
					elHidden.value = true;
					elHidden.disabled = false;
					el.disabled = true;
				}
			}
		}
	} else {
		for (var i = 0; i < document.KualiForm.elements.length; i++) {
			var el = document.KualiForm.elements[i];
			var elHidden = document.KualiForm.elements[i + 2];
			if (el.type == 'checkbox') {
				elHidden.disabled = true;
				el.disabled = false;
			}
		}
	}
	
}

function toggleFinalCheckboxesAndDisable(document) {
	var completed = false;
	var toggledElement;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'select-one' && e.value == '1') {
	  	completed = true;
	  	toggledElement = e;
	  }
	}
	if (completed) {
		for (var i = 0; i < document.KualiForm.elements.length; i++) {
			var el = document.KualiForm.elements[i];
			if (el.type == 'checkbox') {
				var elStatus = document.KualiForm.elements[i - 1];
				if (elStatus != toggledElement) {
					el.disabled = true;
				} else {
					var elHidden = document.KualiForm.elements[i + 2];
					elHidden.value = true;
					elHidden.disabled = true;
					el.disabled = true;
				}
			}
		}
	} else {
		for (var i = 0; i < document.KualiForm.elements.length; i++) {
			var el = document.KualiForm.elements[i];
			var elHidden = document.KualiForm.elements[i + 2];
			if (el.type == 'checkbox') {
				elHidden.disabled = true;
				el.disabled = true;
			}
		}
	}
	
}

function setupVersionsPage(document) {
	var completed = false;
	var toggledElement;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'select-one' && e.value == '1') {
	  	completed = true;
	  	toggledElement = e;
	  }
	}
	if (completed) {
		for (var i = 0; i < document.KualiForm.elements.length; i++) {
			var el = document.KualiForm.elements[i];
			if (el.type == 'checkbox') {
				var elStatus = document.KualiForm.elements[i - 1];
				if (elStatus != toggledElement) {
					el.disabled = true;
					elStatus.disabled = true;
					elStatusHidden.disabled = false;
				} else {
					var elHidden = document.KualiForm.elements[i + 2];
					elHidden.value = true;
					elHidden.disabled = false;
					el.disabled = true;
				}
			}
		}
	} else {
		for (var i = 0; i < document.KualiForm.elements.length; i++) {
			var el = document.KualiForm.elements[i];
			var elHidden = document.KualiForm.elements[i + 2];
			if (el.type == 'checkbox') {
				elHidden.disabled = true;
				el.disabled = false;
				var elStatus = document.KualiForm.elements[i - 1];
				elStatus.disabled
			}
		}
	}
}

function selectAllBudgetForms(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	    //alert(e.name)
	  	if (e.name == 'selectedBudgetPrintFormId') {
 		    if(e.disabled == false){
 		    	e.checked = true;
 		    }
	  		j++; 
	  	}
	  }
	}
}

function unselectAllBudgetForms(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	if (e.name == 'selectedBudgetPrintFormId') {
 		    if(e.disabled == false){
 		    	e.checked = false;
 		    }
	  		j++; 
	  	}
	  }
	}
}
//CustomAttributeService.js - put it in kc-config.xml
//function CustomAttributeService() { }
// CustomAttributeService._path = '../dwr'; 
// CustomAttributeService.getLookupReturnsForAjaxCall = function(p0, callback) { DWREngine._execute(CustomAttributeService._path, 'CustomAttributeService', 'getLookupReturnsForAjaxCall', p0, callback); } 

/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 

function selectAllResearchAreas(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	var name = 'document.protocol.protocolResearchAreas[' + j + '].selectResearchArea';
	  	if (e.name == name) {
 		    e.checked = true;
	  		j++; 
	  	}
	  }
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
	var sequenceNumber = $(versionId).val();
	var docFormKey = $('input[name="docFormKey"]').val();
	var dwrReply = {
		callback:function(data) {
			if ( data != null ) {
				//clear all current options
			    $(transactionId).html("");
			    for (key in data) {
					$(transactionId).append("<option value='"+key+"'>"+data[key]+"</option>");
				}
			}
		},
		errorHandler:function( errorMessage ) {
			window.status = errorMessage;
			//clear all current options
		    $(transactionId).html("");		
		}
	};
	AwardTransactionLookupService.getApplicableTransactionIds(awardNumber+":"+docFormKey, sequenceNumber, dwrReply);
}


function setAllItemsIn(id, value) {
	$("#" + id + " INPUT[type='checkbox']").attr('checked', value);
}


//End Award module

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
 * Load the Organization Name field based on the Organization Code passed in.
 */
function loadOrganizationName(organizationIdFieldName, organizationNameFieldName ) {
	
	var organizationId = DWRUtil.getValue( organizationIdFieldName );
	if (organizationId=='') {
		clearRecipients( organizationNameFieldName, "" );
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null ) {
					if ( organizationNameFieldName != null && organizationNameFieldName != "" ) {
						setRecipientValue( organizationNameFieldName, data );
					}
				} else {
					if ( organizationNameFieldName != null && organizationNameFieldName != "" ) {
						setRecipientValue(  organizationNameFieldName, wrapError( "not found" ), true );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				setRecipientValue( organizationNameFieldName, wrapError( "not found" ), true );
			}
		};
		OrganizationService.getOrganizationName(organizationId,dwrReply);
	}
}

function loadAwardBasisOfPaymentCodes( awardTypeCode, basisOfPaymentCodeFieldName ) {

	if ( awardTypeCode=='' || awardTypeCode== "") {
		//clearMethodOfPaymentCodes( methodOfPaymentCodeFieldName, "" );
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
				} else {
					if ( basisOfPaymentCodeFieldName != null && basisOfPaymentCodeFieldName != "" ) {
						//setRecipientValue(  frequencyCodeFieldName, wrapError( "not found" ), true );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				//setRecipientValue( frequencyCodeFieldName, wrapError( "not found" ), true );
			}
		};
		AwardPaymentAndInvoicesService.getEncodedValidAwardBasisPaymentsByAwardTypeCode( awardTypeCode, dwrReply )
	}
}


function loadAwardMethodOfPaymentCodes( basisOfPaymentCodeFieldName, methodOfPaymentCodeFieldName) {    
    var basisOfPaymentCode = DWRUtil.getValue( basisOfPaymentCodeFieldName );
	if ( basisOfPaymentCode=='' || basisOfPaymentCode== "") {
		//clearMethodOfPaymentCodes( methodOfPaymentCodeFieldName, "" );
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
				} else {
					if ( basisOfPaymentCodeFieldName != null && basisOfPaymentCodeFieldName != "" ) {
						//setRecipientValue(  frequencyCodeFieldName, wrapError( "not found" ), true );
					}
				}
			},
			errorHandler:function( errorMessage ) {
				window.status = errorMessage;
				//setRecipientValue( frequencyCodeFieldName, wrapError( "not found" ), true );
			}
		};
		AwardPaymentAndInvoicesService.getEncodedValidBasisMethodPaymentsByBasisCode( basisOfPaymentCode, dwrReply )
	}

}

function loadFrequencyCode(reportClassCode, reportCodeFieldName,frequencyCodeFieldName) {    
    var reportCode = DWRUtil.getValue( reportCodeFieldName );
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
    var frequencyCode = DWRUtil.getValue( frequencyCodeFieldName );
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
function loadExpeditedDates(approveDateElementId,expiredDateElementId){	
	
	var dateValue = document.getElementById(approveDateElementId).value;
	
	if(dateValue!= null){		
		if(checkdate(dateValue)){		
			var myDate = new Date(dateValue);		
			myDate.setDate(myDate.getDate() - 1);
			myDate.setYear((myDate.getFullYear() + 1));
				var MM = myDate.getMonth()+1;
				var DD = myDate.getDate();
				var YY = myDate.getFullYear();
				  if(MM<10) MM="0"+MM;
				  if(DD<10) DD="0"+DD;
				  //alert(MM+"/"+DD+"/"+YY);				
				  var nextYearDate = MM+"/"+DD+"/"+YY;			
			//alert('updated my date:'+ myDate );								
			document.getElementById(expiredDateElementId).value=nextYearDate;
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
	var committeeId = DWRUtil.getValue(committeeElementId);
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

var protocolCheckListItemDescriptionWindow = null;

/*
 * Display a description for a Check List item in a popup window.
 */
function protocolCheckListItemPop(methodName, lineNum, docFormKey, sessionDocument) {

	var documentWebScope = "";
	if (sessionDocument == true) {
		documentWebScope = "session";
	}

	if (protocolCheckListItemDescriptionWindow != null) {
	    protocolCheckListItemDescriptionWindow.close();
	} 

    protocolCheckListItemDescriptionWindow = window.open(extractUrlBase() +
    	                               "/protocolProtocolActions.do?methodToCall=" + methodName +
    	                               "&docFormKey=" + docFormKey + 
    	                               "&documentWebScope=" + documentWebScope +
    	                               "&line=" + lineNum,
    	                               "CheckListItem", 
    	                               "width=500, height=350, scrollbars=yes, resizable=yes");   
}

/*
 * The Expedited Review and Exempt Studies CheckList are embedded within the
 * web page but are initially invisible.  Based upon what is selected, either
 * make one of the CheckList visible or both invisible.
 */
function updateCheckList(protocolReviewTypeCodeElementId) {
	var protocolReviewTypeCode = DWRUtil.getValue(protocolReviewTypeCodeElementId);
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

function protocolFundingSourcePop(name, docFormKey, sessionDocument, line, currentTabIndex) {

	var documentWebScope = "";
	if (sessionDocument == true) {
		documentWebScope = "session";
	}

	if (protocolFundingSourceWindow != null) {
		protocolFundingSourceWindow.close();
	} 

	protocolFundingSourceWindow = window.open(extractUrlBase() +  
			"/protocolProtocol.do?methodToCall=viewProtocolFundingSource&methodToCallAttribute=methodToCall.viewProtocolFundingSource.line="+line +".anchor"+currentTabIndex
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
	
    var committeeId = DWRUtil.getValue('actionHelper.protocolSubmitAction.committeeId');
    var scheduleId = DWRUtil.getValue('actionHelper.protocolSubmitAction.scheduleId');
	var docFormKey = DWRUtil.getValue( "docFormKey" );
    
    if (scheduleId == "select") {
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
		ProtocolActionAjaxService.getReviewers(protocolId, committeeId, scheduleId,docFormKey, dwrReplyReviewers);
	}
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
	var numReviewers = Math.floor(reviewersArr.length / 3);
	var numRows = Math.floor((numReviewers+1) / 2);
	var reviewersTableLeft = document.getElementById("reviewersTableLeft");
	var reviewersTableRight = document.getElementById("reviewersTableRight");
	setReviewers(reviewersArr, 0, 3*numRows, reviewerTypes, reviewersTableLeft);
	setReviewers(reviewersArr, 3*numRows, 3*numReviewers, reviewerTypes, reviewersTableRight);
	//finally set the number of reviewers for proper trucation
	document.getElementById("numberOfReviewers").value = numReviewers;
}

/*
 * Populates the inner HTML of a table, putting a table cell for each name stored in reviewersArr.
 * Only every other index of reviewersArr is used, starting at beginIndex+1.
 */
function setReviewers(reviewers, beginIndex, endIndex, reviewerTypes, htmlElement) {
	
	removeAllChildren(htmlElement);
				
    var tbody = document.createElement('tbody');
	for (var i = beginIndex; i < endIndex; i += 3) {
		reviewerIndex = i/3;
		
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

//Budget Personnel UI-1.1
var personnelRatesWindow;
function personnelRatesPopup(budgetPeriod, lineNumber, rateClassCode, rateTypeCode, docFormKey, sessionDocument){
var documentWebScope
  if (sessionDocument == "true") {
      documentWebScope="session";
  }
  if (personnelRatesWindow && personnelRatesWindow.open && !personnelRatesWindow.closed){
  	personnelRatesWindow.focus();
  }else{
    personnelRatesWindow = window.open(extractUrlBase()+"/budgetPersonnel.do?methodToCall=personnelRates&budgetPeriod="+budgetPeriod+"&line="+lineNumber+"&rateClassCode="+rateClassCode+"&rateTypeCode="+rateTypeCode+"&docFormKey="+docFormKey+"&documentWebScope="+documentWebScope, "personnelRatesWindow", "width=800, height=300, scrollbars=yes");
    if (window.focus) {
         personnelRatesWindow.focus();
    }
  }
}

var personnelRateCostSharingWindow;
function personnelRateCostSharingPopup(fieldNameInd, budgetPeriod, lineNumber, rateClassCode, rateTypeCode, docFormKey, sessionDocument){
var documentWebScope
  if (sessionDocument == "true") {
      documentWebScope="session";
  }
  if (personnelRateCostSharingWindow && personnelRateCostSharingWindow.open && !personnelRateCostSharingWindow.closed){
  	personnelRateCostSharingWindow.focus();
  }else{
    personnelRateCostSharingWindow = window.open(extractUrlBase()+"/budgetPersonnel.do?methodToCall=personnelRates&fieldName="+fieldNameInd+"&budgetPeriod="+budgetPeriod+"&line="+lineNumber+"&rateClassCode="+rateClassCode+"&rateTypeCode="+rateTypeCode+"&docFormKey="+docFormKey+"&documentWebScope="+documentWebScope, "personnelRateCostSharingWindow", "width=800, height=300, scrollbars=yes");
    if (window.focus) {
         personnelRateCostSharingWindow.focus();
    }
  }
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
    personnelDetailsWindow = window.open(extractUrlBase()+"/budgetPersonnel.do?methodToCall=personnelDetails&budgetPeriod="+budgetPeriod+"&line="+lineNumber+"&personNumber="+personNumber+"&docFormKey="+docFormKey+"&documentWebScope="+documentWebScope, "personnelDetailsWindow", "width=650, height=400, scrollbars=yes");
    if (window.focus) {
         personnelDetailsWindow.focus();
    }
  }
}

var costElementFieldName;

 function updateCostElement(budgetId, costElement, personSequenceNumberField, budgetCategoryTypeCode, callbackFunction ) {
	var personSequenceNumber = personSequenceNumberField.value;
	var docFormKey = DWRUtil.getValue( "docFormKey" );
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
	document.forms[0].submit();
}

function nextPeriodSet() {
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
	// TODO : not sure why dwrutil.getvalue is not working
	//var protocolContingencyCode = DWRUtil.getValue( protocolContingencyCodeFieldName );
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
 * Auto-fill the District Number field of a congressional district when the user chooses a "state" that
 * allows only one district number.
 */
function fillCongressionalDistrictNumber(stateField, districtNumberField) {
    var stateValue = document.getElementById(stateField).value;
    if (stateValue == "US") {
        document.getElementById(districtNumberField).value="all";
    }
    else if (stateValue == "00") {
        document.getElementById(districtNumberField).value="000";
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

function ajaxLoadQn(protocolNumber, submissionNumber,  docFormKey, documentWebScope, summary, qnIdx) {
    var methodToCall = 'submissionQuestionnaireAjax';
    var subItemKey = '&submissionNumber=';
    
	if (summary == true) {
	    methodToCall = 'summaryQuestionnaireAjax';
	    subItemKey = '&sequenceNumber=';
	}
	$j.ajax( {
		url : 'questionnaire.do',
		type : 'POST',
		dataType : 'html',
		data : 'methodToCall='+methodToCall+'&docFormKey=' + docFormKey+'&documentWebScope=' + documentWebScope
		+subItemKey + submissionNumber+'&protocolNumber=' + protocolNumber,
		cache : false,
		async : false,
		timeout : 1000,
		error : function() {
			alert('Error loading XML document');
		},
		success : function(xml) {
			//alert(xml)
			var qnhtml;
			$j(xml).find('h5').each(function() {
				//alert($j(this).html());
				
				qnhtml = $j(this).html();
				qnhtml = qnhtml.replace(/questionpanelcontrol/g, "questionpanelcontrol" + qnIdx);
				qnhtml = qnhtml.replace(/questionpanelcontent/g, "questionpanelcontent" + qnIdx);

				$j('#qnhistory'+qnIdx+'Content').html(qnhtml);
//			    $j(".questionpanel").toggle(
//			            function()
//			            {
//			            	var headerIdx = $j(this).attr("id").substring(20);
//			                var panelcontentid = "questionpanelcontent"+headerIdx;
//			                $j("#"+panelcontentid).slideDown(500);
//			                $j(this).html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
//			                $j("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.showQuestions").attr("value","Y")
//			            },function(){
//			            	var headerIdx = $j(this).attr("id").substring(20);
//			                var panelcontentid = "questionpanelcontent"+headerIdx;
//			                $j("#"+panelcontentid).slideUp(500);
//			                $j(this).html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
//			                $j("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.showQuestions").attr("value","N")
//			            }
//			        );
//			    $j("#questionpanelcontrol0").click();

				});
		    $j(".questionpanel").toggle(
            function()
            {
            	var headerIdx = $j(this).attr("id").substring(20);
                var panelcontentid = "questionpanelcontent"+headerIdx;
                $j("#"+panelcontentid).slideDown(500);
                $j(this).html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
                $j("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.showQuestions").attr("value","Y")
            },function(){
            	var headerIdx = $j(this).attr("id").substring(20);
                var panelcontentid = "questionpanelcontent"+headerIdx;
                $j("#"+panelcontentid).slideUp(500);
                $j(this).html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
                $j("#questionnaireHelper\\.answerHeaders\\["+headerIdx+"\\]\\.showQuestions").attr("value","N")
            }
        );
		    var firstQn = true;
			$j(qnhtml).find('div[id^=questionpanelcontent]').each(function() {
				//alert('hide')
				if (firstQn) {
					var controlid = $j(this).attr("id").replace("content","control");
					firstQn = false;
				    $j('#'+controlid).click();
				} else {
					$j('#'+$j(this).attr("id")).hide();
				}
			});
			$j(".Qmoreinfocontrol").parent().next().hide();
			$j(".Qmoreinfocontrol").toggle(
				function()
				{
					$j(this).parent().next().slideDown(400);
					$j(this).html("Less Information...");
				},function(){
					$j(this).parent().next().slideUp(400);
					$j(this).html("More Information...");
				}
			);


		}
	}); // end ajax

    return false;
}

function closeQuestionnairePop() {
	if (viewQuestionnaireWindow != null) {
		viewQuestionnaireWindow.close();
	} 

}


function callAjax(methodToCall, codeValue, successCallback, errorCallback) {
	$j.ajax( {
		url : 'jqueryAjax.do',
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
 * Display the Protocol link and make the fields read-only if the special review type is Human Subjects
 */
function showHideSpecialReviewProtocolLink(specialReviewTypeCode, idPrefix) {
	var readOnly = specialReviewTypeCode.value == '1';
	if (readOnly) {
		changeObjectVisibility(idPrefix + ".protocolNumber.link.div", "inline");
	} else {
		changeObjectVisibility(idPrefix + ".protocolNumber.link.div", "none"); 
	}
	
	enableDisableReadOnlyDynamicHtmlControl(readOnly, new Array(idPrefix + ".approvalTypeCode", idPrefix + ".applicationDate", idPrefix + ".approvalDate", idPrefix + ".expirationDate", idPrefix + ".exemptionTypeCodes"));
}

function enableDisableReadOnlyDynamicHtmlControl(readOnly, ids) {
	if (readOnly) {
		for (var i = 0; i < ids.length; i++) {
			changeObjectVisibility(ids[i] + ".read.div", "inline");
			changeObjectVisibility(ids[i] + ".edit.div", "none");
			changeObjectVisibility(ids[i] + ".error.div", "none");
		}
	} else {
		for (var i = 0; i < ids.length; i++) {
			changeObjectVisibility(ids[i] + ".read.div", "none");
			changeObjectVisibility(ids[i] + ".edit.div", "inline");
			changeObjectVisibility(ids[i] + ".error.div", "none");
		}
	}
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
	DWRUtil.setValue("document.budget.budgetPeriods["+(budgetPeriod-1)+"].rateOverrideFlag","true");
}
function updateFringeCalcAmounts(budgetPeriodFringeTotal,budgetPeriod,calcAmontsCount){
	var fringeTotal = DWRUtil.getValue("document.budget.budgetPeriods["+(budgetPeriod-1)+"].totalFringeAmount");
	if(budgetPeriodFringeTotal!=fringeTotal){
		setRateOverrideFlag(budgetPeriod);
		for(var i= 0; i < calcAmontsCount; i++) {
			DWRUtil.setValue("document.budget.budgetPeriods["+(budgetPeriod-1)+"].awardBudgetPeriodFringeAmounts["+i+"].calculatedCost","");
		}
		changeObjectVisibility("personnelFringeCalc"+(budgetPeriod-1)+".div.object","none");
		changeObjectVisibility("personnelFringeTotal.div.object","none");
	}
}
function updateFringeTotal(budgetPeriod,calcAmontsCount){
	var fringeTotal=0;
	for(var i= 0; i < calcAmontsCount; i++) {
		var fringeAmount = DWRUtil.getValue("document.budget.budgetPeriods["+(budgetPeriod-1)+"].awardBudgetPeriodFringeAmounts["+i+"].calculatedCost");
		fringeTotal+=parseFloat(fringeAmount);
	}
	setRateOverrideFlag(budgetPeriod);
	DWRUtil.setValue("document.budget.budgetPeriods["+(budgetPeriod-1)+"].totalFringeAmount",fringeTotal);
	changeObjectVisibility("personnelFringeCalc"+(budgetPeriod-1)+".div.object","none");
	changeObjectVisibility("personnelFringeTotal.div.object","none");
}

function updateStateFromCountry() {
	var countryCode = DWRUtil.getValue( 'document.newMaintainableObject.countryCode' );
	
	var dwrReply = {
		callback:function(data) {
			if ( data != null ) {
				DWRUtil.removeAllOptions( 'document.newMaintainableObject.state' );
				$('document.newMaintainableObject.state').options[0] = new Option('', '');
				DWRUtil.addOptions( 'document.newMaintainableObject.state', data, 'postalStateCode', 'postalStateName' );
			} 
		},
		errorHandler:function( errorMessage ) {
			window.status = errorMessage;
		}
	};

	StateService.findAllStatesByAltCountryCode(countryCode, dwrReply);
}



/*
 * Load the Sponsor Name field based on the Sponsor Code passed in.
 */
function loadSponsor(sponsorCodeFieldName, sponsorNameFieldName, entityNameFieldName, prevSponsorCodeFieldName ) {
    var sponsorCode = DWRUtil.getValue( sponsorCodeFieldName );
    var prevSponsorCode = DWRUtil.getValue( prevSponsorCodeFieldName );

    if (sponsorCode=='') {
        clearRecipients( sponsorNameFieldName, "" );
        DWRUtil.setValue(prevSponsorCodeFieldName, "");
    } else {
        var dwrReply = {
            callback:function(data) {
                if ( data != null ) {
                    if ( sponsorNameFieldName != null && sponsorNameFieldName != "" ) {
                        setRecipientValue( sponsorNameFieldName, data.sponsorName );
                        setRecipientValue(entityNameFieldName, data.sponsorName);
                     //   if (sponsorCode!=prevSponsorCode) {
                            DWRUtil.setValue(prevSponsorCodeFieldName, data.sponsorCode);
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
	NotificationModuleRoleService.getModuleRolesForAjaxCall(moduleCode, dwrReply);
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
	var fullNameElement = $jq('input[name="document.newMaintainableObject.rolodexId"]');
	if ($jq(fullNameElement) != null) {
		var rolodexId = $jq(fullNameElement).val();
		if ($jq(fullNameElement).parent().find('div').length == 0) {
			$jq(fullNameElement).parent().append('<div>&nbsp;</div>');
		}
		if (rolodexId == '') {
			$jq(fullNameElement).parent().find('div').html("&nbsp;");
		}
		else
		{
			var dwrReply = {
					callback:function(data) {
				if ( data != null ) {
					$jq(fullNameElement).parent().find('div').html(data.fullName);}
			},
			};
			RolodexServiceRight.getRolodex(rolodexId, dwrReply);
		}
	}
}

