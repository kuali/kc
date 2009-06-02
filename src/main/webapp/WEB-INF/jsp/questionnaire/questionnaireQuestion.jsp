<%--
 Copyright 2006-2009 The Kuali Foundation

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

  			<script language="JavaScript" type="text/javascript"
				src="/${fn:trim(ConfigProperties.app.context.name)}/dwr/engine.js"></script>

			<script language="JavaScript" type="text/javascript"
				src="/${fn:trim(ConfigProperties.app.context.name)}/dwr/util.js"></script>

			<script language="JavaScript" type="text/javascript"
				src="/${fn:trim(ConfigProperties.app.context.name)}/dwr/interface/CustomAttributeService.js"></script>

			<script language="JavaScript" type="text/javascript"
				src="/${fn:trim(ConfigProperties.app.context.name)}/dwr/interface/SponsorService.js"></script>


  <script src="/${fn:trim(ConfigProperties.app.context.name)}/scripts/jquery/jquery.js"></script>
  <link rel="stylesheet" href="/${fn:trim(ConfigProperties.app.context.name)}/css/jquery/screen.css" type="text/css" />
  <link rel="stylesheet" href="/${fn:trim(ConfigProperties.app.context.name)}/css/jquery/new_kuali.css" type="text/css" />
  <link rel="stylesheet" href="/${fn:trim(ConfigProperties.app.context.name)}/css/jquery/jquery.treeview.css" type="text/css" />
  <%-- link rel="stylesheet" href="http://dev.jquery.com/view/trunk/plugins/treeview/jquery.treeview.css" type="text/css" /--%>
  <script type="text/javascript" src="/${fn:trim(ConfigProperties.app.context.name)}/scripts/jquery/jquery.treeview.js"></script>


<kul:tab tabTitle="Questions" defaultOpen="true" useCurrentTabIndexAsKey="true"> 
    <kra-questionnaire:questionnaireCore />
    <kra-questionnaire:questionnaireQuestion />
