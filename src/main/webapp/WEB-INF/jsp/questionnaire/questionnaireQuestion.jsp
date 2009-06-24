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
  <script type="text/javascript" src="/${fn:trim(ConfigProperties.app.context.name)}/scripts/questionnaire.js"></script>


<kul:tab tabTitle="Questionnaire" defaultOpen="true" useCurrentTabIndexAsKey="true"> 
    <kra-questionnaire:questionnaireCore />
    <kra-questionnaire:questionnaireQuestion />
</kul:tab>

    <script>
  var node;
  var i = 1;
  var j = 0;
  var removedNode;
  var cutNode;
  var jsContextPath = "${pageContext.request.contextPath}";
  $(document).ready(function(){
    $.ajaxSettings.cache = false; 
    $("#example").treeview({
               toggle: function() {
                   alert ("toggle "+$(this).attr("id"));
                  },
              animated: "fast",
              collapsed: true,
              control: "#treecontrol"
        });
        
    $("#rootSearch").attr("name","methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(((questionId:newQuestionId,question:newQuestion))).((#newQuestionId:questionId,#)).((<>)).(([])).((**)).((^^)).((&&)).((/questionId/)).((~~)).anchor1");
    
    $("#addRootQn").click(function() {
        //alert("This would add question "+$(".radioQn"+$(this).attr("id").substring(5)+":checked").val()+"-"+$("#newQuestionId").attr("value")+"-"+$("#newQuestion").attr("value"));  
        alert("This would add question "+$(this).parents('li:eq(0)').children('ul:eq(0)').size()+"-"+$(".radioQn"+$(this).attr("id").substring(5)+":checked").val());  
        
        
            i++;

            var listitem = getQuestionNew($("#newQuestion").attr("value"), "V1.01", 'false');
			var ultag = $('<ul></ul>');
            ultag.appendTo(listitem);
            
              listitem.appendTo('ul#example');
        $('#example').treeview({
           add: listitem
        });

        alert("# of siblings "+listitem.siblings('li').size());
        var intag = $('<input type ="text"></input>').attr("value",$("#newQuestionId").attr("value").trim());
        var name = "document\.newMaintainableObject\.businessObject\.questionnaireQuestions["+j+"]\.questionId";
        intag.attr("id",name).attr("name",name);
        j++;
        var trtmp1 = $('<tr></tr>');
        var tdtmp1 = $('<td></td>').html(intag);
        trtmp1.html(tdtmp1);
        trtmp1.appendTo($("#question-table"));
        
        
        return false;
      });
  
    });
  
  
  function getQuestion() {
  //alert("jsContextPath"+jsContextPath);
     var qnaireid =  "qnaireid"+i
     var question = $('<li class="closed"></li>').attr("id", qnaireid);
  
     var div62 = $('<div/>');
     var linktmp = $('<a id="listcontrol02" style="margin-left:2px;">[Yes] Will you be using whole dead animals which are regulated by USDA?</a>');
             linktmp.click(
					function()
					{
					    //alert("listcontrol02");
						$(".hierarchydetail:not(#listcontent02)").slideUp(300);
						$("#listcontent02").slideToggle(300);
					}
				);
     linktmp.appendTo(div62);
     
     var div64 = $(' <div class="hierarchydetail" id="listcontent02" style="margin-top:2px;">')
     var tbl70 = $('<table width="100%" cellpadding="0" cellspacing="0" class="subelement" />');
     thead = $('<thead/>');
     trtmp = $('<tr/>');
     thtmp = $('<th class="subelementheader" align="left">');
     imgtmp = $('<a href="#" class="hidedetail"><img src="/kra-dev/kr/static/images/tinybutton-hide.gif" align="absmiddle" border="0" width="45" height="15"></a>').toggle(
     
     );
     
     thtmp.html("Will you be using whole dead animals which are regulated by USDA? (v.1.045)");
     imgtmp.prependTo(thtmp);
     thtmp.appendTo(trtmp);
     trtmp.appendTo(thead);
     thead.appendTo(tbl70);
     
     
     tbodytmp = $('<tbody/>');
var tr1 = $('<tr></tr>');
var td1 = $('<td class="subelementcontent"></td>');

var tbl80 = $('<table cellpadding="0" cellspacing="0" class="elementtable" width="100%">').attr("id","tbl80"+i);
trtmp = $('<tr></tr>');
var tmp = $('<th style="text-align:right; width:120px;">Node:</th>');
trtmp.html(tmp);
thtmp = $('<th colspan="3"></th>');
  image = $('<img style="border:none;" alt="move down" title="Move Down" type="image" >');
  atag = $('<a href="#"></a>').attr("id","movedn"+i).click(function() {
     alert("This would move this node and its children down within its sibling group.");
                    }); 
  image.attr("src",jsContextPath+"/static/images/jquery/arrow-down.gif");
  //alert("images "+image.attr("src"));
  atag.html(image);
  atag.appendTo(thtmp);
  
  image = $('<a href="#"><img src="/kra-dev/static/images/tinybutton-removenode.gif" width="79" height="15" border="0" alt="Remove Node" title="Remove this node and its child groups/sponsors"></a>&nbsp').attr("id","remove"+i).click(function() {
                      var liId="li#"+qnaireid;
                      removedNode = $(liId).clone(true);
                      //removedNode = $(liId); // this will not work because event also lost
                      alert("Remove node "+removedNode.attr("id"));
                      $(liId).remove();
                    }); 
  image.appendTo(thtmp);
  image = $('<a href="#"><img src="/kra-dev/static/images/tinybutton-cutnode.gif" width="79" height="15" border="0" alt="Cut Node" title="Cut this node and its child roups/sponsors.  (Node will not be removed until you paste it.)"></a>&nbsp').attr("id","cut"+i).click(function() {
                      alert("Cut node");
                    }); 
  image.appendTo(thtmp);                  
  image = $('<a href="#"><img src="/kra-dev/static/images/tinybutton-copynode.gif" width="79" height="15" border="0" alt="Copy Node" title="Copy this node and its child.)"></a>&nbsp').attr("id","copy"+i).click(function() {
                      alert("Cut node");
                    }); 
  image.appendTo(thtmp);                  
  image = $('<a href="#"><img src="/kra-dev/static/images/tinybutton-pastenode.gif" width="79" height="15" border="0" alt="Paste Node" title="Paste your previously cut node structure under this node"></a>').attr("id","paste"+i).click(function() {

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
                                           
                      removedNode = null;
                   }// if removednode                         
                }); 
  image.appendTo(thtmp);                  

