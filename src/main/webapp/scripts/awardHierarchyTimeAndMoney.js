    
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
    
    /*
	 * set up the area of research detail table tag. This is loading on demand.
	 * Only when area of research link is clicked the first time, then it will
	 * be loaded.
	 */
  function tableTag(name, id) {

         var link = $('<a href="#" class="hidedetail"><img src="kr/static/images/tinybutton-hide.gif" align="absmiddle" border="0" width="45" height="15"></a>');
         // var tag = $('<th class="subelementheader"
			// align="left"></th>').attr("id","raHeader"+i).html(name);
         var tag = $('<th  style="background:#939393;height:18px;color:#FFFFFF;text-align:left;padding-left:4px;" align="left"></th>').attr("id","raHeader"+id.substring(4)).html(name);
         link.prependTo(tag);
         tag = $('<tr></tr>').html(tag);
         tag = $('<thead></thead>').html(tag);
         tag = $('<table width="100%" cellpadding="0" cellspacing="0" class="subelement"> </table>').html(tag);
         tbodyTag(name, id).appendTo(tag);
         return tag;
  }

  function tbodyTag(name, id) {

    var idx = id.substring(4);  
    var tblTag = $('<table cellpadding="0" cellspacing="0" class="elementtable" width="100%"></table>')
    var tag=$('<th colspan="4"></th>');
    var image = $('<a href="#"><img src="static/images/tinybutton-removenode.gif" width="79" height="15" border="0" alt="Remove Node" title="Remove this node and its child groups/sponsors"></a>&nbsp').attr("id","remove"+idx).click(function() {
                        var liId="li#"+id;
                        // removedNode = $(liId).clone(true);
                        // removedNode = $(liId); // this will not work because
						// event also lost
                        // alert("Remove node "+removedNode.attr("id"));
                        var parentRACode;
                        if ($(liId).parents('li:eq(0)').size() == 0) {
                           parentRACode = '000001';
                        } else {
                           parentRACode = getResearchAreaCode($(liId).parents('li:eq(0)'));
                        }
                        // alert (parentRACode);
                        addSqlScripts("remove(("+getResearchAreaCode($(liId))+";"+parentRACode+"))");
                        if (deletedNodes == '') {
                            deletedNodes=getResearchAreaCode($(liId));
                        } else {
                            deletedNodes=deletedNodes+";"+getResearchAreaCode($(liId));
                        }
//                        if (newNodes != ";" && newNodes.indexOf(";"+getResearchAreaCode($(liId))+";") > -1) {
//                        	newNodes = newNodes.replace(";"+getResearchAreaCode($(liId))+";",";");
//                            //alert("newnodes "+newNodes);
//                        }   
                        deleteChild(id) ;         
                        $(liId).remove();
                        //alert (sqlScripts);
                        cutNode=null;
                        return false; // eliminate page jumping
                      }); 
    tag.html(image);
    image = $('<a href="#"><img src="static/images/tinybutton-cutnode.gif" width="79" height="15" border="0" alt="Cut Node" title="Cut this node and its child roups/sponsors.  (Node will not be removed until you paste it.)"></a>&nbsp').attr("id","cut"+idx).click(function() {
                        //alert("Cut node");
                         var liId="li#"+id;
                        cutNode = $(liId).clone(true);
                        removedNode=null; // remove & cutNode should not
											// co-exist
                        return false; // eliminate page jumping
                      }); 
    image.appendTo(tag);                  
    image = $('<a href="#"><img src="static/images/tinybutton-pastenode.gif" width="79" height="15" border="0" alt="Paste Node" title="Paste your previously cut node structure under this node"></a>').attr("id","paste"+idx).click(function() {

                  if (removedNode || cutNode) {
                        var parentNode = $("#"+id);
                        var ulTag = parentNode.children('ul');
                        if (ulTag.size() > 0) {                     
                             //alert(ulTag.attr("id"));
                        } else {
                           alert("not found")
                            i++;
                            ulTag = $('<ul class="filetree"></ul>').attr("id","ul"+i);                         
                        }   
                        if (removedNode) {
                            removedNode.appendTo(ulTag);
                            addSqlScripts(getInsertClause(getResearchAreaCode(removedNode), getResearchAreaCode($("#"+id)), getResearchAreaDescription(getResearchAreaCode($("#"+id)), removedNode.children('a:eq(0)').text())));
                            removedNode = null;
                        } else {
                            var liId = cutNode.attr("id");
                            // cutNode.parents() is not working but $("li#"+liId).parents() is fine.
                          //alert ("check ultags "+id+"-"+ $("li#"+liId).parents('li:eq(0)').attr("id")+"-"+$("li#"+liId).attr("id"));
                          if (id == $("li#"+liId).parents('li:eq(0)').attr("id")) {
                        	alert("can't paste to the parent node");
                          } else if (id == liId) {
                        	alert("can't paste to the same node");
                          } else{	
                            var liId = cutNode.attr("id");
                            var parentRACode;
                            // NOTE : $(cutNode).parents('li:eq(0)') is not
							// working
                            // cutNode.parents('li:eq(0)') is not working
                            // $("li#"+liId).parents('li:eq(0)')is ok
                            //alert(liId+"-"+$("li#"+liId).parents().size())
                            if ($("li#"+liId).parents('li:eq(0)').size() == 0) {
                                parentRACode = '000001';
                            } else {
                               parentRACode = getResearchAreaCode($("li#"+liId).parents('li:eq(0)'));
                            }
                            
                            addSqlScripts(getDeleteClause(getResearchAreaCode(cutNode), parentRACode));
                            $("li#"+liId).remove();
                            cutNode.appendTo(ulTag);
                            addSqlScripts(getInsertClause(getResearchAreaCode(cutNode), getResearchAreaCode($("#"+id)), getResearchAreaDescription(getResearchAreaCode($("#"+id)),cutNode.children('a:eq(0)').text())));
                            cutNode = null;
                            ulTag.appendTo(parentNode);
                          } // if then else if not paste back to parent node  
                        }
                       // right now is only doing for cutnode  
                       // ulTag.appendTo(parentNode);
                        
                        // alert("Remove node
						// "+removedNode.children('a:eq(0)').text());
                        // alert (sqlScripts);
                        
                        
                     }// if removednode
                  return false; // eliminate page jumping
                  }); 
                      
                      
                      
    image.appendTo(tag);     
    var notetag = $('<th style="text-align:right;"></th>').html("Node:");             
    tag = $('<tr></tr>').html(tag);
    notetag.prependTo(tag);
    tblTag.html(tag);
    
    // 2nd tr
    var trTag = $('<tr></tr>');
    var tdTag=$('<td class="infoline" style="width:60px;"></td>').html('&nbsp;');
    trTag.html(tdTag);
    var tdTag=$('<td class="infoline" style="width:100px;"></td>').html('<b>Parent Code</b>');
    tdTag.appendTo(trTag);
    var tdTag=$('<td class="infoline" style="width:100px;"></td>').html('<b>Research Code</b>');
    tdTag.appendTo(trTag);
    var tdTag=$('<td class="infoline"></td>').html('<b>Research Area</b>');
    tdTag.appendTo(trTag);
    var tdTag=$('<td class="infoline" style="width:65px;"></td>').html('<b>Action</b>');
    tdTag.appendTo(trTag);
     
    // 3rd tr
      var trTag1 = $('<tr></tr>');
      var tag1 = $('<th style="text-align:right;"></th>').html('Edit:');
      var tdTag1 ;
     // if (i < 6) {
     // alert(id +"-"+$("ul#"+ulTagId).parents('li:eq(0)').size());
     // }
      if ($("ul#"+ulTagId).parents('li:eq(0)').size() == 0) {
        // TODO : this is the second level, the children of '000001'
        tdTag1 = $('<td></td>').html("000001");
        // tdTag1 = $('<td></td>').html(getResearchAreaCode(name));
        // alert(getResearchAreaDescription(name));
      } else {
         // alert($("ul#"+ulTagId).parents('li:eq(0)').children('a:eq(0)').size());
        tdTag1 = $('<td></td>').html(getResearchAreaCode($("ul#"+ulTagId).parents('li:eq(0)')));
      }
      trTag1.html(tag1);
      tdTag1.appendTo(trTag1);
      tdTag1 = $('<td></td>').html(getResearchAreaCode($("#"+id)));
      tdTag1.appendTo(trTag1);
      tdTag1 = $('<td></td>').html($('<input type="text" name="m3" style="width:100%;" readonly="true"/>').attr("id","cdesc"+idx).attr("value",getResearchAreaDescription(getResearchAreaCode($("#"+id)), name)));
      tdTag1.appendTo(trTag1);
      tag1 = $('<th class="infoline" style="text-align:center;"></th>');
      var editlink = $('<a href="#"><img src="static/images/tinybutton-edit1.gif" width="40" height="15" border="0" title="update"></a>').attr("id","editRA"+idx).click(function() {
            var header = $("#raHeader"+$(this).attr("id").substring(6));
            // $("#raHeader"+i) will not work because "i" is evaluated when this
			// function is called; not when this function is created
            var desc = editResearchArea($(this).attr("id").substring(6));
            
            if (desc.length == 0) {
                alert("Research area can not be empty ");
            } else if ($("#cdesc"+$(this).attr("id").substring(6)).attr("value") != desc) {   
            	$("#cdesc"+$(this).attr("id").substring(6)).attr("value", desc);
            var newdesc = getResearchAreaCode($("#item"+$(this).attr("id").substring(6)))+" : "+desc;
            header.html(newdesc);
            $("#itemText"+$(this).attr("id").substring(6)).html(newdesc);
            addSqlScripts(getUpdateClause(getResearchAreaCode($("#item"+$(this).attr("id").substring(6))), desc));
            //alert ($(this).attr("id").substring(6) +"-" +desc+"-"+newdesc);
            // lots of trouble to update the description on item, so add
			// additional 'div' tag for this purposes.
            // tried many different ways, include 'replace', but it did not
			// work. So, finally decide on this approach.
            //alert(sqlScripts);
            }
            return false; // eliminate page jumping
            });  // end editlink click
                  
      tag1.html(editlink);
      tag1.appendTo(trTag1);
     
     
    // 4th tr
      var trTag2 = $('<tr></tr>');
      var tag2 = $('<th style="text-align:right;"></th>').html('Add:');
      var tdTag2 = $('<td></td>').html(getResearchAreaCode($("#"+id)));
      trTag2.html(tag2);
      tdTag2.appendTo(trTag2);
      tdTag2 = $('<td></td>').html($('<input type="text" name="m2" value="" style="width:100%;" maxlength="8" size="8"/>').attr("id","researchCode"+idx));
      tdTag2.appendTo(trTag2);
      tdTag2 = $('<td></td>').html($('<input type="text" name="m3" value="" style="width:100%;" />').attr("id","desc"+idx));
      tdTag2.appendTo(trTag2);
      tag2 = $('<th class="infoline" style="text-align:center;"></th>');
      var addlink = $('<a href="#"><img src="static/images/tinybutton-add1.gif" width="40" height="15" border="0" title="Add this Sub-group"></a>').attr("id","addRA"+idx).click(function() {
                           
            // alert("add
			// node"+$(this).parents('tr:eq(0)').children('th').size());
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
                   data:'researchAreaCode='+trNode.children('td:eq(1)').children('input:eq(0)').attr("value")+'&deletedRas='+deletedNodes+'&addRA=Y',
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
                     
             if (raExist == 'false' && newNodes != ";" && newNodes.indexOf(";"+trNode.children('td:eq(1)').children('input:eq(0)').attr("value")+";") > -1) {
            	 raExist = 'true';
             }   
             if (raExist == 'false') {
                 var parentNode = $("#"+id);
                 var ulTag = parentNode.children('ul');
                 if (parentNode.children('ul').size() == 0) {
                    i++;
                    ulTag = $('<ul class="filetree"></ul>').attr("id","ul"+id.substring(4));
                 }
              
                 ulTag.appendTo(parentNode); 
                                                            
                 var item_text = trNode.children('td:eq(1)').children('input:eq(0)').attr("value") +" : "+trNode.children('td:eq(2)').children('input:eq(0)').attr("value");
                 var listitem = setupListItem(trNode.children('td:eq(1)').children('input:eq(0)').attr("value") ,trNode.children('td:eq(2)').children('input:eq(0)').attr("value"));
                 // alert(listitem.html());
              // need this ultag to force to display folder.
              var childUlTag = $('<ul></ul>').attr("id","ul"+$(this).attr("id").substring(5));
              childUlTag.appendTo(listitem);
              
              // this is new nodes, so it is same as already loaded from DB
              var loadedId = "loaded"+idx;
              var inputtag = $('<input type="hidden"></input>').attr("id",loadedId);
              inputtag.appendTo(childUlTag);
              
                 listitem.appendTo(ulTag);
                 // force to display folder icon
                 $("#researcharea").treeview({
                     add: listitem
                 });
                	 newNodes=newNodes+trNode.children('td:eq(1)').children('input:eq(0)').attr("value")+";";
          
                 addSqlScripts(getInsertClause(trNode.children('td:eq(1)').children('input:eq(0)').attr("value"), getResearchAreaCode($("#"+id)), trNode.children('td:eq(2)').children('input:eq(0)').attr("value")));
              }  else {
                   alert ("Research Area Code already exist");
              }
             }                                
           return false; // eliminate page jumping
           });  // end addlink click
                  
      tag2.html(addlink);
      tag2.appendTo(trTag2);
      
      
	    trTag.appendTo(tblTag);
	    trTag1.appendTo(tblTag);
	    trTag2.appendTo(tblTag);
	    tag = $('<td class="subelementcontent"></td>').html(tblTag);
	    tag = $('<tr></tr>').html(tag);
	    tag = $('<tbody></tbody>').html(tag);
	    return tag;
    }    
    
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
        
        if($("#controlForAwardHierarchyView").attr("value") == 0){
      	  var abc = "<table style=\"border: medium none ; padding: 0px; width: 100%; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top;\">"+txtImage+"&nbsp;"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
	  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"
	  			+"</td>&nbsp;" + txtImage + "</tr></tbody></table>";
        }else if($("#controlForAwardHierarchyView").attr("value") == 1){
        	var abc = "<table style=\"border: medium none ; padding: 0px; width: 100%; border-collapse: collapse;\"><tbody><tr><td rowspan=\"3\" style=\"border: medium none ; border-collapse: collapse; vertical-align: top;\">"+txtImage+"&nbsp;"+text1+"</td>"
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
	  			+"<td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">" +text6 + "</td>&nbsp;" + txtImage
	  			+"</tr></tbody></table>";
        }else if($("#controlForAwardHierarchyView").attr("value") == 2){
	      	var abc = "<table style=\"border: medium none ; padding: 0px; width: 100%; border-collapse: collapse;\"><tbody><tr><td style=\"border: medium none ; border-collapse: collapse; vertical-align: top;\">"+txtImage+"&nbsp;"+text1+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
		  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].currentFundEffectiveDate\""+ " value=\"" +text2 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
		  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].obligationExpirationDate\""+ " value=\"" +text3 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
		  			+"<input type=\"text\" name=\"awardHierarchyNodeItems[" + racode2 + "].finalExpirationDate\""+ " value=\"" +text4 + "\" style=\"width:100%;\" maxlength=\"10\" size=\"10\"/>"+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
		  			+text5+"</td><td style=\"border: 1px solid rgb(153, 153, 153); padding: 0px; text-align: center; width: 112px; border-collapse: collapse;\">"
		  			+text6+"</td>&nbsp;" + txtImage + "</tr></tbody></table>";
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

