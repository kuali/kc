 

var openAwards = [];
var prevScrollPosition;
function addOpenAward(award) {
	openAwards.push(award);
}
function setScrollPosition(scrollPos) {
	prevScrollPosition = scrollPos;
}
$(document).ready(function(){
  $('#awardHierarchyScollable').scroll(function() {
      $('input[name*=awardHierarchyScrollPosition]').attr('value', $(this).scrollTop());
  });
  $.ajaxSettings.cache = false; 
  $("#awardhierarchy").treeview({
             toggle: function() {
	  			 loadChildren(this);
                },
            animated: "fast",
            collapsed: true,
            control: "#treecontrol"
          });
  
  $(document).ajaxStart(function(){
     $("#loading").show();
   });
  $(document).ajaxComplete(function(){
     $("#loading").hide();
   });
  loadChildren(null);
}); // $(document).ready

function openPreviouslyOpenedAwards() {
	if (openAwards.length > 0) {
		for (var i = 0; i < openAwards.length; i++) {
			var openAward = openAwards[i];
			if ($('#li'+openAward).is('.expandable')) {
				$('#li'+openAward).find('div.expandable-hitarea:first').click();
				openAwards.splice(i, 1);
				break;
			}
		}
		if (openAwards.length == 0) { //we opened all previous awards
			$('#awardHierarchyScollable').scrollTop(prevScrollPosition);
		}
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
  function loadChildren(item) {
	  var liNode = null;
	  var ulNode = $('ul#awardhierarchy');
	  var awardNumber = '';
	  var addRA = 'N';	  
	  if (item != null) {
		  var liNode = $(item);
	      var ulNode = liNode.children('ul:eq(0)');
	      var awardNumber = getAwardNumber(liNode);
	      var addRA = 'E';
	  } 
	  
      if (ulNode.children('li').size() == 0) {
          $.ajax({
           url: 'awardHierarchyTimeAndMoneyAjax.do',
           type: 'GET',
           dataType: 'html',
           data:'awardNumber='+awardNumber+'&addRA=' + addRA + '&document.rootAwardNumber=' + $("#document\\.rootAwardNumber").attr("value") + '&currentAwardNumber='+ $("#currentAwardNumber").attr("value") + '&currentSeqNumber='+ $("#currentSeqNumber").attr("value"),
           cache: false,
           async: true,
           timeout: 5000,
           error: function(){
              alert('Error loading XML document');
              if (liNode != null) {
            	  liNode.find('div.collapsable-hitarea:first').click();
              }
              openPreviouslyOpenedAwards();
           },
           success: function(xml){
              $(xml).find('h3').each(function(){
            	  addAwardToHierarchy(this, ulNode);
              });
              fixDatePickers();
              openPreviouslyOpenedAwards();
           }
          });    
      }
      if (liNode != null) {
    	  if ($(liNode).is('.collapsable')) {
    	  	$(liNode).find("input[name*='awardHierarchyToggle']").attr('value', 'true');
    	  } else {
    		$(liNode).find("input[name*='awardHierarchyToggle']").attr('value', 'false');
    	  }
      }
  } // end loadChildren 

  
  function addAwardToHierarchy(str, parent) {
      var item_text = $(str).text();
      var racode = item_text.substring(0,item_text.indexOf("%3A")).trim();
      item_text = item_text.replace("%3A",":"); 
      //build the line description - will include the award number, pi, lead unit and
      //editable and/or summary fields for time and money.
      var idDiv = $('<div class="awardHierarchy"></div>').html(builduUi(item_text, racode));
      //add the div to the link
      var tag = $('<a class="awardHierarchy"></a>').html(idDiv);

      var listitem = $('<li class="closed awardhierarchy" id="li' + racode +'"></li>').html(tag);
      
      // need this ultag to force to display folder.
      var childUlTag = $('<ul></ul>');
      childUlTag.appendTo(listitem);
      listitem.appendTo($(parent));
      // also need this to show 'folder' icon
      $('#awardhierarchy').treeview({
         add: listitem
         
      });
  }
  
  /*
	 * Utility function to get code from 'code : description' This need to be
	 * refined because if code contains ':', then this is not working correctly.
	 */
  function getAwardNumber(node) {
	  var liNode = node;
	  if (!$(liNode).is('li.awardhierarchy')) {
		  liNode = $(node).parents('li.awardhierarchy:eq(0)');
	  }
      return $(liNode).attr('id').substring(2);
  }
  
  function hasFormAlreadyBeenSubmitted() {
      // return false;
  }  
    
    function builduUi(item_text, racode) { 
    	var original_item_text = item_text;
        var text1 = item_text.substring(0,item_text.indexOf("%3A")).trim();        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text2 = item_text.substring(0,item_text.indexOf("%3A")).trim();        
        if(text2.length == 1){
        	text2 = "";
        }
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text3 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        if(text3.length == 1){
        	text3 = "";
        }
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();//final expiration date
        var text4 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        if(text4.length == 1){
        	text4 = "";
        }
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text5 = item_text.substring(0,item_text.indexOf("%3A")).trim();					//amount obligated to date
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text6 = item_text.substring(0,item_text.indexOf("%3A")).trim();					//anticipated total amount
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();//Obligated Distributable
        var text7 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();//Anticipated Distributable
        var text8 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();//Obligated Distributed
        var text9 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();//Anticipated Distributed
        var text10 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text11 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();//obligated total direct
        var text12 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();//obligated total indirect
        var text13 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();//anticipated total direct
        var text14 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();//anticipated total indirect
        var text15 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
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
        
        var index = racode.indexOf("-");
        var racode2 = parseInt(racode.substring(index+1), 10);
        
        if($("#cancelOrFinalStatus").attr("value") == 1){
        	
//        	var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td>" +
//  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text2+"</td>" +
//  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text3+"</td>" +
//  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text4+"</td>" +
//  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text5+"</td>" +
//  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align:right; left: 1335px; border-collapse: collapse;\">"+text6+"</td>"
//  			+"</tr></tbody></table>";
        	
        	if($("#controlForAwardHierarchyView").attr("value") == 0){
		      	  var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top;\">"+txtImage+"&nbsp;"+text1+"</td>"+
					    "<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text2+"</td>" +
			  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text3+"</td>" +
			  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text4+"</td>" +
			  			"</tr></tbody></table>";
		        }else if($("#controlForAwardHierarchyView").attr("value") == 1){
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
		        }else if($("#controlForAwardHierarchyView").attr("value") == 2){
		        	if($("#directIndirectViewEnabled").attr("value") == 1){
			        	if($("#inSingleNodeHierarchy").attr("value") == 1){
			        		var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td>"+
			        		"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text2+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text3+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text4+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"			  			
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + racode2 + "].obligatedTotalDirect\" id=\"awardHierarchyNodeItems[" + racode2 + "].obligatedTotalDirect\""+ " value=\"" +text12 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"			  			
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + racode2 + "].obligatedTotalIndirect\" id=\"awardHierarchyNodeItems[" + racode2 + "].obligatedTotalIndirect\""+ " value=\"" +text13 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text5+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"	
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + racode2 + "].anticipatedTotalDirect\" id=\"awardHierarchyNodeItems[" + racode2 + "].anticipatedTotalDirect\""+ " value=\"" +text14 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"	
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + racode2 + "].anticipatedTotalIndirect\" id=\"awardHierarchyNodeItems[" + racode2 + "].anticipatedTotalIndirect\""+ " value=\"" +text15 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>"
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
		        		if($("#inSingleNodeHierarchy").attr("value") == 1){
			        		var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td>"+
			        		"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text2+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text3+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text4+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + racode2 + "].amountObligatedToDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].amountObligatedToDate\""+ " value=\"" +text5 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + racode2 + "].anticipatedTotalAmount\" id=\"awardHierarchyNodeItems[" + racode2 + "].anticipatedTotalAmount\""+ " value=\"" +text6 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>"
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
        } else { if($("#controlForAwardHierarchyView").attr("value") == 0){
		      	  var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top;\">"+txtImage+"&nbsp;"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse; align:right;\">"
			  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse; align:right;\">"
			  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td nowrap style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse; align:right;\">"
		//+ displaydate()
			  			+"<input type=\"text\"class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/></td>"
			  			+"</tr></tbody></table>";
		        }else if($("#controlForAwardHierarchyView").attr("value") == 1){
		        	var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td>"
		        		+"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 114px; border-collapse: collapse; vertical-align: top; align:right; \">"
			  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/></td>"
			  			+"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 114px; border-collapse: collapse; vertical-align: top; align:right;\">"	  			
			  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" class = 'datepicker' style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"
			  			//+ displaydate()
			  			+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 114px; border-collapse: collapse; vertical-align: top; align:right; \">"
			  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>" 
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
		        }else if($("#controlForAwardHierarchyView").attr("value") == 2){
		        	if($("#directIndirectViewEnabled").attr("value") == 1){
			        	if($("#inSingleNodeHierarchy").attr("value") == 1){
			        		var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; left: border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 110px; align:right; left: border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+
				  			"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"			  			
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + racode2 + "].obligatedTotalDirect\" id=\"awardHierarchyNodeItems[" + racode2 + "].obligatedTotalDirect\""+ " value=\"" +text12 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"			  			
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + racode2 + "].obligatedTotalIndirect\" id=\"awardHierarchyNodeItems[" + racode2 + "].obligatedTotalIndirect\""+ " value=\"" +text13 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text5+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"	
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + racode2 + "].anticipatedTotalDirect\" id=\"awardHierarchyNodeItems[" + racode2 + "].anticipatedTotalDirect\""+ " value=\"" +text14 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"	
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + racode2 + "].anticipatedTotalIndirect\" id=\"awardHierarchyNodeItems[" + racode2 + "].anticipatedTotalIndirect\""+ " value=\"" +text15 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align:right; left: 1335px; border-collapse: collapse;\">"+text6+"</td>"
				  			+"</tr></tbody></table>";	
			        	}else {
			    			var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 110px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text12+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text13+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text5+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text14+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text15+"</td>"
				  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align:right; left: 1335px; border-collapse: collapse;\">"+text6+"</td>" +"</tr></tbody></table>";
			        	} 
		        	}else{
		        		if($("#inSingleNodeHierarchy").attr("value") == 1){
			        		var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; left: border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 110px; align:right; left: border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+
				  			"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + racode2 + "].amountObligatedToDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].amountObligatedToDate\""+ " value=\"" +text5 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" +
				  			"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'input' name=\"awardHierarchyNodeItems[" + racode2 + "].anticipatedTotalAmount\" id=\"awardHierarchyNodeItems[" + racode2 + "].anticipatedTotalAmount\""+ " value=\"" +text6 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>"
				  			+"</tr></tbody></table>";	
			        	}else {
			        		//need this one without inputs for date fields for KRACOEUS-4664
			    			var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 110px; align:right; border-collapse: collapse; \">"
				  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"
				  			+text5+"</td><td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align:right; left: 1335px; border-collapse: collapse;\">"
				  			+text6+"</td>"
				  			+"</tr></tbody></table>";
			        	} 
		        	}
		        }
        }
        abc += '<input type="hidden" value="false" name="awardHierarchyToggle(' + racode + ')"/>';
 	   return abc; 
    }


