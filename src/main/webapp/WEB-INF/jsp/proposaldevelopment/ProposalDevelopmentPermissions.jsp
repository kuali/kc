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

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentPermissions"
	documentTypeName="ProposalDevelopmentDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="permissions">
  	
  	<div align="right"><kul:help documentTypeName="ProposalDevelopmentDocument" pageName="Permissions" /></div>

	<kra-pd:proposalDevelopmentAssignedRoles /> 
	<kra-pd:proposalDevelopmentPermissionUsers />
	<kul:panelFooter />	
	
	<c:if test="${KualiForm.editingMode['modifyPermissions']}">
		<c:if test="${ KualiForm.inWorkflow }" >
			<c:set var="saveViewersSrc" value="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save_viewers.gif" />
			<c:set var="saveViewersProperty" value="methodToCall.saveViewers" />
			<c:set var="saveViewersAlt" value="Save Viewers" />
		</c:if>
	</c:if>
	
	<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" 
		extraButtonSource="${saveViewersSrc}" extraButtonProperty="${saveViewersProperty}" extraButtonAlt="${saveViewersAlt}" />
	<script language="javascript" src="scripts/kuali_application.js"></script>
	<script language="javascript" src="dwr/interface/PersonService.js"></script>
	<script>loadPersonName('newProposalUser.username', 'fullname');</script>
</kul:documentPage>