thtmp.appendTo(trtmp);
trtmp.appendTo(tbl80);

   var tbl95 = $('<table style="border:none; width:100%;" cellpadding="0" cellspacing="0"></table>').attr("id","tbl95"+i);
  trtmp = $('<tr></tr>');
  tdtmp = $('<td style="border:none; width:170px;"></td>');
  // name attribute has to be in 'input' definition, otherwise, IE7 will not work.
  $('<input type="radio" name = "radio" checked="checked" value="sibling" />').attr("class","radioQn"+i).attr("name","radioQn"+i).appendTo(tdtmp);
  $('<span>as sibling&nbsp;&nbsp;&nbsp</span>').appendTo(tdtmp);
  $('<input type="radio" name = "radio" value="child" />').attr("class","radioQn"+i).attr("name","radioQn"+i).appendTo(tdtmp);
  $('<span>as child</span>').appendTo(tdtmp);
  tdtmp.appendTo(trtmp);
  
  tdtmp = $('<td style="border:none;"></td>').html($('<input type="text" style="width:100%" value="" />'));
  tdtmp.appendTo(trtmp);
  tdtmp = $('<td style="border:none; width:30px; text-align:center;"></td>');
  // lookup  example
  // <input type="image" tabindex="1000014" name="methodToCall.performLookup.(!!org.kuali.kra.bo.Sponsor!!).(((sponsorCode:document.sponsorCode,sponsorName:document.sponsor.sponsorName))).((##)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~)).anchorRequiredFieldsforSavingDocument"
  // src="/kra-dev/kr/static/images/searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Search " title="Search " />

  atag = $('<a href="#"></a>');
  //.click(function() {
   //// the expression,$(this).attr("id").substring(6)+":checked").val(), is to use "class" to seach the value of radio buttons
    // alert("Search question"+$(".radioQn"+$(this).attr("id").substring(6)+":checked").val());
    //                });
   qntag = $('<input type="hidden"/>').attr("id","qid"+i).attr("name","qid"+i);
   qntag.appendTo(tdtmp);
  image = $('<img src="/kra-dev/static/images/searchicon.gif" border="0" class="tinybutton"  alt="Search Question" title="Search Question">').attr("id","search"+i); 
  //image.attr("name","methodToCall\.performLookup\.(!!org\.kuali\.kra\.questionnaire\.question\.Question!!)\.(((questionId:document\.newMaintainableObject\.questionId,)))\.((#document\.newMaintainableObject\.questionId:questionId,#))\.((<>))\.(([]))\.((**))\.((^^))\.((&&))\.((/rateClassTypeT/))\.((~~))\.anchor1");
  image.attr("name","methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(((questionId:document.newMaintainableObject.questionId,))).((#document.newMaintainableObject.questionId:questionId,#)).((<>)).(([])).((**)).((^^)).((&&)).((/rateClassTypeT/)).((~~)).anchor1");
  atag.html(image);
  atag.appendTo(tdtmp);
  //name="methodToCall.performLookup.(!!org.kuali.kra.questionnaire.question.Question!!).(((questionId:document.newMaintainableObject.businessObject.questionnaireQuestions[0].questionId,))).((#document.newMaintainableObject.businessObject.questionnaireQuestions[0].questionId:questionId,#)).((<>)).(([])).((**)).((^^)).((&&)).((/questionId/)).((~~)).anchor1"
  image = $('<input type="image" src="/kra-dev/kr/static/images/searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Search Question" title="Search Question" />').attr("id","search"+i);
  image.attr("name","methodToCall\.performLookup\.(!!org\.kuali\.kra\.questionnaire\.question\.Question!!)\.(((questionId:document\.newMaintainableObject\.businessObject\.questionnaireQuestions[0]\.questionId,)))\.((#document\.newMaintainableObject\.businessObject\.questionnaireQuestions[0]\.questionId:questionId,#))\.((<>))\.(([]))\.((**))\.((^^))\.((&&))\.((/questionId/))\.((~~))\.anchor1");
  image.appendTo(tdtmp);
  //var testlookup = $('<input type="image" tabindex="1000009" name="methodToCall\.performLookup\.(!!org\.kuali\.kra\.budget\.bo\.RateClassType!!)\.(((rateClassType:document\.newMaintainableObject\.rateClassType,)))\.((#document\.newMaintainableObject\.rateClassType:rateClassType,#))\.((<>))\.(([]))\.((**))\.((^^))\.((&&))\.((/rateClassTypeT/))\.((~~))\.anchor1" src="/kra-dev/kr/static/images/searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Search Rate Class Type" title="Search Rate Class Type" />');
  //testlookup.appendTo(tdtmp);
  
  tdtmp.appendTo(trtmp);
  trtmp.appendTo(tbl95);
  trtmp1 = $('<tr></tr>');
  $('<th style="text-align:right;">Add Question(s):</th>').appendTo(trtmp1);
  thtmp1 = $('<th colspan="3"></th>');
  tbl95.appendTo(thtmp1);
  thtmp1.appendTo(trtmp1);
  trtmp1.appendTo(tbl80);
  tbl80.appendTo(td1);
  var tbl154 = $('<table width="100%" cellpadding="0" cellspacing="0" style="border-top:#E4E3E4 solid 1px;">');
  trtmp = $('<tr></tr>');
  thtmp = $('<th class="subelementheader" style="text-align:left;" colspan="3">');
  thtmp.html("Requirements for Display");
  image = $('<img id="HScontrol02b" src="/kra-dev/kr/images/tinybutton-show.gif" alt="show/hide this panel" title="show/hide this panel"  style="width:45px; height:15px; border:none; cursor:pointer; padding:2px; vertical-align:middle;" />').toggle(
                function()
                {
                    $("#HSdiv02b").slideDown(400);
                    $("#HScontrol02b").attr("src","/kra-dev/kr/static/images/tinybutton-hide.gif");
                },
                function()
                {
                    $("#HSdiv02b").slideUp(200);
                    $("#HScontrol02b").attr("src","/kra-dev/kr/static/images/tinybutton-show.gif");
                }
  );
  image.prependTo(thtmp);
  thtmp.appendTo(trtmp);
  trtmp.appendTo(tbl154);
  tbl154.appendTo(td1);
                                                                                
 
  var div192 = $('<div id="HSdiv02b"></div>');
