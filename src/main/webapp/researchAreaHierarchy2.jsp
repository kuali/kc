 <%--
 Copyright 2006-2008 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Research Areas Hierarchy</title>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
                    "http://www.w3.org/TR/html4/loose.dtd">


  			<script language="JavaScript" type="text/javascript"
				src="dwr/engine.js"></script>

			<script language="JavaScript" type="text/javascript"
				src="dwr/util.js"></script>

			<script language="JavaScript" type="text/javascript"
				src="dwr/interface/CustomAttributeService.js"></script>

			<script language="JavaScript" type="text/javascript"
				src="dwr/interface/SponsorService.js"></script>


  <script src="scripts/jquery/jquery.js"></script>
  <script src="kr/scripts/core.js"></script>
  <link rel="stylesheet" href="css/jquery/screen.css" type="text/css" />
  <link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
  <link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
  <link href="kr/css/kuali.css" rel="stylesheet" type="text/css" />
  <%-- link rel="stylesheet" href="http://dev.jquery.com/view/trunk/plugins/treeview/jquery.treeview.css" type="text/css" /--%>
  <script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
  
  
  
  
  <script>
  var node;
  var i = 1;
  var removedNode;
  var cutNode;
  var sqlScripts = "";
  var ulTagId;
  
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
               
                  //var subul=this.getElementsByTagName("ul")[0]
                  //if (subul.style.display=="block")
                  //   alert("You've opened this Folder!" + idstr)
                  },
              animated: "fast",
              collapsed: true,
              control: "#treecontrol"
                  
               
            });
   // $("#browser").treeview();
    //$("div#foo").append("Hello World!").css("color","red");
