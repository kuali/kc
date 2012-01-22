
var AJAX_LOCATION = "awardHierarchyTimeAndMoneyAjax.do";
var ROOT_AWARD_LOCATION = "document.rootAwardNumber";
var openAwards = [];
var prevScrollPosition;
function addOpenAward(award) {
	openAwards.push(award);
}
function setScrollPosition(scrollPos) {
	prevScrollPosition = scrollPos;
}
function saveScrollPosition(scroll) {
	  var offset = jQuery(scroll).offset().top;
	  debugLog('Current Position - ' + jQuery(scroll).scrollTop());
	  var liList = jQuery(scroll).find('li.awardhierarchy').toArray();
	  var li = null;
	  for (var i = 1; i < liList.length; i++) {
		  if (jQuery(liList[i]) != null && jQuery(liList[i]).offset() != null
				  && jQuery(liList[i-1]) != null
				  && jQuery(liList[i]).offset().top > offset 
				  && (jQuery(liList[i-1]).offset() == null || jQuery(liList[i-1]).offset().top < offset)) {
			  li = liList[i];
			  break;
		  }
	  }
	  if (li != null) {
		  debugLog('Scrolling to ' + getAwardNumber(li) + ' next time.');
	      jQuery('input[name*=awardHierarchyScrollPosition]').attr('value', getAwardNumber(li));
	  } else {
		  jQuery('input[name*=awardHierarchyScrollPosition]').attr('value', '');
	  }
}
jQuery(document).ready(function(){
  jQuery('#awardHierarchyScrollable').scroll(function() {saveScrollPosition(this);});

  if (openAwards.length > 0) {
	  forceLoading();
	  queueToggle(null, openPreviousAwards);
  } else {
	  queueToggle(null);
  }
}); // jQuery(document).ready

function treeViewToggle(item) {
	if (!jQuery(item).is('.loaded')) {
		queueToggle(item);
	}
	if (jQuery(item).is('.collapsable')) {
		debugLog('Setting award to open next time ' + getAwardNumber(item));
		jQuery(item).find("input[name*='awardHierarchyToggle']").first().attr('value', 'true');
	} else {
		jQuery(item).find("input[name*='awardHierarchyToggle']").first().attr('value', 'false');
	}	  	
}

