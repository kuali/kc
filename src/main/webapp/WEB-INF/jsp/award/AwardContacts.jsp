<%--
 Copyright 2005-2014 The Kuali Foundation

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
	htmlFormAction="awardContacts"
	documentTypeName="AwardDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="contacts"
  	extraTopButtons="${KualiForm.extraTopButtons}" >

<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}" scope="request" />

<%-- modeled after ProposalDevelopmentKeyPersonnel.jsp --%>
<div align="right"><kul:help documentTypeName="AwardDocument" pageName="Contacts" /></div>

<div id="workarea">
	<kra-a:awardProjectPersonnel />
	<kra-a:awardUnitContacts />
	<kra-a:awardSponsorContacts />
	<kra-a:awardCentralAdministrationContacts />
	<kul:panelFooter />
</div>



<SCRIPT type="text/javascript">
	var kualiForm = document.forms['KualiForm'];
	var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="dwr/interface/SponsorService.js"></script>

<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true" />

</kul:documentPage>
