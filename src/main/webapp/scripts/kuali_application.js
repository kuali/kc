


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

		ProposalDevelopmentService.getSponsorCode(sponsorCode,dwrReply);

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
		ProposalDevelopmentService.getSponsorName(sponsorCode,dwrReply);
	}
}


function loadSponsorCode_1( sponsorCodeFieldName) {
    // alternative, delete later
	var sponsorCode = DWRUtil.getValue( sponsorCodeFieldName );
	//alert(sponsorCodeFieldName+" "+sponsorCode)
	//ProposalDevelopmentService.getSponsorCode(sponsorCode,function(data) {
    //DWRUtil.setValue(sponsorCodeFieldName, data);});
	ProposalDevelopmentService.getSponsorCode(sponsorCode,loadinfo);

}

function loadinfo(data) {
  //alert("loadinfo "+data)
  DWRUtil.setValue("document.sponsorCode", data);
}
