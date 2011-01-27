<%--
 Copyright 2005-2010 The Kuali Foundation

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

<kra:infopage title="Questionnaire" action="questionnaire" htmlFormAction="questionnaire">
  	<script src="scripts/jquery/jquery.js"></script>
<script type="text/javascript">
   var $j = jQuery.noConflict();
</script>
    <link rel="stylesheet" href="css/jquery/questionnaire.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
    <script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
    <script type="text/javascript" src="scripts/jquery/CalendarPopup.js"></script>
    
   	
  	

    <kra-questionnaire:submissionQuestionnaireAD viewOnly="false" bean = "${KualiForm.questionnaireHelper}" property = "questionnaireHelper"/>
		    <script type="text/javascript" src="scripts/questionnaireAnswer.js"></script>
    <div id="formComplete"></div> 
	<c:set var="prop" value="questionnaireHelper.protocolNumber"/>
	${kfunc:registerEditableProperty(KualiForm, prop)}
	<input type="hidden" name="${prop}" id ="${prop}" 
           value = "${KualiForm.questionnaireHelper.protocolNumber}" />
	<c:set var="prop" value="questionnaireHelper.submissionNumber"/>
	${kfunc:registerEditableProperty(KualiForm, prop)}
	<input type="hidden" name="${prop}" id ="${prop}" 
           value = "${KualiForm.questionnaireHelper.submissionNumber}" />
    <SCRIPT type="text/javascript">

    $j(document).ready(function()     {
   	   var input = $j('<input type="image" id = "save" class="globalbuttons" src="kr/static/images/buttonsmall_save.gif" name="methodToCall.saveSubmissionQuestionnaire">');
   	   input.prependTo($j("#globalbuttons"));
    } ); 

    </SCRIPT> 
		
</kra:infopage>
