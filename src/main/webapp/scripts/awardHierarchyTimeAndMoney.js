    
    var i = 1;
    var j = 1;
    var ulTagId;
    var loadedidx=0;
      
    $(document).ready(function(){
      $.ajaxSettings.cache = false; 
      $("#awardhierarchy").treeview({
                 toggle: function() {
                     var idstr=$(this).attr("id").substring(4);
                     var tagId = "listcontrol"+idstr;
                     var divId = "listcontent"+idstr;
                 
                     $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                     $("#"+divId).slideToggle(300);
                     loadChildrenRA($("#itemText"+idstr).text(), tagId);
                     //when loading children, we must remove all of the previous images and scripts.  Otherwise, there will be multiple
                     //datepickers added to each cell depending on how deep we dig into the hierarchy and how many times we toggle.
                     $('.datepickerImage').remove();
     				 $('.datepickerScript').remove();
                     $('.datepicker').each(
                    			function() {
                    				var id = $(this).attr("id");
                    			    var img1 =$("<img class='datepickerImage' src='kr/static/images/cal.gif' id='" + id +"_datepicker' style='cursor: pointer;'  title='Date selector' alt='Date selector' onmouseover=\"this.style.backgroundColor='red';\" onmouseout=\"this.style.backgroundColor='transparent';\"/>");
                    			    var script1 =$(" <script class='datepickerScript' type='text/javascript'>	Calendar.setup({ inputField : '"+ id +"', ifFormat : '%m/%d/%Y',  button : '" + id + "_datepicker'});</script>");
                    			    img1.insertAfter($(this));
                    			    script1.insertAfter(img1);
                    			});
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
      
    }); // $(document).ready
     

    /*
	 * Load first level area of research when page is initially loaded
	 */
    function loadFirstLevel(){ 
     
      $.ajax({
        url: 'awardHierarchyTimeAndMoneyAjax.do',
        type: 'GET',
        dataType: 'html',
        cache: false,
        data:'awardNumber=&addRA=N&document.rootAwardNumber=' + $("#document\\.rootAwardNumber").attr("value")  + '&currentAwardNumber='+ $("#currentAwardNumber").attr("value") + '&currentSeqNumber='+ $("#currentSeqNumber").attr("value"),
        async:false,
        timeout: 1000,
        error: function(){
           alert('Error loading XML document');
        },
        success: function(xml){
           $(xml).find('h3').each(function(){
           var item_text = $(this).text();
           i++;
           var racode = item_text.substring(0,item_text.indexOf("%3A")).trim();
           item_text = item_text.replace("%3A",":");
           var id = "item"+i;
           var tagId = "listcontrol"+i;
           var divId = "listcontent"+i;
           
          // NOTES : if use 'div', then FF will display the '+' and idDiv in
			// separate lines. IE7 is fine
          // But 'IE7 has problem with 'span'
          
//          var idDiv;
//          if ( jQuery.browser.msie ) { 
//               idDiv = $('<div></div>').attr("id","itemText"+i).html(builduUi(item_text, racode)); 
//               // for later change RA description
//          } else {
//               idDiv = $('<span>').attr("id","itemText"+i).html(builduUi(item_text, racode));  
//                //for later change RA description
//          }
           
           var idDiv;
           if ( jQuery.browser.msie ) { 
        	   
                idDiv = $('<div></div>').attr("id","itemText"+i).html(builduUi(item_text, racode)); 
                // for later change RA description
           } else {
                idDiv = $('<div></div>').attr("id","itemText"+i).html(builduUi(item_text, racode));  
                 //for later change RA description
           }
               
           var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(idDiv);
           var div = $('<div  class="hierarchydetail" style="margin-top:2px; "></div>').attr("id",divId);
       	   var hidracode = $('<input type="hidden" id = "racode" name = "racode" />').attr("id",
    			"racode" + i).attr("name", "racode" + i).attr("value",racode);
       	   hidracode.appendTo(div);
       	   
           var listitem = $('<li class="closed"></li>').attr("id",id).html(tag);
           
           ulTagId = "browser";
           div.appendTo(listitem);
           // need this ultag to force to display folder.
           var childUlTag = $('<ul></ul>').attr("id","ul"+i);
           childUlTag.appendTo(listitem);
           listitem.appendTo('ul#awardhierarchy');
           // also need this to show 'folder' icon
           $('#awardhierarchy').treeview({
              add: listitem
              
           });
       
           });
        }
       });  
    }  // generate
    
    function revString(str) { 
    	   var retStr = "";    	   
    	   for (j=str.length - j ; j > - 1 ; j--){ 
    	      retStr += str.substr(j,1); 
    	   } 
    	   return retStr; 
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
        	var txtImage = "<img src=\"static/images/award_pending.gif\" alt=\"Hold\" title=\"Hold\" />";
        }
        
        var racodereverse = revString(racode);
        var index = racodereverse.indexOf("0");
        var i2 = 12 - index;
        var racode2 = racode.substring(i2,12);
        text2 = text2.trim();
        if(text2 == ' '){
        	text2 = '';
        } 
        
        if($("#cancelOrFinalStatus").attr("value") == 1){
        	var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td>" +
//        	"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
//  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; align:right; border-collapse: collapse; \">"
//  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 110px; align:right; border-collapse: collapse; \">"
//  			+"<input type=\"text\" class = 'datepicker' name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\" id=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:70%;\" maxlength=\"10\" size=\"10\"/>"+"</td>" 
  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text2+"</td>" +
  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text3+"</td>" +
  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text4+"</td>" +
  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; align:right; border-collapse: collapse;\">"+text5+"</td>" +
  			"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; align:right; left: 1335px; border-collapse: collapse;\">"+text6+"</td>"
  			+"</tr></tbody></table>";
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
 	   return abc; 
    }
  

 

  /*
	 * load children area of research when parents RA is expanding.
	 */
  function loadChildrenRA(nodeName, tagId) {
      var parentNode = $("#"+tagId);
      var liNode = parentNode.parents('li:eq(0)');
      var ulNode = liNode.children('ul:eq(0)');
      var inputNodev;
 
      if (liNode.children('ul').size() == 0 || ulNode.children('input').size() == 0 ) {
          // alert(liNode.children('ul').size());
          $.ajax({
           url: 'awardHierarchyTimeAndMoneyAjax.do',
           type: 'GET',
           dataType: 'html',
           data:'awardNumber='+getAwardNumber(liNode)+'&addRA=E' + '&currentAwardNumber='+ $("#currentAwardNumber").attr("value") + '&currentSeqNumber='+ $("#currentSeqNumber").attr("value"),
           cache: false,
           async: false,
           timeout: 1000,
           error: function(){
              alert('Error loading XML document');
           },
           success: function(xml){
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
              $(xml).find('h3').each(function(){
              var item_text = $(this).text();
              i++;
              var racode = item_text.substring(0,item_text.indexOf("%3A")).trim();
              item_text = item_text.replace("%3A",":");
              var id = "item"+i;
              var tagId = "listcontrol"+i;
              var divId = "listcontent"+i;
              
          var idDiv;
          if ( jQuery.browser.msie ) { 
               idDiv = $('<div></div>').attr("id","itemText"+i).html(builduUi(item_text, racode)); // for
																					// later
																					// change
																					// RA
																					// description
          } else {    
               idDiv = $('<span>').attr("id","itemText"+i).html(builduUi(item_text, racode)); // for
																			// later
																			// change
																			// RA
																			// description
          }
              var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(idDiv);
              var detDiv = $('<div  class="hierarchydetail" style="margin-top:2px; "></div>').attr("id",divId);
         	   var hidracode = $('<input type="hidden" id = "racode" name = "racode" />').attr("id",
              			"racode" + i).attr("name", "racode" + i).attr("value",racode);
                 	   hidracode.appendTo(detDiv);
                 	   
              var listitem = $('<li class="closed"></li>').attr("id",id).html(tag);
              ulTagId = ulTag.attr("id");
              detDiv.appendTo(listitem);
              // need this ultag to force to display folder.
              var childUlTag = $('<ul></ul>').attr("id","ul"+i);
              childUlTag.appendTo(listitem);
              listitem.appendTo(ulTag);
              // force to display folder icon
              $("#awardhierarchy").treeview({
                 add: listitem
              });
              
              });
           }
          });    
      }
      loadedidx=i;
  } // end loadChildrenRA

  /*
	 * Utility function to get code from 'code : description' This need to be
	 * refined because if code contains ':', then this is not working correctly.
	 */
  function getAwardNumber(node) {
       // TODO : this maybe problemmatic because it makes the assumption that
	   // areacode does not contain ":"
       return $("#racode"+node.attr("id").substring(4)).attr("value");
  }
  
  function hasFormAlreadyBeenSubmitted() {
      // return false;
  }

  $(document).ready(function(){
		  // performance test
      loadFirstLevel();
      loadedidx=i;
      $('.datepicker').each(
   			function() {
   				var id = $(this).attr("id");
   			    var img1 =$("<img class='datepickerImage' src='kr/static/images/cal.gif' id='" + id +"_datepicker' style='cursor: pointer;'  title='Date selector' alt='Date selector' onmouseover=\"this.style.backgroundColor='red';\" onmouseout=\"this.style.backgroundColor='transparent';\"/>");
   			    var script1 =$(" <script class='datepickerScript' type='text/javascript'>	Calendar.setup({ inputField : '"+ id +"', ifFormat : '%m/%d/%Y',  button : '" + id + "_datepicker'});</script>");
   			    img1.insertAfter($(this));
   			    script1.insertAfter(img1);
   			});
   })

