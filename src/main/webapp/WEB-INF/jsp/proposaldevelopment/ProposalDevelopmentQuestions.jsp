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

<c:set var="readOnly" value="${not KualiForm.editingMode['modifyProposal']}" scope="request" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentQuestions"
	documentTypeName="ProposalDevelopmentDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="questions">
  	
	<script type="text/javascript">var $j = jQuery.noConflict();</script>
    <link rel="stylesheet" href="css/jquery/questionnaire.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
    <link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
    <script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
    <script type="text/javascript" src="scripts/jquery/CalendarPopup.js"></script>
    
  	<div align="right"><kul:help documentTypeName="ProposalDevelopmentDocument" pageName="Questions" /></div>
	<div id = "workarea">
	<kra-pd:proposalDevelopmentQuestionnaireAnswers bean = "${KualiForm.questionnaireHelper}" property = "questionnaireHelper"/>
	
	<c:set var = "forceTabNonTransparent" value = "true"/>
  	<c:if test="${fn:length(KualiForm.questionnaireHelper.answerHeaders) == 0}">
  		<c:set var="forceTabNonTransparent" value = "false"/>
  	</c:if>
 	 	
	<kra-pd:proposalDevelopmentQuestionnaireAnswers bean = "${KualiForm.s2sQuestionnaireHelper}" property = "s2sQuestionnaireHelper" forceNonTransparent="${forceTabNonTransparent}"/>
   	
    <script type="text/javascript" src="scripts/questionnaireAnswer.js"></script>
  	
  	
	<c:set var = "topTabTransparent" value = "true"/>
  	<c:if test="${fn:length(KualiForm.questionnaireHelper.answerHeaders) gt 0 or fn:length(KualiForm.s2sQuestionnaireHelper.answerHeaders) gt 0}">
  		<c:set var="topTabTransparent" value = "false"/>
  	</c:if>
  	
	<kra-pd:proposalYnq topTabTransparent="${topTabTransparent}" />
	<kul:panelFooter />
	</div>
<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true" />
<script language="javascript" src="scripts/kuali_application.js"></script>


</kul:documentPage>
