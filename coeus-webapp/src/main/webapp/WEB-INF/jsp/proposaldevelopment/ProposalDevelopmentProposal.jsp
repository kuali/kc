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
<%@ page import="org.kuali.kra.infrastructure.Constants"%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="displayKeywordPanel" value="<%=session.getAttribute(Constants.KEYWORD_PANEL_DISPLAY)%>" />
<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />

<c:set var="readOnly" value="${not KualiForm.editingMode['modifyProposal']}" scope="request" /> 

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="proposalDevelopmentProposal"
	documentTypeName="ProposalDevelopmentDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="proposal">
  	
<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />

<div align="right"><kul:help documentTypeName="ProposalDevelopmentDocument" pageName="Proposal" /></div>
<c:choose><c:when test="${!KualiForm.hidePropDevDocDescriptionPanel}">
	<kul:documentOverview editingMode="${KualiForm.editingMode}" />
</c:when><c:otherwise>
    <div id="workarea">
    <c:set var="requiredTransparent" value="true"/>
</c:otherwise></c:choose>
<kra-pd:proposalDevelopmentRequiredFields transparent="${requiredTransparent}"/>
<kra-pd:proposalDevelopmentSponsorProgramInformation />
<kra-pd:proposalDevelopmentOrganizationAndLocation />
<c:if test="${KualiForm.proposalDevelopmentParameters['deliveryInfoDisplayIndicator'].value == 'Y'}">
   <kra-pd:proposalDevelopmentDeliveryInfo />
</c:if>

<c:if test="${displayKeywordPanel}">
<kra-pd:proposalDevelopmentKeywords />
</c:if>

<kul:panelFooter />
<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" suppressCancelButton="true" />

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;
var $j = jQuery.noConflict();
</SCRIPT>
<script language="javascript" src="scripts/kuali_application.js"></script>
<script language="javascript" src="dwr/interface/SponsorService.js"></script>

</kul:documentPage>