/*
    $.ajaxStart(function() {   
       $("div#foo").text("Loading...");   
    });  
    $.ajaxComplete(function() {   
       $("div#foo").text("");   
    });  
*/    
    
    //$("body").append('<div id="loading"></div>');
    //$("#loading").css("color","red");
    $(document).ajaxStart(function(){
    // this is weird, it will not show if the alert is not included??
       //return false;
       //var img = $('<a href="#"><img src="static/images/jquery/ajax-loader.gif" /></a>')
       $("#loading").show();
       //alert ("start Ajax");
       //return false;
     });

    $(document).ajaxComplete(function(){
       //alert ("complete Ajax");
       $("#loading").hide();
       //return false;
     });
    
    
     
     
     $("#save").click(function(){    
       alert ("save"); 
       $.ajax({
         url: 'researchAreaAjax.do',
         type: 'GET',
         dataType: 'html',
         cache: false,
         data:'sqlScripts='+sqlScripts+'&addRA=S',
         async:false,
         timeout: 1000,
         error: function(){
            alert('Error loading XML document');
         },
         success: function(xml){
            //alert("success"+xml);
         }
       });
       return false;
      }); 
            
            
            
     $("#add0").click(function(){    
     // click 'add' for 000001
          alert ("top add");
       
       
                 var trNode = $(this).parents('tr:eq(0)');
          //alert(trNode.children('td:eq(1)').children('input:eq(0)').attr("value")+"-"+trNode.children('td:eq(2)').children('input:eq(0)').attr("value"));

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
                    //alert(raExist);
        
                     });
                  }
               });   // end ajax  
         
         
           if (raExist == 'false') {
               var ulTag = $("#example");
                                                          
               var item_text = trNode.children('td:eq(1)').children('input:eq(0)').attr("value") +" : "+trNode.children('td:eq(2)').children('input:eq(0)').attr("value");
               var listitem = setupListItem(item_text);
               //alert(listitem.html());
            // need this ultag to force to display folder.
            var childUlTag = $('<ul></ul>').attr("id","ul"+i);
            childUlTag.appendTo(listitem);
               listitem.appendTo(ulTag);
               //alert("ultagid "+ulTag.attr("id").substring(2));
               // force to display folder icon
               $("#example").treeview({
                   add: listitem
               // toggle: function() {
               //   var subul=this.getElementsByTagName("ul")[0]
               //   if (subul.style.display=="block")
               //      alert("You've opened this Folder!")
               //   } 
               });
               
               // apend to sqlScripts
               sqlScripts = sqlScripts +"#;#"+getInsertClause(trNode.children('td:eq(1)').children('input:eq(0)').attr("value"), '000001', trNode.children('td:eq(2)').children('input:eq(0)').attr("value"));
               //alert("sqlScripts = "+sqlScripts);
            }  else {
                 alert ("Research Area Code already exist");
            }
           };                                
       
        
       return false;
      }); // add0 
            
  }); // $(document).ready
        
  // }); // $(document).ready

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
         //alert("success"+xml);
         $(xml).find('h3').each(function(){
         var item_text = $(this).text();
         i++;
         //var item = $(this).find('item').text()
         //alert(item_text+"-")
         //var text1 = $('<span class="file"></span>').html(item_text);
         //alert(text1)
        // $('<li></li>').html($('<span class="file"></span>').html(item_text)).appendTo('ul#file31');
         var id = "item"+i;
         var tagId = "listcontrol"+i;
         var divId = "listcontent"+i;
         
        // if (i == 1) {
         var idDiv = $('<div></div>').attr("id","itemText"+i).html(item_text); // for later change RA description
         var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(idDiv);
         var div = $('<div  class="hierarchydetail" style="margin-top:2px; "></div>').attr("id",divId);
         //$(document).ready(function () {
             tag.click(
                    function()
                    {
                        //alert ("sibling "+$(this).siblings('div:eq(0)').attr("id"));
                        $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                        if ($(this).siblings('div:eq(1)').children('table:eq(0)').size() == 0) {
                            var idx = $(this).attr("id").substring(11);
                        	tableTag(item_text, "item"+idx).appendTo($("#listcontent"+idx));
                            $("#"+divId).slideToggle(300);
                        }    
                        $("#"+divId).slideToggle(300);
                        loadChildrenRA(item_text, tagId);
                    }
                );
            // });        
//         } else {
//         var tag = $('<a id="listcontrol04" ></a>').attr("style","margin-left:2px;").html(item_text);
//         var div = $('<div id="listcontent04" class="hierarchydetail" style="margin-top:2px; "></div>');
//         }
          //<div class="hierarchydetail" id="listcontent02" style="margin-top:2px;">
         
         //var text = <a id="listcontrol02" style="margin-left:2px;">01. : AGRICULTURE, AGRICULTURE OPERATIONS, AND RELATED SCIENCES</a>
         var listitem = $('<li class="closed"></li>').attr("id",id).html(tag);
         //tag.appendTo(listitem);
         //listitem.appendTo('ul#file31');
         ulTagId = "browser";
         //tableTag(item_text, id).appendTo(div)
         div.appendTo(listitem);
         // need this ultag to force to display folder.
         var childUlTag = $('<ul></ul>').attr("id","ul"+i);
         childUlTag.appendTo(listitem);
         listitem.appendTo('ul#example');
                 // also need this to show 'folder' icon
         $('#example').treeview({
            add: listitem
            // toggle: function() {
            //   var subul=this.getElementsByTagName("ul")[0]
            //   if (subul.style.display=="block")
            //      alert("You've opened this Folder!")
            //   } 
            
         });
        // setupListItem(item_text).appendTo('ul#browser');
     
         });
      }
     });  
    // return false;  
  }  // generate
  

function tableTag(name, id) {

       var link = $('<a href="#" class="hidedetail"><img src="kr/static/images/tinybutton-hide.gif" align="absmiddle" border="0" width="45" height="15"></a>');
       var tag = $('<th  class="subelementheader" align="left"></th>').attr("id","raHeader"+i).html(name);
       link.prependTo(tag);
       tag = $('<tr></tr>').html(tag);
       tag = $('<thead></thead>').html(tag);
       tag = $('<table width="100%" cellpadding="0" cellspacing="0" class="subelement"> </table>').html(tag);
       tbodyTag(name, id).appendTo(tag);
       return tag;
}

