<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
  	
  	
	<c:set var = "hasQuestionnaires" value = "false"/>
  	<c:if test="${not empty KualiForm.questionnaireHelper.answerHeaders or not empty KualiForm.s2sQuestionnaireHelper.answerHeaders}">
  		<c:set var="hasQuestionnaires" value = "true"/>
  	</c:if>
	
	<kra-pd:proposalYnq topTabTransparent="${!hasQuestionnaires}"/>  	
  	
  	<c:if test="${!hasQuestionnaires && empty KualiForm.document.developmentProposalList[0].proposalYnqs}">
  		<kul:tabTop tabTitle="Questions" defaultOpen="true">
			<div class="tab-container" align="center">
    			<h2>There are no questions defined for this proposal.</h2>
    		</div>
  		</kul:tabTop>
	</c:if>
	<kul:panelFooter />
	</div>
<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true" />
<script language="javascript" src="scripts/kuali_application.js"></script>


</kul:documentPage>