function openPreviousAwards(requestTracker) {
	if (requestTracker.liNode != null) {
		jQuery(requestTracker.liNode).find('div.expandable-hitarea:first').click();
	}
	jQuery(requestTracker.children).each(function() {
		var awardNumber = getAwardNumber(this);
		var idx = jQuery.inArray(awardNumber, openAwards);
		debugLog(idx + ' -- ' + awardNumber + ' -- ' + openAwards);
		if (idx != -1) {
			queueToggle(this, openPreviousAwards);
			openAwards.splice(idx, 1);
		}
	});
	if (activeRequest == null && pendingRequests.length == 0) {
		clearForceLoading();
		//make sure loading is hidden so we can get the offset of the div and the selected award
		jQuery('#loading').hide();
		debugLog('Scroll height ' + jQuery('#awardHierarchyScrollable > ul').outerHeight() + ", " + prevScrollPosition + " position is " + jQuery('#li' + prevScrollPosition).position().top);
		jQuery('#awardHierarchyScrollable').scrollTop(
				jQuery('#li' + prevScrollPosition).position().top
		);
	}
}
  
  function addAwardToHierarchy(info, parent) {
	  var awardNumber = info['awardNumber'];
      //build the line description - will include the award number, pi, lead unit and
      //editable and/or summary fields for time and money.
      var idDiv = jQuery('<div class="awardHierarchy"></div>').html(builduUi(info, awardNumber));
      //add the div to the link
      var tag = jQuery('<a class="awardHierarchy"></a>').html(idDiv);

      var listitem = jQuery('<li class="closed awardhierarchy" id="li' + awardNumber +'"></li>').html(tag);
      
      // need this ultag to force to display folder.
      var childUlTag = jQuery('<ul></ul>');
      childUlTag.appendTo(listitem);
      listitem.appendTo(jQuery(parent));
      // also need this to show 'folder' icon
      jQuery('#awardhierarchy').treeview({
         add: listitem
         
      });
      return listitem;
  }
  
  /*
	 * Utility function to get code from 'code : description' This need to be
	 * refined because if code contains ':', then this is not working correctly.
	 */
  function getAwardNumber(node) {
	  var liNode = node;
	  if (!jQuery(liNode).is('li.awardhierarchy')) {
		  alert('looking for parent');
		  liNode = jQuery(node).parents('li.awardhierarchy:eq(0)');
	  }
      return jQuery(liNode).attr('id').substring(2);
  }
  
  function getDetailString(info) {
	  return info['awardNumber'] + " : " + info['accountNumber'] + " : " + info['principalInvestigatorName'] + " : " + info['leadUnitName'];
  }
  
  function hasFormAlreadyBeenSubmitted() {
      // return false;
  }  
    
    function builduUi(info, awardNumber) {
    	var text1 = getDetailString(info);
    	var text2 = info['currentFundEffectiveDate'];
    	var text3 = info['obligationExpirationDate'];
    	var text4 = info['finalExpirationDate'];
    	var text5 = info['amountObligatedToDate'];
    	var text6 = info['anticipatedTotalAmount'];
    	var text7 = info['obliDistributableAmount'];
    	var text8 = info['antDistributableAmount'];
    	var text9 = info['distributedObligatedAmount'];
    	var text10 = info['distributedAnticipatedAmount'];
    	var text11 = info['awardStatusCode'];
    	var text12 = info['obligatedTotalDirect'];
    	var text13 = info['obligatedTotalIndirect'];
    	var text14 = info['anticipatedTotalDirect'];
    	var text15 = info['anticipatedTotalIndirect'];
    	var text16 = info['projectStartDate'];
    	var text17 = info['title'];
    	var text18 = info['awardId'];
    	var text19 = info['awardDocumentNumber'];
        
        if(text11 == 1){
        	var txtImage = "<img src=\"static/images/award_active.gif\" alt=\"Active\" title=\"Active\" />";
        }else if(text11 == 2){
        	var txtImage = "<img src=\"static/images/award_inactive.gif\" alt=\"Inactive\" title=\"Inactive\" />";
        }else if(text11 == 3){
        	var txtImage = "<img src=\"static/images/award_pending.gif\" alt=\"Pending\" title=\"Pending\" />";
        }else if(text11 == 4){
        	var txtImage = "<img src=\"static/images/award_inactive.gif\" alt=\"Terminated\" title=\"Terminated\" />";
        }else if(text11 == 5){
        	var txtImage = "<img src=\"static/images/award_inactive.gif\" alt=\"Closed\" title=\"Closed\" />";
        }else if(text11 == 6){
        	var txtImage = "<img src=\"static/images/award_holding.gif\" alt=\"Hold\" title=\"Hold\" />";
        }
        
        var index = awardNumber.indexOf("-");
        var awardNumber2 = parseInt(awardNumber.substring(index+1), 10);
        
        if(jQuery("#cancelOrFinalStatus").attr("value") == 1){
        	
//        	var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td>" +
//  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text2+"</td>" +
//  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text3+"</td>" +
//  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text4+"</td>" +
//  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text5+"</td>" +
//  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align:right; left: 1335px; border-collapse: collapse;\">"+text6+"</td>"
//  			+"</tr></tbody></table>";
        	
        	if(jQuery("#controlForAwardHierarchyView").attr("value") == 0){
		      	  var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top;\">"+txtImage+"&nbsp;"+text1+"</td>"+
					    "<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text2+"</td>" +
			  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text3+"</td>" +
			  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text4+"</td>" +
			  			"</tr></tbody></table>";
		        }else if(jQuery("#controlForAwardHierarchyView").attr("value") == 1){
		        	var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td>"+
			        	"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text2+"</td>" +
			  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text3+"</td>" +
			  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text4+"</td>" 
		        		+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: right; width: 112px; align:right; left: 1116px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);\">  Distributed: </td>" 
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse; \">" +text9 + "</td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse; \">" +text10 + "</td>"
			  			+"</tr></tbody></table>"
			  			+"<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse;\">&nbsp;</td>"
			  			+"<td style=\"width: 112px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse; align:right;\">&nbsp;</td><td style=\"width: 112px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse; align:right; left:  882px; \">"
			  			+"&nbsp;</td><td style=\"width: 112px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse; align:right;\">&nbsp;</td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: right; width: 112px; border-collapse: collapse; align:right; font-weight: bold; background-color: rgb(195, 195, 195);\">  Distributable: </td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">" +text7 + "</td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">" +text8 + "</td>"
			  			+"</tr></tbody></table>"
			  			+"<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse;\">&nbsp;</td>"
			  			+"<td style=\"width: 112px; align:right; left: 765px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse;\">&nbsp;</td><td style=\"width: 112px; align:right; border: 1px solid rgb(153, 153, 153); border-collapse: collapse;\">&nbsp;</td>"
			  			+"<td style=\"width: 112px; align:right; left: 999px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse;\">&nbsp;</td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: right; width: 112px; align:right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);\">  Total: </td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">" +text5 + "</td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">" +text6 + "</td>"
			  			+"</tr></tbody></table>";
		        }else if(jQuery("#controlForAwardHierarchyView").attr("value") == 2){
		        	if(jQuery("#directIndirectViewEnabled").attr("value") == 1){
			        	if(jQuery("#inSingleNodeHierarchy").attr("value") == 1){
			        		var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td>"+
			        		"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text2+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text3+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text4+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"			  			
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligatedTotalDirect\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligatedTotalDirect\""+ " value=\"" +text12 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"			  			
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligatedTotalIndirect\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligatedTotalIndirect\""+ " value=\"" +text13 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text5+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"	
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].anticipatedTotalDirect\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].anticipatedTotalDirect\""+ " value=\"" +text14 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"	
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].anticipatedTotalIndirect\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].anticipatedTotalIndirect\""+ " value=\"" +text15 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align:right; left: 1335px; border-collapse: collapse;\">"+text6+"</td>"
				  			+"</tr></tbody></table>";	
			        	}else {
			    			var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td>"+
			    			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text2+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text3+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text4+"</td>" 
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text12+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text13+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text5+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text14+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text15+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align:right; left: 1335px; border-collapse: collapse;\">"+text6+"</td>" +"</tr></tbody></table>";
			        	} 
		        	}else{
		        		if(jQuery("#inSingleNodeHierarchy").attr("value") == 1){
			        		var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td>"+
			        		"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text2+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text3+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text4+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].amountObligatedToDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].amountObligatedToDate\""+ " value=\"" +text5 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].anticipatedTotalAmount\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].anticipatedTotalAmount\""+ " value=\"" +text6 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>"
				  			+"</tr></tbody></table>";	
			        	}else {
			        		//need this one without inputs for date fields for KRACOEUS-4664
			    			var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td>"+
			    			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text2+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text3+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text4+"</td>" +
			    			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"
				  			+text5+"</td><td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align:right; left: 1335px; border-collapse: collapse;\">"
				  			+text6+"</td>"
				  			+"</tr></tbody></table>";
			        	} 
		        	}
		        }
        } else { if(jQuery("#controlForAwardHierarchyView").attr("value") == 0){
		      	  var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top;\">"+txtImage+"&nbsp;"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse; align:right;\">"
			  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].currentFundEffectiveDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse; align:right;\">"
			  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligationExpirationDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td nowrap style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse; align:right;\">"
		//+ displaydate()
			  			+"<input type=\"text\"class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].finalExpirationDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/></td>"
			  			+"</tr></tbody></table>";
		        }else if(jQuery("#controlForAwardHierarchyView").attr("value") == 1){
		        	var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td>"
		        		+"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 114px; border-collapse: collapse; vertical-align: top; align:right; \">"
			  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].currentFundEffectiveDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/></td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 114px; border-collapse: collapse; vertical-align: top; align:right;\">"	  			
			  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligationExpirationDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" class = 'datepicker' style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"
			  			//+ displaydate()
			  			+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 114px; border-collapse: collapse; vertical-align: top; align:right; \">"
			  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].finalExpirationDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>" 
			  			+"</td><td style=\"border: 1px solid rgb(153, 153, 153); text-align: right; width: 112px; align:right; left: 1116px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);\">  Distributed: </td>" 
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse; \">" +text9 + "</td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse; \">" +text10 + "</td>"
			  			+"</tr></tbody></table>"
			  			+"<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse;\">&nbsp;</td>"
			  			+"<td style=\"width: 112px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse; align:right;\">&nbsp;</td><td style=\"width: 112px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse; align:right; left:  882px; \">"
			  			+"&nbsp;</td><td style=\"width: 112px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse; align:right;\">&nbsp;</td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: right; width: 112px; border-collapse: collapse; align:right; font-weight: bold; background-color: rgb(195, 195, 195);\">  Distributable: </td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">" +text7 + "</td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">" +text8 + "</td>"
			  			+"</tr></tbody></table>"
			  			+"<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse;\">&nbsp;</td>"
			  			+"<td style=\"width: 112px; align:right; left: 765px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse;\">&nbsp;</td><td style=\"width: 112px; align:right; border: 1px solid rgb(153, 153, 153); border-collapse: collapse;\">&nbsp;</td>"
			  			+"<td style=\"width: 112px; align:right; left: 999px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse;\">&nbsp;</td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: right; width: 112px; align:right; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);\">  Total: </td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">" +text5 + "</td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">" +text6 + "</td>"
			  			+"</tr></tbody></table>";
		        }else if(jQuery("#controlForAwardHierarchyView").attr("value") == 2){
		        	if(jQuery("#directIndirectViewEnabled").attr("value") == 1){
			        	if(jQuery("#inSingleNodeHierarchy").attr("value") == 1){
			        		var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].currentFundEffectiveDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; left: border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligationExpirationDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 110px; align:right; left: border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].finalExpirationDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+
				  			"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"			  			
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligatedTotalDirect\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligatedTotalDirect\""+ " value=\"" +text12 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"			  			
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligatedTotalIndirect\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligatedTotalIndirect\""+ " value=\"" +text13 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text5+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"	
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].anticipatedTotalDirect\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].anticipatedTotalDirect\""+ " value=\"" +text14 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"	
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].anticipatedTotalIndirect\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].anticipatedTotalIndirect\""+ " value=\"" +text15 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align:right; left: 1335px; border-collapse: collapse;\">"+text6+"</td>"
				  			+"</tr></tbody></table>";	
			        	}else {
			    			var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].currentFundEffectiveDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligationExpirationDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 110px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].finalExpirationDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text12+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text13+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text5+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text14+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text15+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align:right; left: 1335px; border-collapse: collapse;\">"+text6+"</td>" +"</tr></tbody></table>";
			        	} 
		        	}else{
		        		if(jQuery("#inSingleNodeHierarchy").attr("value") == 1){
			        		var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].currentFundEffectiveDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; left: border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligationExpirationDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 110px; align:right; left: border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].finalExpirationDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+
				  			"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].amountObligatedToDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].amountObligatedToDate\""+ " value=\"" +text5 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].anticipatedTotalAmount\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].anticipatedTotalAmount\""+ " value=\"" +text6 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>"
				  			+"</tr></tbody></table>";	
			        	}else {
			        		//need this one without inputs for date fields for KRACOEUS-4664
			    			var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].currentFundEffectiveDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligationExpirationDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 110px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + awardNumber2 + "].finalExpirationDate\" id=\"awardHierarchyNodeItems[" + awardNumber2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"
				  			+text5+"</td><td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align:right; left: 1335px; border-collapse: collapse;\">"
				  			+text6+"</td>"
				  			+"</tr></tbody></table>";
			        	} 
		        	}
		        }
        }
        abc += '<input type="hidden" value="false" name="awardHierarchyToggle(' + awardNumber + ')"/>';
 	   return abc; 
    }