function tbodyTag(name, id) {

  var tblTag = $('<table cellpadding="0" cellspacing="0" class="elementtable" width="100%"></table>')
  var tag=$('<th colspan="4"></th>');
  var image = $('<a href="#"><img src="static/images/tinybutton-removenode.gif" width="79" height="15" border="0" alt="Remove Node" title="Remove this node and its child groups/sponsors"></a>&nbsp').attr("id","remove"+i).click(function() {
                      var liId="li#"+id;
                      removedNode = $(liId).clone(true);
                      //removedNode = $(liId); // this will not work because event also lost
                      //alert("Remove node "+removedNode.attr("id"));
                      var parentRACode;
                      if ($(liId).parents('li:eq(0)').size() == 0) {
                         parentRACode = '000001';
                      } else {
                         parentRACode = getResearchAreaCode($(liId).parents('li:eq(0)').children('a:eq(0)').text());
                      }
                      alert (parentRACode);
                      sqlScripts = sqlScripts +"#;#"+getDeleteClause(getResearchAreaCode(removedNode.children('a:eq(0)').text()), parentRACode);
                      $(liId).remove();
                      alert (sqlScripts);
                      cutNode=null;
                    }); 
  tag.html(image);
  image = $('<a href="#"><img src="static/images/tinybutton-cutnode.gif" width="79" height="15" border="0" alt="Cut Node" title="Cut this node and its child roups/sponsors.  (Node will not be removed until you paste it.)"></a>&nbsp').attr("id","cut"+i).click(function() {
                      alert("Cut node");
                       var liId="li#"+id;
                      cutNode = $(liId).clone(true);
                      removedNode=null; // remove & cutNode should not co-exist
                    }); 
  image.appendTo(tag);                  
  image = $('<a href="#"><img src="static/images/tinybutton-pastenode.gif" width="79" height="15" border="0" alt="Paste Node" title="Paste your previously cut node structure under this node"></a>').attr("id","paste"+i).click(function() {

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
                          sqlScripts = sqlScripts +"#;#"+getInsertClause(getResearchAreaCode(removedNode.children('a:eq(0)').text()), getResearchAreaCode(name), getResearchAreaDescription(removedNode.children('a:eq(0)').text()));
                          removedNode = null;
                      } else {
                          var liId = cutNode.attr("id");
                          var parentRACode;
                          if ($(liId).parents('li:eq(0)').size() == 0) {
                              parentRACode = '000001';
                          } else {
                             parentRACode = getResearchAreaCode($("li#"+liId).parents('li:eq(0)').children('a:eq(0)').text());
                          }
                          
                          sqlScripts = sqlScripts +"#;#"+getDeleteClause(getResearchAreaCode(cutNode.children('a:eq(0)').text()), parentRACode);
                          $("li#"+liId).remove();
                          cutNode.appendTo(ulTag);
                          sqlScripts = sqlScripts +"#;#"+getInsertClause(getResearchAreaCode(cutNode.children('a:eq(0)').text()), getResearchAreaCode(name), getResearchAreaDescription(cutNode.children('a:eq(0)').text()));
                          cutNode = null;
                      }
                      ulTag.appendTo(parentNode);
                      
                      //alert("Remove node "+removedNode.children('a:eq(0)').text());
                      //alert (sqlScripts);
                      
                      
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
      //tdTag1 = $('<td></td>').html(getResearchAreaCode(name));
      //alert(getResearchAreaDescription(name));
    } else {
       // alert($("ul#"+ulTagId).parents('li:eq(0)').children('a:eq(0)').size());
      tdTag1 = $('<td></td>').html(getResearchAreaCode($("ul#"+ulTagId).parents('li:eq(0)').children('a:eq(0)').text()));
    }
    trTag1.html(tag1);
    tdTag1.appendTo(trTag1);
    tdTag1 = $('<td></td>').html(getResearchAreaCode(name));
    tdTag1.appendTo(trTag1);
    tdTag1 = $('<td></td>').html($('<input type="text" name="m3" style="width:100%;" />').attr("id","cdesc"+i).attr("value",getResearchAreaDescription(name)));
    tdTag1.appendTo(trTag1);
    tag1 = $('<th class="infoline" style="text-align:center;"></th>');
    var editlink = $('<a href="#"><img src="static/images/tinybutton-update.gif" width="40" height="15" border="0" title="update"></a>').attr("id","editRA"+i).click(function() {
          var header = $("#raHeader"+$(this).attr("id").substring(6));
          //$("#raHeader"+i) will not work because "i" is evaluated when this function is called; not when this function is created
          alert ($(this).attr("id").substring(6));
          var newdesc = getResearchAreaCode(header.text())+" : "+$("#cdesc"+$(this).attr("id").substring(6)).attr("value");
          header.html(newdesc);
          $("#itemText"+$(this).attr("id").substring(6)).html(newdesc);
          sqlScripts = sqlScripts +"#;#"+getUpdateClause(getResearchAreaCode(header.text()), $("#cdesc"+$(this).attr("id").substring(6)).attr("value"));
          // lots of trouble to update the description on item, so add additional 'div' tag for this purposes.          
          // tried many different ways, include 'replace', but it did not work.   So, finally decide on this approach.
          alert(sqlScripts);
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
                         
          //alert("add node"+$(this).parents('tr:eq(0)').children('th').size());
          var trNode = $(this).parents('tr:eq(0)');
          //alert(trNode.children('td:eq(1)').children('input:eq(0)').attr("value")+"-"+trNode.children('td:eq(2)').children('input:eq(0)').attr("value"));

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
                    //alert(raExist);
        
                     });
                  }
               });   // end ajax  
         
         
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
               //alert(listitem.html());
            // need this ultag to force to display folder.
            var childUlTag = $('<ul></ul>').attr("id","ul"+i);
            childUlTag.appendTo(listitem);
               listitem.appendTo(ulTag);
               alert("ultagid "+ulTag.attr("id").substring(2));
               // force to display folder icon
               $("#example").treeview({
                   add: listitem
               // toggle: function() {
               //   var subul=this.getElementsByTagName("ul")[0]
               //   if (subul.style.display=="block")
               //      alert("You've opened this Folder!")
               //   } 
               });
               
               // apend to sqlScripts
               sqlScripts = sqlScripts +"#;#"+getInsertClause(trNode.children('td:eq(1)').children('input:eq(0)').attr("value"), getResearchAreaCode(name), trNode.children('td:eq(2)').children('input:eq(0)').attr("value"));
               //alert("sqlScripts = "+sqlScripts);
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
  //alert("1"+tag.html());
  tag = $('<tr></tr>').html(tag);
  //alert(tag.html());
  tag = $('<tbody></tbody>').html(tag);
  //alert(tag.html());
  return tag;
}

