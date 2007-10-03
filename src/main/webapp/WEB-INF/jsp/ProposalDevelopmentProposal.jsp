<%--
 Copyright 2005-2006 The Kuali Foundation.

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
<%@ page import="org.kuali.kra.infrastructure.Constants"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="displayKeywordPanel" value="<%=request.getAttribute(Constants.KEYWORD_PANEL_DISPLAY)%>" />

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentProposal"
	documentTypeName="ProposalDevelopmentDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="save"
  	headerTabActive="proposal">

<kul:documentOverview editingMode="${KualiForm.editingMode}" />
<kra-pd:proposalDevelopmentRequiredFields />
<kra-pd:proposalDevelopmentOrganizationAndLocation />
<kra-pd:proposalDevelopmentSponsorProgramInformation />
<kra-pd:proposalDevelopmentDeliveryInfo />

<c:if test="${displayKeywordPanel == 'True'}">
<kra-pd:proposalDevelopmentKeywords />
</c:if>

<kul:panelFooter />
<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" />

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="dwr/interface/SponsorService.js"></script>

</kul:documentPage>