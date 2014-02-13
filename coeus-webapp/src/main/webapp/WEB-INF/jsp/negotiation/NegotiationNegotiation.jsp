<%--
 Copyright 2005-2013 The Kuali Foundation

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

<c:set var="negotiationAttributes" value="${DataDictionary.NegotiationDocument.attributes}" />

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="negotiationNegotiation"
	documentTypeName="NegotiationDocument"
	renderMultipart="true"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="negotiation">
  	
<script type="text/javascript"> 
   var $jq = jQuery.noConflict();
</script>
<script type="text/javascript" src="scripts/medusaView.js"></script>  	
  	
<c:set var="readOnly" value="${not KualiForm.editingMode['modify']}"/>
<c:set var="medusaLink" value="${KualiForm.methodToCall eq 'medusa'}"/>
<div align="right"><kul:help documentTypeName="NegotiationDocument" pageName="Negotiation" /></div>
<kul:documentOverview editingMode="${KualiForm.editingMode}" />

 <kra-negotiation:negotiation />

 <c:if test="${fn:length(KualiForm.customDataHelper.customAttributeGroups) > 0}">
 <kul:tab tabTitle="Custom Data" defaultOpen="false" tabErrorKey="customDataHelper.customDataList*" useRiceAuditMode="false">
 <kra-negotiation:NegotiationCustomDataTab readOnly="${readOnly}"/>
 </kul:tab>
  </c:if>
  
 <kra-negotiation:negotiationActivities />
  
<kul:tab tabTitle="Medusa" defaultOpen="${medusaLink}" tabErrorKey="">
<kra-m:medusa helpParameterNamespace="KC-NEGOTIATION" helpParameterDetailType="Document" helpParameterName="negotiationMedusaHelp" />
</kul:tab>


<kul:panelFooter />
	<kul:documentControls 
		transactionalDocument="true"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${!KualiForm.editingMode['create'] && !KualiForm.editingMode['modify'] && !KualiForm.editingMode['modify_activity']}"
		/>

</kul:documentPage>
