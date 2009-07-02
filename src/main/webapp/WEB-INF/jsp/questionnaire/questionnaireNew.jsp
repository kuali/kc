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

<kul:page showDocumentInfo="false" docTitle="Questionnaire"
	htmlFormAction="questionnaireNew" transactionalDocument="false"
	headerTitle="Questionnaire" auditCount="0">
          
  			<script language="JavaScript" type="text/javascript"
				src="dwr/engine.js"></script>

			<script language="JavaScript" type="text/javascript"
				src="dwr/util.js"></script>

			<script language="JavaScript" type="text/javascript"
				src="dwr/interface/CustomAttributeService.js"></script>

			<script language="JavaScript" type="text/javascript"
				src="dwr/interface/SponsorService.js"></script>


  <script src="scripts/jquery/jquery.js"></script>
  <link rel="stylesheet" href="css/jquery/screen.css" type="text/css" />
  <link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
  <link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
  <link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
  <%-- link rel="stylesheet" href="http://dev.jquery.com/view/trunk/plugins/treeview/jquery.treeview.css" type="text/css" /--%>
  <script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
  <script type="text/javascript" src="scripts/questionnaire.js"></script>




    <div align="center" style="margin:10px">
        <kul:tabTop defaultOpen="true" tabTitle="Questionnaire" tabErrorKey="questionnaire*">
    <kra-questionnaire:questionnaireCore1 />
    <kra-questionnaire:questionnaireQuestion1 /> 
        </kul:tabTop>
        <kul:panelFooter />
                
        <div id="globalbuttons" class="globalbuttons">
        	   <input type="image" id="save" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" /> 
               <a href='portal.do?methodToCall=refresh&amp;docFormKey=88888888&amp;anchor=&amp;docNum='  title="cancel">
                   <img src="kr/static/images/buttonsmall_cancel.gif" class="tinybutton" alt="cancel" title="cancel" border="0" />
				</a>           
</div>
    </div>
    
    
        <script>
  var node;
  var i = 1;
  var j = 0;
  var removedNode;
  var cutNode;
  var sqlScripts = "create new";
  var jsContextPath = "${pageContext.request.contextPath}";
  $(document).ready(function(){
    $.ajaxSettings.cache = false; 
    $("#example").treeview({
               toggle: function() {
                   //alert ("toggle "+$(this).attr("id"));
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

            var listitem = getQuestionNew($("#newQuestion").attr("value"),1, "V1.01", 'false');
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

            var listitem = getQuestionNew("Test Question",1,"V.1.01",'false');
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
      var questionType = ['select','Yes/No', 'Yes/No/NA', 'Number', 'Date', 'Text', 'Lookup'];
   
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
     
   
     // need to modify this for questionnaire
  //$(document).ready(function(){
     $("#save").click(function(){    
       var qname = $('#newQuestionnaire\\.name').attr("value");
       var qdescription =$('#newQuestionnaire\\.description').attr("value");
       var qisfinal = $('#newQuestionnaire\\.isFinal').attr("checked");
       //if (qisfinal == 'on') {
       //    qisfinal = true;
       //} else {
       //    qisfinal = false;
       //}
       alert ("save"+qname+qdescription+$('#newQuestionnaire\\.isFinal').attr("checked")); 
       $.ajax({
         url: 'questionnaireAjax.do',
         type: 'GET',
         dataType: 'html',
         cache: false,
         data:'sqlScripts='+sqlScripts+'&newQuestionnaire.name='+qname+'&newQuestionnaire.description='+qdescription+'&newQuestionnaire.isFinal='+qisfinal,
         async:false,
         timeout: 1000,
         error: function(){
            alert('Error loading XML document');
         },
         success: function(xml){
            $(xml).find('h3').each(function(){
                var item_text = $(this).text();
            });
         }
       });
       
       return false;
      }); 
   //}      
   
   
    </script>
    
    
</kul:page>
