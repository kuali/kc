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

<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />

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
	
	<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true"
		extraButtonSource="${saveViewersSrc}" extraButtonProperty="${saveViewersProperty}" extraButtonAlt="${saveViewersAlt}" />
	<script language="javascript" src="scripts/kuali_application.js"></script>
	<script language="javascript" src="dwr/interface/KraPersonService.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<!-- <script type='text/javascript' src='dwr/engine.js'></script> -->
	<script>loadPersonName('newProposalUser.username', 'fullname');</script>
</kul:documentPage>
