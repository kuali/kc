<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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
  	
<c:set var="readOnly" value="${not KualiForm.editingMode['fullEntry']}"/>
<c:set var="medusaLink" value="${KualiForm.methodToCall eq 'medusa'}"/>
<div align="right">
    <kra:shortUrl shortUrl="${KualiForm.shortUrl}"/>
    <kul:help documentTypeName="NegotiationDocument" pageName="Negotiation" />
</div>
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
    <c:if test="${readOnly}">
        <c:set var="extraButtonSource" value="${ConfigProperties.kra.externalizable.images.url}buttonsmall_edit_temp.gif"/>
        <c:set var="extraButtonProperty" value="methodToCall.edit"/>
        <c:set var="extraButtonAlt" value="Edit or Version"/>
    </c:if>
	<kul:documentControls 
		transactionalDocument="true"
		suppressRoutingControls="true"
		extraButtonSource="${extraButtonSource}"
		extraButtonProperty="${extraButtonProperty}"
		extraButtonAlt="${extraButtonAlt}"
		viewOnly="${!KualiForm.editingMode['create'] && !KualiForm.editingMode['modify'] && !KualiForm.editingMode['modify_activity']}"
		/>

</kul:documentPage>