</kul:tab>

    <script>
  var node;
  var i = 0;
  var removedNode;
  $(document).ready(function(){
    $.ajaxSettings.cache = false; 
    $("#example").treeview();
    
    $("div#foo").append("Hello World!").css("color","red");
  });
  
   function addQuestion() {
     alert($("#document\\.newMaintainableObject\\.businessObject\\.questionnaireQuestions\\[0\\]\\.questionId").attr("value"));
     var qid = $("#document\\.newMaintainableObject\\.businessObject\\.questionnaireQuestions\\[0\\]\\.questionId");
     if (qid.attr("value").trim() == "") {
        alert("Please enter question description");
       // var branches = $("<li><span class='folder'>New Sublist</span><ul>" + 
		//	"<li><span class='file'>Item1</span></li>" + 
		//	"<li><span class='file'>Item2</span></li></ul></li>").appendTo("#example");
        var branches = $("<li><span class='folder'>New Sublist</span></li>").appendTo("#example");
		$("#example").treeview({
			add: branches
		});
        
        
     } else {
     alert("add q"+qid.attr("value"));
     
                 i++;

            var id = "item"+i;
            var tagId = "listcontrol"+i;
            var divId = "listcontent"+i;
            var item_text = qid.attr("value");
            var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(item_text);
            var div = $('<div  class="hierarchydetail" style="margin-top:2px; "></div>').attr("id",divId);
       $(document).ready(function () {
            tag.click(
					function()
					{
					    //alert ("click "+tagId);
						$(".hierarchydetail:not(#"+divId+")").slideUp(300);
						$("#"+divId).slideToggle(300);						
					}
				);
		}); 		
		    //var spantag = $('<span class="folder"></span>').html("test");
            var listitem = $('<li class="closed"></li>').attr("id",id).html(tag);
            tableTag(item_text, id).appendTo(div)
            div.appendTo(listitem);
            //spantag.prependTo(listitem);
            
            // need this ultag to show 'folder' icon.
			var ultag = $('<ul></ul>');
            ultag.appendTo(listitem);
            
            
            listitem.appendTo('ul#example');
        
        // also need this to show 'folder' icon
        $('#example').treeview({
           add: listitem
        });

        
     
     
     } //end else
   } // end addQuestion
   
   
   function tableTag(name, id) {

       var link = $('<a href="#" class="hidedetail"><img src="kr/static/images/tinybutton-hide.gif" align="absmiddle" border="0" width="45" height="15"></a>');
       var tag = $('<th  class="subelementheader" align="left"></th>').html(name);
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
                      alert("Remove node "+removedNode.attr("id"));
                      $(liId).remove();
                    }); 
  tag.html(image);
  image = $('<a href="#"><img src="static/images/tinybutton-cutnode.gif" width="79" height="15" border="0" alt="Cut Node" title="Cut this node and its child roups/sponsors.  (Node will not be removed until you paste it.)"></a>&nbsp').attr("id","cut"+i).click(function() {
                      alert("Cut node");
                    }); 
  image.appendTo(tag);                  
  image = $('<a href="#"><img src="static/images/tinybutton-pastenode.gif" width="79" height="15" border="0" alt="Paste Node" title="Paste your previously cut node structure under this node"></a>').attr("id","paste"+i).click(function() {

                if (removedNode) {
                      var parentNode = $("#"+id);
                      var ulTag = parentNode.children('ul');
                      if (ulTag.size() > 0) {                     
                           alert(ulTag.attr("id"));
                      } else {
                         alert("not found")
                          i++;
                          ulTag = $('<ul class="filetree"></ul>').attr("id","ul"+i);                         
                      }   
                      removedNode.appendTo(ulTag);
                      ulTag.appendTo(parentNode);
                      
                    /*  
                      alert("remove node"+removedNode.attr("id")+"-"+removedNode.children().size());
                      for (j=0 ; j < i ; j++)  {
                        if ( $("#listcontrol"+String(j)).length > 0 ) {
                          alert("exists "+j);
                           $("#listcontrol"+String(j)).click(
					          function()
					          {
					             //alert ("click 03");
						         $(".hierarchydetail:not(#listcontent"+String(j)).slideUp(300);
						         $("#listcontent"+String(j)).slideToggle(300);
					           }
				            );
				          }                        
                        }                         
                      */
                      
    //removedNode.hide().with('a',function(){
       //alert("remove node id ");
        //$(this).width( $(this).width+200 );
   // }).fadeIn();
   

                  
// traverse child node temporarily removed


       // traverse child nodes               
//       removedNode.find('a').each(function(){
//       //alert ("find children "+$(this).attr("id")");
//       if ($(this).attr("id").indexOf("listcontrol") >= 0) {
//          alert("find children "+$(this).attr("id")+"-"+$(this).attr("id").substr(11,$(this).attr("id").length));
//          var listContentId = '#listcontent' + $(this).attr("id").substr(11,$(this).attr("id").length);
//          $(this).click(function()
//					          {
//						         $(".hierarchydetail:not(#listcontent5)").slideUp(300);
//						         $('#listcontent5').slideToggle(300);
//					           }
//				            );
//       }
//       if ($(this).attr("id").indexOf("addRA") >= 0) {
//          alert("find children "+$(this).attr("id"));
//       }
//       if ($(this).attr("id").indexOf("remove") >= 0) {
//          alert("find children "+$(this).attr("id"));
//       }
//       if ($(this).attr("id").indexOf("cut") >= 0) {
//          alert("find children "+$(this).attr("id"));
//          $(this).click(function()
//					          {
//						         alert("cut node");
//					           }
//				            );
//       }
//       if ($(this).attr("id").indexOf("paste") >= 0) {
//          alert("find children "+$(this).attr("id"));
//       }
//       // $(this).width( $(this).width+200 );
//    }).end() // Traverse back to #elem
//    .find('div').each(function(){
//        alert("find children "+$(this).attr("id") );
//    }).end()
//    ;



                   
                      /*
                         $("#listcontrol5").click(
					          function()
					          {
					             alert ("click 05");
						         $(".hierarchydetail:not(#listcontent5)").slideUp(300);
						         $("#listcontent5").slideToggle(300);
					           }
				            );
                     */
                     
                      removedNode = null;
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
    var tag1 = $('<th style="text-align:right;"></th>').html('Add:');
    var tdTag1 = $('<td></td>').html(getResearchAreaCode(name));
    trTag1.html(tag1);
    tdTag1.appendTo(trTag1);
    tdTag1 = $('<td></td>').html($('<input type="text" name="m2" value="" style="width:100%;" />').attr("id","researchCode"+i));
    tdTag1.appendTo(trTag1);
    tdTag1 = $('<td></td>').html($('<input type="text" name="m3" value="" style="width:100%;" />').attr("id","desc"+i));
    tdTag1.appendTo(trTag1);
    tag1 = $('<th class="infoline" style="text-align:center;"></th>');
    var addlink = $('<a href="#"><img src="static/images/tinybutton-add1.gif" width="40" height="15" border="0" title="Add this Sub-group"></a>').attr("id","addRA"+i).click(function() {
                         
          //alert("add node"+$(this).parents('tr:eq(0)').children('th').size());
          var trNode = $(this).parents('tr:eq(0)');
          //alert(trNode.children('td:eq(1)').children('input:eq(0)').attr("value")+"-"+trNode.children('td:eq(2)').children('input:eq(0)').attr("value"));

         if (trNode.children('td:eq(1)').children('input:eq(0)').attr("value") == "") {
           alert("must enter research area code");
         } else if (trNode.children('td:eq(2)').children('input:eq(0)').attr("value") == "") {
           alert("must enter research area");
         } else {
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
            listitem.appendTo(ulTag);
          }  
                                           
         }); 
                
    tag1.html(addlink);
    tag1.appendTo(trTag1);
    
    
  trTag.appendTo(tblTag);
  trTag1.appendTo(tblTag);
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
            var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(name);
            var detDiv = $('<div  class="hierarchydetail" style="margin-top:2px; "></div>').attr("id",divId);
       //$(document).ready(function () {
            $(tag).click(
					function()
					{
						$(".hierarchydetail:not(#"+divId+")").slideUp(300);
						$("#"+divId).slideToggle(300);
						// comment out temporarily
						loadChildrenRA(name, tagId);
					}
				);
		//});
			//alert(tag.html());	
            var listitem = $('<li class="closed"></li>').attr("id",id1).html(tag);
            tableTag(name, id1).appendTo(detDiv)
            detDiv.appendTo(listitem);
            //alert(listitem.html());
            return listitem;
}


function loadChildrenRA(nodeName, tagId) {
    var parentNode = $("#"+tagId);
    alert ("load subnodes for "+nodeName+"-"+parentNode.parents('li:eq(0)').attr("id")+"-" );
    var liNode = parentNode.parents('li:eq(0)');
    var ulNode = liNode.children('ul:eq(0)');
    var inputNodev;
//    alert (ulNode);
//    if (liNode.children('ul').size() > 0 ) {
//        inputNodev = ulNode.children('input:eq(0)');
//    }
    
    
    //if (liNode.children('ul').size() == 0 ) {
    if (liNode.children('ul').size() == 0 || ulNode.children('input').size() == 0 ) {
        alert(liNode.children('ul').size());
        $.ajax({
         url: 'researchAreasLoad.jsp',
         type: 'GET',
         dataType: 'html',
         data:'researchAreaCode='+getResearchAreaCode(nodeName),
         cache: false,
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
            //var item = $(this).find('item').text()
            //alert(item_text+"-")
            //var text1 = $('<span class="file"></span>').html(item_text);
            //alert(text1)
           // $('<li></li>').html($('<span class="file"></span>').html(item_text)).appendTo('ul#file31');
            var id = "item"+i;
            var tagId = "listcontrol"+i;
            var divId = "listcontent"+i;
            
           // if (i == 1) {
            var tag = $('<a style = "margin-left:2px;" ></a>').attr("id",tagId).html(item_text);
            var detDiv = $('<div  class="hierarchydetail" style="margin-top:2px; "></div>').attr("id",divId);
            tag.click(
					function()
					{
					    //alert ("click "+tagId);
						$(".hierarchydetail:not(#"+divId+")").slideUp(300);
						detDiv.slideToggle(300);
						loadChildrenRA(item_text, tagId);
					}
				);
				alert (tag.attr("onClick"));
//            } else {
//            var tag = $('<a id="listcontrol04" ></a>').attr("style","margin-left:2px;").html(item_text);
//            var div = $('<div id="listcontent04" class="hierarchydetail" style="margin-top:2px; "></div>');
//            }
             //<div class="hierarchydetail" id="listcontent02" style="margin-top:2px;">
            
            //var text = <a id="listcontrol02" style="margin-left:2px;">01. : AGRICULTURE, AGRICULTURE OPERATIONS, AND RELATED SCIENCES</a>
            var listitem = $('<li class="closed"></li>').attr("id",id).html(tag);
            //tag.appendTo(listitem);
            //listitem.appendTo('ul#file31');
            tableTag(item_text, id).appendTo(detDiv)
            detDiv.appendTo(listitem);
            //listitem.appendTo('ul#file31');
            listitem.appendTo(ulTag);
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
   
   
    </script>