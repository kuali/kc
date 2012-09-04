<%--
 Copyright 2005-2010 The Kuali Foundation

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
 

  	
<div align="right"><kul:help documentTypeName="IacucProtocolDocument" pageName="Protocol" /></div>
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
<!-- <script language="javascript" src="dwr/interface/UnitService.js"></script> -->
</kul:documentPage>
