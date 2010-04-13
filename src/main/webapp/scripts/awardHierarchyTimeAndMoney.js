    
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
        data:'awardNumber=&addRA=N&document.rootAwardNumber=' + $("#document\\.rootAwardNumber").attr("value"),
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
          
          var idDiv;
          if ( jQuery.browser.msie ) { 
               idDiv = $('<div></div>').attr("id","itemText"+i).html(builduUi(item_text, racode)); 
               // for later change RA description
          } else {
               idDiv = $('<span>').attr("id","itemText"+i).html(builduUi(item_text, racode));  
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
        var text5 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text6 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
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
        
        if($("#controlForAwardHierarchyView").attr("value") == 0){
      	  var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top;\">"+txtImage+"&nbsp;"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse; position:absolute;left:800px;\">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse; position:absolute;left:912px;\">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td nowrap style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse; position:absolute;left:1024px;\">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/></td>"
	  			+"<td align=\"right\" style=\"border: medium none ; width: 15px; border-collapse: collapse; vertical-align: top; position:absolute; left:1145px; \" >"+txtImage+"&nbsp;</td>"
	  			+"</tr></tbody></table>";
        }else if($("#controlForAwardHierarchyView").attr("value") == 1){
        	var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td>"
        		+"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 114px; border-collapse: collapse; vertical-align: top; position: absolute; left: 465px; \">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/></td>"
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 114px; border-collapse: collapse; vertical-align: top; position: absolute; left:  582px; \">"	  			
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"
	  			+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 114px; border-collapse: collapse; vertical-align: top; position: absolute; left: 699px; \">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>" 
	  			+"</td><td style=\"border: 1px solid rgb(153, 153, 153); text-align: right; width: 112px; position: absolute; left: 816px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);\">  Distributed: </td>" 
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 932px; border-collapse: collapse; \">" +text9 + "</td>"
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 1044px; border-collapse: collapse; \">" +text10 + "</td>"
	  			+"<td align=\"left\" style=\"border: medium none ; width: 5px; position: absolute; left: 1174px; border-collapse: collapse; vertical-align: top; \" >"+txtImage+"&nbsp;</td></tr></tbody></table>"
	  			+"<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse;\">&nbsp;</td>"
	  			+"<td style=\"width: 112px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse; position: absolute; left: 465px;\">&nbsp;</td><td style=\"width: 112px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse; position: absolute; left:  582px; \">"
	  			+"&nbsp;</td><td style=\"width: 112px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse; position: absolute; left: 699px;\">&nbsp;</td>"
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: right; width: 112px; border-collapse: collapse; position: absolute; left: 816px; font-weight: bold; background-color: rgb(195, 195, 195);\">  Distributable: </td>"
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 932px; border-collapse: collapse;\">" +text7 + "</td>"
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 1044px; border-collapse: collapse;\">" +text8 + "</td>"
	  			+"<td align=\"left\" style=\"width: 5px; border: medium none ; border-collapse: collapse; position: absolute; left: 1174px; \">&nbsp;</td></tr></tbody></table>"
	  			+"<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse;\">&nbsp;</td>"
	  			+"<td style=\"width: 112px; position: absolute; left: 465px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse;\">&nbsp;</td><td style=\"width: 112px; position: absolute; left: 582px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse;\">&nbsp;</td>"
	  			+"<td style=\"width: 112px; position: absolute; left: 699px; border: 1px solid rgb(153, 153, 153); border-collapse: collapse;\">&nbsp;</td>"
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: right; width: 112px; position: absolute; left: 816px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);\">  Total: </td>"
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 932px; border-collapse: collapse;\">" +text5 + "</td>"
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 1044px; border-collapse: collapse;\">" +text6 + "</td>"
	  			+"<td align=\"left\" style=\"width: 5px; position: absolute; left: 1174px; border: medium none ; border-collapse: collapse;\"> &nbsp;</td></tr></tbody></table>";
        }else if($("#controlForAwardHierarchyView").attr("value") == 2){
	      	var abc = "<table style=\"border: medium none ; padding: 0px; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top\">"+txtImage+"&nbsp;"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; position: absolute; left: 600px; border-collapse: collapse; \">"
		  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; position: absolute; left:  712px; border-collapse: collapse; \">"
		  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 110px; position: absolute; left: 824px; border-collapse: collapse; \">"
		  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 112px; position: absolute; left: 936px; border-collapse: collapse;\">"
		  			+text5+"</td><td style=\"border: 1px solid rgb(153, 153, 153); text-align: center; width: 110px; position: absolute; left: 1050px; border-collapse: collapse;\">"
		  			+text6+"</td>"
		  			+"<td align=\"right\" style=\"border: medium none ; width: 10px; position: absolute; left: 1455px; border-collapse: collapse; vertical-align: top; \" >"+txtImage+"&nbsp;</td>"
		  			+"</tr></tbody></table>";
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
           data:'awardNumber='+getAwardNumber(liNode)+'&addRA=E',
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
  })

