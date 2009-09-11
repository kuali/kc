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
	 * top level to add research area
	 */
   $("#add0").click(function(){    
   // click 'add' for 000001
         var trNode = $(this).parents('tr:eq(0)');
        // alert(trNode.children('td:eq(1)').children('input:eq(0)').attr("value")+"-"+trNode.children('td:eq(2)').children('input:eq(0)').attr("value"));

       if (trNode.children('td:eq(1)').children('input:eq(0)').attr("value") == "") {
         alert("must enter research area code");
       } else if (trNode.children('td:eq(2)').children('input:eq(0)').attr("value") == "") {
         alert("must enter research area");
       } else {
       
       // check if code exists
           var raExist
           $.ajax({
               url: 'researchAreaAjax.do',
               type: 'GET',
               dataType: 'html',
               data:'researchAreaCode='+trNode.children('td:eq(1)').children('input:eq(0)').attr("value")+'&addRA=Y',
               cache: false,
               async: false,
               timeout: 1000,
               error: function(){
                  alert('Error loading XML document');
               },
               success: function(xml){
                  $(xml).find('h3').each(function(){
                     raExist = $(this).text();
                  // alert(raExist);
      
                   });
                }
             });   // end ajax
       
       
         if (raExist == 'false') {
             var ulTag = $("#researcharea");
                                                        
             var item_text = trNode.children('td:eq(1)').children('input:eq(0)').attr("value") +" : "+trNode.children('td:eq(2)').children('input:eq(0)').attr("value");
             var listitem = setupListItem(trNode.children('td:eq(1)').children('input:eq(0)').attr("value") , trNode.children('td:eq(2)').children('input:eq(0)').attr("value"));
             // alert(listitem.html());
          // need this ultag to force to display folder.
          var childUlTag = $('<ul></ul>').attr("id","ul"+i);
          childUlTag.appendTo(listitem);
          
          // this is new nodes, so it is same as already loaded from DB
          var loadedId = "loaded"+i;
          var inputtag = $('<input type="hidden"></input>').attr("id",loadedId);
          inputtag.appendTo(childUlTag);
          
             listitem.appendTo(ulTag);
             // alert("ultagid "+ulTag.attr("id").substring(2));
             // force to display folder icon
             $("#researcharea").treeview({
                 add: listitem
             // toggle: function() {
             // var subul=this.getElementsByTagName("ul")[0]
             // if (subul.style.display=="block")
             // alert("You've opened this Folder!")
             // }
             });
             
             // apend to sqlScripts
             addSqlScripts(getInsertClause(trNode.children('td:eq(1)').children('input:eq(0)').attr("value"), '000001', trNode.children('td:eq(2)').children('input:eq(0)').attr("value")));
             // alert("sqlScripts = "+sqlScripts);
          }  else {
               alert ("Research Area Code already exist");
          }
         };                                
     
      
     return false;
    }); // add0    
     

    /*
	 * Load first level area of research when page is initially loaded
	 */
    function loadFirstLevel(){ 
      
      $.ajax({
        url: 'awardHierarchyAwardActionsAjax.do',
        type: 'GET',
        dataType: 'html',
        cache: false,
        data:'awardNumber=&addRA=N&rootAwardNumber=' + $("#rootAwardNumber").attr("value"),
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
       	   
           // $(document).ready(function () {
           tag.click(
                  function()
                  {
                      // alert ("sibling
						// "+$(this).siblings('div:eq(0)').attr("id"));
                      $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                      var idx = $(this).attr("id").substring(11);
                      if ($(this).siblings('div:eq(1)').children('table:eq(0)').size() == 0) {
                          tableTag(item_text, "item"+idx).appendTo($("#listcontent"+idx));
                          if ($("#"+divId).is(":hidden")) {
                              // alert(divId + " hidden0");
                               $("#listcontent"+idx).show();
                               // $("#listcontent"+idx).slideToggle(300);
                          }
                      } else {
                          $("#listcontent"+idx).slideToggle(300);
                      }   
                      
                      loadChildrenRA(item_text, tagId);
                  }
              );
          // });
       	
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
        
        item_text = item_text.substring(item_text.indexOf("%3A")+3, item_text.length).trim();
        var text11 = item_text.substring(0,item_text.indexOf("%3A")).trim();
        
        if(text11 == 1){
        	var txtImage = "<img src=\"static/images/award_active.gif\" />";
        }else if(text11 == 2 || text11 == 4 || text11 == 5){
        	var txtImage = "<img src=\"static/images/award_inactive.gif\" />";
        }else if(text11 == 3 || text11 == 6){
        	var txtImage = "<img src=\"static/images/award_pending.gif\" />";
        }
        var racodereverse = revString(racode);
        var index = racodereverse.indexOf("0");
        var i2 = 12 - index;
        var racode2 = racode.substring(i2,12);
        
        
      	var abc = "<table style=\"border: medium none ; padding: 0px; width: 100%; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top;\">"+txtImage+"&nbsp;"+text1 
      				+"</td></tr></tbody></table>";
        
      	return abc; 
    }
    
    /*
	 * set up the area of research detail table tag. This is loading on demand.
	 * Only when area of research link is clicked the first time, then it will
	 * be loaded.
	 */
  function tableTag(name, id) {         
         // var tag = $('<th class="subelementheader"
			// align="left"></th>').attr("id","raHeader"+i).html(name);
         var text1 = name.substring(0,name.indexOf("%3A")).trim();
         name = name.substring(name.indexOf("%3A")+3, name.length).trim();
         
         var tag = $('<th  style="background:#939393;height:18px;color:#FFFFFF;text-align:left;padding-left:4px;" align="left"></th>').attr("id","raHeader"+id.substring(4)).html("Detail: " + text1);
         
         tag = $('<tr></tr>').html(tag);
         tag = $('<thead></thead>').html(tag);
         tag = $('<table width="100%" cellpadding="0" cellspacing="0" class="subelement"> </table>').html(tag);
         tbodyTag(name, id).appendTo(tag);
         return tag;
  }

  function tbodyTag(name, id) {
	  
	  var text2 = name.substring(0,name.indexOf("%3A")).trim();
      
	  name = name.substring(name.indexOf("%3A")+3, name.length).trim();
      var text3 = name.substring(0,name.indexOf("%3A")).trim();
      
      name = name.substring(name.indexOf("%3A")+3, name.length).trim();
      var text4 = name.substring(0,name.indexOf("%3A")).trim();
      
      name = name.substring(name.indexOf("%3A")+3, name.length).trim();
      var text5 = name.substring(0,name.indexOf("%3A")).trim();
      
      name = name.substring(name.indexOf("%3A")+3, name.length).trim();
      var text6 = name.substring(0,name.indexOf("%3A")).trim();
      
      name = name.substring(name.indexOf("%3A")+3, name.length).trim();
      var text7 = name.substring(0,name.indexOf("%3A")).trim();
      
      name = name.substring(name.indexOf("%3A")+3, name.length).trim();
      var text8 = name.substring(0,name.indexOf("%3A")).trim();
      
      name = name.substring(name.indexOf("%3A")+3, name.length).trim();
      var text9 = name.substring(0,name.indexOf("%3A")).trim();
      
      name = name.substring(name.indexOf("%3A")+3, name.length).trim();
      var text10 = name.substring(0,name.indexOf("%3A")).trim();
      
      name = name.substring(name.indexOf("%3A")+3, name.length).trim();
      var text11 = name.substring(0,name.indexOf("%3A")).trim();
      
      name = name.substring(name.indexOf("%3A")+3, name.length).trim();
      var text12 = name.substring(0,name.indexOf("%3A")).trim();
      
      name = name.substring(name.indexOf("%3A")+3, name.length).trim();
      var text13 = name.substring(0,name.indexOf("%3A")).trim();
      

    var idx = id.substring(4);  
    var tblTag = $('<table cellpadding="0" cellspacing="0" class="elementtable" width="100%"></table>')

    
    // 1st tr
    var trTag = $('<tr></tr>');
    var thTag1=$('<th style="text-align:right;width:160px;"></th>').html('<b>Project Start Date</b>');
    trTag.html(thTag1);
    var tdTag1=$('<td></td>').html(text12);
    tdTag1.appendTo(trTag);
    var thTag2=$('<th style="text-align:right;width:160px;"></th>').html('<b>Obligation Start Date</b>');
    thTag2.appendTo(trTag);    
    var tdTag2=$('<td></td>').html(text2);
    tdTag2.appendTo(trTag);
    
    // 2nd tr
    var trTag1 = $('<tr></tr>');
    var thTag3=$('<th style="text-align:right;width:160px;"></th>').html('<b>Project End Date</b>');
    trTag1.html(thTag3);
    var tdTag3=$('<td ></td>').html(text4);
    tdTag3.appendTo(trTag1);
    var thTag4=$('<th style="text-align:right;width:160px;"></th>').html('<b>Obligation End Date</b>');
    thTag4.appendTo(trTag1);    
    var tdTag4=$('<td ></td>').html(text3);
    tdTag4.appendTo(trTag1);
    
    // 3rd tr
    var trTag2 = $('<tr></tr>');
    var thTag5=$('<th style="text-align:right;width:160px;"></th>').html('<b>Anticipated Amount</b>');
    trTag2.html(thTag5);
    var tdTag5=$('<td ></td>').html("$" + text5);
    tdTag5.appendTo(trTag2);
    var thTag6=$('<th style="text-align:right;width:160px;"></th>').html('<b>Obligated Amount</b>');
    thTag6.appendTo(trTag2);    
    var tdTag6=$('<td ></td>').html("$" + text6);
    tdTag6.appendTo(trTag2);
    
    // 4th tr
    var trTag3 = $('<tr></tr>');
    var thTag7=$('<th style="text-align:right;width:160px;"></th>').html('<b>Title</b>');
    trTag3.html(thTag7);
    var tdTag7=$('<td colspan="3" ></td>').html(text13);
    tdTag7.appendTo(trTag3);
      
    trTag.appendTo(tblTag);
    trTag1.appendTo(tblTag);
    trTag2.appendTo(tblTag);
    trTag3.appendTo(tblTag);
    tag = $('<td class="subelementcontent"></td>').html(tblTag);
    tag = $('<tr></tr>').html(tag);
    tag = $('<tbody></tbody>').html(tag);
    return tag;
  }    
  
  /*
	 * set up area of resear list tag. the main table detail is not set up
	 * initially.
	 */
  function setupListItem(code, name) {
            i++;
            var id1 = "item"+i;
            var tagId = "listcontrol"+i;
            var divId = "listcontent"+i;
            var idDiv;
            if ( jQuery.browser.msie ) { 
                idDiv = $('<div></div>').attr("id","itemText"+i).html(code +" : "+name); // for
																				// later
																				// change
																				// RA
																				// description
            } else {
                idDiv = $('<span>').attr("id","itemText"+i).html(code +" : "+name); // for
																			// later
																			// change
																			// RA
																			// description
            }
            var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(idDiv);
            var detDiv = $('<div  class="hierarchydetail" style="margin-top:2px; "></div>').attr("id",divId);
        	   var hidracode = $('<input type="hidden" id = "racode" name = "racode" />').attr("id",
         			"racode" + i).attr("name", "racode" + i).attr("value",code);
            	   hidracode.appendTo(detDiv);
       // $(document).ready(function () {
            $(tag).click(
                    function()
                    {
                        $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                        if ($(this).siblings('div:eq(1)').children('table:eq(0)').size() == 0) {
                            var idx = $(this).attr("id").substring(11);
                            tableTag(code +" : "+name, "item"+idx).appendTo($("#listcontent"+idx));
                            if ($("#"+divId).is(":hidden")) {
                                $("#listcontent"+idx).show();
                            }
                        } else {   

                       // $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                            $("#"+divId).slideToggle(300);
                            // $("#"+divId).show();;
                        }  
                        // TODO : this is a new item, so should not need to loadchildren ?
                     //   loadChildrenRA(code +" : "+name, tagId);
                    }
                );
        // });
            // alert(tag.html());
            var listitem = $('<li class="closed"></li>').attr("id",id1).html(tag);
            // tableTag(name, id1).appendTo(detDiv)
            detDiv.appendTo(listitem);
            // alert(listitem.html());
            return listitem;
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
           url: 'awardHierarchyAwardActionsAjax.do',
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
                 	  
                 	   tag.click(
                              function()
                              {
                                  // alert ("click "+tagId);
                                  $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                                  var idx = $(this).attr("id").substring(11);
                                  if ($(this).siblings('div:eq(1)').children('table:eq(0)').size() == 0) {
                                      tableTag(item_text, "item"+idx).appendTo($("#listcontent"+idx));
                                      if ($("#listcontent"+idx).is(":hidden")) {
                                      // alert(divId + " hidden0");
                                           $("#listcontent"+idx).show();
                                       // $("#listcontent"+idx).slideToggle(300);
                                      }
                                  } else {
                                      $("#listcontent"+idx).slideToggle(300);
                                  }   

                                  loadChildrenRA(item_text, tagId);
                              }
                          );
                 	  
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

	  //for (var k = 0; k<3;k++) {
		  // performance test
      loadFirstLevel();
      $("#listcontent00").show();
      loadedidx=i;
	  //}
      // $("#listcontrol00").show();
      // $("#listcontent00").slideToggle(300);
  })
  $("#loading").hide();
