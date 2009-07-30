    var node;
    var i = 1;
    var removedNode=null;
    var cutNode;
    var sqlScripts = "";
    var ulTagId;
    var sqls = [];
    var sqlidx = 0;
    var deletedNodes="";
    var newNodes=";";
      
    $(document).ready(function(){
      $.ajaxSettings.cache = false; 
      $("#example").treeview({
                 toggle: function() {
                     var idstr=$(this).attr("id").substring(4);
                     var tagId = "listcontrol"+idstr;
                     var divId = "listcontent"+idstr;
                 
                     $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                     $("#"+divId).slideToggle(300);
                     loadChildrenRA($("#itemText"+idstr).text(), tagId);
                 
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
	 * A function to add sql statement to collection of sqlscripts, which will
	 * be sent to server when 'save' is clicked'. 1900 lenth limit for query
	 * string in ajax request.
	 */
      function addSqlScripts(sqlcommand) {
          // alert("add "+sqlcommand+"-"+sqlScripts)
          if ((sqlScripts.length+sqlcommand.length) > 1900) {
              // right now the query string also contains newquestionnaire
				// data, so
              // limit to 1700 for test now.
              sqls[sqlidx++] = sqlScripts;
              sqlScripts = "";
              // alert(sqlidx);
          }
          sqlScripts = sqlScripts + "#;#" + sqlcommand;
     }
       
       
              
              
       /*
		 * top level to add research area
		 */
       $("#add0").click(function(){    
       // click 'add' for 000001
            alert ("top add");
         
         
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
                 var ulTag = $("#example");
                                                            
                 var item_text = trNode.children('td:eq(1)').children('input:eq(0)').attr("value") +" : "+trNode.children('td:eq(2)').children('input:eq(0)').attr("value");
                 var listitem = setupListItem(item_text);
                 // alert(listitem.html());
              // need this ultag to force to display folder.
              var childUlTag = $('<ul></ul>').attr("id","ul"+i);
              childUlTag.appendTo(listitem);
                 listitem.appendTo(ulTag);
                 // alert("ultagid "+ulTag.attr("id").substring(2));
                 // force to display folder icon
                 $("#example").treeview({
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
              
          
    // }); // $(document).ready

    /*
	 * Load first level area of research when page is initially loaded
	 */
    function loadFirstLevel(){ 
      
      $.ajax({
        url: 'researchAreaAjax.do',
        type: 'GET',
        dataType: 'html',
        cache: false,
        data:'researchAreaCode=000001&addRA=',
        async:false,
        timeout: 1000,
        error: function(){
           alert('Error loading XML document');
        },
        success: function(xml){
           // alert("success"+xml);
           $(xml).find('h3').each(function(){
           var item_text = $(this).text();
           i++;
           var id = "item"+i;
           var tagId = "listcontrol"+i;
           var divId = "listcontent"+i;
           
          // NOTES : if use 'div', then FF will display the '+' and idDiv in
			// separate lines. IE7 is fine
          // But 'IE7 has problem with 'span'
          var idDiv;
          if ( jQuery.browser.msie ) { 
               idDiv = $('<div></div>').attr("id","itemText"+i).html(item_text); // for
																					// later
																					// change
																					// RA
																					// description
          } else {
               idDiv = $('<span>').attr("id","itemText"+i).html(item_text); // for
																			// later
																			// change
																			// RA
																			// description
          }
           var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(idDiv);
           var div = $('<div  class="hierarchydetail" style="margin-top:2px; "></div>').attr("id",divId);
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
           listitem.appendTo('ul#example');
                   // also need this to show 'folder' icon
           $('#example').treeview({
              add: listitem
              
           });
          // setupListItem(item_text).appendTo('ul#browser');
       
           });
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
         var tag = $('<th  style="background:#939393;height:18px;color:#FFFFFF;text-align:left;padding-left:4px;" align="left"></th>').attr("id","raHeader"+i).html(name);
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
                           parentRACode = getResearchAreaCode($(liId).parents('li:eq(0)').children('a:eq(0)').text());
                        }
                        // alert (parentRACode);
                        addSqlScripts("remove(("+getResearchAreaCode($(liId).children('a:eq(0)').text())+"))");
                        if (deletedNodes == '') {
                            deletedNodes=getResearchAreaCode($(liId).children('a:eq(0)').text());
                        } else {
                            deletedNodes=deletedNodes+";"+getResearchAreaCode($(liId).children('a:eq(0)').text());
                        }
                        if (newNodes != ";" && newNodes.indexOf(";"+getResearchAreaCode($(liId).children('a:eq(0)').text())+";") > -1) {
                        	newNodes = newNodes.replace(";"+getResearchAreaCode($(liId).children('a:eq(0)').text())+";",";");
                            alert("newnodes "+newNodes);
                        }   
                                   
                        $(liId).remove();
                        alert (sqlScripts);
                        cutNode=null;
                      }); 
    tag.html(image);
    image = $('<a href="#"><img src="static/images/tinybutton-cutnode.gif" width="79" height="15" border="0" alt="Cut Node" title="Cut this node and its child roups/sponsors.  (Node will not be removed until you paste it.)"></a>&nbsp').attr("id","cut"+idx).click(function() {
                        alert("Cut node");
                         var liId="li#"+id;
                        cutNode = $(liId).clone(true);
                        removedNode=null; // remove & cutNode should not
											// co-exist
                      }); 
    image.appendTo(tag);                  
    image = $('<a href="#"><img src="static/images/tinybutton-pastenode.gif" width="79" height="15" border="0" alt="Paste Node" title="Paste your previously cut node structure under this node"></a>').attr("id","paste"+idx).click(function() {

                  if (removedNode || cutNode) {
                        var parentNode = $("#"+id);
                        var ulTag = parentNode.children('ul');
                        if (ulTag.size() > 0) {                     
                             alert(ulTag.attr("id"));
                        } else {
                           alert("not found")
                            i++;
                            ulTag = $('<ul class="filetree"></ul>').attr("id","ul"+i);                         
                        }   
                        if (removedNode) {
                            removedNode.appendTo(ulTag);
                            addSqlScripts(getInsertClause(getResearchAreaCode(removedNode.children('a:eq(0)').text()), getResearchAreaCode(name), getResearchAreaDescription(removedNode.children('a:eq(0)').text())));
                            removedNode = null;
                        } else {
                            var liId = cutNode.attr("id");
                            var parentRACode;
                            // NOTE : $(cutNode).parents('li:eq(0)') is not
							// working
                            // cutNode.parents('li:eq(0)') is not working
                            // $("li#"+liId).parents('li:eq(0)')is ok
                            alert(liId+"-"+$("li#"+liId).parents().size())
                            if ($("li#"+liId).parents('li:eq(0)').size() == 0) {
                                parentRACode = '000001';
                            } else {
                               parentRACode = getResearchAreaCode($("li#"+liId).parents('li:eq(0)').children('a:eq(0)').text());
                            }
                            
                            addSqlScripts(getDeleteClause(getResearchAreaCode(cutNode.children('a:eq(0)').text()), parentRACode));
                            $("li#"+liId).remove();
                            cutNode.appendTo(ulTag);
                            addSqlScripts(getInsertClause(getResearchAreaCode(cutNode.children('a:eq(0)').text()), getResearchAreaCode(name), getResearchAreaDescription(cutNode.children('a:eq(0)').text())));
                            cutNode = null;
                        }
                        ulTag.appendTo(parentNode);
                        
                        // alert("Remove node
						// "+removedNode.children('a:eq(0)').text());
                        // alert (sqlScripts);
                        
                        
                     }// if removednode
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
        tdTag1 = $('<td></td>').html(getResearchAreaCode($("ul#"+ulTagId).parents('li:eq(0)').children('a:eq(0)').text()));
      }
      trTag1.html(tag1);
      tdTag1.appendTo(trTag1);
      tdTag1 = $('<td></td>').html(getResearchAreaCode(name));
      tdTag1.appendTo(trTag1);
      tdTag1 = $('<td></td>').html($('<input type="text" name="m3" style="width:100%;" readonly="true"/>').attr("id","cdesc"+i).attr("value",getResearchAreaDescription(name)));
      tdTag1.appendTo(trTag1);
      tag1 = $('<th class="infoline" style="text-align:center;"></th>');
      var editlink = $('<a href="#"><img src="static/images/tinybutton-edit1.gif" width="40" height="15" border="0" title="update"></a>').attr("id","editRA"+i).click(function() {
            var header = $("#raHeader"+$(this).attr("id").substring(6));
            // $("#raHeader"+i) will not work because "i" is evaluated when this
			// function is called; not when this function is created
            alert ($(this).attr("id").substring(6));
            
            var desc = editResearchArea($(this).attr("id").substring(6));
            
            if (desc.length == 0) {
                alert("Research area can not be empty ");
            } else if ($("#cdesc"+$(this).attr("id").substring(6)).attr("value") != desc) {   
            	$("#cdesc"+$(this).attr("id").substring(6)).attr("value", desc);
            var newdesc = getResearchAreaCode(header.text())+" : "+desc;
            header.html(newdesc);
            $("#itemText"+$(this).attr("id").substring(6)).html(newdesc);
            addSqlScripts(getUpdateClause(getResearchAreaCode(header.text()), desc));
            // lots of trouble to update the description on item, so add
			// additional 'div' tag for this purposes.
            // tried many different ways, include 'replace', but it did not
			// work. So, finally decide on this approach.
            alert(sqlScripts);
            }
            });  // end editlink click
                  
      tag1.html(editlink);
      tag1.appendTo(trTag1);
     
     
    // 4th tr
      var trTag2 = $('<tr></tr>');
      var tag2 = $('<th style="text-align:right;"></th>').html('Add:');
      var tdTag2 = $('<td></td>').html(getResearchAreaCode(name));
      trTag2.html(tag2);
      tdTag2.appendTo(trTag2);
      tdTag2 = $('<td></td>').html($('<input type="text" name="m2" value="" style="width:100%;" maxlength="8" size="8"/>').attr("id","researchCode"+i));
      tdTag2.appendTo(trTag2);
      tdTag2 = $('<td></td>').html($('<input type="text" name="m3" value="" style="width:100%;" />').attr("id","desc"+i));
      tdTag2.appendTo(trTag2);
      tag2 = $('<th class="infoline" style="text-align:center;"></th>');
      var addlink = $('<a href="#"><img src="static/images/tinybutton-add1.gif" width="40" height="15" border="0" title="Add this Sub-group"></a>').attr("id","addRA"+i).click(function() {
                           
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
                    ulTag = $('<ul class="filetree"></ul>').attr("id","ul"+i);
                 }
              
                 ulTag.appendTo(parentNode); 
                                                            
                 var item_text = trNode.children('td:eq(1)').children('input:eq(0)').attr("value") +" : "+trNode.children('td:eq(2)').children('input:eq(0)').attr("value");
                 var listitem = setupListItem(item_text);
                 // alert(listitem.html());
              // need this ultag to force to display folder.
              var childUlTag = $('<ul></ul>').attr("id","ul"+i);
              childUlTag.appendTo(listitem);
                 listitem.appendTo(ulTag);
                 alert("ultagid "+ulTag.attr("id").substring(2));
                 // force to display folder icon
                 $("#example").treeview({
                     add: listitem
                 // toggle: function() {
                 // var subul=this.getElementsByTagName("ul")[0]
                 // if (subul.style.display=="block")
                 // alert("You've opened this Folder!")
                 // }
                 });
                	 newNodes=newNodes+trNode.children('td:eq(1)').children('input:eq(0)').attr("value")+";";
          
                 // apend to sqlScripts
                 addSqlScripts(getInsertClause(trNode.children('td:eq(1)').children('input:eq(0)').attr("value"), getResearchAreaCode(name), trNode.children('td:eq(2)').children('input:eq(0)').attr("value")));
                 // alert("sqlScripts = "+sqlScripts);
              }  else {
                   alert ("Research Area Code already exist");
              }
             }                                
           });  // end addlink click
                  
      tag2.html(addlink);
      tag2.appendTo(trTag2);
      
      
    trTag.appendTo(tblTag);
    trTag1.appendTo(tblTag);
    trTag2.appendTo(tblTag);
    tag = $('<td class="subelementcontent"></td>').html(tblTag);
    // alert("1"+tag.html());
    tag = $('<tr></tr>').html(tag);
    // alert(tag.html());
    tag = $('<tbody></tbody>').html(tag);
    // alert(tag.html());
    return tag;
  }

  /*
	 * This is for editing 'research area' description. it will pop up a window
	 * to ask for modification.
	 */
  function editResearchArea(idx) {
	  var desc = $("#cdesc"+idx).attr("value");
      var newDesc = window.prompt("Modify Research Area ", desc);
      newDesc = newDesc.trim();
        if (newDesc.length > 200) {
        	newDesc = newDesc.substring(0,200);
        }
        return newDesc;
  }
  
  /*
	 * set up area of resear list tag. the main table detail is not set up
	 * initially.
	 */
  function setupListItem(name) {
              i++;
              var id1 = "item"+i;
              var tagId = "listcontrol"+i;
              var divId = "listcontent"+i;
              var idDiv;
              if ( jQuery.browser.msie ) { 
                  idDiv = $('<div></div>').attr("id","itemText"+i).html(name); // for
																				// later
																				// change
																				// RA
																				// description
              } else {
                  idDiv = $('<span>').attr("id","itemText"+i).html(name); // for
																			// later
																			// change
																			// RA
																			// description
              }
              var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(idDiv);
              var detDiv = $('<div  class="hierarchydetail" style="margin-top:2px; "></div>').attr("id",divId);
         // $(document).ready(function () {
              $(tag).click(
                      function()
                      {
                          $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                          if ($(this).siblings('div:eq(1)').children('table:eq(0)').size() == 0) {
                              var idx = $(this).attr("id").substring(11);
                              tableTag(name, "item"+idx).appendTo($("#listcontent"+idx));
                              if ($("#"+divId).is(":hidden")) {
                                  // alert(divId + " hidden");
                                  // $("#listcontent"+idx).slideToggle(300);
                                  $("#listcontent"+idx).show();
                              }
                          } else {   

                         // $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                              $("#"+divId).slideToggle(300);
                              // $("#"+divId).show();;
                          }  
                          // comment out temporarily
                          loadChildrenRA(name, tagId);
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
           url: 'researchAreaAjax.do',
           type: 'GET',
           dataType: 'html',
           data:'researchAreaCode='+getResearchAreaCode(nodeName),
           cache: false,
           async: false,
           timeout: 1000,
           error: function(){
              alert('Error loading XML document');
           },
           success: function(xml){
              // alert("success"+xml);
              i++;
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
              var id = "item"+i;
              var tagId = "listcontrol"+i;
              var divId = "listcontent"+i;
              
             // if (i == 1) {
          var idDiv;
          if ( jQuery.browser.msie ) { 
               idDiv = $('<div></div>').attr("id","itemText"+i).html(item_text); // for
																					// later
																					// change
																					// RA
																					// description
          } else {    
               idDiv = $('<span>').attr("id","itemText"+i).html(item_text); // for
																			// later
																			// change
																			// RA
																			// description
          }
              var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(idDiv);
              var detDiv = $('<div  class="hierarchydetail" style="margin-top:2px; "></div>').attr("id",divId);
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
              $("#example").treeview({
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
      
  } // end loadChildrenRA

  /*
	 * Utility function to get code from 'code : description' This need to be
	 * refined because if code contains ':', then this is not working correctly.
	 */
  function getResearchAreaCode(nodeName) {
   // TODO : this maybe problemmatic because it makes the assumption that
	// areacode does not contain ":"
          var endIdx = nodeName.indexOf(":");
          return nodeName.substring(0, endIdx - 1);    
  }
  
  /*
	 * similar to getResearchAreaCode, except this is for 'description'
	 */
  function getResearchAreaDescription(nodeName) {

          var endIdx = nodeName.indexOf(":");
          // alert(endIdx);
          return nodeName.substring(endIdx+2);    
          // return nodeName;
  }

      /*
		 * create insert sql statement
		 */
      function getInsertClause(code, parentCode, description) {
          // TODO need to rework on real update_user
       
          var columns="RESEARCH_AREA_CODE,PARENT_RESEARCH_AREA_CODE,HAS_CHILDREN_FLAG, DESCRIPTION, update_timestamp, update_user";
          description = description.replace(/'/g, "''");

          var values="'"+code+"','"+parentCode+"', 'N', '"+description+"', sysdate, user";
          return "insert R values("+values+")";
       }
     
      /*
		 * create delete sql statement
		 */
      function getDeleteClause(code, parentCode) {
       
           return "delete R'" + code +"' and PARENT_RESEARCH_AREA_CODE = '"+parentCode+"'";
       }

      /*
		 * create update sql statement to update description
		 */
     function getUpdateClause(code, newDesc) {
    	 newDesc = newDesc.replace(/'/g, "''");
           return "update R'"+newDesc+ "' where RESEARCH_AREA_CODE = '" + code +"'";   
     }

     function okToSave() {
             alert("oktosave " );
              document.getElementById("sqlScripts").value=sqlScripts;
             return "true";
          
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
	  //}
      // $("#listcontrol00").show();
      // $("#listcontent00").slideToggle(300);
  })
    $("#loading").hide();

  /*
	 * function to save what has been done up to this point.
	 */
  $("#save").click(function(){    
      if (sqlScripts.indexOf("#;#") > -1) {
           // if current sqlScripts is not in array yet
           // 10 should be fine to use as check
           sqls[sqlidx++] = sqlScripts;
       }
       alert ("save"+sqls.length+sqlScripts); 
             
     for ( var k = 0; k < sqls.length; k++) {
       sqlScripts = sqls[k];
       sqlScripts = sqlScripts.replace(/#;#/g, ";;;");
       $("#headermsg").html(""); // clear error message
       var retmsg;
       $.ajax({
        url: 'researchAreaAjax.do',
        type: 'GET',
        dataType: 'html',
        cache: false,
        data:'sqlScripts='+sqlScripts+'&addRA=S',
        async:false,
        timeout: 1000,
        error: function(){
           // alert('Error loading XML document');
           // jumpToAnchor('topOfForm');
           $('<span id="msg"/>').css("color", "red").html(
                   "Error when save Areas of Research").appendTo(
                   $("#headermsg"))
           $('<br/>').appendTo($("#headermsg"));
           
        },
        success: function(xml){
            $(xml).find('h3').each(function(){
                retmsg = $(this).text();
             // alert(raExist);
 
              });
            if (retmsg == 'Success') {
           sqlScripts = "";
               $('<span id="msg"/>').css("color", "black").html(
                       "Areas of Research saved successfully").appendTo(
                       $("#headermsg"));
               $('<br/>').appendTo($("#headermsg"));
              // jumpToAnchor('topOfForm');
           // alert("success"+xml);
            } else {
            	// alert (retmsg);
                $('<span id="msg"/>').css("color", "red").html(
                "Error when save Areas of Research <br/>"+retmsg).appendTo(
                $("#headermsg"))
               $('<br/>').appendTo($("#headermsg"));
            }	
        }
       });
    }
          sqlidx = 0;
         
        return false;
   }); 

    
