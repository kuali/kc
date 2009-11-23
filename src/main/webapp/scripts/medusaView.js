    var node;
    var i = 1;
    var j = 1;
    var removedNode=null;
    var cutNode;
    var sqlScripts = "";
    var ulTagId;
    var sqls = [];
    var sqlidx = 0;
    var deletedNodes="";
    var newNodes=";";
    var loadedidx=0;
      
    $(document).ready(function(){
      $.ajaxSettings.cache = false; 
      $("#medusaview").treeview({
                 toggle: function() {
                     var idstr=$(this).attr("id").substring(4);
                     var tagId = "listcontrol"+idstr;
                     var divId = "listcontent"+idstr;
                 
                     $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                     $("#"+divId).slideToggle(300);

                     //loadChildrenRA($("#itemText"+idstr).text(), tagId);
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
    });
         
    function processError(){
        alert('Error loading XML document');
     }
    
    function processData(xml){
     	
        $(xml).find('h3').each(function(){
        var item_text = $(this).text();
        i++;
        var viewSelector = item_text.substring(0,item_text.indexOf("%2A")).trim();
        item_text = item_text.substring(item_text.indexOf("%2A")+3, item_text.length).trim();
        var racode = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        var childNodesText = item_text.substring(item_text.indexOf("%5A")+3, item_text.indexOf("%5B")).trim();

        var detailsString = item_text.substring(item_text.indexOf("%31")+3,item_text.indexOf("%32")).trim();
        item_text = item_text.substring(0,item_text.indexOf("%31")) + item_text.substring(item_text.indexOf("%32")+3,item_text.length);
        
        var id = "item"+i;
        var tagId = "listcontrol"+i;
        var divId = "listcontent"+i;
        
       // NOTES : if use 'div', then FF will display the '+' and idDiv in
			// separate lines. IE7 is fine
       // But 'IE7 has problem with 'span'
       
       var idDiv;
       if ( jQuery.browser.msie ) { 
            idDiv = $('<div></div>').attr("id","itemText"+i).html(builduUi(item_text)); // for
																					// later
																					// change
																					// RA
																					// description
       } else {
            idDiv = $('<span>').attr("id","itemText"+i).html(builduUi(item_text)); // for
																			// later
																			// change
																			// RA
																			// description
       }
            
        var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(idDiv);
        var div = $('<div  class="hierarchydetail" style="margin-top:2px;" align="left"></div>').attr("id",divId);
    	var hidracode = $('<input type="hidden" id = "racode" name = "racode" />').attr("id",
 			"racode" + i).attr("name", "racode" + i).attr("value",racode);
    	hidracode.appendTo(div);
    	
        tag.click(
                function()
                {
                    // alert ("sibling
						// "+$(this).siblings('div:eq(0)').attr("id"));
                    $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                    var idx = $(this).attr("id").substring(11);
                    if ($(this).siblings('div:eq(1)').children('table:eq(0)').size() == 0) {                    	  
                  	  tbodyTag(detailsString, "item"+idx, viewSelector).appendTo($("#listcontent"+idx));

                        if ($("#"+divId).is(":hidden")) {
                            // alert(divId + " hidden0");
                             $("#listcontent"+idx).show();
                             // $("#listcontent"+idx).slideToggle(300);
                        }
                    } else {
                        $("#listcontent"+idx).slideToggle(300);
                    }
                }
            );
    	
        var listitem = $('<li class="closed"></li>').attr("id",id).html(tag);

        ulTagId = "browser";        
        div.appendTo(listitem);
        // need this ultag to force to display folder.
        var childUlTag = $('<ul></ul>').attr("id","ul"+i);
        childUlTag.appendTo(listitem);           
        listitem.appendTo('ul#medusaview');
                // also need this to show 'folder' icon
        
        $('#medusaview').treeview({
           add: listitem
           
        });
        if(viewSelector == "A"){
        	loadChildrenAwardView(viewSelector, item_text, tagId,childNodesText);	
        }else if(viewSelector == "P"){
        	loadChildrenProposalView(viewSelector, item_text, tagId,childNodesText);
        }	
        });
     }
    
    function revString(str) { 
    	   var retStr = "";    	   
    	   for (j=str.length - j ; j > - 1 ; j--){ 
    	      retStr += str.substr(j,1); 
    	   } 
    	   return retStr; 
    }
    
   
    
    function builduUi(item_text) { 
    	var text1 = item_text.substring(0,item_text.indexOf("%3A")).trim();
    	var module1 = item_text.substring(0,1);
    	var txtImage = "";
        if(module1 == 'D'){
        	txtImage = "<img src=\"static/images/developmentproposal12.gif\" />";
        }else if(module1=='I'){
        	txtImage = "<img src=\"static/images/instituteproposal12.gif\" />";
        }else{
        	txtImage = "<img src=\"static/images/sponsor12.gif\" />";
        }	
        
      	var abc = "<table style=\"border: medium none ; padding: 0px; width: 100%; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top;\">" + txtImage +" " + text1 
      				+ "</td></tr></tbody></table>";
        
      	return abc; 
    }
  
  function tbodyTag(name, id, module) {
	  if(module == 'A'){
		  return displayAwardDetails(name, id);
	  }else if(module == 'P'){
		  return displayInstitutionalProposalDetails(name, id);
	  }else if(module == 'D'){
		  return displayDevelopmentProposalDetails(name, id);
	  }  
  }

  function displayAwardDetails(name, id) {
	
	var tblTag = $('<table id="tbody1_1" style="border: 1px solid rgb(147, 147, 147); padding: 0px; width: 97%; border-collapse: collapse;"></table>')
	
    // 1st tr	  
	var thTag1Text = name.substring(0, name.indexOf(":"));
	name = name.substring(name.indexOf(":")+1, name.length);
    var trTag1 = $('<tr></tr>');
    var thTag1=$('<th colspan="4" style="border-style: solid; text-align:left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;"></th>').html(thTag1Text);
    trTag1.html(thTag1);
    
    // 2nd tr
    var trTag2 = $('<tr></tr>');    
    var tdTag2_1 = $('<td align="right" colspan="2" class="subelementheader"></td>');
    var openAwardButton = $('<input type="image" title="Open Award" alt="Open Award" style="border: medium none ;" src="static/images/tinybutton-openaward.gif"/>').attr("property","methodToCall.copyAward.awardNumber").attr("name","methodToCall.copyAward.awardNumber");
    openAwardButton.appendTo(tdTag2_1);
    trTag2.html(tdTag2_1);
    var tdTag2_2 = $('<td align="left" colspan="2" class="subelementheader"></td>');
    var notesButton = $('<input type="image" title="Notes" alt="Notes" style="border: medium none ;" src="static/images/tinybutton-notes.gif"/>').attr("property","methodToCall.copyAward.awardNumber").attr("name","methodToCall.copyAward.awardNumber");
    notesButton.appendTo(tdTag2_2);
    tdTag2_2.appendTo(trTag2);
    
    //3rd tr
    var trTag3 = $('<tr></tr>');
    var thTag3=$('<th colspan="4" style="border-style: solid; text-align:left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;"></th>').html("Summary");
    trTag3.html(thTag3);
    
    //4th tr
    var tdTag4_2Text = name.substring(0, name.indexOf(":"));
	name = name.substring(name.indexOf(":")+1, name.length);
	var tdTag4_4Text = name.substring(0, name.indexOf(":"));
	name = name.substring(name.indexOf(":")+1, name.length);
	
    
    var trTag4 = $('<tr></tr>');
    var thTag4_1 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Award ID:");
    var tdTag4_2 = $('<td align="left" ></td>').html(tdTag4_2Text);
    var thTag4_3 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Award Type:");
    var tdTag4_4 = $('<td align="left" ></td>').html(tdTag4_4Text);
    trTag4.html(thTag4_1);
    tdTag4_2.appendTo(trTag4);
    thTag4_3.appendTo(trTag4);
    tdTag4_4.appendTo(trTag4);
    
    //5th tr
    var tdTag5_2Text = name.substring(0, name.indexOf(":"));
	name = name.substring(name.indexOf(":")+1, name.length);
	var tdTag5_4Text = name.substring(0, name.indexOf(":"));
	name = name.substring(name.indexOf(":")+1, name.length);
    
    var trTag5 = $('<tr></tr>');
    var thTag5_1 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Sponsor Award ID:");
    var tdTag5_2 = $('<td align="left" ></td>').html(tdTag5_2Text);
    var thTag5_3 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Activity Type:");
    var tdTag5_4 = $('<td align="left" ></td>').html(tdTag5_4Text);
    trTag5.html(thTag5_1);
    tdTag5_2.appendTo(trTag5);
    thTag5_3.appendTo(trTag5);
    tdTag5_4.appendTo(trTag5);
    
    //6th tr
    var tdTag6_2Text = name.substring(0, name.indexOf(":"));
	name = name.substring(name.indexOf(":")+1, name.length);
		
    var trTag6 = $('<tr></tr>');
    var thTag6_1 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Award Status:");
    var tdTag6_2 = $('<td align="left" ></td>').html(tdTag6_2Text);
    var thTag6_3 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>');
    var tdTag6_4 = $('<td align="left" ></td>');
    trTag6.html(thTag6_1);
    tdTag6_2.appendTo(trTag6);
    thTag6_3.appendTo(trTag6);
    tdTag6_4.appendTo(trTag6);
    
    //7th tr
    var tdTag7_2Text = name.substring(0, name.indexOf(":"));
	name = name.substring(name.indexOf(":")+1, name.length);
	
    var trTag7 = $('<tr></tr>');
    var thTag7_1 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Title:");
    var tdTag7_2 = $('<td align="left" colspan="3" ></td>').html(tdTag7_2Text);    
    trTag7.html(thTag7_1);
    tdTag7_2.appendTo(trTag7);
    
    //8th tr
    var trTag8 = $('<tr></tr>');
    var thTag8=$('<th colspan="4" style="border-style: solid; text-align:left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;"></th>').html("Dates & Amounts");
    trTag8.html(thTag8);
    
    //9th tr
    var tdTag9_2Text = name.substring(0, name.indexOf(":"));
	name = name.substring(name.indexOf(":")+1, name.length);
    var trTag9 = $('<tr></tr>');
    var thTag9_1 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Sponsor:");
    var tdTag9_2 = $('<td align="left" colspan="3" ></td>').html(tdTag9_2Text);    
    trTag9.html(thTag9_1);
    tdTag9_2.appendTo(trTag9);
    
    //10th tr
    var tdTag10_2Text = name.substring(0, name.indexOf(":"));
	name = name.substring(name.indexOf(":")+1, name.length);
	var tdTag10_4Text = name.substring(0, name.indexOf(":"));
	name = name.substring(name.indexOf(":")+1, name.length);
    var trTag10 = $('<tr></tr>');
    var thTag10_1 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Project Start Date:");
    var tdTag10_2 = $('<td align="left" ></td>').html(tdTag10_2Text);
    var thTag10_3 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Obligation Start Date");
    var tdTag10_4 = $('<td align="left" ></td>').html(tdTag10_4Text);;
    trTag10.html(thTag10_1);
    tdTag10_2.appendTo(trTag10);
    thTag10_3.appendTo(trTag10);
    tdTag10_4.appendTo(trTag10);
    
    //11th tr
    var tdTag11_2Text = name.substring(0, name.indexOf(":"));
	name = name.substring(name.indexOf(":")+1, name.length);
	var tdTag11_4Text = name.substring(0, name.indexOf(":"));
	name = name.substring(name.indexOf(":")+1, name.length);
    var trTag11 = $('<tr></tr>');
    var thTag11_1 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Project End Date:");
    var tdTag11_2 = $('<td align="left" ></td>').html(tdTag11_2Text);
    var thTag11_3 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Obligation End Date");
    var tdTag11_4 = $('<td align="left" ></td>').html(tdTag11_4Text);;
    trTag11.html(thTag11_1);
    tdTag11_2.appendTo(trTag11);
    thTag11_3.appendTo(trTag11);
    tdTag11_4.appendTo(trTag11);
    
    //12th tr
    var tdTag12_2Text = name.substring(0, name.indexOf(":"));
	name = name.substring(name.indexOf(":")+1, name.length);
	var tdTag12_4Text = name.substring(0, name.indexOf(":"));
	name = name.substring(name.indexOf(":")+1, name.length);
    var trTag12 = $('<tr></tr>');
    var thTag12_1 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Anticipated Amount:");
    var tdTag12_2 = $('<td align="left" ></td>').html("$"+tdTag12_2Text);
    var thTag12_3 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Obligated Amount");
    var tdTag12_4 = $('<td align="left" ></td>').html("$"+tdTag12_2Text);
    trTag12.html(thTag12_1);
    tdTag12_2.appendTo(trTag12);
    thTag12_3.appendTo(trTag12);
    tdTag12_4.appendTo(trTag12);
    
    //13th tr
    var trTag13 = $('<tr></tr>');
    var thTag13=$('<th colspan="4" style="border-style: solid; text-align:left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;"></th>').html("Award Details Recorded");
    trTag13.html(thTag13);
    
    //14th tr
    var trTag14 = $('<tr></tr>');
    var thTag14_1 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Approved Subaward?");
    var tdTag14_2 = $('<td align="left" ></td>').html("");
    var thTag14_3 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Payment Schedule?");
    var tdTag14_4 = $('<td align="left" ></td>').html("");
    trTag14.html(thTag14_1);
    tdTag14_2.appendTo(trTag14);
    thTag14_3.appendTo(trTag14);
    tdTag14_4.appendTo(trTag14);
    
    //15th tr
    var trTag15 = $('<tr></tr>');
    var thTag15_1 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Approved Equipment?");
    var tdTag15_2 = $('<td align="left" ></td>').html("");
    var thTag15_3 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Sponsor Funding Transferred?");
    var tdTag15_4 = $('<td align="left" ></td>').html("");
    trTag15.html(thTag15_1);
    tdTag15_2.appendTo(trTag15);
    thTag15_3.appendTo(trTag15);
    tdTag15_4.appendTo(trTag15);
    
    //16th tr
    var trTag16 = $('<tr></tr>');
    var thTag16_1 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Approved Foreign Travel?");
    var tdTag16_2 = $('<td align="left" ></td>').html("");
    var thTag16_3 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Cost Share?");
    var tdTag16_4 = $('<td align="left" ></td>');
    trTag16.html(thTag16_1);
    tdTag16_2.appendTo(trTag16);
    thTag16_3.appendTo(trTag16);
    tdTag16_4.appendTo(trTag16);
    
    //17th tr  
    var trTag17 = $('<tr></tr>');
    var thTag17_1 = $('<th style="border: 1px solid rgb(147, 147, 147); text-align:right; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("F&A?");
    var tdTag17_2 = $('<td colspan="3" align="left" class="subelementheader"></td>').html("1243");
    trTag17.html(thTag17_1);
    tdTag17_2.appendTo(trTag17);
    
    //18th tr
    var trTag18 = $('<tr></tr>');
    var thTag18=$('<th colspan="4" style="border-style: solid; text-align:left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;"></th>').html("Investigators");
    trTag18.html(thTag18);
    
    //19th tr
    var trTag19 = $('<tr></tr>');
    var thTag19_1 = $('<th colspan="2" style="border: 1px solid rgb(147, 147, 147); text-align:center; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Investigators");
    var thTag19_2 = $('<th colspan="2" style="border: 1px solid rgb(147, 147, 147); text-align:center; padding: 3px; border-collapse: collapse; vertical-align: top; background-color: rgb(230, 230, 230); background-image: none; width: 160px;"></th>').html("Units");    
    trTag19.html(thTag19_1);
    thTag19_2.appendTo(trTag19);
    
    trTag1.appendTo(tblTag);
    trTag2.appendTo(tblTag);
    trTag3.appendTo(tblTag);
    trTag4.appendTo(tblTag);
    trTag5.appendTo(tblTag);
    trTag6.appendTo(tblTag);
    trTag7.appendTo(tblTag);
    trTag8.appendTo(tblTag);
    trTag9.appendTo(tblTag);
    trTag10.appendTo(tblTag);
    trTag11.appendTo(tblTag);
    trTag12.appendTo(tblTag);
    trTag13.appendTo(tblTag);
    trTag14.appendTo(tblTag);
    trTag15.appendTo(tblTag);
    trTag16.appendTo(tblTag);
    trTag17.appendTo(tblTag);
    trTag18.appendTo(tblTag);
    trTag19.appendTo(tblTag);
    
    return tblTag;
  }    

  
  function displayInstitutionalProposalDetails(name, id){
	  
	  var tblTag = $('<table id="tbody1_1" style="border: 1px solid rgb(147, 147, 147); padding: 0px; width: 97%; border-collapse: collapse;"></table>')
	  
	  // 1st tr
	  var thTag1_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  
	  var trTag1 = $('<tr></tr>');
	  var thTag1=$('<th colspan="4" style="border-style: solid; text-align:left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;"></th>').html("Institutional Proposal");
	  trTag1.html(thTag1);
	  
	  var trTag2 = $('<tr></tr>');
	  var tdTag1=$('<td></td>');
	  
	  var tblTag2 = $('<table id="tbody1_1" style="border: 1px solid rgb(147, 147, 147); padding: 0px; width: 97%; border-collapse: collapse;"></table>');
	  
	  var trTag2_1 = $('<tr></tr>');
	  var tdTag2_1 = $('<td colspan="4" style="border:1px solid rgb(147, 147, 147); text-align:center;"></td');
	  
	  var openProposalButton = $('<input type="image" title="Open Proposal" alt="Open Proposal" style="border: medium none ;" src="static/images/tinybutton-openproposal.gif"/>').attr("property","methodToCall.copyAward.awardNumber").attr("name","methodToCall.copyAward.awardNumber");
	  var openNotesButton = $('<input type="image" title="Notes" alt="Notes" style="border: medium none ;" src="static/images/tinybutton-notes.gif"/>').attr("property","methodToCall.copyAward.awardNumber").attr("name","methodToCall.copyAward.awardNumber");
	  var openNegotiationButton = $('<input type="image" title="Negotiation" alt="Negotiation" style="border: medium none ;" src="static/images/tinybutton-negotiation.gif"/>').attr("property","methodToCall.copyAward.awardNumber").attr("name","methodToCall.copyAward.awardNumber");
	  openProposalButton.appendTo(tdTag2_1);
	  openNotesButton.appendTo(tdTag2_1);
	  openNegotiationButton.appendTo(tdTag2_1);	  
	  trTag2_1.html(tdTag2_1);
	
	  var trTag2_2 = $('<tr></tr>');
	  var thTag2_2_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:center;"></th>').html("Proposal No.");
	  var thTag2_2_2 = $('<th colspan="2" style="border:1px solid rgb(147, 147, 147); text-align:center;"></th>').html("Title");
	  var thTag2_2_3 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:center;"></th>').html("Status");
	  trTag2_2.html(thTag2_2_1);
	  thTag2_2_2.appendTo(trTag2_2);
	  thTag2_2_3.appendTo(trTag2_2);
	  
	  var tdTag2_4_1_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_4_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_4_3_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_4 = $('<tr></tr>');
	  var tdTag2_4_1 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:center;"></td>').html(tdTag2_4_1_Text);
	  var tdTag2_4_2 = $('<td colspan="2" style="border:1px solid rgb(147, 147, 147); text-align:center;"></td>').html(tdTag2_4_2_Text);
	  var tdTag2_4_3 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:center;"></td>').html(tdTag2_4_3_Text);
	  trTag2_4.html(tdTag2_4_1);
	  tdTag2_4_2.appendTo(trTag2_4);
	  tdTag2_4_3.appendTo(trTag2_4);
	  
	  var tdTag2_5_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_5_4_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_5 = $('<tr></tr>');
	  var thTag2_5_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Proposal Type:");
	  var tdTag2_5_2 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_5_2_Text);
	  var thTag2_5_3 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Sponsor Prpsl No:");
	  var tdTag2_5_4 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_5_4_Text);
	  trTag2_5.html(thTag2_5_1);
	  tdTag2_5_2.appendTo(trTag2_5);
	  thTag2_5_3.appendTo(trTag2_5);
	  tdTag2_5_4.appendTo(trTag2_5);
	    
	  var tdTag2_6_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_6_4_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_6 = $('<tr></tr>');
	  var thTag2_6_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Account:");
	  var tdTag2_6_2 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_6_2_Text);
	  var thTag2_6_3 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Activity Type:");
	  var tdTag2_6_4 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_6_4_Text);
	  trTag2_6.html(thTag2_6_1);
	  tdTag2_6_2.appendTo(trTag2_6);
	  thTag2_6_3.appendTo(trTag2_6);
	  tdTag2_6_4.appendTo(trTag2_6);
	    
	  var tdTag2_7_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_7_4_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_7 = $('<tr></tr>');
	  var thTag2_7_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("NSF Code:");
	  var tdTag2_7_2 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_7_2_Text);
	  var thTag2_7_3 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Notice of Opp:");
	  var tdTag2_7_4 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_7_2_Text);
	  trTag2_7.html(thTag2_7_1);
	  tdTag2_7_2.appendTo(trTag2_7);
	  thTag2_7_3.appendTo(trTag2_7);
	  tdTag2_7_4.appendTo(trTag2_7);
	  
	  var tdTag2_81_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_81 = $('<tr></tr>');
	  var thTag2_81_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Sponsor");
	  var tdTag2_81_2 = $('<td colspan="3" style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_81_2_Text);
	  trTag2_81.html(thTag2_81_1);
	  tdTag2_81_2.appendTo(trTag2_81);
	  
	  var tdTag2_8_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_8 = $('<tr></tr>');
	  var thTag2_8_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Prime Sponsor");
	  var tdTag2_8_2 = $('<td colspan="3" style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_8_2_Text);
	  trTag2_8.html(thTag2_8_1);
	  tdTag2_8_2.appendTo(trTag2_8);
	  
	  var trTag2_9 = $('<tr></tr>');
	  var thTag2_9_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>')
	  var thTag2_9_2 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:left;"></th>').html("Initial Period");
	  var thTag2_9_3 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Total Period");
	  var thTag2_9_4 = $('<th rowspan="6" style="border:1px solid rgb(147, 147, 147); text-align:left;"></th>');
	  trTag2_9.html(thTag2_9_1);
	  thTag2_9_2.appendTo(trTag2_9);
	  thTag2_9_3.appendTo(trTag2_9);
	  thTag2_9_4.appendTo(trTag2_9);
	  
	  var tdTag2_10_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_10_3_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_10 = $('<tr></tr>');
	  var thTag2_10_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Requested Start Date:");
	  var tdTag2_10_2 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_10_2_Text);
	  var tdTag2_10_3 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:right;"></td>').html(tdTag2_10_3_Text);	  
	  trTag2_10.html(thTag2_10_1);
	  tdTag2_10_2.appendTo(trTag2_10);
	  tdTag2_10_3.appendTo(trTag2_10);

	  var tdTag2_11_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_11_3_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_11 = $('<tr></tr>');
	  var thTag2_11_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Requested End Date:");
	  var tdTag2_11_2 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_11_2_Text);
	  var tdTag2_11_3 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:right;"></td>').html(tdTag2_11_3_Text);	  
	  trTag2_11.html(thTag2_11_1);
	  tdTag2_11_2.appendTo(trTag2_11);
	  tdTag2_11_3.appendTo(trTag2_11);
	  
	  var tdTag2_12_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_12_3_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_12 = $('<tr></tr>');
	  var thTag2_12_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Total Direct Cost:");
	  var tdTag2_12_2 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_12_2_Text);
	  var tdTag2_12_3 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:right;"></td>').html(tdTag2_12_3_Text);	  
	  trTag2_12.html(thTag2_12_1);
	  tdTag2_12_2.appendTo(trTag2_12);
	  tdTag2_12_3.appendTo(trTag2_12);
	  
	  var tdTag2_13_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_13_3_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_13 = $('<tr></tr>');
	  var thTag2_13_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Total Indirect Cost:");
	  var tdTag2_13_2 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_13_2_Text);
	  var tdTag2_13_3 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:right;"></td>').html(tdTag2_13_3_Text);	  
	  trTag2_13.html(thTag2_13_1);
	  tdTag2_13_2.appendTo(trTag2_13);
	  tdTag2_13_3.appendTo(trTag2_13);
	  
	  var tdTag2_14_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_14_3_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_14 = $('<tr></tr>');
	  var thTag2_14_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Total All Cost:");
	  var tdTag2_14_2 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_14_2_Text);
	  var tdTag2_14_3 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:right;"></td>').html(tdTag2_14_3_Text);	  
	  trTag2_14.html(thTag2_14_1);
	  tdTag2_14_2.appendTo(trTag2_14);
	  tdTag2_14_3.appendTo(trTag2_14);
	  
	  var trTag2_15 = $('<tr></tr>');
	  var thTag2_15_1 = $('<th colspan="2" style="border:1px solid rgb(147, 147, 147); text-align:center;"></th>').html("Investigators");
	  var thTag2_15_2 = $('<th colspan="2" style="border:1px solid rgb(147, 147, 147); text-align:center;"></th>').html("Units");
	  trTag2_15.html(thTag2_15_1);
	  thTag2_15_2.appendTo(trTag2_15);
	  
	  var tdTag2_16_1_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_16_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  tdTag2_16_2_Text = replaceAll(tdTag2_16_2_Text,";",":");
	  var trTag2_16 = $('<tr></tr>');
	  var tdTag2_16_1 = $('<td colspan="2" style="border:1px solid rgb(147, 147, 147); text-align:center;"></td>').html(tdTag2_16_1_Text);
	  var tdTag2_16_2 = $('<td colspan="2" style="border:1px solid rgb(147, 147, 147); text-align:center;"></td>').html(tdTag2_16_2_Text);
	  trTag2_16.html(tdTag2_16_1);
	  tdTag2_16_2.appendTo(trTag2_16);
	  
	  trTag2_1.appendTo(tblTag2);
	  trTag2_2.appendTo(tblTag2);
	  trTag2_4.appendTo(tblTag2);
	  trTag2_5.appendTo(tblTag2);
	  trTag2_6.appendTo(tblTag2);
	  trTag2_7.appendTo(tblTag2);
	  trTag2_8.appendTo(tblTag2);
	  trTag2_9.appendTo(tblTag2);
	  trTag2_10.appendTo(tblTag2);
	  trTag2_11.appendTo(tblTag2);
	  trTag2_12.appendTo(tblTag2);
	  trTag2_13.appendTo(tblTag2);
	  trTag2_14.appendTo(tblTag2);
	  trTag2_15.appendTo(tblTag2);
	  trTag2_16.appendTo(tblTag2);
	  
	  tdTag1.html(tblTag2);
	  tdTag1.appendTo(trTag2);
	  trTag1.appendTo(tblTag);
	  trTag2.appendTo(tblTag);
	  
	  return tblTag;
  }
  
  function displayDevelopmentProposalDetails(name, id){
	  
	  var tblTag = $('<table id="tbody1_1" style="border: 1px solid rgb(147, 147, 147); padding: 0px; width: 97%; border-collapse: collapse;"></table>')
	  
	  // 1st tr
	  var thTag1_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag1 = $('<tr></tr>');
	  var thTag1=$('<th colspan="4" style="border-style: solid; text-align:left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;"></th>').html(thTag1_Text);
	  trTag1.html(thTag1);
	  
	  var trTag2 = $('<tr></tr>');
	  var tdTag1=$('<td></td>');
	  
	  var tblTag2 = $('<table id="tbody1_1" style="border: 1px solid rgb(147, 147, 147); padding: 0px; width: 97%; border-collapse: collapse;"></table>');
	  
	  var trTag2_1 = $('<tr></tr>');
	  var tdTag2_1 = $('<td colspan="4" style="border:1px solid rgb(147, 147, 147); text-align:center;"></td');
	  
	  var openProposalButton = $('<input type="image" title="Open Proposal" alt="Open Proposal" style="border: medium none ;" src="static/images/tinybutton-openproposal.gif"/>').attr("property","methodToCall.copyAward.awardNumber").attr("name","methodToCall.copyAward.awardNumber");
	  var openNotesButton = $('<input type="image" title="Notes" alt="Notes" style="border: medium none ;" src="static/images/tinybutton-notes.gif"/>').attr("property","methodToCall.copyAward.awardNumber").attr("name","methodToCall.copyAward.awardNumber");
	  openProposalButton.appendTo(tdTag2_1);
	  openNotesButton.appendTo(tdTag2_1);
	  trTag2_1.html(tdTag2_1);
	
	  var tdTag2_2_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_2_4_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_2 = $('<tr></tr>');
	  var thTag2_2_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Proposal No:");
	  var tdTag2_2_2 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_2_2_Text);
	  var thTag2_2_3 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Status:");
	  var tdTag2_2_4 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_2_4_Text);
	  trTag2_2.html(thTag2_2_1);
	  tdTag2_2_2.appendTo(trTag2_2);
	  thTag2_2_3.appendTo(trTag2_2);
	  tdTag2_2_4.appendTo(trTag2_2);
	  
	  var tdTag2_3_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_3 = $('<tr></tr>');
	  var thTag2_3_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Lead Unit");
	  tdTag2_3_2_Text = replaceAll(tdTag2_3_2_Text,";",":");
	  var tdTag2_3_2 = $('<td colspan="3" style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_3_2_Text);
	  trTag2_3.html(thTag2_3_1);
	  tdTag2_3_2.appendTo(trTag2_3);
	  
	  var tdTag2_4_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_4_4_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_4 = $('<tr></tr>');
	  var thTag2_4_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Start Date:");
	  var tdTag2_4_2 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_4_2_Text);
	  var thTag2_4_3 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("End Date:");
	  var tdTag2_4_4 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_4_4_Text);
	  trTag2_4.html(thTag2_4_1);
	  tdTag2_4_2.appendTo(trTag2_4);
	  thTag2_4_3.appendTo(trTag2_4);
	  tdTag2_4_4.appendTo(trTag2_4);
	  
	  var tdTag2_5_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_5 = $('<tr></tr>');
	  var thTag2_5_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Title:");
	  var tdTag2_5_2 = $('<td colspan="3" style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_5_2_Text);
	  trTag2_5.html(thTag2_5_1);
	  tdTag2_5_2.appendTo(trTag2_5);
	    
	  var tdTag2_6_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_6 = $('<tr></tr>');
	  var thTag2_6_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Proposal Type:");
	  var tdTag2_6_2 = $('<td colspan="3" style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_6_2_Text);
	  trTag2_6.html(thTag2_6_1);
	  tdTag2_6_2.appendTo(trTag2_6);
	    
	  var tdTag2_7_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_7 = $('<tr></tr>');
	  var thTag2_7_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("NSF Code:");
	  var tdTag2_7_2 = $('<td colspan="3" style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_7_2_Text);
	  trTag2_7.html(thTag2_7_1);
	  tdTag2_7_2.appendTo(trTag2_7);
	  
	  var tdTag2_8_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_8 = $('<tr></tr>');
	  var thTag2_8_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Sponsor:");
	  var tdTag2_8_2 = $('<td colspan="3" style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_8_2_Text);
	  trTag2_8.html(thTag2_8_1);
	  tdTag2_8_2.appendTo(trTag2_8);
	  
	  var tdTag2_9_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);	  
	  var trTag2_9 = $('<tr></tr>');
	  var thTag2_9_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Prime Sponsor:");
	  var tdTag2_9_2 = $('<td colspan="3" style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_9_2_Text);
	  trTag2_9.html(thTag2_9_1);
	  tdTag2_9_2.appendTo(trTag2_9);
	  
	  var tdTag2_10_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_10_4_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_10 = $('<tr></tr>');
	  var thTag2_10_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Sponsor Proposal No:");
	  var tdTag2_10_2 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_10_2_Text);
	  var thTag2_10_3 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Activity Type:");
	  var tdTag2_10_4 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_10_4_Text);
	  trTag2_10.html(thTag2_10_1);
	  tdTag2_10_2.appendTo(trTag2_10);
	  thTag2_10_3.appendTo(trTag2_10);
	  tdTag2_10_4.appendTo(trTag2_10);
	  
	  var tdTag2_11_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_11 = $('<tr></tr>');
	  var thTag2_11_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Program Title:");
	  var tdTag2_11_2 = $('<td colspan="3" style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_11_2_Text);
	  trTag2_11.html(thTag2_11_1);
	  tdTag2_11_2.appendTo(trTag2_11);
	  
	  var tdTag2_12_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_12_4_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_12 = $('<tr></tr>');
	  var thTag2_12_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Notice of Opportunity:");
	  var tdTag2_12_2 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_12_2_Text);
	  var thTag2_12_3 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Program No:");
	  var tdTag2_12_4 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:right;"></td>').html(tdTag2_12_4_Text);	  
	  trTag2_12.html(thTag2_12_1);
	  tdTag2_12_2.appendTo(trTag2_12);
	  thTag2_12_3.appendTo(trTag2_12);
	  tdTag2_12_4.appendTo(trTag2_12);
	  
	  var tdTag2_13_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_13_4_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var trTag2_13 = $('<tr></tr>');
	  var thTag2_13_1 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Narrative:");
	  var tdTag2_13_2 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:left;"></td>').html(tdTag2_13_2_Text);
	  var thTag2_13_3 = $('<th style="border:1px solid rgb(147, 147, 147); text-align:right;"></th>').html("Budget:");
	  var tdTag2_13_4 = $('<td style="border:1px solid rgb(147, 147, 147); text-align:right;"></td>').html(tdTag2_13_4_Text);	  
	  trTag2_13.html(thTag2_13_1);
	  tdTag2_13_2.appendTo(trTag2_13);
	  thTag2_13_3.appendTo(trTag2_13);
	  tdTag2_13_4.appendTo(trTag2_13);
	  
	  var trTag2_15 = $('<tr></tr>');
	  var thTag2_15_1 = $('<th colspan="2" style="border:1px solid rgb(147, 147, 147); text-align:center;"></th>').html("Investigators");
	  var thTag2_15_2 = $('<th colspan="2" style="border:1px solid rgb(147, 147, 147); text-align:center;"></th>').html("Units");
	  trTag2_15.html(thTag2_15_1);
	  thTag2_15_2.appendTo(trTag2_15);
	  
	  var tdTag2_16_2_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  var tdTag2_16_4_Text = name.substring(0,name.indexOf(":"));
	  name = name.substring(name.indexOf(":")+1,name.length);
	  tdTag2_16_2_Text = replaceAll(tdTag2_16_2_Text,";",":");
	  tdTag2_16_4_Text = replaceAll(tdTag2_16_4_Text,";",":");
	  var trTag2_16 = $('<tr></tr>');
	  var tdTag2_16_1 = $('<td colspan="2" style="border:1px solid rgb(147, 147, 147); text-align:center;"></td>').html(tdTag2_16_2_Text);
	  var tdTag2_16_2 = $('<td colspan="2" style="border:1px solid rgb(147, 147, 147); text-align:center;"></td>').html(tdTag2_16_4_Text);
	  trTag2_16.html(tdTag2_16_1);
	  tdTag2_16_2.appendTo(trTag2_16);
	  
	  trTag2_1.appendTo(tblTag2);
	  trTag2_2.appendTo(tblTag2);
	  trTag2_3.appendTo(tblTag2);
	  trTag2_4.appendTo(tblTag2);
	  trTag2_5.appendTo(tblTag2);
	  trTag2_6.appendTo(tblTag2);
	  trTag2_7.appendTo(tblTag2);
	  trTag2_8.appendTo(tblTag2);
	  trTag2_9.appendTo(tblTag2);
	  trTag2_10.appendTo(tblTag2);
	  trTag2_11.appendTo(tblTag2);
	  trTag2_12.appendTo(tblTag2);
	  trTag2_13.appendTo(tblTag2);
	  trTag2_15.appendTo(tblTag2);
	  trTag2_16.appendTo(tblTag2);
	  
	  tdTag1.html(tblTag2);
	  tdTag1.appendTo(trTag2);
	  trTag1.appendTo(tblTag);
	  trTag2.appendTo(tblTag);
	  
	  return tblTag;
  }
  
  /*
	 * load children area of research when parents RA is expanding.
	 */
function loadChildrenProposalView(viewSelector, nodeName, tagId, childrenNodeText) {
    var parentNode = $("#"+tagId);
    var liNode = parentNode.parents('li:eq(0)');
    var ulNode = liNode.children('ul:eq(0)');
    var inputNodev;
    
    if (liNode.children('ul').size() == 0 || ulNode.children('input').size() == 0 ) {
            var ulTag ;
            if (liNode.children('ul').size() == 0) {
                ulTag = $('<ul class="filetree"></ul>').attr("id","ul"+i);
            } else {
                ulTag = ulNode;
            }
            ulTag.appendTo(liNode);
            var loadedId = "loaded"+i;
            var inputtag = $('<input type="hidden"></input>').attr("id",loadedId);
            inputtag.appendTo(ulTag);
            var module;
            while(childrenNodeText.length > 0){
	            var childNode1 = childrenNodeText.substring(childrenNodeText.indexOf("%5C1")+4,childrenNodeText.indexOf("%5C2")).trim();
	            childrenNodeText = childrenNodeText.substring(childrenNodeText.indexOf("%5C2")+4, childrenNodeText.length).trim();
	            
	            module = childNode1.substring(0,1);
	            i++;
	            
	            var id = "item"+i;
	            var tagId = "listcontrol"+i;
	            var divId = "listcontent"+i;

		        var idDiv;
		        if ( jQuery.browser.msie ) { 
		             idDiv = $('<div></div>').attr("id","itemText"+i).html(builduUi(childNode1));
		        } else {    
		             idDiv = $('<span>').attr("id","itemText"+i).html(builduUi(childNode1));
		        }
	            var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(idDiv);
	            var detDiv = $('<div  class="hierarchydetail" style="margin-top:2px; " align="left" ></div>').attr("id",divId);
	            
	            var detailsString = childNode1.substring(childNode1.indexOf("%31")+3,childNode1.indexOf("%32")).trim();
	            childNode1 = childNode1.substring(0,childNode1.indexOf("%31")) + childNode1.substring(childNode1.indexOf("%32")+3,childNode1.length);
	            
	            tag.click(
	                    function()
	                    {
	                       $(".hierarchydetail:not(#"+divId+")").slideUp(300);
	                        var idx = $(this).attr("id").substring(11);
	                        if ($(this).siblings('div:eq(1)').children('table:eq(0)').size() == 0) {                    	  
	                      	  tbodyTag(detailsString, "item"+idx, childNode1.substring(0,1)).appendTo($("#listcontent"+idx));

	                            if ($("#"+divId).is(":hidden")) {
	                                // alert(divId + " hidden0");
	                                 $("#listcontent"+idx).show();
	                                 // $("#listcontent"+idx).slideToggle(300);
	                            }
	                        } else {
	                            $("#listcontent"+idx).slideToggle(300);
	                        }
	                    }
	                );
	            
	            var listitem = $('<li class="closed"></li>').attr("id",id).html(tag);
	            ulTagId = ulTag.attr("id");
	            detDiv.appendTo(listitem);
	            var childUlTag = $('<ul></ul>').attr("id","ul"+i);
	            childUlTag.appendTo(listitem);
	            listitem.appendTo(ulTag);
	            
	            if (i==1) {
	            // alert (listitem.html());
	            }
            }
    }
    loadedidx=i;
} // end loadChildrenProposalView

/*
 * load children area of research when parents RA is expanding.
 */
function loadChildrenAwardView(viewSelector, nodeName, tagId, childrenNodeText) {
	var parentNode = $("#"+tagId);
	var liNode = parentNode.parents('li:eq(0)');
	var ulNode = liNode.children('ul:eq(0)');
	var inputNodev;

	if (liNode.children('ul').size() == 0 || ulNode.children('input').size() == 0 ) {
	    var ulTag ;
        if (liNode.children('ul').size() == 0) {
            ulTag = $('<ul class="filetree"></ul>').attr("id","ul"+i);
        } else {
            ulTag = ulNode;
        }
        
        ulTag.appendTo(liNode);
        var loadedId = "loaded"+i;
        var inputtag = $('<input type="hidden"></input>').attr("id",loadedId);
        inputtag.appendTo(ulTag);
        
        while(childrenNodeText.length > 0){	
        
            var childNode1 = childrenNodeText.substring(childrenNodeText.indexOf("%5C1")+4,childrenNodeText.indexOf("%5C2")).trim();
            childrenNodeText = childrenNodeText.substring(childrenNodeText.indexOf("%5C2")+4, childrenNodeText.length).trim();
            var thirdLevelText = childrenNodeText.substring(childNode1.indexOf("%6A")+4, childrenNodeText.indexOf("%6B")).trim();
            childrenNodeText = childrenNodeText.substring(childrenNodeText.indexOf("%6B")+3, childrenNodeText.length).trim();
            i++;
            
            var id = "item"+i;
            var tagId = "listcontrol"+i;
            var divId = "listcontent"+i;

	        var idDiv;
	        if ( jQuery.browser.msie ) { 
	             idDiv = $('<div></div>').attr("id","itemText"+i).html(builduUi(childNode1));
	        } else {    
	             idDiv = $('<span>').attr("id","itemText"+i).html(builduUi(childNode1));
	        }
            var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(idDiv);
            var detDiv = $('<div  class="hierarchydetail" style="margin-top:2px; " align="left" ></div>').attr("id",divId);
               	  
            var listitem = $('<li class="closed"></li>').attr("id",id).html(tag);
            ulTagId = ulTag.attr("id");
            detDiv.appendTo(listitem);
            
            var detailsString = childNode1.substring(childNode1.indexOf("%31")+3,childNode1.indexOf("%32")).trim();
            childNode1 = childNode1.substring(0,childNode1.indexOf("%31")) + childNode1.substring(childNode1.indexOf("%32")+3,childNode1.length);
            
            tag.click(
                    function()
                    {
                        // alert ("sibling
    						// "+$(this).siblings('div:eq(0)').attr("id"));
                        $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                        var idx = $(this).attr("id").substring(11);
                        if ($(this).siblings('div:eq(1)').children('table:eq(0)').size() == 0) {                    	  
                      	  tbodyTag(detailsString, "item"+idx, "P").appendTo($("#listcontent"+idx));

                            if ($("#"+divId).is(":hidden")) {
                                // alert(divId + " hidden0");
                                 $("#listcontent"+idx).show();
                                 // $("#listcontent"+idx).slideToggle(300);
                            }
                        } else {
                            $("#listcontent"+idx).slideToggle(300);
                        }
                    }
                );
            
            // need this ultag to force to display folder.
            var childUlTag = $('<ul></ul>').attr("id","ul"+i);
            childUlTag.appendTo(listitem);
            listitem.appendTo(ulTag);
            $("#medusaview").treeview({
                add: listitem
            });
            if (i==1) {
            }
            loadThirdLevel(viewSelector, tagId,thirdLevelText);
        }
}
loadedidx=i;
} // end loadChildrenAwardView

/*
 * load children area of research when parents RA is expanding.
 */
function loadThirdLevel(viewSelector, tagId, childrenNodeText) {
	var parentNode = $("#"+tagId);
	var liNode = parentNode.parents('li:eq(0)');
	var ulNode = liNode.children('ul:eq(0)');
	var inputNodev;

	if (liNode.children('ul').size() == 0 || ulNode.children('input').size() == 0 ) {
        var ulTag ;
        if (liNode.children('ul').size() == 0) {
            ulTag = $('<ul class="filetree"></ul>').attr("id","ul"+i);
        } else {
            ulTag = ulNode;
        }

        ulTag.appendTo(liNode);
        var loadedId = "loaded"+i;
        var inputtag = $('<input type="hidden"></input>').attr("id",loadedId);
        inputtag.appendTo(ulTag);
        
        while(childrenNodeText.length > 0){	
        
            var childNode1 = childrenNodeText.substring(childrenNodeText.indexOf("%6C1")+4,childrenNodeText.indexOf("%6C2")).trim();
            childrenNodeText = childrenNodeText.substring(childrenNodeText.indexOf("%6C2")+4, childrenNodeText.length).trim();
            
            var detailsString = childNode1.substring(childNode1.indexOf("%31")+3,childNode1.indexOf("%32")).trim();
            childNode1 = childNode1.substring(0,childNode1.indexOf("%31")) + childNode1.substring(childNode1.indexOf("%32")+3,childNode1.length);
            
            i++;
            
            var id = "item"+i;
            var tagId = "listcontrol"+i;
            var divId = "listcontent"+i;

	        var idDiv;
	        if ( jQuery.browser.msie ) { 
	             idDiv = $('<div></div>').attr("id","itemText"+i).html(builduUi(childNode1));
	        } else {    
	             idDiv = $('<span>').attr("id","itemText"+i).html(builduUi(childNode1));
	        }
            var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(idDiv);
            var detDiv = $('<div  class="hierarchydetail" style="margin-top:2px; " align="left" ></div>').attr("id",divId);
               	  
            tag.click(
                    function()
                    {
                        // alert ("sibling
    						// "+$(this).siblings('div:eq(0)').attr("id"));
                        $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                        var idx = $(this).attr("id").substring(11);
                        if ($(this).siblings('div:eq(1)').children('table:eq(0)').size() == 0) {                    	  
                      	  tbodyTag(detailsString, "item"+idx, "D").appendTo($("#listcontent"+idx));

                            if ($("#"+divId).is(":hidden")) {
                                // alert(divId + " hidden0");
                                 $("#listcontent"+idx).show();
                                 // $("#listcontent"+idx).slideToggle(300);
                            }
                        } else {
                            $("#listcontent"+idx).slideToggle(300);
                        }
                    }
                );

            
            var listitem = $('<li class="closed"></li>').attr("id",id).html(tag);
            ulTagId = ulTag.attr("id");
            detDiv.appendTo(listitem);
            var childUlTag = $('<ul></ul>').attr("id","ul"+i);
            childUlTag.appendTo(listitem);
            listitem.appendTo(ulTag);
            if (i==1) {
            // alert (listitem.html());
            }
        }
}
loadedidx=i;
} // end loadThirdLevel

function replaceAll(Source,stringToFind,stringToReplace){
	  var temp = Source;
	    var index = temp.indexOf(stringToFind);
	        while(index != -1){
	            temp = temp.replace(stringToFind,stringToReplace);
	            index = temp.indexOf(stringToFind);
	        }
	        return temp;
	}
  
  /*
	 * Utility function to get code from 'code : description' This need to be
	 * refined because if code contains ':', then this is not working correctly.
	 */
  function getAwardNumber(node) {
   // TODO : this maybe problemmatic because it makes the assumption that
	// areacode does not contain ":"
//          var endIdx = nodeName.indexOf(":");
//          return nodeName.substring(0, endIdx - 1);    
       return $("#racode"+node.attr("id").substring(4)).attr("value");
  }
  
  <!-- initial state -->
  $(".hierarchydetail").hide();
<!-- hidedetail -->
  $(".hidedetail").toggle(
      function()
      {
          $(".hierarchydetail").slideUp(300);
      }
  );
<!-- listcontent00 -->
  $("#listcontrol00").click(
      function()
      {
          $(".hierarchydetail:not(#listcontent00)").slideUp(300);
          $("#listcontent00").slideToggle(300);
      }
  );  
  
  function hasFormAlreadyBeenSubmitted() {
      // return false;
  }

  $(document).ready(function(){
      loadFirstLevel();
      $("#listcontent00").show();
      loadedidx=i;
  })
  $("#loading").hide();