function setupListItem(name) {
            i++;
            var id1 = "item"+i;
            var tagId = "listcontrol"+i;
            var divId = "listcontent"+i;
            var idDiv = $('<div></div>').attr("id","itemText"+i).html(name); // for later change RA description
            var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(idDiv);
            var detDiv = $('<div  class="hierarchydetail" style="margin-top:2px; "></div>').attr("id",divId);
       //$(document).ready(function () {
            $(tag).click(
					function()
					{
                        $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                        if ($(this).siblings('div:eq(1)').children('table:eq(0)').size() == 0) {
                            var idx = $(this).attr("id").substring(11);
                            tableTag(name, "item"+idx).appendTo($("#listcontent"+idx));
                            $("#listcontent"+idx).slideToggle(300);
                        }    

                       // $(".hierarchydetail:not(#"+divId+")").slideUp(300);
						$("#"+divId).slideToggle(300);
						// comment out temporarily
						loadChildrenRA(name, tagId);
					}
				);
		//});
			//alert(tag.html());	
            var listitem = $('<li class="closed"></li>').attr("id",id1).html(tag);
            //tableTag(name, id1).appendTo(detDiv)
            detDiv.appendTo(listitem);
            //alert(listitem.html());
            return listitem;
}


function loadChildrenRA(nodeName, tagId) {
    var parentNode = $("#"+tagId);
    //alert ("load subnodes for "+nodeName+"-"+parentNode.parents('li:eq(0)').attr("id")+"-" );
    var liNode = parentNode.parents('li:eq(0)');
    var ulNode = liNode.children('ul:eq(0)');
    var inputNodev;
//    alert (ulNode);
//    if (liNode.children('ul').size() > 0 ) {
//        inputNodev = ulNode.children('input:eq(0)');
//    }
    
    
    //if (liNode.children('ul').size() == 0 ) {
    if (liNode.children('ul').size() == 0 || ulNode.children('input').size() == 0 ) {
        //alert(liNode.children('ul').size());
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
            //alert("success"+xml);
            i++;
            var ulTag ;
            if (liNode.children('ul').size() == 0) {
                ulTag = $('<ul class="filetree"></ul>').attr("id","ul"+i);
            } else {
                ulTag = ulNode;
            }
           
            //alert(ulTag.html());
            
            //ulTag.appendTo(parentNode);
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
            var idDiv = $('<div></div>').attr("id","itemText"+i).html(item_text); // for later change RA description
            var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(idDiv);
            var detDiv = $('<div  class="hierarchydetail" style="margin-top:2px; "></div>').attr("id",divId);
            tag.click(
					function()
					{
					    //alert ("click "+tagId);
                        $(".hierarchydetail:not(#"+divId+")").slideUp(300);
                        if ($(this).siblings('div:eq(1)').children('table:eq(0)').size() == 0) {
                            var idx = $(this).attr("id").substring(11);
                            tableTag(item_text, "item"+idx).appendTo($("#listcontent"+idx));
                            $("#"+divId).slideToggle(300);
                        }    

						//$(".hierarchydetail:not(#"+divId+")").slideUp(300);
						$("#"+divId).slideToggle(300);
						loadChildrenRA(item_text, tagId);
					}
				);
            var listitem = $('<li class="closed"></li>').attr("id",id).html(tag);
            //tag.appendTo(listitem);
            //listitem.appendTo('ul#file31');
            ulTagId = ulTag.attr("id");
            //tableTag(item_text, id).appendTo(detDiv)
            detDiv.appendTo(listitem);
            //listitem.appendTo('ul#file31');
            // need this ultag to force to display folder.
            var childUlTag = $('<ul></ul>').attr("id","ul"+i);
            childUlTag.appendTo(listitem);
            listitem.appendTo(ulTag);
            // force to display folder icon
            $("#example").treeview({
               add: listitem
               // toggle: function() {
               //   var subul=this.getElementsByTagName("ul")[0]
               //   if (subul.style.display=="block")
               //      alert("You've opened this Folder!")
               //   } 
            });
            
            if (i==1) {
            //alert (listitem.html());
            }
        
            });
         }
        });    
    }
    
} // end loadChildrenRA