var tbl196 = $('<table cellpadding="0" cellspacing="0" class="elementtable" style="width:100%; border-top:none;"></table>');
trtmp = $('<tr></tr>');
thtmp = $('<th style="text-align:center; width:150px;"></th>').html("Add");
thtmp.appendTo(trtmp);
tdtmp = $('<td class="content_info" style="text-align:center;"></td>');
selecttmp = $('<select name="CustomData"></select>');
$('<option value="0" selected="selected">select</option>').appendTo(selecttmp);
$('<option value="1">and</option>').appendTo(selecttmp);
$('<option value="2">or</option>').appendTo(selecttmp);
selecttmp.appendTo(tdtmp);
tdtmp.appendTo(trtmp);
tdtmp = $('<td class="content_info" style="text-align:center;"></td>').html("Parent Response");
selecttmp = $('<select name="CustomData"></select>');
$('<option value="0" selected="selected">select</option>').appendTo(selecttmp);
$('<option value="1">Contains text value</option>').appendTo(selecttmp);
$('<option value="2">Matches text</option>').appendTo(selecttmp);
$('<option value="3">Less than number</option>').appendTo(selecttmp);
$('<option value="4">Less than or equals number</option>').appendTo(selecttmp);
$('<option value="5">Equals number</option>').appendTo(selecttmp);
$('<option value="6">Greater than or equals number</option>').appendTo(selecttmp);
$('<option value="7">Greater than number</option>').appendTo(selecttmp);
$('<option value="8">Before date</option>').appendTo(selecttmp);
$('<option value="9">After date</option>').appendTo(selecttmp);
selecttmp.appendTo(tdtmp);
tdtmp.appendTo(trtmp);
tdtmp = $('<td class="content_info" style="text-align:center;"></td>').html("Value:");
$('<input type="text" size="25" />').appendTo(tdtmp);
tdtmp.appendTo(trtmp);
tdtmp = $('<td class="content_info" class="content_white" style="width:65px; text-align:center;"></td>');
image = $('<input name="addquestionnairetemplate" src="/kra-dev/kr/static/images/tinybutton-add1.gif" style="border:none;" alt="add" type="image" />').click(function() {
  alert("This would add the specified requirement.");  return false;
});

