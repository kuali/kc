<%--
 Copyright 2006-2009 The Kuali Foundation.

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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>


<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="identityManagementPersonDocument"
	documentTypeName="IdentityManagementPersonDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0">

	<c:set var="readOnly" value="${KualiForm.document.documentHeader.workflowDocument.routeHeader.docRouteStatus == 'F' }" scope="request" /> 
 	<kul:hiddenDocumentFields />
	<kul:documentOverview editingMode="${KualiForm.editingMode}" />
	<kim:personOverview />
	<kim:personContact />
	<kim:personPrivacy />
	<kim:personMembership />
		
	<kul:notes />
	<kul:routeLog />
	<kul:adHocRecipients />
	<kul:panelFooter />
	<kul:documentControls transactionalDocument="false" />

</kul:documentPage>