function getResearchAreaCode(nodeName) {

        var endIdx = nodeName.indexOf(":");
        return nodeName.substring(0, endIdx - 1);    
}
function getResearchAreaDescription(nodeName) {

        var endIdx = nodeName.indexOf(":");
        //alert(endIdx);
        return nodeName.substring(endIdx+2);    
        //return nodeName;    
}

    function getInsertClause(code, parentCode, description) {
     
        var columns="RESEARCH_AREA_CODE,PARENT_RESEARCH_AREA_CODE,HAS_CHILDREN_FLAG, DESCRIPTION, update_timestamp, update_user";
        // need to rework on real update_user
        var values="'"+code+"','"+parentCode+"', 'N', '"+description+"', sysdate, user";
         return "insert into research_areas ("+columns+") values("+values+")";
     }
   
    function getDeleteClause(code, parentCode) {
     
         return "delete from research_areas where RESEARCH_AREA_CODE = '" + code +"' and PARENT_RESEARCH_AREA_CODE = '"+parentCode+"'";
     }

   function getUpdateClause(code, newDesc) {
         return "update research_areas set DESCRIPTION ='"+newDesc+ "' where RESEARCH_AREA_CODE = '" + code +"'";   
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



   
   
   

    </script>
  
</head>
<body>
<%--
<form name="ResearchAreasForm" id="kualiForm" method="post" action="/kra-dev/researchAreas.do" 
    enctype="" > 
--%>
<html:form styleId="kualiForm" method="post" action="/researchAreas.do"
	enctype="" onsubmit="return hasFormAlreadyBeenSubmitted();">

	<div class="headerarea-small" id="headerarea-small">
	<h1 align="center">Research Areas Hierarchy</h1>
	</div>

	<input type="hidden" id="sqlScripts" name="sqlScripts" />

   <div id = "loading">
      <a href="#"><img src="static/images/jquery/ajax-loader.gif" /></a>
   </div> 

<%-- 0000001 --%>
                                            <img src="static/images/jquery/hierarchy-root.png" width="14" height="14" border="0"> <a id="listcontrol00" style="margin-left:2px;">000001 : All Research Areas</a>
                                            <div id="treecontrol" style="display:inline;">
                                                &nbsp;&nbsp;&nbsp;&nbsp;<a title="Collapse the entire tree below" href="#"><img src="static/images/jquery/minus.gif" /> Collapse All</a>
                                                &nbsp;&nbsp;&nbsp;&nbsp;<a title="Expand the entire tree below" href="#"><img src="static/images/jquery/plus.gif" /> Expand All</a>
                                                <!--&nbsp;&nbsp;&nbsp;&nbsp;<a title="Toggle the tree below, opening closed branches, closing open branches" href="#">Toggle All</a>-->
                                                <!--<a href="#"><img align="absmiddle" src="../images/searchicon.gif" width="16" height="16" border="0" alt="Search for Group" title="Search for Area of Research"></a>-->
                                            </div>
                                            <div class="hierarchydetail" id="listcontent00" style="margin-top:2px;">
                                            	
                                                <table width="100%" cellpadding="0" cellspacing="0" class="subelement">
                                                    <thead>
                                                        <tr>
                                                            <th class="subelementheader" align="left">
                                                                <a href="#" class="hidedetail"><img src="../images/tinybutton-hide.gif" align="absmiddle" border="0" width="45" height="15"></a>
                                                                000001 : All Research Areas
                                                            </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td class="subelementcontent">
                                                                
                                                                <table cellpadding="0" cellspacing="0" class="elementtable" width="100%">
                                                                    <!--<tr>
                                                                        <th style="text-align:right;">Node:</th>
                                                                        <th colspan="4">
                                                                            <span class="fineprint">Area of Research root can not be moved.</span>
                                                                        </th>
                                                                    </tr>-->
                                                                     <tr>
                                                                        <td class="infoline" style="width:60px;">&nbsp;
                                                                            
                                                                        </td>
                                                                        <td class="infoline" style="width:100px;">
                                                                            <b>Parent Code</b>
                                                                        </td>
                                                                        <td class="infoline" style="width:100px;">
                                                                            <b>Research Code</b>
                                                                        </td>
                                                                        <td class="infoline">
                                                                            <b>Research Area</b>
                                                                        </td> 
                                                                        <td class="infoline" style="width:65px;">
                                                                            <b>Action</b>
                                                                        </td>
                                                                    </tr>
                                                                    <!--<tr>
                                                                        <th style="text-align:right;">
                                                                            Edit:
                                                                        </th>
                                                                        <td colspan='4'>
                                                                           <span class="fineprint">Area of Research root can not be modified.</span>
                                                                        </th> 
                                                                    </tr>-->
                                                                    <tr>
                                                                        <th style="text-align:right;">
                                                                            Add:
                                                                        </th>
                                                                        <td>
                                                                            000001
                                                                        </td>
                                                                        <td>
                                                                            <input type="text" name="m2" value="" style="width:100%;" maxlength="8" size="8"/>
                                                                        </td>
                                                                        <td>
                                                                            <input type="text" name="m3" value="" style="width:100%;" />
                                                                        </td>
                                                                        <th class="infoline" style="text-align:center;">
 	          <input type="image" id="add0" src="static/images/tinybutton-add1.gif" />  
                 <%-- <a href="#"><img src="../images/tinybutton-add1.gif" width="40" height="15" border="0" title="Add this Sub-group"></a>--%>
                                                                        </th>  
                                                                    </tr>
                                                                </table>
                                                        
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                
                                            </div>

<%-- 0000001 --%>


  <ul id="example" class="filetree">
		<%-- <li><span class="folder">00000</span>
		</li> --%>
	</ul>
	
        <div id="globalbuttons" class="globalbuttons">
	<%-- <input type="submit" id="generate" value="Generate!" /> --%>
	<input type="image" id="save" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" /> 
    <a
							href='portal.do?methodToCall=refresh&amp;docFormKey=88888888&amp;anchor=&amp;docNum='  title="cancel"><img
							src="/kra-dev/kr/static/images/buttonsmall_cancel.gif" class="tinybutton" alt="cancel" title="cancel"
							border="0" /></a>           <!--  still posted -->
          <%--   <input type="submit" name="save" id="generate" 
				src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif"
				class="globalbuttons" title="save" alt="save"> --%>
            <%-- <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="globalbuttons" property="methodToCall.saveSponsorHierarchy" title="save" alt="save" onclick="return okToSave();return false;" />    
            <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" styleClass="globalbuttons" property="methodToCall.cancelResearchAreas" title="cancel" alt="cancel" /> --%>  
        </div>
  </html:form>
</body>
<script>



$(document).ready(function(){

	loadFirstLevel();
})
  $("#loading").hide();
</script>
 </html>