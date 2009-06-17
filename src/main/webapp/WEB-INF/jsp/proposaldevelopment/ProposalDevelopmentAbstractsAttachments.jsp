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
<%@ page language="java" %> 
<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentAbstractsAttachments"
	documentTypeName="ProposalDevelopmentDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="abstractsAttachments">
<div align="right"><kul:help documentTypeName="ProposalDevelopmentDocument" pageName="Abstracts and Attachments" /></div>
<kra-pd:proposalDevelopmentAttachments /> 
<kra-pd:proposalDevelopmentPersonnelAttachments /> 
<kra-pd:proposalDevelopmentInstitutionalAttachments /> 
<kra-pd:proposalDevelopmentAbstracts />
<kul:notes displayTopicFieldInNotes="true" />
<kul:panelFooter />
<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" />
<script language="javascript" src="scripts/kuali_application.js"></script>


</kul:documentPage>
