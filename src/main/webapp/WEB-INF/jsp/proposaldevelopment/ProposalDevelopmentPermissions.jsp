<%--
 Copyright 2005-2008 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

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

	<kra-pd:proposalDevelopmentAssignedRoles /> 
	<kra-pd:proposalDevelopmentPermissionUsers />
	<kul:panelFooter />	
	
	<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" />
	<script language="javascript" src="scripts/kuali_application.js"></script>
	<script language="javascript" src="dwr/interface/PersonService.js"></script>
	<script>loadPersonName('newProposalUser.username', 'fullname');</script>
</kul:documentPage>
