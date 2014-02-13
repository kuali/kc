var AJAX_LOCATION = "awardHierarchyAwardActionsAjax.do";
var ROOT_AWARD_LOCATION = "rootAwardNumber";
var selectedAwardNumber = jQuery('#selectedAwardNumber').attr("value");
var currentAwardNumber = jQuery("#currentAwardNumber").attr("value");
var selectedNodeReached = false;
var canCreateAward= jQuery("#canCreateAward").attr("value");
jQuery(document).ready(function() {
	  if (selectedAwardNumber.length > 1) {
		  forceLoading();
		  queueToggle(null, openSelectedAward);
	  } else {
		  queueToggle(null);
	  }
});

//method called by the treeview that is setup in the awardHierarchyShared.js
function treeViewToggle(item) {
	debugLog('toggling ' + getAwardNumber(item));
	jQuery("div.awardHierarchy").slideUp(300);
	if (!jQuery(item).is('.loaded')) {
		queueToggle(item);
	}
}

function openSelectedAward(requestTracker) {
	if (requestTracker.liNode != null && !selectedNodeReached) {
		jQuery(requestTracker.liNode).find('div.expandable-hitarea:first').click();
	}
	jQuery(requestTracker.children).each(function() {
		var awardNumber = getAwardNumber(this);
		if (awardNumber == selectedAwardNumber) {
			debugLog(awardNumber +' == '+ selectedAwardNumber);
			selectedNodeReached = true;
			pendingRequests = [];
			var liNode = this;
			setTimeout(function() {
				jQuery(liNode).find('a.awardHierarchy').first().click();
			}, 1000);
		} else if (!selectedNodeReached){
			debugLog(awardNumber +' != '+ selectedAwardNumber);
			queueToggle(this, openSelectedAward);
		}
	});
	if (activeRequest == null && pendingRequests.length == 0) {
		setTimeout(function() {
			clearForceLoading();
			//make sure loading is hidden so we can get the offset of the div and the selected award
			jQuery('#loading').hide();
			if (selectedAwardNumber.length > 1) {
				debugLog('Scroll height ' + jQuery('#awardHierarchyScrollable > ul').outerHeight() + ", " + selectedAwardNumber + " position is " + jQuery('#li' + selectedAwardNumber).position().top);
				jQuery('#awardHierarchyScrollable').scrollTop(
						jQuery('#li' + selectedAwardNumber).position().top
				);
			}
		}, 2000);
	}
}
  
  function addAwardToHierarchy(info, parent) {;
      var awardNumber = info['awardNumber'];
      //build the line description - will include the award number, pi, lead unit and
      //editable and/or summary fields for time and money.
      var idDiv = jQuery('<div class="awardHierarchyLink"></div>').html(builduUi(info, awardNumber));
       //add the div to the link
      var tag = jQuery('<a class="awardHierarchy"></a>').html(idDiv);

      var listitem = jQuery('<li class="closed awardhierarchy" id="li' + awardNumber +'"></li>').html(tag);
      var detailsDiv = jQuery('<div class="awardHierarchy" id="details' + awardNumber +'"></div>');
      detailsDiv.appendTo(jQuery(listitem));
      
      // need this ultag to force to display folder.
      var childUlTag = jQuery('<ul></ul>');
      childUlTag.appendTo(listitem);
      listitem.appendTo(jQuery(parent));
      
      tag.click(function() {
    	  linkOnClick(jQuery(this), info);
      });
      
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
		  liNode = jQuery(node).parents('li.awardhierarchy:eq(0)');
	  }
      return jQuery(liNode).attr('id').substring(2);
  }
    
    function builduUi(info, awardNumber) {
    	var awardStatus = info['awardStatusCode'];
        if(awardStatus == 1){
        	var txtImage = "<img src=\"static/images/award_active.gif\" alt=\"Active\" title=\"Active\" />";
        }else if(awardStatus == 2){
        	var txtImage = "<img src=\"static/images/award_inactive.gif\" alt=\"Inactive\" title=\"Inactive\" />";
        }else if(awardStatus == 3){
        	var txtImage = "<img src=\"static/images/award_pending.gif\" alt=\"Pending\" title=\"Pending\" />";
        }else if(awardStatus == 4){
        	var txtImage = "<img src=\"static/images/award_inactive.gif\" alt=\"Terminated\" title=\"Terminated\" />";
        }else if(awardStatus == 5){
        	var txtImage = "<img src=\"static/images/award_inactive.gif\" alt=\"Closed\" title=\"Closed\" />";
        }else if(awardStatus == 6){
        	var txtImage = "<img src=\"static/images/award_holding.gif\" alt=\"Hold\" title=\"Hold\" />";
        }
        
        var index = awardNumber.indexOf("-");
        var awardNumber2 = parseInt(awardNumber.substring(index+1), 10);
        var abc;
        if(awardNumber == currentAwardNumber){
        	abc = "<span class='awardHierarchyLinkText'>" + txtImage + "&nbsp;" + getDetailString(info)
      		+ "</span><br clear='both'/><br/>";
      	abc += '<input type="hidden" value="false" name="awardHierarchyToggle(' + awardNumber + ')"/>';
        }else{
        	abc = "<span class='awardHierarchyLinkText'>" + txtImage + "&nbsp;" + getDetailString(info) + "</span><span class='hierarchyOpenLink'>" 
      		+ "<a href='awardHome.do?methodToCall=docHandler&docId=" + info['awardDocumentNumber'] + "&awardNumber=" + awardNumber + "&command=displayDocSearchView&placeHolderAwardId=" + info['awardId'] + "' target='_blank'><img src='static/images/tinybutton-open.gif' /></a>&nbsp;"
      		+ "<a href='awardMedusa.do?methodToCall=docHandler&docId=" + info['awardDocumentNumber'] + "&awardNumber=" + awardNumber + "&command=displayDocSearchView&placeHolderAwardId=" + info['awardId'] + "' target='_blank'><img src='static/images/tinybutton-medusa.jpg' /></a></span>"
      		+ "<br clear='both'/>";
      	abc += '<input type="hidden" value="false" name="awardHierarchyToggle(' + awardNumber + ')"/>';
        }
      	return abc; 
    }
  
  function tbodyTag2(info,firstCounter) {
	  var awardNumber = info['awardNumber'];
      var index = awardNumber.indexOf("-");
      var indexForHiddenField = parseInt(awardNumber.substring(index+1), 10);
      
	  var tblTag = jQuery('<table id="tbody2_1" style="border: 1px solid rgb(147, 147, 147); padding: 0px; border-collapse: collapse;"></table>')
	    
	  	var trTag0 = jQuery('<tr></tr>');
	    var thTag0 = jQuery('<th colspan="5" style="border-style: solid; text-align:left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;"></th>').html("Award Copy");
	    trTag0.html(thTag0);
	    trTag0.appendTo(tblTag);
	    // 1st tr
	    var trTag = jQuery('<tr></tr>');
	    var thTag1=jQuery('<th style="border: 1px solid rgb(147, 147, 147); padding: 3px; border-collapse: collapse; background-color: rgb(230, 230, 230); background-image: none; width: 130px; vertical-align: middle;">').html('<b>Copy Descendents: </b>');
	    trTag.html(thTag1);
	    var disabled = "";
	    if (!info['hasChildren']) {
	    	disabled = "disabled=''";
	    }
	    if(jQuery("#awardHierarchyTempObject\\[" + indexForHiddenField + "\\]\\.copyDescendants").attr("value")){
	    	var checkbox = jQuery('<input class="nobord" type="checkbox" ' + disabled + '></input>').attr("name","awardHierarchyTempObject["+indexForHiddenField+"].copyDescendants").attr("id","awardHierarchyTempObject["+indexForHiddenField+"].copyDescendants").attr("value",jQuery("#awardHierarchyTempObject\\[" + indexForHiddenField + "\\]\\.copyDescendants").attr("value")).attr("checked",jQuery("#awardHierarchyTempObject\\[" + indexForHiddenField + "\\]\\.copyDescendants").attr("value"));
	    }else{
	    	var checkbox = jQuery('<input class="nobord" type="checkbox" ' + disabled + '></input>').attr("name","awardHierarchyTempObject["+indexForHiddenField+"].copyDescendants").attr("id","awardHierarchyTempObject["+indexForHiddenField+"].copyDescendants");
	    }
	    
	    var hiddenTagForCheckBox = jQuery('<input type="hidden" />').attr("name","elementsToReset").attr("value","awardHierarchyTempObject["+indexForHiddenField+"].copyDescendants");
	    
	    var tdTag1=jQuery('<td style="border: 1px solid rgb(147, 147, 147); padding: 3px; border-collapse: collapse; background-color: rgb(255, 255, 255); vertical-align: middle; width: 30px;">');
	    checkbox.appendTo(tdTag1);
	    hiddenTagForCheckBox.appendTo(tdTag1);
	    tdTag1.appendTo(trTag);	    
	    var thTag2=jQuery('<th style="border: 1px solid rgb(147, 147, 147); padding: 3px; border-collapse: collapse; background-color: rgb(230, 230, 230); background-image: none; width: 60px; vertical-align: middle;">').html('<b>Copy as:</b>');
	    thTag2.appendTo(trTag);
	    
	    var subTblTag = jQuery('<table align="right" cellspacing="0" cellpadding="0" style="border: medium none ; background: transparent none repeat scroll 0% 0%; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous;"></table>')
	    var subTrTag = jQuery('<tr></tr>');
	    var subTdTag1 = jQuery('<td style="border: medium none ; background: transparent none repeat scroll 0% 0%; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous; text-align: left; width: 60px;">');
	    var subTdTag2 = jQuery('<td style="border: medium none ; background: transparent none repeat scroll 0% 0%; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous; vertical-align: bottom; text-align: right; width: 100px;">');
	    var subTdTag3 = jQuery('<td style="border: medium none ; background: transparent none repeat scroll 0% 0%; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous; vertical-align: bottom; text-align: left; width: 125px;">');
	    var subTdTag4 = jQuery('<td style="border: medium none ; background: transparent none repeat scroll 0% 0%; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous; vertical-align: bottom; text-align: right; width: 20px;">');
	    	    
	    if(jQuery("#awardHierarchyTempObject" + indexForHiddenField + "copyAwardRadio").attr("value") == "a" || firstCounter == 0){
	    	var radio1 = jQuery('<input class="nobord" value="a" checked="checked" type="radio" name="awardHierarchyTempObject['+indexForHiddenField+'].copyAwardRadio" />').attr("id","awardHierarchyTempObject"+indexForHiddenField+"copyAwardRadio");
	    	firstCounter = 1;
	    }else{
	    	var radio1 = jQuery('<input class="nobord" type="radio" name="awardHierarchyTempObject['+indexForHiddenField+'].copyAwardRadio" />').attr("id","awardHierarchyTempObject"+indexForHiddenField+"copyAwardRadio").attr("value","a");
	    	firstCounter = 1;
	    }
	    
	    subTdTag1.html('new');
	    radio1.appendTo(subTdTag1);
	    
	    if(jQuery("#awardHierarchyTempObject" + indexForHiddenField + "copyAwardRadio").attr("value") == "d"){
	    	var radio2 = jQuery('<input class="nobord"  type="radio" value="d" checked="checked" name="awardHierarchyTempObject['+indexForHiddenField+'].copyAwardRadio" />').attr("id","awardHierarchyTempObject"+indexForHiddenField+"copyAwardRadio");
	    }else{
	    	var radio2 = jQuery('<input class="nobord" type="radio" name="awardHierarchyTempObject['+indexForHiddenField+'].copyAwardRadio" />').attr("id","awardHierarchyTempObject"+indexForHiddenField+"copyAwardRadio").attr("value","d");
	    }
	    
	    subTdTag2.html('child of');
	    radio2.appendTo(subTdTag2);
	    
	    var lookupField = jQuery('<input type="image" title="Lookup" alt="Lookup" src="static/images/searchicon.gif"/>').attr("name","methodToCall.performLookup.(!!org.kuali.kra.award.home.Award!!).(((awardNumber:awardHierarchyTempObject["+indexForHiddenField+"].awardNumber2,awardHierarchyTempObject["+indexForHiddenField+"].awardNumber2:awardNumber))).((``)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~))");
	    var selectBoxText = jQuery("#awardHierarchyTempObject\\[" + indexForHiddenField + "\\]\\.selectBox2").attr("value");
	    var selectTag = jQuery('<select />').attr("name","awardHierarchyTempObject["+indexForHiddenField+"].copyAwardPanelTargetAward").attr("id","awardHierarchyTempObject"+indexForHiddenField+"_copyAwardPanelTargetAward");   
	    var optionTag = jQuery("<option> select: </option>").attr("value","0");
	    optionTag.appendTo(selectTag);
	    while(selectBoxText.length>1){
	    	var optionValue = selectBoxText.substring(0,selectBoxText.indexOf("%3A")).trim();	    	
	    	selectBoxText = selectBoxText.substring(selectBoxText.indexOf("%3A")+3, selectBoxText.length).trim();
	    	if(jQuery("#awardHierarchyTempObject\\[" + indexForHiddenField + "\\]\\.awardNumber2").attr("value") == optionValue){
	    		var optionTag = jQuery("<option>"+optionValue+"</option>").attr("value",optionValue).attr("selected",true);
	    	}else{
	    		var optionTag = jQuery("<option>"+optionValue+"</option>").attr("value",optionValue);
	    	}
	    	optionTag.appendTo(selectTag);	    	
	    }
	    
	    selectTag.appendTo(subTdTag3)

	    var hiddenAward = jQuery('<input type="hidden" name="awardHierarchyTempObject[' + indexForHiddenField + '].awardNumber" />').attr("id",
         			"awardHierarchyTempObject[" + indexForHiddenField + "].awardNumber").attr("value", awardNumber);

	    lookupField.appendTo(subTdTag4);
	    hiddenAward.appendTo(subTdTag4);
	    subTdTag1.appendTo(subTrTag);
	    subTdTag2.appendTo(subTrTag);
	    subTdTag3.appendTo(subTrTag);
	    subTdTag4.appendTo(subTrTag);
	    
	    subTrTag.appendTo(subTblTag);
	    
	    var tdTag2=jQuery('<td align="left" width="305px"></td>').html(subTblTag);
	    tdTag2.appendTo(trTag);	    
	    trTag.appendTo(tblTag);
	    
	    if(canCreateAward == "true"){
		    var tdTag3=jQuery('<td align="left" style="border: 1px solid rgb(147, 147, 147); padding: 3px; border-collapse: collapse; background-color: rgb(255, 255, 255); vertical-align: middle; text-align: center; width: 65px;">');
		    var copyButton = jQuery('<input type="image" title="Copy" alt="copy" style="border: medium none ;" src="static/images/tinybutton-copy2.gif"/>').attr("property","methodToCall.copyAward.awardNumber"+awardNumber).attr("name","methodToCall.copyAward.awardNumber"+awardNumber);
		    copyButton.appendTo(tdTag3);	    
		    tdTag3.appendTo(trTag);	    
		    trTag.appendTo(tblTag);
	    }
	    jQuery("body").delegate("#awardHierarchyTempObject" +indexForHiddenField+ "_copyAwardPanelTargetAward", "change", function() {	  		    	
	    	jQuery('input:radio[@name="awardHierarchyTempObject['+indexForHiddenField+'].copyAwardRadio"]').filter('[value="d"]').attr('checked', 'checked');
	    	
	    });
	    
	    
	    jQuery("#awardHierarchyTempObject"+indexForHiddenField+"copyAwardRadio").live( "click", function() {	    	
	    			if(jQuery(this).val() != "b") {	    				
	    				jQuery("#awardHierarchyTempObject" +indexForHiddenField+ "_copyAwardPanelTargetAward").prop('selectedIndex',0);	    				
	    			}
	    });
	    
	    
	    return tblTag;
  }  
  
  function tbodyTag3(info, firstCounter) {
	  
	  var awardNumber = info['awardNumber'];
	  var index = awardNumber.indexOf("-");
      var indexForHiddenField = parseInt(awardNumber.substring(index+1), 10);
      
      
	  var tblTag = jQuery('<table id="tbody3_1" style="border: 1px solid rgb(147, 147, 147); padding: 0px; border-collapse: collapse;"></table>')

	    var trTag0 = jQuery('<tr></tr>');
	    var thTag0 = jQuery('<th colspan="3" style="border-style: solid; text-align:left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;"></th>').html("New Child");
	    trTag0.html(thTag0);
	    trTag0.appendTo(tblTag);
	    
	    // 1st tr
	    var trTag = jQuery('<tr></tr>');
	    var thTag1=jQuery('<th style="border: 1px solid rgb(147, 147, 147); padding: 3px; border-collapse: collapse; background-color: rgb(230, 230, 230); background-image: none; width: 70px; vertical-align: middle;"></th>').html('<b>Based on:</b>');
	    trTag.html(thTag1);
	
	    var subTblTag = jQuery('<table cellspacing="0" cellpadding="0" style="border: medium none ; background: transparent none repeat scroll 0% 0%; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous;"></table>')
	    var subTrTag = jQuery('<tr></tr>');
	    var subTdTag1 = jQuery('<td style="border: medium none ; background: transparent none repeat scroll 0% 0%; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous; text-align: left;"></td>');
	    var subTdTag2 = jQuery('<td style="border: medium none ; background: transparent none repeat scroll 0% 0%; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous; vertical-align: bottom; text-align: right;"></td>');
	    var subTdTag3 = jQuery('<td style="border: medium none ; background: transparent none repeat scroll 0% 0%; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous; vertical-align: bottom; text-align: right;"></td>');
	    var subTdTag4 = jQuery('<td style="border: medium none ; background: transparent none repeat scroll 0% 0%; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous; vertical-align: bottom; text-align: left;"></td>');
	    var subTdTag5 = jQuery('<td style="border: medium none ; background: transparent none repeat scroll 0% 0%; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous; vertical-align: bottom; text-align: right;"></td>');
	    	    
	    
	    
	    if(jQuery("#awardHierarchyTempObject" + indexForHiddenField + "createNewChildRadio").attr("value") == "a" || firstCounter == 0 ){
	    	var radio1 = jQuery('<input class="nobord" type="radio" name="awardHierarchyTempObject['+indexForHiddenField+'].createNewChildRadio" />').attr("id","awardHierarchyTempObject"+indexForHiddenField+"createNewChildRadio").attr("value","a").attr("checked",true);
	    	firstCounter = 1;	    	
	    }else{
	    	var radio1 = jQuery('<input class="nobord" type="radio" name="awardHierarchyTempObject['+indexForHiddenField+'].createNewChildRadio" />').attr("id","awardHierarchyTempObject"+indexForHiddenField+"createNewChildRadio").attr("value","a");
	    }
	    subTdTag1.html('new');
	    radio1.appendTo(subTdTag1);
	    
	    if(jQuery("#awardHierarchyTempObject" + indexForHiddenField + "createNewChildRadio").attr("value") == "b"){
	    	var radio2 = jQuery('<input class="nobord" type="radio" name="awardHierarchyTempObject['+indexForHiddenField+'].createNewChildRadio" />').attr("id","awardHierarchyTempObject"+indexForHiddenField+"createNewChildRadio").attr("value","b").attr("checked",true);	    	
	    }else{
	    	var radio2 = jQuery('<input class="nobord" type="radio" name="awardHierarchyTempObject['+indexForHiddenField+'].createNewChildRadio" />').attr("id","awardHierarchyTempObject"+indexForHiddenField+"createNewChildRadio").attr("value","b");
	    }
	    subTdTag2.html('copy from parent');
	    radio2.appendTo(subTdTag2);
	    
	    if(jQuery("#awardHierarchyTempObject" + indexForHiddenField + "createNewChildRadio").attr("value") == "c"){
	    	var radio3 = jQuery('<input class="nobord" type="radio" name="awardHierarchyTempObject['+indexForHiddenField+'].createNewChildRadio" />').attr("id","awardHierarchyTempObject"+indexForHiddenField+"createNewChildRadio").attr("value","c").attr("checked",true);	    	
	    }else{
	    	var radio3 = jQuery('<input class="nobord" type="radio" name="awardHierarchyTempObject['+indexForHiddenField+'].createNewChildRadio" />').attr("id","awardHierarchyTempObject"+indexForHiddenField+"createNewChildRadio").attr("value","c");
	    }
	    
	    subTdTag3.html('selected award');
	    radio3.appendTo(subTdTag3);	

	    var lookupField = jQuery('<input type="image" title="Lookup" alt="Lookup" src="static/images/searchicon.gif"/>').attr("name","methodToCall.performLookup.(!!org.kuali.kra.award.home.Award!!).(((awardNumber:awardHierarchyTempObject["+indexForHiddenField+"].awardNumber1,awardHierarchyTempObject["+indexForHiddenField+"].awardNumber1:awardNumber))).((``)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~))");
	    var selectBoxText = jQuery("#awardHierarchyTempObject\\[" + indexForHiddenField + "\\]\\.selectBox1").attr("value");
	    var selectTag = jQuery('<select />').attr("name","awardHierarchyTempObject["+indexForHiddenField+"].newChildPanelTargetAward").attr("id","awardHierarchyTempObject"+indexForHiddenField+"_newChildPanelTargetAward");   
	    var optionTag = jQuery("<option> select: </option>").attr("value","0");
	    optionTag.appendTo(selectTag);
	    while(selectBoxText.length>1){
	    	var optionValue = selectBoxText.substring(0,selectBoxText.indexOf("%3A")).trim();
	    	selectBoxText = selectBoxText.substring(selectBoxText.indexOf("%3A")+3, selectBoxText.length).trim();
	    	if(jQuery("#awardHierarchyTempObject\\[" + indexForHiddenField + "\\]\\.awardNumber1").attr("value") == optionValue){
	    		var optionTag = jQuery("<option>"+optionValue+"</option>").attr("value",optionValue).attr("selected",true);	    
	    	}else{
	    		var optionTag = jQuery("<option>"+optionValue+"</option>").attr("value",optionValue);
	    	}
	    	optionTag.appendTo(selectTag);	    	
	    } 
	    jQuery("#awardHierarchyTempObject" +indexForHiddenField+ "_newChildPanelTargetAward").live("change", function() {	    	 
	    	jQuery('input:radio[@name="awardHierarchyTempObject['+indexForHiddenField+'].createNewChildRadio"]').filter('[value="c"]').attr('checked', 'checked');
	 	   	
	    });	 	 
	   
	    selectTag.appendTo(subTdTag4)	    
	    
	    lookupField.appendTo(subTdTag5);
	    
	    subTdTag1.appendTo(subTrTag);
	    subTdTag2.appendTo(subTrTag);
	    subTdTag3.appendTo(subTrTag);
	    subTdTag4.appendTo(subTrTag);
	    subTdTag5.appendTo(subTrTag);
	    
	    subTrTag.appendTo(subTblTag);
	    
	    var tdTag1=jQuery('<td align="left"></td>').html(subTblTag);
	    tdTag1.appendTo(trTag);	    
	    trTag.appendTo(tblTag);
	    
	    if(canCreateAward == "true"){
		    var tdTag2=jQuery('<td align="left" style="border: 1px solid rgb(147, 147, 147); padding: 3px; border-collapse: collapse; background-color: rgb(255, 255, 255); vertical-align: middle; text-align: center; width: 65px;"></td>');
		    var createButton = jQuery('<input type="image" title="Create" alt="create" style="border: medium none ;" src="static/images/tinybutton-create.gif"/>').attr("property","methodToCall.create.awardNumber"+awardNumber).attr("name","methodToCall.create.awardNumber"+awardNumber);	    
		    createButton.appendTo(tdTag2);	    
		    tdTag2.appendTo(trTag);
		    trTag.appendTo(tblTag);
	    }
	    jQuery("#awardHierarchyTempObject" + indexForHiddenField + "createNewChildRadio").live("click", function() {
	    			if($j(this).val() != "c") {     				
	    				$j("#awardHierarchyTempObject" +indexForHiddenField+ "_newChildPanelTargetAward").prop('selectedIndex',0);	    				
	    			}
	    });
	    return tblTag;
  }

  function tbodyTag1(info) {
      var tblTag = jQuery('#templates table.awardDetails').clone();
      jQuery(tblTag).find('.detailHeading').text(decodeEntities(getDetailString(info)));
      jQuery(tblTag).find('.projectStartDate').text(info['projectStartDate']);
      jQuery(tblTag).find('.obligationStartDate').text(info['currentFundEffectiveDate']);
      jQuery(tblTag).find('.projectEndDate').text(info['finalExpirationDate']);
      jQuery(tblTag).find('.obligationEndDate').text(info['obligationExpirationDate']);
      jQuery(tblTag).find('.anticipatedAmount').text("$" + info['anticipatedTotalAmount']);
      jQuery(tblTag).find('.obligatedAmount').text("$" + info['amountObligatedToDate']);
      jQuery(tblTag).find('.title').text(decodeEntities(info['title']));
      return tblTag;
  }
  
  var decodeEntities = (function() {
      // this prevents any overhead from creating the object each time
      var element = document.createElement('div');

      function decodeHTMLEntities (str) {
          if(str && typeof str === 'string') {
              // strip script/html tags
              str = str.replace(/<script[^>]*>([\S\s]*?)<\/script>/gmi, '');
              str = str.replace(/<\/?\w(?:[^"'>]|"[^"]*"|'[^']*')*>/gmi, '');
              element.innerHTML = str;
              str = element.textContent;
              element.textContent = '';
          }
          return str;
      }
      return decodeHTMLEntities;
  })();
  
  function getDetailString(info) {
	  return info['awardNumber'] + " : " + info['accountNumber'] + " : " + info['principalInvestigatorName'] + " : " + info['leadUnitName'];
  }
 
  function linkOnClick(link, info) {
	  var detailDiv = jQuery(link).siblings('div.awardHierarchy').first();
	  var firstCounter = 0;
      jQuery("div.awardHierarchy").filter(function() { return jQuery(this).attr('id') != jQuery(detailDiv).attr('id'); }).slideUp(300);
      
      if (jQuery(link).siblings('div.awardHierarchy').first().children('table:eq(0)').size() == 0) {
    	  tbodyTag1(info).appendTo(detailDiv);
          tbodyTag2(info, firstCounter).appendTo(detailDiv);
          
          if(info['awardDocumentFinalStatus']) { 
        	  tbodyTag3(info, firstCounter).appendTo(detailDiv);
          }
          if (jQuery(detailDiv).is(":hidden")) {
               jQuery(detailDiv).show();  
          }
      } else {
          jQuery(detailDiv).slideToggle(300); 
      }   
  }