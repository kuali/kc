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

<c:set var="protocolDocumentAttributes" value="${DataDictionary.IacucProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.IacucProtocol.attributes}" />

<%-- <c:set var="protocolRiskLevelsAttributes" value="${DataDictionary.ProtocolRiskLevel.attributes}" /> --%>
<%-- <c:set var="riskLevelAttributes" value="${DataDictionary.RiskLevel.attributes}" /> --%>

<c:set var="protocolReferenceAttributes" value="${DataDictionary.IacucProtocolReference.attributes}" />
<c:set var="protocolReferenceBeanAttributes" value="${DataDictionary.IacucProtocolReferenceBean.attributes}" />
<%-- <c:set var="protocolReferenceTypeAttributes" value="${DataDictionary.IacucProtocolReferenceType.attributes}" /> --%>
<c:set var="researchAreasAttributes" value="${DataDictionary.ResearchArea.attributes}" />

<c:set var="protocolLocationAttributes" value="${DataDictionary.IacucProtocolLocation.attributes}" />
<c:set var="organizationAttributes" value="${DataDictionary.Organization.attributes}" />
<c:set var="protocolOrganizationTypeAttributes" value="${DataDictionary.IacucProtocolOrganizationType.attributes}" />
<c:set var="rolodexAttributes" value="${DataDictionary.Rolodex.attributes}" />

<c:set var="protocolFundingSourceAttributes" value="${DataDictionary.IacucProtocolFundingSource.attributes}" />
<c:set var="fundingSourceTypeAttributes" value="${DataDictionary.FundingSourceType.attributes}" />
<%-- <c:set var="protocolParticipantAttributes" value="${DataDictionary.ProtocolParticipant.attributes}" /> --%>


<c:set var="className" value="org.kuali.kra.iacuc.document.IacucProtocolDocument" />
<c:set var="iacucProtocolProtocol" value="iacucProtocolProtocol" />
<c:set var="iacucProtocolLocation" value="iacucProtocolLocation" />
<%-- <c:set var="iacucProtocolParticipant" value="iacucProtocolParticipant" /> --%>

<c:set var="researchAreaLookupBoClassName" value="org.kuali.kra.iacuc.IacucResearchArea" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="iacucProtocolProtocol"
	documentTypeName="IacucProtocolDocument"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="protocol">
<script type="text/javascript">
   var $j = jQuery.noConflict();
</script>

<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}"/>
  	
<div align="right">
    <kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
    <kul:help documentTypeName="IacucProtocolDocument" pageName="Protocol" />
</div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />

<kra-protocol:protocolRequiredFields
    protocolDocumentAttributes="${protocolDocumentAttributes}" 
    protocolAttributes="${protocolAttributes}"
    action="${iacucProtocolProtocol}"
    className="${className}"
    displayLayStatementsRow = "true"
    showProjectType = "true"/>
    
    
<kra-protocol:protocolStatusDate 
    protocolAttributes="${protocolAttributes}"/>
    

<kra-protocol:protocolAdditionalInformation
     protocolDocumentAttributes="${protocolDocumentAttributes}"
     protocolAttributes="${protocolAttributes}"
     protocolReferenceAttributes="${protocolReferenceAttributes}"
     protocolReferenceBeanAttributes="${protocolReferenceBeanAttributes}"
     researchAreasAttributes="${researchAreasAttributes}"
     action="${iacucProtocolProtocol}"
     className="${className}"
     researchAreaLookupBoClassName="${researchAreaLookupBoClassName}"
     suppressFDAAppNumber = "true"/>

     
<kra-protocol:protocolLocations 
    protocolLocationAttributes="${protocolLocationAttributes}"
    organizationAttributes="${organizationAttributes}"
    protocolOrganizationTypeAttributes="${protocolOrganizationTypeAttributes}"
    rolodexAttributes="${rolodexAttributes}"
    action="${iacucProtocolLocation}"/>
    
    
<kra-protocol:protocolFundingSources 
    protocolFundingSourceAttributes="${protocolFundingSourceAttributes}"
    fundingSourceTypeAttributes="${fundingSourceTypeAttributes}"
    protocolModule = "iacuc" />
    
    
<%-- <kra-protocol:protocolParticipants  --%>
<%--     attributes="${protocolParticipantAttributes}" --%>
<%--     action="${iacucProtocolParticipant}"/> --%>
    

<kul:panelFooter />
    <c:if test="${readOnly && KualiForm.editingMode['canModify'] && KualiForm.displayEditButton}">
        <c:set var="extraButtonSource" value="${ConfigProperties.kra.externalizable.images.url}buttonsmall_edit_temp.gif"/>
        <c:set var="extraButtonProperty" value="methodToCall.edit"/>
        <c:set var="extraButtonAlt" value="Edit"/>
    </c:if>
	<kul:documentControls 
		transactionalDocument="false"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${KualiForm.editingMode['viewOnly']}"
		/>

<SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;

</SCRIPT>

<script language="javascript" src="dwr/interface/IacucProtocolFundingSourceService.js"></script>
</kul:documentPage>
