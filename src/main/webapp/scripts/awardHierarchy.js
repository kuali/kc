    
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
                     //alert(idstr +"-"+ loadedidx)
                     //if (idstr <= loadedidx) {  // TODO : if this is not a new node
                     loadChildrenRA($("#itemText"+idstr).text(), tagId);
                     //}
                    // var subul=this.getElementsByTagName("ul")[0]
                    // if (subul.style.display=="block")
                    // alert("You've opened this Folder!" + idstr)
                    },
                animated: "fast",
                collapsed: true,
                control: "#treecontrol"
                    
                 
              });
     // $("#browser").treeview();
      // $("div#foo").append("Hello World!").css("color","red");
  /*
	 * $.ajaxStart(function() { $("div#foo").text("Loading..."); });
	 * $.ajaxComplete(function() { $("div#foo").text(""); });
	 */    
      
      // $("body").append('<div id="loading"></div>');
      // $("#loading").css("color","red");
      $(document).ajaxStart(function(){
      // this is weird, it will not show if the alert is not included??
         // return false;
         // var img = $('<a href="#"><img
			// src="static/images/jquery/ajax-loader.gif" /></a>')
         $("#loading").show();
         // alert ("start Ajax");
         // return false;
       });

      $(document).ajaxComplete(function(){
         // alert ("complete Ajax");
         $("#loading").hide();
         // return false;
       });
    }); // $(document).ready
     

    /*
	 * Load first level area of research when page is initially loaded
	 */
    function loadFirstLevel(){ 
      
      $.ajax({
        url: 'awardHierarchyAjax.do',
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
           //alert("success"+xml);
 //for (var k=0; k< 10; k++) {       	
           $(xml).find('h3').each(function(){
           var item_text = $(this).text();
           i++;
           var racode = item_text.substring(0,item_text.indexOf("%3A")).trim();
           item_text = item_text.replace("%3A",":");
          // if (i < 4 ) {
        	//   alert(item_text+"-"+racode);
          // }	   
           var id = "item"+i;
           var tagId = "listcontrol"+i;
           var divId = "listcontent"+i;
           
          // NOTES : if use 'div', then FF will display the '+' and idDiv in
			// separate lines. IE7 is fine
          // But 'IE7 has problem with 'span'
          
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
           var div = $('<div  class="hierarchydetail" style="margin-top:2px; "></div>').attr("id",divId);
       	   var hidracode = $('<input type="hidden" id = "racode" name = "racode" />').attr("id",
    			"racode" + i).attr("name", "racode" + i).attr("value",racode);
       	   hidracode.appendTo(div);
                   
           var listitem = $('<li class="closed"></li>').attr("id",id).html(tag);
           
           // tag.appendTo(listitem);
           // listitem.appendTo('ul#file31');
           ulTagId = "browser";
           // tableTag(item_text, id).appendTo(div)
           div.appendTo(listitem);
           // need this ultag to force to display folder.
           var childUlTag = $('<ul></ul>').attr("id","ul"+i);
           childUlTag.appendTo(listitem);
           listitem.appendTo('ul#awardhierarchy');
                   // also need this to show 'folder' icon
           $('#awardhierarchy').treeview({
              add: listitem
              
           });
          // setupListItem(item_text).appendTo('ul#browser');
       
           });
// } // end test loop 'for'          
        }
       });  
      // return false;
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
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text3 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text4 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text5 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text6 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text7 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text8 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text9 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text10 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        var racodereverse = revString(racode);
        var index = racodereverse.indexOf("0");
        var i2 = 12 - index;
        var racode2 = racode.substring(i2,12);
        
        if($("#controlForAwardHierarchyView").attr("value") == 0){
      	  var abc = "<table style=\"border: medium none ; padding: 0px; width: 100%; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top;\">"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"
	  			+"</td></tr></tbody></table>";
        }
        if($("#controlForAwardHierarchyView").attr("value") == 1){
        	var abc = "<table style=\"border: medium none ; padding: 0px; width: 100%; border-collapse: collapse;\"><tbody><tr><td rowspan=\"3\" style=\"border: medium none ; border-collapse: collapse; vertical-align: top;\">"+text1+"</td>"
        		+"<td rowspan=\"2\" style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/></td>"
	  			+"<td  rowspan=\"2\" style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"	  			
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td  rowspan=\"2\" style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); text-align: right; width: 100px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);\">  Distributed: </td>" 
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">" +text9 + "</td>"
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">" +text10 + "</td></tr><tr>"
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); text-align: right; width: 100px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);\">  Distributable: </td>"
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">" +text7 + "</td>"
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">" +text8 + "</td></tr><tr>"
	  			+"<td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td style=\"border: 1px solid rgb(153, 153, 153); text-align: right; width: 100px; border-collapse: collapse; font-weight: bold; background-color: rgb(195, 195, 195);\">  Total: </td>"
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">" +text5 + "</td>"
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">" +text6 + "</td>"
	  			+"</tr></tbody></table>";
        }
        if($("#controlForAwardHierarchyView").attr("value") == 2){
      	var abc = "<table style=\"border: medium none ; padding: 0px; width: 100%; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top;\">"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
	  			+text5+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
	  			+text6+"</td></tr></tbody></table>";
        } 
 	   return abc; 
    }
  

 

  /*
	 * load children area of research when parents RA is expanding.
	 */
  function loadChildrenRA(nodeName, tagId) {
      var parentNode = $("#"+tagId);
      // alert ("load subnodes for
		// "+nodeName+"-"+parentNode.parents('li:eq(0)').attr("id")+"-" );
      var liNode = parentNode.parents('li:eq(0)');
      var ulNode = liNode.children('ul:eq(0)');
      var inputNodev;
// alert (ulNode);
// if (liNode.children('ul').size() > 0 ) {
// inputNodev = ulNode.children('input:eq(0)');
// }
      
      
      // if (liNode.children('ul').size() == 0 ) {
      if (liNode.children('ul').size() == 0 || ulNode.children('input').size() == 0 ) {
          // alert(liNode.children('ul').size());
          $.ajax({
           url: 'awardHierarchyAjax.do',
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
              //i++;
              var ulTag ;
              if (liNode.children('ul').size() == 0) {
                  ulTag = $('<ul class="filetree"></ul>').attr("id","ul"+i);
              } else {
                  ulTag = ulNode;
              }
             
              // alert(ulTag.html());
              
              // ulTag.appendTo(parentNode);
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
              
             // if (i == 1) {
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
              // tag.appendTo(listitem);
              // listitem.appendTo('ul#file31');
              ulTagId = ulTag.attr("id");
              // tableTag(item_text, id).appendTo(detDiv)
              detDiv.appendTo(listitem);
              // listitem.appendTo('ul#file31');
              // need this ultag to force to display folder.
              var childUlTag = $('<ul></ul>').attr("id","ul"+i);
              childUlTag.appendTo(listitem);
              listitem.appendTo(ulTag);
              // force to display folder icon
              $("#awardhierarchy").treeview({
                 add: listitem
                 // toggle: function() {
                 // var subul=this.getElementsByTagName("ul")[0]
                 // if (subul.style.display=="block")
                 // alert("You've opened this Folder!")
                 // }
              });
              
              if (i==1) {
              // alert (listitem.html());
              }
          
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
//          var endIdx = nodeName.indexOf(":");
//          return nodeName.substring(0, endIdx - 1);    
       return $("#racode"+node.attr("id").substring(4)).attr("value");
  }
  
  function hasFormAlreadyBeenSubmitted() {
      // return false;
  }

  $(document).ready(function(){

	  //for (var k = 0; k<3;k++) {
		  // performance test
      loadFirstLevel();
      loadedidx=i;
	  //}
      // $("#listcontrol00").show();
      // $("#listcontent00").slideToggle(300);
  })