image.appendTo(tdtmp);
tdtmp.appendTo(trtmp);
trtmp.appendTo(tbl196);
tbl196.appendTo(div192);
                         
                         
     var tbl266 = $('<table cellpadding="0" cellspacing="0" class="elementtable" style="width:100%; border-top:none;"></table>');
    trtmp = $('<tr></tr>');
    thtmp = $('<th style="text-align:left; border-top:none; width:150px;">').html("Current Requirements:");
    thtmp.appendTo(trtmp);
    tdtmp=$('<td style="text-align:center; width:20px; border-top:none;">').html("1");
    tdtmp.appendTo(trtmp);
    tdtmp=$('<td style="text-align:left; border-top:none;">').html("Matches text: Yes");
    tdtmp.appendTo(trtmp);
    tdtmp=$('<td class="content_white" style="text-align:center; border-top:none; width:65px;">');
    image = $('<input src="/kra-dev/kr/static/images/tinybutton-delete1.gif"  style="border:none;" alt="delete" type="image" />').click(function() {
      alert("This would delete this requirement."); return false;
    });
    image.appendTo(tdtmp);
    tdtmp.appendTo(trtmp);
    trtmp.appendTo(tbl266);
    trtmp = $('<tr></tr>');
    tdtmp=$('<td style="text-align:right; font-style:italic;">').html("(or)");
    tdtmp.appendTo(trtmp);
    tdtmp=$('<td style="text-align:center;">').html("2");
    tdtmp.appendTo(trtmp);
    tdtmp=$('<td style="text-align:left;">').html("Matches text: Sometimes");
    tdtmp.appendTo(trtmp);
    tdtmp=$('<td class="content_white" style="text-align:center;">')
    image = $('<input src="/kra-dev/kr/static/images/tinybutton-delete1.gif"  style="border:none;" alt="delete" type="image" />').click(function() {
      alert("This would delete this requirement."); return false;
    });
    image.appendTo(tdtmp);
    tdtmp.appendTo(trtmp);
    trtmp.appendTo(tbl266);
    tbl266.appendTo(div192);
    div192.appendTo(td1);
                                                                                    
         
     var tbl325 = $('<table width="100%" cellpadding="0" cellspacing="0" style="border-top:#E4E3E4 solid 1px;">');
     trtmp = $('<tr></tr>');
     thtmp = $('<th class="subelementheader" style="text-align:left;" colspan="3">').html("Response");
     image = $('<img id="HScontrol01" src="/kra-dev/kr/static/images/tinybutton-show.gif" alt="show/hide this panel" title="show/hide this panel"  style="width:45px; height:15px; border:none; cursor:pointer; padding:2px; vertical-align:middle;" />').toggle(
                function()
                {
                    $("#HSdiv01").slideDown(400);
                    $("#HScontrol01").attr("src","/kra-dev/kr/static/images/tinybutton-hide.gif");
                },
                function()
                {
                    $("#HSdiv01").slideUp(200);
                    $("#HScontrol01").attr("src","/kra-dev/kr/static/images/tinybutton-show.gif");
                }
     );
     image.prependTo(thtmp);
     thtmp.appendTo(trtmp);
     trtmp.appendTo(tbl325);
     tbl325.appendTo(td1);
     
     var div360 = $('<div id="HSdiv01"></div>'); 
     var tbl362 = $('<table class="content_table">');
     trtmp = $('<tr></tr>');
     tdtmp = $('<td class="content_grey" style="width:110px; text-align:center;">').html("Type");
     tdtmp.appendTo(trtmp);
     tdtmp = $('<td class="content_grey" style="text-align:center;">').html("Values");
     tdtmp.appendTo(trtmp);
     trtmp.appendTo(tbl362);
     trtmp = $('<tr></tr>');
     tdtmp = $('<td class="content_white" style="text-align:center; vertical-align:top;">');
     selecttmp = $('<select id="responsetypecontrol1b"></select>');
     $('<option value="0" selected="selected">select</option>').appendTo(selecttmp);
     $('<option value="1">Yes/No</option>').appendTo(selecttmp);
     $('<option value="2">Yes/No/NA</option>').appendTo(selecttmp);
     $('<option value="3">Number</option>').appendTo(selecttmp);
     $('<option value="4">Date</option>').appendTo(selecttmp);
     $('<option value="5">Text</option>').appendTo(selecttmp);
     $('<option value="6">Dropdown</option>').appendTo(selecttmp);
     $('<option value="7">Checkbox</option>').appendTo(selecttmp);
     $('<option value="8">Lookup</option>').appendTo(selecttmp);
     selecttmp.appendTo(tdtmp);
     tdtmp.appendTo(trtmp);
                                                                               
 
      tdtmp = $('<td class="content_white" style="text-align:left;">');
    divtmp = $('<div id="responsetypeSelect1b" class="responsetypediv1b">').html($("<i>[NB: The Type pulldown to the left would be READ ONLY.  It is presented here as a dynamic trigger for the purposes of mocking each type's value.]</i>"));
    divtmp.appendTo(tdtmp);
    divtmp = $('<div id="responsetypeYesNo1b" class="responsetypediv1b">').html($('<p>The user will be presented with the following radio buttons: Yes, No.<br />Only one selection is possible.<br />A selection is required.</p>'));
    divtmp.appendTo(tdtmp);
    divtmp = $('<div id="responsetypeYesNoNA1b" class="responsetypediv1b">').html($('<p>The user will be presented with the following pulldown: Yes, No, Not Applicable.<br />Only one selection is possible.<br />A selection is required.</p>'));
    divtmp.appendTo(tdtmp);
    divtmp = $('<div id="responsetypeNumber1b" class="responsetypediv1b">').html($('<p>The user will be presented with 1 text box.<br />The entered value will be validated requiring a number only.<br />The maximum length of the number in characters is 5.<br />The number of possible answers is 1. </p>'));
    divtmp.appendTo(tdtmp);
    divtmp = $('<div id="responsetypeDate1b" class="responsetypediv1b">').html($('<p>The user will be presented with 2 text boxes.<br />The entered value will be validated for a date in MM/DD/YYYY format.<br />A response is required for each text box.</p>'));
    divtmp.appendTo(tdtmp);
    divtmp = $('<div id="responsetypeText1b" class="responsetypediv1b">').html($('<p>The user will be presented with 2 text areas.<br />The number of possible answers is 2.<br />Maximum length of each response in characters: 256.</p>'));
    divtmp.appendTo(tdtmp);
    divtmp = $('<div id="responsetypeDropdown1b" class="responsetypediv1b">').html($('<p>The user will be presented with a dropdown of options.<br />Only one selection is possible.<br />A selection is required.</p> Possible answers are:<br />1. One Fish<br />2. Two Fish<br />3. Red Fish<br />4. Blue Fish'));
    divtmp.appendTo(tdtmp);
    divtmp = $('<div id="responsetypeCheckbox1b" class="responsetypediv1b">').html($('<p>The user will be presented with 4 checkboxes.<br />At least one selection is required.<br />Up to 4 selections are allowed.</p>Possible answers are:<br />1. One Byte<br />2. Two Bites<br />3. Red Light<br />4. Green lights'));
    divtmp = $('<div id="responsetypeSearch1b" class="responsetypediv1b">').html($('<p>The user will be presented with the ability to search for: Person.<br />The field to return is: Name.<br />The number of possible returns is 2.</p>'));
    divtmp.appendTo(tdtmp);  
    tdtmp.appendTo(trtmp);  
    trtmp.appendTo(tbl362);
    tbl362.appendTo(div360);
    div360.appendTo(td1);    
    td1.appendTo(tr1);
    tr1.appendTo(tbodytmp);
    tbodytmp.appendTo(tbl70);
    tbl70.appendTo(div64);
    div64.appendTo(div62);
                                                                                    
                                                        
  div62.appendTo(question);
                         
                                                                             
 //alert (question.text())
                                                                                
  return question;


  } // end addQuestion
  
  
   function addQuestion() {
     //alert($("#document\\.newMaintainableObject\\.businessObject\\.questionnaireQuestions\\[0\\]\\.questionId").attr("value"));
     //var qid = $("#document\\.newMaintainableObject\\.businessObject\\.questionnaireQuestions\\[0\\]\\.questionId");
     var qid = $("#newQuestionId");
     //alert ("qid "+qid.attr("id"));
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
     //alert("add q"+qid.attr("value"));
     
                 i++;

            var listitem = getQuestionNew("Test Question","V.1.01",'false');
			var ultag = $('<ul></ul>');
            ultag.appendTo(listitem);
            
            
            listitem.appendTo('ul#example');
        
        // also need this to show 'folder' icon
        $('#example').treeview({
           add: listitem
        });

        var intag = $('<input type ="text"></input>').attr("value",qid.attr("value").trim());
        var name = "document\.newMaintainableObject\.businessObject\.questionnaireQuestions["+j+"]\.questionId";
        intag.attr("id",name).attr("name",name);
        j++;
        var trtmp = $('<tr></tr>');
        var tdtmp = $('<td></td>').html(intag);
        trtmp.html(tdtmp);
        trtmp.appendTo($("#question-table"));
     
     
     } //end else
   } // end addQuestion
   
   
   function tableTag(name, id) {

       var link = $('<a href="#" class="hidedetail"><img src="/kra-dev/kr/static/images/tinybutton-hide.gif" align="absmiddle" border="0" width="45" height="15"></a>');
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
   
   
 
    $(document).ready(function(){
        
       //alert("qncount= "+$("#qncount").attr("value"));
       // kind of set up questions when return from lookup.  still has long way to go.
       $("#newQuestionId").attr("value","10");
       	for(var k= 1; k <= $("#qncount").attr("value"); k++) {
       	//for(var k= 1; k <= 100; k++) {
       	   addQuestion();
       	}
        //alert(k)
  
    });
   
      var opArray = ['select', 'and', 'or'];
      var responseArray = ['select', 'Contains text value', 'Matches text', 'Less than number', 'Less than or equals number', 'Equals number', 'Greater than or equals number', 'Greater than number', 'Before date', 'After date'];
      var questionType = ['Yes/No', 'Yes/No/NA', 'Number', 'Date', 'Text', 'Lookup'];
   
      var responseOptions = $('<select name="CustomData"></select>');
      $('<option value="0" selected="selected">select</option>').appendTo(responseOptions);
      $('<option value="1">Contains text value</option>').appendTo(responseOptions);
      $('<option value="2">Matches text</option>').appendTo(responseOptions);
      $('<option value="3">Less than number</option>').appendTo(responseOptions);
      $('<option value="4">Less than or equals number</option>').appendTo(responseOptions);
      $('<option value="5">Equals number</option>').appendTo(responseOptions);
      $('<option value="6">Greater than or equals number</option>').appendTo(responseOptions);
      $('<option value="7">Greater than number</option>').appendTo(responseOptions);
      $('<option value="8">Before date</option>').appendTo(responseOptions);
      $('<option value="9">After date</option>').appendTo(responseOptions);
     
   
    </script>