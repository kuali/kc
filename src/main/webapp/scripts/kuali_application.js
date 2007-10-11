

function selectAllKeywords(document) {
    var j = 0;
	for (var i = 0; i < document.KualiForm.elements.length; i++) {
	  var e = document.KualiForm.elements[i];
	  if(e.type == 'checkbox') {
	  	var name = 'document.propScienceKeywords[' + j + '].selectKeyword';
	  	if (e.name == name) {
 		    e.checked = true;
	  		j++;
	  	}
	  }
	}
}

function textAreaPop(text,textAreaName,htmlFormAction,textAreaLabel){
  url=window.location.href
  pathname=window.location.pathname
  idx1=url.indexOf(pathname);
  idx2=url.indexOf("/",idx1+1);
  extractUrl=url.substr(0,idx2)
  text=text.replace(/\n/g,'<br>');
  window.open(extractUrl+"/TextArea.jsp?" + text+"&textAreaFieldName="+textAreaName+"&htmlFormAction="+htmlFormAction+"&textAreaFieldLabel="+textAreaLabel, "_blank", "width=640, height=600, scrollbars=yes");
}

var textAreaFieldName
function setTextArea() {
  passData=document.location.search.substring(1);
  var idx=passData.indexOf("&textAreaFieldName=")
  var idx2=passData.indexOf("&htmlFormAction=")
  textAreaFieldName=passData.substring(idx+19,idx2)
  text=passData.substr(0,idx)
  text=unescape(text).replace(/<br>/g,"\n")
  document.getElementById(textAreaFieldName).value =unescape(text)
//  alert (escape(text))
//  alert (unescape(text))

}

function postValueToParentWindow() {
  opener.document.getElementById(textAreaFieldName).value = document.getElementById(textAreaFieldName).value;
  self.close();
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
function proposalAttachmentRightsPop(lineNumber){

  if (propAttRightWindow && propAttRightWindow.open && !propAttRightWindow.closed){
  	propAttRightWindow.focus();
  }else{
    propAttRightWindow = window.open(extractUrlBase()+"/proposalDevelopmentAbstractsAttachments.do?methodToCall=getProposalAttachmentRights&line="+lineNumber, "mywindow", "width=800, height=300, scrollbars=yes");
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
function openNewWindow(action,methodToCall,lineNumber){
  window.open(extractUrlBase()+"/"+action+".do?methodToCall="+methodToCall+"&line="+lineNumber);
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


