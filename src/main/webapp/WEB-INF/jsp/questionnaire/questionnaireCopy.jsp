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
	htmlFormAction="questionnaireMaint" transactionalDocument="false"
	headerTitle="Questionnaire" auditCount="0">

	<script language="JavaScript" type="text/javascript"
		src="dwr/engine.js"></script>

	<script language="JavaScript" type="text/javascript" src="dwr/util.js"></script>

	<script language="JavaScript" type="text/javascript"
		src="dwr/interface/CustomAttributeService.js"></script>

	<script language="JavaScript" type="text/javascript"
		src="dwr/interface/SponsorService.js"></script>


<%--  
	<script src="scripts/jquery/jquery.js"></script>
	<link rel="stylesheet" href="css/jquery/screen.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
	<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css"
		type="text/css" />
	<link rel="stylesheet" href="css/jquery/jquery.treeview.css"
		type="text/css" />
	<script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
	<script type="text/javascript" src="scripts/questionnaire.js"></script>
--%>



	<div align="center" style="margin: 10px"><c:if
		test="${empty ErrorPropertyList and !(empty QuestionnaireForm.retData)}">
		<div id="headermsg" align="left">${QuestionnaireForm.retData}</div>
		<br />
	</c:if> <kul:tabTop defaultOpen="true" tabTitle="Questionnaire"
		tabErrorKey="newQuestionnaire.*">
		<kra-questionnaire:questionnaireCopy />
	</kul:tabTop> <kul:panelFooter />

	<div id="globalbuttons" class="globalbuttons"><%-- <input type="image" id="copy" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_copy.gif" /> --%>
	<html:image property="methodToCall.copyQuestionnaire"
		src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_copy.gif"
		title="Copy Questionnaire" alt="Copy Questionnaire"
		styleClass="tinybutton" /> <a
		href='portal.do?methodToCall=refresh&amp;docFormKey=88888888&amp;anchor=&amp;docNum='
		title="cancel"> <img src="kr/static/images/buttonsmall_cancel.gif"
		class="tinybutton" alt="cancel" title="cancel" border="0" /> </a></div>
	</div>


	<script>
     var sqlScripts;
     $("#copy").click(function(){    
       var qname = $('#newQuestionnaire\\.name').attr("value");
       var qdescription =$('#newQuestionnaire\\.description').attr("value");
       var qisfinal = $('#newQuestionnaire\\.isFinal').attr("checked");
       var questionnaireId = $('#fromQuestionnaire\\.questionnaireId').attr("value");
       alert ("copy"+qname+qdescription+questionnaireId); 
       $.ajax({
         url: 'questionnaireAjax.do',
         type: 'GET',
         dataType: 'html',
         cache: false,
         data:'sqlScripts=copyQuestionnaire&newQuestionnaire.name='+qname+'&newQuestionnaire.questionnaireId='+'&newQuestionnaire.description='+qdescription+'&newQuestionnaire.isFinal='+qisfinal,
         async:false,
         timeout: 1000,
         error: function(){
            alert('Error loading XML document');
                $('<span id="msg"/>').css("color","gray").html("Error when copying Questionnaire").appendTo($("#headermsg"));
                $('<br/>').appendTo($("#headermsg"));
                jumpToAnchor('topOfForm');            
         },
         success: function(xml){
            $(xml).find('h3').each(function(){
               alert("successfully copied");
                var item_text = $(this).text();
                $('<span id="msg"/>').css("color","gray").html("Questionnaire copied successfully").appendTo($("#headermsg"));
                $('<br/>').appendTo($("#headermsg"));
                jumpToAnchor('topOfForm');            
            });
         }
       });
       
       return false;
      }); 
   //}      
   
   
   

   
    </script>


</kul:page>
